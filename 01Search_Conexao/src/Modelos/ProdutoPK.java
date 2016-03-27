/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;

public class ProdutoPK implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private int idEstabelecimento;
    private String idCodigoBarras;

    public ProdutoPK() {
    }

    public ProdutoPK(int idEstabelecimento, String idCodigoBarras) {
        this.idEstabelecimento = idEstabelecimento;
        this.idCodigoBarras = idCodigoBarras;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getIdCodigoBarras() {
        return idCodigoBarras;
    }

    public void setIdCodigoBarras(String idCodigoBarras) {
        this.idCodigoBarras = idCodigoBarras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEstabelecimento;
        hash += (idCodigoBarras != null ? idCodigoBarras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoPK)) {
            return false;
        }
        ProdutoPK other = (ProdutoPK) object;
        if (this.idEstabelecimento != other.idEstabelecimento) {
            return false;
        }
        if ((this.idCodigoBarras == null && other.idCodigoBarras != null) || (this.idCodigoBarras != null && !this.idCodigoBarras.equals(other.idCodigoBarras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ProdutoPK[ idEstabelecimento=" + idEstabelecimento + ", idCodigoBarras=" + idCodigoBarras + " ]";
    }
    
}
