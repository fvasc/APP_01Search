/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

public class ItemListaPK {
	
    private int idLista;
    private int idItem;

    public ItemListaPK() {
    }

    public ItemListaPK(int idLista, int idItem) {
        this.idLista = idLista;
        this.idItem = idItem;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLista;
        hash += (int) idItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemListaPK)) {
            return false;
        }
        ItemListaPK other = (ItemListaPK) object;
        if (this.idLista != other.idLista) {
            return false;
        }
        if (this.idItem != other.idItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ItemListaPK[ idLista=" + idLista + ", idItem=" + idItem + " ]";
    }
    
}
