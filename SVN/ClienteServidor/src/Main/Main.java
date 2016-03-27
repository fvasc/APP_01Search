package Main;

import java.util.ArrayList;

import Modelos.Codigobarras;
import Modelos.Departamento;
import Modelos.Estabelecimento;
import Modelos.Marca;
import Modelos.Produto;
import Modelos.Subdepartamento;
import conexao.Conexao;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*Item item = new Item();
		item.setIdItem(11);
		
		ArrayList<Codigobarras> c1 = new ArrayList<Codigobarras>();
		c1 = Conexao.getReference().buscarCodigobarras(item);
		System.out.println("TAMANHO C1 "+c1.size());
	
		item = Conexao.getReference().buscarItem(item);
		System.out.println("ITEM "+item.getNome()+" "+item.getIdItem());*/
		
		
		/*Subdepartamento sub = new Subdepartamento();
		sub.setIdSubdepartamento(8);
		
		ArrayList<Codigobarras> c2 = new ArrayList<Codigobarras>();
		c2 = Conexao.getReference().buscarCodigobarras(sub);
		System.out.println("TAMANHO C2 "+c2.size());
		
		sub = Conexao.getReference().buscarSubdepartamento(sub.getIdSubdepartamento());
		System.out.println("SUBDEPARTAMENTO "+sub.getNome()+" "+sub.getIdSubdepartamento());*/
		
		
		/*String busca = "a";
		ArrayList<Codigobarras> c3 = new ArrayList<Codigobarras>();
		c3 = Conexao.getReference().buscarCodigobarras(busca);
		System.out.println("TAMANHO C3 "+c3.size());*/
		
		
		/*Codigobarras codigobarras = new Codigobarras();
		codigobarras.setIdCodigoBarras("78913310026");
		ArrayList<Produto> c4 = new ArrayList<Produto>();
		c4 = Conexao.getReference().buscarProduto(codigobarras.getIdCodigoBarras());
		System.out.println("TAMANHO C4 "+c4.size());*/
		
		
		/*Departamento departamento = new Departamento();
		departamento.setIdDepartamento(2);
		
		ArrayList<Departamento> c5 = new ArrayList<Departamento>();
		c5 = Conexao.getReference().buscarDepartamentos();
		System.out.println("TAMANHO C5 "+c5.size());
		
		departamento = Conexao.getReference().buscarDepartamento(departamento.getIdDepartamento());
		System.out.println("DEPARTAMENTO "+departamento.getNome()+" "+departamento.getSubdepartamento().size());
		
		ArrayList<Subdepartamento> c11 = new ArrayList<Subdepartamento>();
		c11 = Conexao.getReference().buscarSubdepartamentos(departamento.getIdDepartamento());
		System.out.println("TAMANHO C11 "+c11.size());*/
		
		
		/*Usuario usuario = new Usuario();
		usuario.setIdUsuario(1);
		
		ArrayList<Lista> c6 = new ArrayList<Lista>();
		c6 = Conexao.getReference().buscarLista(usuario.getIdUsuario());
		System.out.println("TAMANHO C6 "+c6.size());
		
		ArrayList<Item> c7 = new ArrayList<Item>();
		c7 = Conexao.getReference().buscarItem(usuario.getIdUsuario());
		System.out.println("TAMANHO C7 "+c7.size());*/
		
		
		/*ItemLista itemLista = new ItemLista();
		ItemListaPK pk = new ItemListaPK();
		pk.setIdItem(11);
		pk.setIdLista(9);
		itemLista.setItemListaPK(pk);
		
		itemLista = Conexao.getReference().buscarItemLista(itemLista);
		System.out.println("ITEMLISTA "+itemLista.getQuantidade()+ " "+itemLista.getAtivo());*/
		
		
		/*Lista lista = new Lista();
		lista.setIdLista(9);
		
		ArrayList<ItemLista> c10 = new ArrayList<ItemLista>();
		c10 = Conexao.getReference().buscarItemLista(lista);
		System.out.println("TAMANHO C10 "+c10.size());*/
		
		
		/*Marca marca = new Marca();
		
		Codigobarras codigobarras = new Codigobarras();
		codigobarras.setIdCodigoBarras("75010092240");
		
		marca = Conexao.getReference().buscarMarca(codigobarras);
		System.out.println("MARCA "+marca.getNome()+ " "+marca.getNome());
		*/
		
		/*ArrayList<Estabelecimento> c12 = new ArrayList<Estabelecimento>();
		c12 = Conexao.getReference().buscarEstabelecimento();
		System.out.println("TAMANHO C12 "+c12.size());*/
		
		
		/*Estabelecimento est = new Estabelecimento();
		est.setIdEstabelecimento(1);
		
		ArrayList<Produto> c13 = new ArrayList<Produto>();
		c13 = Conexao.getReference().buscarDestaque(est);
		System.out.println("TAMANHO C13 "+c13.size());*/
		
		
		Codigobarras codigobarras = new Codigobarras();
		codigobarras.setIdCodigoBarras("78913310026");
		
		Subdepartamento subdepartamento = new Subdepartamento();
		subdepartamento = Conexao.getReference().buscarSubdepartamento(codigobarras);
		
		System.out.println("SUBDEPARTAMENTO "+ subdepartamento.getIdDepartamento().getNome());
		
		
	}
}
