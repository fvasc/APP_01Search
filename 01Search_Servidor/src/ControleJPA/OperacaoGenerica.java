package ControleJPA;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class OperacaoGenerica<T, K> {

	protected EntityManager manager;

	public void abrir() {
		if (this.manager == null || !this.manager.isOpen()) {
			this.manager = Emf.get().createEntityManager();

			this.manager.getTransaction().begin();
		}
	}

	public void fechar() {

		this.manager.close();
	}

	public T create(T obj) {

		abrir();

		this.manager.persist(obj);

		this.manager.getTransaction().commit();

		fechar();

		return obj;
	}

	public void delete(T obj) {

		abrir();

		Object o = this.manager.merge(obj);

		this.manager.remove(o);

		this.manager.getTransaction().commit();

		fechar();
	}

	public void update(T obj) {

		abrir();

		this.manager.merge(obj);

		this.manager.getTransaction().commit();

		fechar();
	}

	public T select(T obj, K fk) {

		abrir();

		T resposta = (T) this.manager.find(obj.getClass(), fk);

		fechar();

		return resposta;
	}

	public List<T> selectAll(String tableName) {

		abrir();

		Query query = this.manager.createQuery("FROM " + tableName);

		List<T> resposta = query.getResultList();

		fechar();

		return resposta;
	}
}
