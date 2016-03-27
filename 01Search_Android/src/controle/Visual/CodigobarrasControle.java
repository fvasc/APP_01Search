package controle.Visual;

import java.util.ArrayList;
import Modelos.Codigobarras;
import Modelos.Departamento;
import Modelos.Marca;
import Modelos.Subdepartamento;
import Modelos.Estabelecimento;
import Modelos.Produto;
import android.graphics.drawable.Drawable;
import conexao.Conexao;
import controle.Cache;

public class CodigobarrasControle {

	private static CodigobarrasControle instance;
	
	private Codigobarras corrente;
	private Produto produto;
	
	private int estabelecimentoCorrente;

	private ArrayList<Estabelecimento> estabelecimentos;
	private ArrayList<Produto> produtos;
	private Drawable imagem;
	
	public static CodigobarrasControle get(){
		if(instance == null)
			instance = new CodigobarrasControle();
		
		return instance;
	}
	
	private void atualizaCampos(){
		estabelecimentos = Conexao.getReference().buscarEstabelecimento();
		estabelecimentoCorrente = 0;
		produtos = Conexao.getReference().buscarProduto(corrente);
		setImagem(Cache.get().getImagem(corrente.getImagem()));
	}

	public Codigobarras getCorrente() {
		return corrente;
	}

	public void setCorrente(Codigobarras corrente) {
		this.corrente = corrente;
		atualizaCampos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getEstabelecimentoCorrente() {
		return estabelecimentoCorrente;
	}

	public void setEstabelecimentoCorrente(int estabelecimentoCorrente) {
		this.estabelecimentoCorrente = estabelecimentoCorrente;
	}

	public ArrayList<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}

	public void setEstabelecimentos(ArrayList<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public Drawable getImagem() {
		return imagem;
	}

	public void setImagem(Drawable imagem) {
		this.imagem = imagem;
	}	
}
