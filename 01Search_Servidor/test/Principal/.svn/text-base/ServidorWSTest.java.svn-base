package Principal;

import java.util.ArrayList;
import java.util.List;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.IgnoreTextAndAttributeValuesDifferenceListener;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;

import Converters.HibernateCollectionConverter;
import Modelos.Codigobarras;
import Modelos.Conversao;
import Modelos.Departamento;
import Modelos.Estabelecimento;
import Modelos.Item;
import Modelos.ItemLista;
import Modelos.Lista;
import Modelos.ListaUsuario;
import Modelos.Login;
import Modelos.Marca;
import Modelos.Produto;
import Modelos.Subdepartamento;
import Modelos.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.Mapper;

public class ServidorWSTest extends XMLTestCase{

	private static XStream xStream;
	private static ServidorWS servidorWS;
	private String resultado;
	
	@Before
	public void setUp() throws Exception {
		
		servidorWS = new ServidorWS();
		
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
		
		//converter collections do hibernate para o xtream
		xStream.addDefaultImplementation(java.util.ArrayList.class, org.hibernate.collection.internal.PersistentList.class);
		xStream.addDefaultImplementation(java.util.ArrayList.class, org.hibernate.collection.internal.PersistentBag.class);

		Mapper mapper = xStream.getMapper();
		xStream.registerConverter(new HibernateCollectionConverter(mapper));
		
		XMLUnit.setIgnoreWhitespace(Boolean.TRUE);
	}

	@Test
	public void testBuscarCodigobarras_Busca() throws Exception {
		String busca = "a";
		resultado = servidorWS.buscarCodigobarras_Busca(busca);
		System.out.println(resultado);
		
	}

	@Test
	public void testBuscarCodigobarras_Subdepartamento() throws Exception {
		
		Subdepartamento subdepartamento = new Subdepartamento();
		subdepartamento.setIdSubdepartamento(1);
		
		resultado = servidorWS.buscarCodigobarras_Subdepartamento(subdepartamento.getIdSubdepartamento());
		
		String myControlXML = "<PersistentBag><Codigobarras><idCodigoBarras>1</idCodigoBarras><nome>A</nome><imagem>A</imagem><dataCadastro>2012</dataCadastro><descricao>A</descricao></Codigobarras></PersistentBag>";
        String myTestXML = resultado;
		
        DifferenceListener myDifferenceListener = new IgnoreTextAndAttributeValuesDifferenceListener();
        Diff myDiff = new Diff(myControlXML, myTestXML);
        myDiff.overrideDifferenceListener(myDifferenceListener);
        assertTrue("test XML matches control skeleton XML " + myDiff, myDiff.similar());
	}
		

	@Test
	public void testBuscarCodigobarras_Item() throws Exception {
		Item item = new Item();
		item.setIdItem(11);
		
		resultado = servidorWS.buscarCodigobarras_Item(item.getIdItem());
		System.out.println(resultado);
		String myControlXML = "<PersistentBag><Codigobarras><idCodigoBarras>78910000233</idCodigoBarras><nome>Negresco</nome><imagem>78910000233808.jpg</imagem><dataCadastro>2012-04-26 22:00:49.0</dataCadastro><descricao>Biscoito Sabor Chocolate com Recheio Baunilha.</descricao></Codigobarras><Codigobarras><idCodigoBarras>78910000862</idCodigoBarras><nome>Negresco Eclipse</nome><imagem>7891000086216.jpg</imagem><dataCadastro>2012-04-26 22:00:49.0</dataCadastro><descricao>O Verdadeiro Biscoito do Programador!</descricao></Codigobarras></PersistentBag>";
        String myTestXML = resultado;
		
        /*
        DifferenceListener myDifferenceListener = new IgnoreTextAndAttributeValuesDifferenceListener();
        Diff myDiff = new Diff(myControlXML, myTestXML);
        myDiff.overrideDifferenceListener(myDifferenceListener);
        assertTrue("test XML matches control skeleton XML " + myDiff, myDiff.similar());*/
	}

	@Test
	public void testBuscarProduto_Codigobarras() {
		resultado = servidorWS.buscarProduto_Codigobarras("75010092240");
		System.out.println(resultado);
	}

