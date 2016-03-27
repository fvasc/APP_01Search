package conexao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.Mapper;
import Converters.HibernateCollectionConverter;
import Modelos.*;

public class Conexao {

	private static final String NAMESPACE = "http://Principal/";
	private static final String ip = "192.168.1.101";
	private static final String URL = "http://" + ip
			+ ":8060/WebService/projeto?wsdl";
	private String nomeMetodo;
	private static Conexao reference;
	public static final String caminho = "http://" + ip + "/01Search/imagens/";
	public static XStream xStream;

	public static Conexao getReference() {
		if (reference == null)
			reference = new Conexao();

		return reference;
	}

	static {
		xStream = new XStream(new DomDriver());

		xStream.alias("Departamento", Departamento.class);
		xStream.alias("Estabelecimento", Estabelecimento.class);
		xStream.alias("Lista", Lista.class);
		xStream.alias("Login", Login.class);
		xStream.alias("Marca", Marca.class);
		xStream.alias("Codigobarras", Codigobarras.class);
		xStream.alias("Produto", Produto.class);
		xStream.alias("Codigobarras", Codigobarras.class);
		xStream.alias("Conversao", Conversao.class);
		xStream.alias("Usuario", Usuario.class);
		xStream.alias("Item", Item.class);
		xStream.alias("ItemLista", ItemLista.class);
		xStream.alias("Subdepartamento", Subdepartamento.class);
		xStream.alias("ArrayList", ArrayList.class);
		xStream.alias("List", List.class);
		xStream.alias("ListaUsuario", ListaUsuario.class);
		xStream.alias("PersistentBag",
				org.hibernate.collection.internal.PersistentBag.class);
		xStream.alias("HistoricoPreco", HistoricoPreco.class);
		xStream.alias("HistoricoPrecoPK", HistoricoPrecoPK.class);
		xStream.alias("Comentario", Comentario.class);

		// converter collections do hibernate para o xtream
		xStream.addDefaultImplementation(java.util.ArrayList.class,
				org.hibernate.collection.internal.PersistentList.class);
		xStream.addDefaultImplementation(java.util.ArrayList.class,
				org.hibernate.collection.internal.PersistentBag.class);

		Mapper mapper = xStream.getMapper();
		xStream.registerConverter(new HibernateCollectionConverter(mapper));
	}

	private String conexaoServidor(String nomeMetodo, PropertyInfo propInfo) {

		String result = "";

		SoapObject request = new SoapObject(NAMESPACE, nomeMetodo);
		if (propInfo != null) {
			request.addProperty(propInfo);
		}

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(NAMESPACE + nomeMetodo, envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			result = resultsRequestSOAP.toString();

		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}

		return result;
	}

