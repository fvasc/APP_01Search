/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Formula;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@Table(name = "item_lista", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "ItemLista.findAll", query = "SELECT i FROM ItemLista i"),
    @NamedQuery(name = "ItemLista.findByIdLista", query = "SELECT i FROM ItemLista i WHERE i.itemListaPK.idLista = :idLista and ativo = true"),
    @NamedQuery(name = "ItemLista.findByIdUsuario", query = "SELECT i FROM ItemLista il, Usuario u, Item i  WHERE il.itemListaPK.idItem = i.idItem and" +
    		" u in (i.usuarios)  and u.idUsuario = :idUsuario"),
    @NamedQuery(name = "ItemLista.findByIdItem", query = "SELECT i FROM ItemLista i WHERE i.itemListaPK.idItem = :idItem"),
    @NamedQuery(name = "ItemLista.findByQuantidade", query = "SELECT i FROM ItemLista i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemLista.findByAtivo", query = "SELECT i FROM ItemLista i WHERE i.ativo = :ativo"),
    @NamedQuery(name = "ItemLista.findByPeriodicidade", query = "SELECT i FROM ItemLista i WHERE i.periodicidade = :periodicidade")})
public class ItemLista implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ItemListaPK itemListaPK;
    
    @Column(name = "quantidade")
    private int quantidade;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @XStreamOmitField
    @Column(name = "periodicidade")
    private int periodicidade;
    
    
    @JoinColumn(name = "idItem", referencedColumnName = "idItem", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
    
    @XStreamOmitField
    @JoinColumn(name = "idUnidade", referencedColumnName = "idUnidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private UnidadeMedida unidadeMedida;
    
   /* @Formula("IFNULL((select sum(h.quantidade) from Historico_Item_Lista h, item_lista i, lista l where  l.idLista = i.idLista and " +
    		"h.idLista = i.idLista and h.idItem = i.idItem and h.versaoCompra = l.versaoCompra and " +
    		"h.idItem = idItem and h.idLista = idLista),0)")*/
    @Transient
	private Integer qtdComprada = 0;

    @Formula("IFNULL((select getItensHistorico(idLista,idItem) from item_lista i where i.idLista = idLista and i.idItem = idItem),'')")
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

	public void setQtdComprada(Integer qtdComprada) {
		this.qtdComprada = qtdComprada;
	}


    public ItemLista(ItemListaPK itemListaPK) {
        this.itemListaPK = itemListaPK;
    }

    public ItemLista(ItemListaPK itemListaPK, int quantidade, boolean ativo, int periodicidade) {
        this.itemListaPK = itemListaPK;
        this.quantidade = quantidade;
        this.ativo = ativo;
        this.periodicidade = periodicidade;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemLista)) {
            return false;
        }
        ItemLista other = (ItemLista) object;
        if ((this.itemListaPK == null && other.itemListaPK != null) || (this.itemListaPK != null && !this.itemListaPK.equals(other.itemListaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return item.getNome();
    }
    
}
