package controle;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import bancoDadosLocal.OperacaoGenerica;
import bancoDadosLocal.modelos.CodigobarrasSQLite;
import bancoDadosLocal.modelos.MarcaSQLite;
import bancoDadosLocal.modelos.SubdepartamentoSQLite;
import conexao.Conexao;
import Modelos.Codigobarras;
import Modelos.Marca;
import Modelos.Subdepartamento;
import android.content.Context;
import android.graphics.drawable.Drawable;

public class Cache {
	private static Cache instance;
	private HashMap<String, Drawable> listImagens;
	private HashMap<String, Codigobarras> listCodigoBarras;
	private HashMap<Integer, Marca> listMarcas;
	private HashMap<Integer, Subdepartamento> listSubdepartamentos;
	private boolean temporario = true;
	private boolean banco = true;
	private long dataAtuTemp = System.currentTimeMillis();
	private long prazoTemp = 9999;
	private long dataAtuBanc = System.currentTimeMillis();
	private long prazoBanc = prazoTemp;

	public Cache() {
		instance = this;
		
		listImagens = new HashMap<String, Drawable>();
		listCodigoBarras = new HashMap<String, Codigobarras>();
		listMarcas = new HashMap<Integer, Marca>();
		listSubdepartamentos = new HashMap<Integer, Subdepartamento>();
	}

	public static Cache get() {
		if (instance == null)
			new Cache();
		return instance;
	}
	
	private Boolean isTemporario(){
		if(((System.currentTimeMillis() - prazoTemp) >= dataAtuTemp) || !temporario)
			return false;
		
		return true;
	}
	
	private Boolean isBanco(){
		if(((System.currentTimeMillis() - prazoBanc) >= dataAtuBanc) || !banco)
			return false;
		
		return true;
	}

	public Drawable getImagem(String nome) {
		Drawable img = listImagens.get(nome);

		if (img == null)
			return downloadImagem(nome);
		else
			return img;
	}

	private Drawable downloadImagem(String caminho) {
		try {
			URL url = new URL(Conexao.caminho + caminho);
			InputStream is = (InputStream) url.getContent();
			Drawable img = Drawable.createFromStream(is, "src");

			listImagens.put(caminho, img);

			return img;
		} catch (Exception e) {
			return null;
		}
	}

	private <T, K> void insereCache(HashMap<K, T> list, K id, T objeto, OperacaoGenerica<T> sQLite){
		if (isTemporario())
			list.put(id, objeto);
		
		if (isBanco())
			sQLite.insert(objeto);
	}
	
	private <K, T> T buscaCache(HashMap<K, T> list, K id, OperacaoGenerica<T> sQLite){
		T objeto = null;
		
		if (isTemporario())
			objeto = list.get(id);
		if (objeto == null) {
			if (isBanco())
				objeto = sQLite.select(id.toString());			
		}
		
		return objeto;
	}
	
	public Codigobarras getCodigoBarras(Context context, String id) {
		Codigobarras codigobarras = (Codigobarras) buscaCache(listCodigoBarras, id, CodigobarrasSQLite.get(context));
		
		if (codigobarras == null)
			codigobarras = Conexao.getReference().buscarCodigobarras_Codigobarras(id);
		if (codigobarras != null)
			insereCache(listCodigoBarras, id, codigobarras, CodigobarrasSQLite.get(context));		

		return codigobarras;
	}
	
	public Marca getMarca(Context context, int id) {
		
		Marca marca = (Marca) buscaCache(listMarcas, id, MarcaSQLite.get(context));
		
		if (marca == null)
			marca = Conexao.getReference().buscarMarca(id);
		if (marca != null)
			insereCache(listMarcas, id, marca, MarcaSQLite.get(context));		

		return marca;
	}
	
	public Subdepartamento getSubdepartamento(Context context, int id) {
		
		Subdepartamento subdepartamento = (Subdepartamento) buscaCache(listSubdepartamentos, id, SubdepartamentoSQLite.get(context));
		
		if (subdepartamento == null)
			subdepartamento = Conexao.getReference().buscarSubdepartamento(id);
		if (subdepartamento != null)
			insereCache(listSubdepartamentos, id, subdepartamento, SubdepartamentoSQLite.get(context));		

		return subdepartamento;
	}
}
