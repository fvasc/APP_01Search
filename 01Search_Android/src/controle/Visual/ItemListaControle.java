package controle.Visual;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import Modelos.Codigobarras;
import Modelos.CodigobarrasItem;
import Modelos.Estabelecimento;
import Modelos.Item;
import Modelos.ItemLista;
import Modelos.ItemListaPK;
import Modelos.Lista;
import Modelos.Usuario;
import Modelos.UsuarioItem;
import conexao.Conexao;
import controle.Sessao;

public class ItemListaControle {

	private static final ItemListaControle itemListaControle = new ItemListaControle();

	public static ItemListaControle get() {
		return itemListaControle;
	}

	public ArrayList<Item> buscarItem(Usuario usuario) {

		ArrayList<Item> arrayList = Conexao.getReference().buscarItem(usuario);
		return arrayList;

	}

	public ArrayList<ItemLista> buscarItemLista(Lista lista) {

		ArrayList<ItemLista> arrayList = new ArrayList<ItemLista>();

		Lista lis = new Lista();
		lis.setIdLista(lista.getIdLista());
		arrayList = Conexao.getReference().buscarItemLista(lis);
		return arrayList;

	}

	public ArrayList<ArrayList<Codigobarras>> buscarCodigobarras(
			ArrayList<ItemLista> itensListas, Estabelecimento estabelecimento) {

		ArrayList<ItemLista> array_ItemLista = new ArrayList<ItemLista>();

		for (ItemLista itemLista : itensListas) {

			
			ItemListaPK pk = new ItemListaPK();
			pk.setIdItem(itemLista.getItemListaPK().getIdItem());
			pk.setIdLista(itemLista.getItemListaPK().getIdLista());
			
			ItemLista il = new ItemLista();
			il.setItemListaPK(pk);
			
			array_ItemLista.add(il);
		}
		
		ArrayList<ArrayList<Codigobarras>> array_Codigobarras = 
				Conexao.getReference().buscarCodigobarras(array_ItemLista, estabelecimento);

		return array_Codigobarras;

	}

	public ArrayList<ArrayList<Codigobarras>> buscarCodigobarrasPreco(
			ArrayList<ArrayList<Codigobarras>> arrCodigobarras, Estabelecimento estabelecimento) {

		ArrayList<ArrayList<Codigobarras>> array = 
				(ArrayList<ArrayList<Codigobarras>>) Conexao.getReference().buscarPrecoCorrente(arrCodigobarras, estabelecimento);

		return array;
	}

	public void excluirCodigobarrasItem(CodigobarrasItem codigobarrasItem) {

		Conexao.getReference().removerCodigobarrasItem(codigobarrasItem);
	}

	public void excluirItemLista(ItemLista itemLista) {

		Conexao.getReference().removerItemLista(itemLista);
	}

	public Item adicionarItem(Item item) {

		item = Conexao.getReference().adicionarItem(item);

		return item;
	}

	public void adicionarUsuarioItem(UsuarioItem usuarioItem) {

		Conexao.getReference().adicionarUsuarioItem(usuarioItem);
	}

	public void adicionarItemLista(ItemLista itemLista) {

		Conexao.getReference().adicionarItemLista(itemLista);
	}

	public ArrayList<Estabelecimento> buscarEstabelecimento(ArrayList<Codigobarras> arrCodigobarrasQuantidade) {
		ArrayList<Estabelecimento> array = Conexao.getReference().buscarEstabelecimento(arrCodigobarrasQuantidade);
		
		return array;
	}

	public void adicionarHistorico(ArrayList<ItemLista> arrItemLista, ArrayList<ArrayList<Codigobarras>> arrCodigobarras, 
			Estabelecimento estabelecimento) {
		Conexao.getReference().adicionarHistorico(arrItemLista, arrCodigobarras, estabelecimento);
		
	}

}
