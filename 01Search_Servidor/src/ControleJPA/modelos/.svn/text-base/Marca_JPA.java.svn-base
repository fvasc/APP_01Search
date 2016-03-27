package ControleJPA.modelos;

import javax.persistence.Query; 
import ControleJPA.OperacaoGenerica;
import Modelos.Marca;

public class Marca_JPA extends OperacaoGenerica<Marca, Integer>{

	private static final Marca_JPA marca_JPA = new Marca_JPA();

    public static Marca_JPA get() {
        return marca_JPA;
    }
    
    public Marca buscarMarca(String marcaNome) {

    	abrir();
    	
    	Query query = manager.createNamedQuery("Marca.findByNomeLike");
		
		query.setParameter("nome", marcaNome);
		
		Marca lista = (Marca) query.getResultList();

		fechar();

		return lista;
	}
}
