package ControleJPA.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.persistence.Query;
import ControleJPA.OperacaoGenerica;
import Modelos.Codigobarras;
import Modelos.Estabelecimento;
import Modelos.Produto;
import Modelos.ProdutoPK;

public class Produto_JPA extends OperacaoGenerica<Produto, ProdutoPK> {

	private static final Produto_JPA produto_JPA = new Produto_JPA();

	public static Produto_JPA get() {
		return produto_JPA;
	}

	public ArrayList<Produto> buscarDestaque(Estabelecimento estabelecimento) {

		abrir();

		Query query = manager.createNamedQuery("Produto.findByDestaque");

		query.setParameter("idEstabelecimento",
				estabelecimento.getIdEstabelecimento());

		ArrayList<Produto> lista = (ArrayList<Produto>) query.getResultList();

		for (Produto produto : lista) {
			produto.getCodigobarras();
		}

		fechar();

		return lista;
	}

	public ArrayList<Produto> buscarProduto(Codigobarras codigobarras) {

		abrir();

		Query query = manager.createNamedQuery("Produto.findByIdCodigoBarras");
		query.setParameter("idCodigoBarras", codigobarras.getIdCodigoBarras());

		ArrayList<Produto> lista = (ArrayList<Produto>) query.getResultList();

		fechar();

		return lista;
	}

	
}