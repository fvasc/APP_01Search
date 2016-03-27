package ControleJPA;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Emf {
	
	private static final EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("01search");

	public static EntityManagerFactory get() {
		return entityManagerFactory;
	}

}