	private String conexaoServidor(String nomeMetodo, Class<?> tipo,
			Object parametro) {

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = tipo;
		propInfo.setValue(parametro);

		SoapObject request = new SoapObject(NAMESPACE, nomeMetodo);
		request.addProperty(propInfo);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(NAMESPACE + nomeMetodo, envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			return resultsRequestSOAP.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public ArrayList<Comentario> buscarComentarioCodigobarras(
			Codigobarras codigobarras) {
		nomeMetodo = "buscarComentario_Codigobarras";
		
		ArrayList<Comentario> listaComentarios = new ArrayList<Comentario>();
		
		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, codigobarras.getIdCodigoBarras());
			
			System.out.println(resultado);
			
			listaComentarios = (ArrayList<Comentario>) xStream.fromXML(resultado);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return listaComentarios;
	}

	public void adicionarHistorico(ArrayList<ItemLista> arrItemLista,
			ArrayList<ArrayList<Codigobarras>> arrCodigobarras,
			Estabelecimento estabelecimento) {

		// converter pra xml
		String xsArrItemLista = xStream.toXML(arrItemLista);
		String xsArrCodigobarras = xStream.toXML(arrCodigobarras);

		// criar hashmap com o array em xml e o id do estabelecimento
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("ItemLista", xsArrItemLista);
		hash.put("Codigobarras", xsArrCodigobarras);
		hash.put("estabelecimento", estabelecimento.getIdEstabelecimento()
				.toString());

		// converter o hash para xml
		String xsHash = xStream.toXML(hash);

		nomeMetodo = "adicionarHistorico";

		ArrayList<ArrayList<Codigobarras>> array = new ArrayList<ArrayList<Codigobarras>>();

		try {

			conexaoServidor(nomeMetodo, PropertyInfo.STRING_CLASS, xsHash);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<Codigobarras>> buscarPrecoCorrente(
			ArrayList<ArrayList<Codigobarras>> arrCodigobarras,
			Estabelecimento estabelecimento) {

		// converter pra xml
		String xsArrCodigobarras = xStream.toXML(arrCodigobarras);

		// criar hashmap com o array em xml e o id do estabelecimento
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("array", xsArrCodigobarras);
		hash.put("estabelecimento", estabelecimento.getIdEstabelecimento()
				.toString());

		// converter o hash para xml
		String xsHash = xStream.toXML(hash);

		nomeMetodo = "buscarPrecoCorrente";

		ArrayList<ArrayList<Codigobarras>> array = new ArrayList<ArrayList<Codigobarras>>();

		try {

			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, xsHash);

			array = (ArrayList<ArrayList<Codigobarras>>) xStream
					.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
		
		
	}

	public ArrayList<ArrayList<Codigobarras>> buscarCodigobarras(
			ArrayList<ItemLista> array_itemLista,
			Estabelecimento estabelecimento) {

		nomeMetodo = "buscarCodigobarras_ItemLista_Estabelecimento";

		// converter pra xml
		String xsArrItemLista = xStream.toXML(array_itemLista);

		// criar hashmap com o array em xml e o id do estabelecimento
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("array", xsArrItemLista);
		hash.put("estabelecimento", estabelecimento.getIdEstabelecimento()
				.toString());

		// converter o hash para xml
		String xsHash = xStream.toXML(hash);

		ArrayList<ArrayList<Codigobarras>> array_Codigobarras = new ArrayList<ArrayList<Codigobarras>>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, xsHash);

			array_Codigobarras = (ArrayList<ArrayList<Codigobarras>>) xStream
					.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return array_Codigobarras;
	}

	public Codigobarras buscarCodigobarras_Codigobarras(String id) {

		nomeMetodo = "buscarCodigobarras_Codigobarras";
		Codigobarras codigobarras = null;

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, id);

			System.out.println(resultado);

			codigobarras = (Codigobarras) xStream.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return codigobarras;
	}

	public ArrayList<Codigobarras> buscarCodigobarras(String busca) {

		nomeMetodo = "buscarCodigobarras_Busca";
		ArrayList<Codigobarras> listaProdutos = new ArrayList<Codigobarras>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, busca);

			listaProdutos = (ArrayList<Codigobarras>) xStream
					.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProdutos;
	}

	public ArrayList<Codigobarras> buscarCodigobarras(
			Subdepartamento subdepartamento) {

		nomeMetodo = "buscarCodigobarras_Subdepartamento";
		ArrayList<Codigobarras> listaProdutos = new ArrayList<Codigobarras>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS,
					subdepartamento.getIdSubdepartamento());