	@Test
	public void testBuscarDepartamento() throws Exception{
		
		String resultado = servidorWS.buscarDepartamento();
		
		
		String myControlXML = "<PersistentBag><Departamento><idDepartamento>1</idDepartamento><nome>Limpeza</nome><Subdepartamento><idSubdepartamento>1</idSubdepartamento><nome>Esponja de Aço</nome></Subdepartamento></Departamento><Departamento><idDepartamento>2</idDepartamento><nome>Higiene</nome><Subdepartamento><idSubdepartamento>2</idSubdepartamento><nome>Aparelho de Barbear</nome></Subdepartamento><Subdepartamento><idSubdepartamento>3</idSubdepartamento><nome>Shampoo</nome>    </Subdepartamento></Departamento><Departamento><idDepartamento>3</idDepartamento><nome>Mercearia</nome><Subdepartamento><idSubdepartamento>6</idSubdepartamento><nome>Macarrão Instantâneo</nome></Subdepartamento><Subdepartamento><idSubdepartamento>7</idSubdepartamento><nome>Aveia</nome></Subdepartamento><Subdepartamento><idSubdepartamento>8</idSubdepartamento><nome>Biscoito</nome></Subdepartamento><Subdepartamento><idSubdepartamento>9</idSubdepartamento><nome>Cereal</nome></Subdepartamento><Subdepartamento><idSubdepartamento>10</idSubdepartamento><nome>Barra de Cereal</nome></Subdepartamento></Departamento><Departamento><idDepartamento>4</idDepartamento><nome>Bebidas</nome><Subdepartamento><idSubdepartamento>11</idSubdepartamento><nome>Água Mineral</nome></Subdepartamento><Subdepartamento><idSubdepartamento>12</idSubdepartamento><nome>Refresco</nome></Subdepartamento><Subdepartamento><idSubdepartamento>13</idSubdepartamento><nome>Água de Coco</nome></Subdepartamento></Departamento></PersistentBag>";
        String myTestXML = resultado;
        System.out.println(resultado);
		
        DifferenceListener myDifferenceListener = new IgnoreTextAndAttributeValuesDifferenceListener();
        Diff myDiff = new Diff(myControlXML, myTestXML);
        myDiff.overrideDifferenceListener(myDifferenceListener);
        assertTrue("test XML matches control skeleton XML " + myDiff, myDiff.similar());
        
	}

	@Test
	public void testBuscarLista_Usuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarItem_Usuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarItem_Item() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarItemLista_ItemLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarItemLista_Lista() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarMarca_Codigobarras() {
		fail("Not yet implemented");
	}

	/*@Test
	public void testBuscarSubdepartamento_Subdepartamento() {
		fail("Not yet implemented");
	}*/

	/*@Test
	public void testBuscarSubdepartamento_Departamento() throws Exception {

		String resultado = servidorWS.buscarSubdepartamento_Departamento(1);
		
		String myControlXML = "<PersistentBag><Subdepartamento><idSubdepartamento>1</idSubdepartamento><nome>a</nome></Subdepartamento></PersistentBag>";
        String myTestXML = resultado;
		
        DifferenceListener myDifferenceListener = new IgnoreTextAndAttributeValuesDifferenceListener();
        Diff myDiff = new Diff(myControlXML, myTestXML);
        myDiff.overrideDifferenceListener(myDifferenceListener);
        assertTrue("test XML matches control skeleton XML " + myDiff, myDiff.similar());
	}*/

	@Test
	public void testBuscarSubdepartamento_Codigobarras() throws Exception {

		String resultado = servidorWS.buscarSubdepartamento_Codigobarras("75010092240");
		
		String myControlXML = "<PersistentBag><Subdepartamento><idSubdepartamento>1</idSubdepartamento><nome>a</nome></Subdepartamento></PersistentBag>";
        String myTestXML = resultado;
        System.out.println(resultado);
		
        DifferenceListener myDifferenceListener = new IgnoreTextAndAttributeValuesDifferenceListener();
        Diff myDiff = new Diff(myControlXML, myTestXML);
        myDiff.overrideDifferenceListener(myDifferenceListener);
        assertTrue("test XML matches control skeleton XML " + myDiff, myDiff.similar());
	}

	@Test
	public void testBuscarEstabelecimento() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarDestaque() {
		String resultado = servidorWS.buscarDestaque(1);
		System.out.println(resultado);
	}

	@Test
	public void testAdicionarLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionarItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionarItemLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarItemLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoverItemLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoverListaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoverUsuarioItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoverProdutosLista() {
		fail("Not yet implemented");
	}

}
