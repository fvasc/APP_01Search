package ControleJPA.modelos;
import java.util.List;

import javax.persistence.Query;

import ControleJPA.OperacaoGenerica;
import Modelos.Usuario;
import Modelos.Item;
import Modelos.Lista;

public class Usuario_JPA extends OperacaoGenerica<Usuario, Integer>{

	private static final Usuario_JPA cliente_JPA = new Usuario_JPA();
	
	public static Usuario_JPA get() {
	        return cliente_JPA;
	}
	 
	public List<Lista> buscarLista(Usuario usuario) {
			
	    	abrir();

	    	Query query = manager.createNamedQuery("Usuario.findListas");
			
			query.setParameter("idUsuario", usuario.getIdUsuario());
			
			List<Lista> lista = (List<Lista>) query.getResultList();
	    
			fechar();
		
			return lista;
	}
	
}