			listaProdutos = (ArrayList<Codigobarras>) xStream
					.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProdutos;
	}

	public ArrayList<Codigobarras> buscarCodigobarras(Item item) {

		nomeMetodo = "buscarCodigobarras_Item";
		ArrayList<Codigobarras> listaProdutos = new ArrayList<Codigobarras>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS, item.getIdItem());

			listaProdutos = (ArrayList<Codigobarras>) xStream
					.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProdutos;
	}

	public ArrayList<Produto> buscarProduto(Codigobarras codigobarras) {

		nomeMetodo = "buscarProduto_Codigobarras";

		ArrayList<Produto> lista = null;

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, codigobarras.getIdCodigoBarras());

			lista = (ArrayList<Produto>) xStream.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<Departamento> buscarDepartamento() {

		nomeMetodo = "buscarDepartamento";

		ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();

		try {
			String resultado = conexaoServidor(nomeMetodo, null);

			listaDepartamentos = (ArrayList<Departamento>) xStream
					.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaDepartamentos;
	}

	public Departamento buscarDepartamento(Subdepartamento subdepartamento) {

		nomeMetodo = "buscarDepartamento_Subdepartamento";

		Departamento objeto = new Departamento();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS,
					subdepartamento.getIdSubdepartamento());

			System.out.println("buscarDepartamento_Subdepartamento "
					+ resultado);
			System.out.println();

			objeto = (Departamento) xStream.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objeto;
	}

	public ArrayList<Lista> buscarLista(Usuario usuario) {

		nomeMetodo = "buscarLista_Usuario";

		ArrayList<Lista> listaListas = new ArrayList<Lista>();

		try {

			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS, usuario.getIdUsuario());

			listaListas = (ArrayList<Lista>) xStream.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaListas;
	}

	public ArrayList<Item> buscarItem(Usuario usuario) {

		nomeMetodo = "buscarItem_Usuario";

		ArrayList<Item> itens = new ArrayList<Item>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS, usuario.getIdUsuario());

			itens = (ArrayList<Item>) xStream.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itens;
	}

	public Item buscarItem(Item item) {

		nomeMetodo = "buscarItem_Item";

		Item objeto = new Item();

		try {

			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS, item.getIdItem());

			objeto = (Item) xStream.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objeto;
	}

	public ItemLista buscarItemLista(ItemLista itemLista) {

		nomeMetodo = "buscarItemLista_ItemLista";

		ItemLista objeto = new ItemLista();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS,
					xStream.toXML(itemLista.getItemListaPK()));

			objeto = (ItemLista) xStream.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objeto;
	}

	public ArrayList<ItemLista> buscarItemLista(Usuario usuario) {

		nomeMetodo = "buscarItemLista_Usuario";

		ArrayList<ItemLista> objeto = new ArrayList<ItemLista>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS,
					xStream.toXML(usuario.getIdUsuario()));

			objeto = (ArrayList<ItemLista>) xStream.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objeto;
	}

	public ArrayList<ItemLista> buscarItemLista(Lista lista) {

		nomeMetodo = "buscarItemLista_Lista";

		ArrayList<ItemLista> array = new ArrayList<ItemLista>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS, lista.getIdLista());

			array = (ArrayList<ItemLista>) xStream.fromXML(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return array;
	}

	public Marca buscarMarca(Codigobarras codigobarras) {

		nomeMetodo = "buscarMarca_Codigobarras";

		Marca marca = new Marca();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, codigobarras.getIdCodigoBarras());

			marca = (Marca) xStream.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return marca;
	}

	public Marca buscarMarca(int idMarca) {

		nomeMetodo = "buscarMarca_Id";

		Marca marca = new Marca();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS, idMarca);

			marca = (Marca) xStream.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return marca;
	}

	/*
	 * public Subdepartamento buscarSubdepartamento(Subdepartamento
	 * subdepartamento) {
	 * 
	 * nomeMetodo = "buscarSubdepartamento_Subdepartamento";
	 * 
	 * Subdepartamento objeto = new Subdepartamento();
	 * 
	 * try { String resultado = conexaoServidor(nomeMetodo,
	 * PropertyInfo.INTEGER_CLASS, subdepartamento.getIdSubdepartamento());
	 * 
	 * System.out.println("buscarSubdepartamento_Subdepartamento "+resultado);
	 * System.out.println();
	 * 
	 * objeto = (Subdepartamento) xStream.fromXML(resultado);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return objeto; }
	 */

	public Subdepartamento buscarSubdepartamento(Codigobarras codigobarras) {

		nomeMetodo = "buscarSubdepartamento_Codigobarras";

		Subdepartamento objeto = new Subdepartamento();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, codigobarras.getIdCodigoBarras());

			objeto = (Subdepartamento) xStream.fromXML(resultado);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return objeto;
	}

	public Subdepartamento buscarSubdepartamento(int idSubdepartamento) {

		nomeMetodo = "buscarSubdepartamento_Id";

		Subdepartamento objeto = new Subdepartamento();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS, idSubdepartamento);

			objeto = (Subdepartamento) xStream.fromXML(resultado);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return objeto;
	}

	public ArrayList<Subdepartamento> buscarSubdepartamento(
			Departamento departamento) {

		nomeMetodo = "buscarSubdepartamento_Departamento";

		ArrayList<Subdepartamento> objeto = new ArrayList<Subdepartamento>();

		try {
			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.INTEGER_CLASS,
					departamento.getIdDepartamento());

			System.out
					.println("buscarSubdepartamento_Departamento ++++++++++++"
							+ resultado);
			System.out.println();

			objeto = (ArrayList<Subdepartamento>) xStream.fromXML(resultado);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return objeto;
	}

	public ArrayList<Estabelecimento> buscarEstabelecimento() {

		nomeMetodo = "buscarEstabelecimento";

		ArrayList<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();

		try {
			String resultado = conexaoServidor(nomeMetodo, null);

			listaEstabelecimentos = (ArrayList<Estabelecimento>) xStream
					.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return listaEstabelecimentos;
	}

	public ArrayList<Estabelecimento> buscarEstabelecimento(
			ArrayList<Codigobarras> arrCodigobarrasQuantidade) {

		nomeMetodo = "buscarEstabelecimento_HashMap";

		ArrayList<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();

		try {
			String param = xStream.toXML(arrCodigobarrasQuantidade);

			String resultado = conexaoServidor(nomeMetodo,
					PropertyInfo.STRING_CLASS, param);

			listaEstabelecimentos = (ArrayList<Estabelecimento>) xStream
					.fromXML(resultado);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return listaEstabelecimentos;
	}

	public ArrayList<HistoricoPreco> buscarHistoricoPreco(
			HistoricoPrecoPK historicoPrecoPK) {

		nomeMetodo = "buscarHistoricoPreco_HistoricoPrecoPK";

		ArrayList<HistoricoPreco> array = new ArrayList<HistoricoPreco>();

		String param = xStream.toXML(historicoPrecoPK);

		String resultado = conexaoServidor(nomeMetodo,
				PropertyInfo.STRING_CLASS, param);

		array = (ArrayList<HistoricoPreco>) xStream.fromXML(resultado);

		return array;
	}

	public ArrayList<Produto> buscarDestaque(Estabelecimento estabelecimento) {

		nomeMetodo = "buscarDestaque";

		ArrayList<Produto> array = new ArrayList<Produto>();

		String resultado = conexaoServidor(nomeMetodo,
				PropertyInfo.INTEGER_CLASS,
				estabelecimento.getIdEstabelecimento());

		array = (ArrayList<Produto>) xStream.fromXML(resultado);

		return array;
	}

	public void atualizarItemLista(ItemLista itemLista) {

		nomeMetodo = "atualizarItemLista";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(itemLista);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);
	}

	public void atualizarItem(Item item) {

		nomeMetodo = "atualizarItem";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(item);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);

	}

	public void atualizarLista(Lista lista) {

		nomeMetodo = "atualizarLista";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(lista);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);

	}

	public Lista adicionarLista(Lista lista) {

		nomeMetodo = "adicionarLista";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(lista);

		propInfo.setValue(xs);

		String resultado = conexaoServidor(nomeMetodo, propInfo);

		Lista l = (Lista) xStream.fromXML(resultado);

		return l;
	}

	public void adicionarUsuarioItem(UsuarioItem usuarioItem) {

		nomeMetodo = "adicionarUsuarioItem";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(usuarioItem);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);

	}

	public Item adicionarItem(Item item) {

		nomeMetodo = "adicionarItem";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(item);

		propInfo.setValue(xs);

		String resultado = conexaoServidor(nomeMetodo, propInfo);

		Item i = (Item) xStream.fromXML(resultado);

		return i;
	}

	public void adicionarItemLista(ItemLista item) {

		nomeMetodo = "adicionarItemLista";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(item);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);
	}

	public void removerListaUsuario(ListaUsuario listaUsuario) {

		nomeMetodo = "removerListaUsuario";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(listaUsuario);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);
	}

	public void removerUsuarioItem(UsuarioItem usuarioItem) {

		nomeMetodo = "removerUsuarioItem";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(usuarioItem);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);
	}

	public void removerCodigobarrasItem(CodigobarrasItem codigobarrasItem) {

		nomeMetodo = "removerCodigobarrasItem";

		CodigobarrasItemPK codigobarrasItemPK = new CodigobarrasItemPK();
		codigobarrasItemPK.setIdCodigobarras(codigobarrasItem
				.getCodigobarrasItemPK().getIdCodigobarras());
		codigobarrasItemPK.setIdItem(codigobarrasItem.getCodigobarrasItemPK()
				.getIdItem());

		CodigobarrasItem objeto = new CodigobarrasItem();
		objeto.setCodigobarrasItemPK(codigobarrasItemPK);

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(objeto);

		propInfo.setValue(xs);

		conexaoServidor(nomeMetodo, propInfo);
	}

	public void removerItemLista(ItemLista itemLista) {

		nomeMetodo = "removerItemLista";

		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "arg0";
		propInfo.type = PropertyInfo.STRING_CLASS;
		String xs = xStream.toXML(itemLista);

		propInfo.setValue(xs);

		System.out.println("SAIDA DE ITEMLISTA PARA ATUALIZAÇCAO2 " + xs);

		conexaoServidor(nomeMetodo, propInfo);
	}
}