package controle.Visual;

import java.util.ArrayList;

import conexao.Conexao;

import Modelos.Codigobarras;
import Modelos.Item;
import Modelos.Usuario;


public class ItemControle {
	
private static final ItemControle itemControle = new ItemControle();
	
	public static ItemControle get(){
		return itemControle;
	}

	public ArrayList<Item> buscarItem(Usuario usuario) {
		
		ArrayList<Item> arrayList = new ArrayList<Item>();
		
		Usuario u = new Usuario();
		u.setIdUsuario(usuario.getIdUsuario());
		arrayList = Conexao.getReference().buscarItem(u);
		return arrayList;

	}

	public ArrayList<ArrayList<Codigobarras>> buscarCodigobarras(
			ArrayList<Item> itens) {
		
		ArrayList<ArrayList<Codigobarras>> arrayList = new ArrayList<ArrayList<Codigobarras>>();

		for (Item item : itens) {
			
			Item i = new Item();
			i.setIdItem(item.getIdItem());
			arrayList.add((ArrayList<Codigobarras>) Conexao.getReference().buscarCodigobarras(i)); 
			
		}
		
		return arrayList;
	}
}
