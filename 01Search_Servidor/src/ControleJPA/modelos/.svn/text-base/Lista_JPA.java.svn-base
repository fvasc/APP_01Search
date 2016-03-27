package ControleJPA.modelos;

import java.util.List;

import javax.persistence.Query;

import ControleJPA.OperacaoGenerica;
import Modelos.Lista;

public class Lista_JPA extends OperacaoGenerica<Lista, Integer>{

	private static final Lista_JPA listas_JPA = new Lista_JPA();

    public static Lista_JPA get() {
        return listas_JPA;
    }

	public List<Lista> buscaListas(int usuarioId) {
		
		abrir();

    	Query query = manager.createNamedQuery("Lista.findByUsuario");
		
		query.setParameter("idUsuario", usuarioId);
		
		//recebendo com list
		List<Lista> lista = (List<Lista>) query.getResultList();
    
		fechar();
	
		return lista;
	}
}
