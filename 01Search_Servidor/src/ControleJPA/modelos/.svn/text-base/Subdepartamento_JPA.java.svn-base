package ControleJPA.modelos;

import java.util.ArrayList;
import javax.persistence.Query;
import ControleJPA.OperacaoGenerica;
import Modelos.Codigobarras;
import Modelos.Departamento;
import Modelos.Subdepartamento;

public class Subdepartamento_JPA extends OperacaoGenerica<Subdepartamento, Integer>  {

	private static final Subdepartamento_JPA subdepartamento_JPA = new Subdepartamento_JPA();

    public static Subdepartamento_JPA get() {
        return subdepartamento_JPA;
    }
    
    public ArrayList<Subdepartamento> buscarSubdepartamento(Departamento departamento) {

    	abrir();

    	Query query = manager.createNamedQuery("Subdepartamento.findByIdDepartamento");
		
		query.setParameter("idDepartamento", departamento.getIdDepartamento());

		ArrayList<Subdepartamento> lista = (ArrayList<Subdepartamento>) query.getResultList();

		fechar();

		return lista;
	}
    
    public ArrayList<Subdepartamento> buscarSubdepartamento(String subdepartamentoNome) {

    	abrir();

    	Query query = manager.createNamedQuery("Subdepartamento.findByNomeLike");
		
		query.setParameter("nome", subdepartamentoNome);

		ArrayList<Subdepartamento> lista = (ArrayList<Subdepartamento>) query.getSingleResult();

		fechar();

		return lista;
	}
    
	public Subdepartamento buscarSubdepartamento(Codigobarras codigobarras) {

		abrir();

		System.out.println("ENTRADA " +codigobarras.getIdCodigoBarras() +" "+codigobarras.getDescricao()+" "+
		codigobarras.getNome()+" "+codigobarras.getImagem());
		
		Query query = manager
				.createNamedQuery("Subdepartamento.findByIdCodigobarras");

		query.setParameter("codigobarras", codigobarras);

		Subdepartamento subdepartamento = (Subdepartamento) query.getSingleResult();
		
		
		System.out.println("SAIDA " +subdepartamento.getNome() +" "+subdepartamento.getIdSubdepartamento()+" "+
				subdepartamento.getIdDepartamento().getNome()+" "+subdepartamento.getIdDepartamento().getIdDepartamento());
		/*Departamento departamento = subdepartamento.getIdDepartamento();
		subdepartamento.setIdDepartamento(departamento);*/

		fechar();

		return subdepartamento;
	}
	
}
