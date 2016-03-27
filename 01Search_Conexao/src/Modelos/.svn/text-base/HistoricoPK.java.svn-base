/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Date;

public class HistoricoPK implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private int idLista;
    private int idItem;
    private String idCodigoBarras;
    private int idEstabelecimento;
    private Date compraData;
    private Integer versaoCompra;

    public HistoricoPK() {
    }

    public HistoricoPK(int idLista, int idItem, String idCodigoBarras, int idEstabelecimento, Date compraData) {
        this.idLista = idLista;
        this.idItem = idItem;
        this.idCodigoBarras = idCodigoBarras;
        this.idEstabelecimento = idEstabelecimento;
        this.compraData = compraData;
    }

    public Integer getVersaoCompra() {
		return versaoCompra;
	}

	public void setVersaoCompra(Integer versao_compra) {
		this.versaoCompra = versao_compra;
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

    public String getIdCodigoBarras() {
        return idCodigoBarras;
    }

    public void setIdCodigoBarras(String idCodigoBarras) {
        this.idCodigoBarras = idCodigoBarras;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Date getCompraData() {
        return compraData;
    }

    public void setCompraData(Date compraData) {
        this.compraData = compraData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLista;
        hash += (int) idItem;
        hash += (idCodigoBarras != null ? idCodigoBarras.hashCode() : 0);
        hash += (int) idEstabelecimento;
        hash += (compraData != null ? compraData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoPK)) {
            return false;
        }
        HistoricoPK other = (HistoricoPK) object;
        if (this.idLista != other.idLista) {
            return false;
        }
        if (this.idItem != other.idItem) {
            return false;
        }
        if ((this.idCodigoBarras == null && other.idCodigoBarras != null) || (this.idCodigoBarras != null && !this.idCodigoBarras.equals(other.idCodigoBarras))) {
            return false;
        }
        if (this.idEstabelecimento != other.idEstabelecimento) {
            return false;
        }
        if ((this.compraData == null && other.compraData != null) || (this.compraData != null && !this.compraData.equals(other.compraData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.HistoricoPK[ idLista=" + idLista + ", idItem=" + idItem + ", idCodigoBarras=" + idCodigoBarras + ", idEstabelecimento=" + idEstabelecimento + ", compraData=" + compraData + " ]";
    }
    
}
