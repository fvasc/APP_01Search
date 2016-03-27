package ControleJPA.modelos;

import ControleJPA.OperacaoGenerica;
import Modelos.HistoricoItemLista;
import Modelos.HistoricoItemListaPK;

public class HistoricoItemLista_JPA extends OperacaoGenerica<HistoricoItemLista, HistoricoItemListaPK> {

	private static final HistoricoItemLista_JPA historicoItemLista_JPA = new HistoricoItemLista_JPA();

	public static HistoricoItemLista_JPA get() {
		return historicoItemLista_JPA;
	}

}
