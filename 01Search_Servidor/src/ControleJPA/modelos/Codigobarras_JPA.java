package ControleJPA.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import ControleJPA.OperacaoGenerica;
import Modelos.Codigobarras;
import Modelos.CodigobarrasItem;
import Modelos.Estabelecimento;
import Modelos.Historico;
import Modelos.ItemLista;
import Modelos.Marca;
import Modelos.Produto;
import Modelos.ProdutoPK;
import Modelos.Subdepartamento;

public class Codigobarras_JPA extends OperacaoGenerica<Codigobarras, String> {

	private static final Codigobarras_JPA codigobarras_JPA = new Codigobarras_JPA();

	public static Codigobarras_JPA get() {
		return codigobarras_JPA;
	}

	public ArrayList<Codigobarras> buscarCodigobarras(
			Subdepartamento subdepartamento) {

		abrir();

		Query query = manager
				.createNamedQuery("Codigobarras.findByIdSubdepartamento");

		query.setParameter("idSubdepartamento",
				subdepartamento.getIdSubdepartamento());

		ArrayList<Codigobarras> lista = (ArrayList<Codigobarras>) query
				.getResultList();

		fechar();

		return lista;
	}

	public Codigobarras buscarCodigobarras(Codigobarras codigobarras) {

		abrir();

		Query query = manager
				.createNamedQuery("Codigobarras.findByIdCodigoBarras");

		query.setParameter("idCodigoBarras", codigobarras.getIdCodigoBarras());

		Codigobarras obj = (Codigobarras) query.getSingleResult();

		obj.getIdMarca();
		obj.getIdSubdepartamento();

		fechar();

		return obj;
	}

	public ArrayList<Codigobarras> buscarCodigobarras(String busca) {

		abrir();

		ArrayList<Codigobarras> lista = new ArrayList<Codigobarras>();

		Codigobarras codigo = manager.find(Codigobarras.class, busca);

		if (codigo != null) {

			lista.add(codigo);

			fechar();

			return lista;

		}

		Query query = manager.createNamedQuery("Codigobarras.findByNomeLike");
		query.setParameter("nome", '%' + busca + '%');

		if (!query.getResultList().isEmpty()) {

			lista = (ArrayList<Codigobarras>) query.getResultList();

			fechar();

			return lista;

		}

		query = manager.createNamedQuery("Codigobarras.findByDescricaoLike");
		query.setParameter("descricao", busca);

		if (!query.getResultList().isEmpty()) {

			lista = (ArrayList<Codigobarras>) query.getResultList();

			fechar();

			return lista;

		}

		return lista;
	}

	public Marca buscarMarca(Codigobarras objeto) {

		abrir();

		Query query = manager
				.createNamedQuery("Codigobarras.findMarcaByIdCodigobarras");

		query.setParameter("idCodigoBarras", objeto.getIdCodigoBarras());

		Marca marca = (Marca) query.getSingleResult();

		fechar();

		return marca;
	}

	public ArrayList<ArrayList<Codigobarras>> buscarCodigobarras(
			ArrayList<ItemLista> array_itemLista, Integer idEstabelecimento) {

		ArrayList<ArrayList<Codigobarras>> array_Codigobarras = new ArrayList<ArrayList<Codigobarras>>();

		abrir();

		for (ItemLista il : array_itemLista) {

			// buscar codigosbarras do item corrente
			Query query = manager
					.createNamedQuery("Item.findCodigobarrasByIdItem");
			query.setParameter("idItem", il.getItemListaPK().getIdItem());

			ArrayList<Codigobarras> codigos_barras = (ArrayList<Codigobarras>) query
					.getResultList();

			for (Codigobarras c : codigos_barras) {

				/*// buscar quantidade comprada
				query = manager.createNamedQuery("Historico.findQtdComprada");
				query.setParameter("itemId", il.getItemListaPK().getIdItem());
				query.setParameter("listaId", il.getItemListaPK().getIdLista());
				query.setParameter("codigoId", c.getIdCodigoBarras());

				Integer qtdComprada;
				try {
					qtdComprada = (Integer) query.getSingleResult();
				} catch (NoResultException e) {
					qtdComprada = 0;
				}

				// setar quantidade comprada
				c.setQtdComprada(qtdComprada);*/

				// buscar precoCorrente
				BigDecimal precoCorrente = precoCorrente(c.getIdCodigoBarras(),
						idEstabelecimento);

				// setar precoCorrente
				c.setPrecoCorrente(precoCorrente);

			}

			// adicionar no array
			array_Codigobarras.add(codigos_barras);
		}

		fechar();

		return array_Codigobarras;
	}

	private BigDecimal precoCorrente(String idCodigoBarras,
			Integer idEstabelecimento) {

		// buscar precoCorrente
		ProdutoPK produtoPK = new ProdutoPK();
		produtoPK.setIdCodigoBarras(idCodigoBarras);
		produtoPK.setIdEstabelecimento(idEstabelecimento);

		// buscar no banco
		Query query = manager.createNamedQuery("Produto.findByIdProdutoPK");
		query.setParameter("produtoPK", produtoPK);

		BigDecimal precoCorrente;
		Produto produto;
		try {
			produto = (Produto) query.getSingleResult();
			precoCorrente = produto.getPreco();

		} catch (NoResultException e) {
			precoCorrente = new BigDecimal("0");
		}

		return precoCorrente;
	}

	public ArrayList<Estabelecimento> buscarEstabelecimento(
			ArrayList<Codigobarras> arr) {

		abrir();

		ArrayList<Estabelecimento> arrEstabelecimento = new ArrayList<Estabelecimento>();

		arrEstabelecimento = (ArrayList<Estabelecimento>) Estabelecimentos_JPA
				.get().selectAll("Estabelecimento");

		for (Estabelecimento e : arrEstabelecimento) {

			BigDecimal valorCompra = new BigDecimal("0");

			for (Codigobarras c : arr) {

				BigDecimal preco = precoCorrente(c.getIdCodigoBarras(),
						e.getIdEstabelecimento());

				BigDecimal quantidade = new BigDecimal(c.getQtdComprada());

				BigDecimal multiplicacao = preco.multiply(quantidade);

				valorCompra = valorCompra.add(multiplicacao);
			}

			e.setPrecoListaCorrente(valorCompra);

		}

		fechar();

		return arrEstabelecimento;
	}

	public ArrayList<ArrayList<Codigobarras>> buscarPrecoCorrente(
			ArrayList<ArrayList<Codigobarras>> arrCodigobarras,
			int estabelecimentoId) {

		abrir();

		// for para pegar o valor do produto de cada codigobarras
		for (int a = 0; a < arrCodigobarras.size(); a++) {

			for (int b = 0; b < arrCodigobarras.get(a).size(); b++) {

				// buscar precoCorrente
				BigDecimal precoCorrente = precoCorrente(arrCodigobarras.get(a)
						.get(b).getIdCodigoBarras(), estabelecimentoId);

				// setar precoCorrente
				arrCodigobarras.get(a).get(b).setPrecoCorrente(precoCorrente);

			}
		}

		fechar();

		return arrCodigobarras;
	}

}
