package ControleJPA.modelos;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Query;

import ControleJPA.OperacaoGenerica;
import Modelos.Codigobarras;
import Modelos.Historico;
import Modelos.HistoricoItemLista;
import Modelos.HistoricoItemListaPK;
import Modelos.HistoricoPK;
import Modelos.ItemLista;
import Modelos.UnidadeMedida;

public class Historico_JPA extends OperacaoGenerica<Historico, HistoricoPK> {

	private static final Historico_JPA historico_JPA = new Historico_JPA();

	public static Historico_JPA get() {
		return historico_JPA;
	}

	public void adicionarHistorico(ArrayList<ItemLista> arrItemLista,
			ArrayList<ArrayList<Codigobarras>> arrCodigobarras,
			Integer idEstabelecimento) {

		abrir();

		// native query, pois jpa nao aceite subquerys
		String q = "SELECT MAX(t.val) FROM (SELECT MAX(h.versaoCompra) AS Val"
				+ " FROM historico h UNION ALL SELECT MAX(hil.versaoCompra) AS Val "
				+ "FROM historico_item_lista hil)t";

		Query query = manager.createNativeQuery(q);

		Integer versao_compra = ((Integer) query.getSingleResult() + 1);
		


		Date data = new Date();

		for (int a = 0; a < arrItemLista.size(); a++) {

			// gravar item Lista
			HistoricoItemListaPK pkIl = new HistoricoItemListaPK();
			Integer idItem = arrItemLista.get(a).getItemListaPK().getIdItem();
			Integer idLista = arrItemLista.get(a).getItemListaPK().getIdLista();
			pkIl.setIdItem(idItem);
			pkIl.setIdLista(idLista);
			pkIl.setCompraData(data);
			pkIl.setIdEstabelecimento(idEstabelecimento);
			pkIl.setVersaoCompra(versao_compra);

			HistoricoItemLista historicoil = new HistoricoItemLista();
			historicoil.setHistoricoItemListaPK(pkIl);
			historicoil.setQuantidade(arrItemLista.get(a).getQtdComprada());

			for (int b = 0; b < arrCodigobarras.get(a).size(); b++) {

				// se a quantidade for 0 nao gravar
				if (arrCodigobarras.get(a).get(b).getQtdComprada() > 0) {

					HistoricoPK pk = new HistoricoPK();
					pk.setCompraData(data);
					pk.setIdCodigoBarras(arrCodigobarras.get(a).get(b)
							.getIdCodigoBarras());
					pk.setIdEstabelecimento(idEstabelecimento);
					pk.setIdItem(idItem);
					pk.setIdLista(idLista);
					pk.setVersaoCompra(versao_compra);

					Historico historico = new Historico();
					historico.setHistoricoPK(pk);
					historico.setIdUnidade(new UnidadeMedida(1));
					historico.setPreco(arrCodigobarras.get(a).get(b)
							.getPrecoCorrente());
					historico.setQuantidade(arrCodigobarras.get(a).get(b)
							.getQtdComprada());

					create(historico);
				}
			}

			// se a quantidade for 0 nao gravar
			if (arrItemLista.get(a).getQtdComprada() > 0) {
				HistoricoItemLista_JPA.get().create(historicoil);

			}
		}

	}

}
