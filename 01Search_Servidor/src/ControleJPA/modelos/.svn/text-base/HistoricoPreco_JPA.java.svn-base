package ControleJPA.modelos;

import java.util.ArrayList;

import javax.persistence.Query;
import ControleJPA.OperacaoGenerica;
import Modelos.HistoricoPreco;
import Modelos.HistoricoPrecoPK;

public class HistoricoPreco_JPA extends OperacaoGenerica<HistoricoPreco, HistoricoPrecoPK>{

	private static final HistoricoPreco_JPA historicoPreco_JPA = new HistoricoPreco_JPA();

    public static HistoricoPreco_JPA get() {
        return historicoPreco_JPA;
    }
    
	public ArrayList<HistoricoPreco> buscarCodigobarras(HistoricoPrecoPK historicoPrecoPK) {
		
		abrir();

		Query query = manager.createNamedQuery("HistoricoPreco.findByIdCodigoBarrasEidEstabelecimento");

		query.setParameter("idCodigoBarras", historicoPrecoPK.getIdCodigoBarras());
		query.setParameter("idEstabelecimento", historicoPrecoPK.getIdEstabelecimento());

		ArrayList<HistoricoPreco> array = (ArrayList<HistoricoPreco>) query.getResultList();

		fechar();

		return array;
	}
}
