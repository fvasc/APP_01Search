package ControleJPA.modelos;

import javax.persistence.Query;

import ControleJPA.OperacaoGenerica;
import Modelos.Departamento;
import Modelos.Subdepartamento;

public class Departamento_JPA extends OperacaoGenerica<Departamento, Integer>{
	
	private static final Departamento_JPA departamento_JPA = new Departamento_JPA();

    public static Departamento_JPA get() {
        return departamento_JPA;
    }

	public Departamento buscarSubdepartamento(Subdepartamento subdepartamento) {

		abrir();
    	
		Query query = manager.createNamedQuery("Departamento.findBySubdepartamento");
		
		query.setParameter("idSubdepartamento", subdepartamento.getIdSubdepartamento());

		Departamento lista = (Departamento) query.getSingleResult();

		fechar();

		return lista;
	}

}
