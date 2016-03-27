/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;

import org.hibernate.annotations.Formula;

public class ItemLista implements Serializable {

	private static final long serialVersionUID = 1L;
	protected ItemListaPK itemListaPK;
	private int quantidade;
	private boolean ativo;
	private int periodicidade;
	private Item item;
	private UnidadeMedida unidadeMedida;
	private Integer qtdComprada;
	private String data_qtdRestante;

	public ItemLista() {
	}

	public String getData_qtdRestante() {
		return data_qtdRestante;
	}

	public void setData_qtdRestante(String data_qtdRestante) {
		this.data_qtdRestante = data_qtdRestante;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Integer getQtdComprada() {
		return qtdComprada;
	}

	public void setQtdComprada(Integer qtd) {
		this.qtdComprada = qtd;
	}

	public ItemLista(ItemListaPK itemListaPK) {
		this.itemListaPK = itemListaPK;
	}

	public ItemLista(int idLista, int idItem) {
		this.itemListaPK = new ItemListaPK(idLista, idItem);
	}

	public ItemListaPK getItemListaPK() {
		return itemListaPK;
	}

	public void setItemListaPK(ItemListaPK itemListaPK) {
		this.itemListaPK = itemListaPK;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(int periodicidade) {
		this.periodicidade = periodicidade;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (itemListaPK != null ? itemListaPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ItemLista)) {
			return false;
		}
		ItemLista other = (ItemLista) object;
		if ((this.itemListaPK == null && other.itemListaPK != null)
				|| (this.itemListaPK != null && !this.itemListaPK
						.equals(other.itemListaPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return item.getNome();
	}

}
