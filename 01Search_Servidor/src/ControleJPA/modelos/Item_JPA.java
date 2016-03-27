package ControleJPA.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ControleJPA.OperacaoGenerica;
import Modelos.Codigobarras;
import Modelos.Item;
import Modelos.Usuario;

public class Item_JPA extends OperacaoGenerica<Item, Integer> {

	private static final Item_JPA item_JPA = new Item_JPA();

	public static Item_JPA get() {
		return item_JPA;
	}

	public ArrayList<Codigobarras> buscarCodigobarras(Item item) {

		abrir();

		Query query = manager.createNamedQuery("Item.findCodigobarrasByIdItem");

		query.setParameter("idItem", item.getIdItem());

		ArrayList<Codigobarras> array = (ArrayList<Codigobarras>) query
				.getResultList();

		fechar();

		return array;
	}

	public List<Item> buscarItemAtivo(Usuario usuario) {

		abrir();

		Query query = manager.createNamedQuery("Item.findByUsuario");

		query.setParameter("idUsuario", usuario.getIdUsuario());

		List<Item> itens = (List<Item>) query.getResultList();

		for (Item i : itens) {
			i.getCodigoBarras();
		}

		fechar();

		return itens;
	}

	public Item buscarItem(Item item) {

		abrir();

		Query query = manager.createNamedQuery("Item.findByIdItem");

		query.setParameter("idItem", item.getIdItem());

		Item objeto = (Item) query.getSingleResult();

		fechar();

		return objeto;
	}

	public Item buscarItem(Usuario usuario) {

		abrir();

		Query query = manager.createNamedQuery("Item.findByIdItem");

		query.setParameter("idItem", usuario.getIdUsuario());

		Item objeto = (Item) query.getSingleResult();

		fechar();

		return objeto;
	}

}
