/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Nathan
 */
@Embeddable
public class HistoricoPrecoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCodigoBarras", nullable = false, length = 11)
    private String idCodigoBarras;
    @Basic(optional = false)
    @Column(name = "idEstabelecimento", nullable = false)
    private int idEstabelecimento;
    @Basic(optional = false)
    @Column(name = "precoData", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date precoData;

    public HistoricoPrecoPK() {
    }

    public HistoricoPrecoPK(String idCodigoBarras, int idEstabelecimento, Date precoData) {
        this.idCodigoBarras = idCodigoBarras;
        this.idEstabelecimento = idEstabelecimento;
        this.precoData = precoData;
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

    public Date getPrecoData() {
        return precoData;
    }

    public void setPrecoData(Date precoData) {
        this.precoData = precoData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCodigoBarras != null ? idCodigoBarras.hashCode() : 0);
        hash += (int) idEstabelecimento;
        hash += (precoData != null ? precoData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoPrecoPK)) {
            return false;
        }
        HistoricoPrecoPK other = (HistoricoPrecoPK) object;
        if ((this.idCodigoBarras == null && other.idCodigoBarras != null) || (this.idCodigoBarras != null && !this.idCodigoBarras.equals(other.idCodigoBarras))) {
            return false;
        }
        if (this.idEstabelecimento != other.idEstabelecimento) {
            return false;
        }
        if ((this.precoData == null && other.precoData != null) || (this.precoData != null && !this.precoData.equals(other.precoData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.HistoricoPrecoPK[ idCodigoBarras=" + idCodigoBarras + ", idEstabelecimento=" + idEstabelecimento + ", precoData=" + precoData + " ]";
    }
    
}
