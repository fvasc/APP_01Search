package ControleJPA.modelos;

import ControleJPA.OperacaoGenerica;
import Modelos.CodigobarrasItem;
import Modelos.CodigobarrasItemPK;


public class CodigobarrasItem_JPA extends OperacaoGenerica<CodigobarrasItem, CodigobarrasItemPK>{

	private static final CodigobarrasItem_JPA codigobarrasItem_JPA = new CodigobarrasItem_JPA();

	public static CodigobarrasItem_JPA get() {
		return codigobarrasItem_JPA;
	}

}
