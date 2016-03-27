package controle.Visual;

import java.util.ArrayList;


import Modelos.Codigobarras;
import Modelos.Item;
import Modelos.ItemLista;
import Modelos.Lista;
import Modelos.Usuario;
import conexao.Conexao;

public class ListaControle {
	
	private static final ListaControle listaControle = new ListaControle();
	
	public static ListaControle get(){
		return listaControle;
	}

	/*public ArrayList<ItemLista> buscarItemLista(Lista lista) {
		
		ArrayList<ItemLista> arrayList = new ArrayList<ItemLista>();
		
		Lista l = new Lista();
		l.setIdLista(lista.getIdLista());
		arrayList = Conexao.getReference().buscarItemLista(l);
		return arrayList;

	}

	public ArrayList<ArrayList<Codigobarras>> buscarCodigobarras(
			ArrayList<ItemLista> itensListas) {

		ArrayList<ArrayList<Codigobarras>> arrayList = new ArrayList<ArrayList<Codigobarras>>();
		
		for (ItemLista itemLista : itensListas) {
			
			Item item = new Item();
			item.setIdItem(itemLista.getItemListaPK().getIdItem());
			arrayList.add((ArrayList<Codigobarras>) Conexao.getReference().buscarCodigobarras(item)); 
			
		}
		
		return arrayList;
		
	}*/

	public Item buscarItem(int idItem) {

		Modelos.Item item = new Item();
		item.setIdItem(idItem);
		item = Conexao.getReference().buscarItem(item);
		return item;
	}
	
	public ArrayList<Lista> buscarLista(Usuario usuario) {
		
		Usuario u = new Usuario();
		u.setIdUsuario(usuario.getIdUsuario());

		ArrayList<Lista> array = Conexao.getReference().buscarLista(u);
		
		return array;
		
	}

}
