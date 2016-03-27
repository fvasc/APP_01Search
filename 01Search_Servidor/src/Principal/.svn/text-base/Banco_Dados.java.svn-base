package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Banco_Dados {

	private EntityManagerFactory factory;
	private EntityManager manager;
	private static Banco_Dados reference;

	public Banco_Dados() {
		reference = this;

		iniciaConexao();
		fechaConexao();
	}

	public static Banco_Dados getReference() {
		if (reference == null)
			reference = new Banco_Dados();

		return reference;
	}

	private void iniciaConexao() {
		factory = Persistence.createEntityManagerFactory("01search");
		manager = factory.createEntityManager();
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void fechaConexao() {
		factory.close();
	}
}
