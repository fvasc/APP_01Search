/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Nathan
 */
@Entity
@Table(name = "historicoPreco", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "HistoricoPreco.findAll", query = "SELECT h FROM HistoricoPreco h"),
    @NamedQuery(name = "HistoricoPreco.findByIdCodigoBarrasEidEstabelecimento", query = "SELECT h FROM HistoricoPreco h WHERE h.historicoPrecoPK.idCodigoBarras = :idCodigoBarras AND h.historicoPrecoPK.idEstabelecimento = :idEstabelecimento"),
    @NamedQuery(name = "HistoricoPreco.findByIdCodigoBarras", query = "SELECT h FROM HistoricoPreco h WHERE h.historicoPrecoPK.idCodigoBarras = :idCodigoBarras"),
    @NamedQuery(name = "HistoricoPreco.findByIdEstabelecimento", query = "SELECT h FROM HistoricoPreco h WHERE h.historicoPrecoPK.idEstabelecimento = :idEstabelecimento"),
    @NamedQuery(name = "HistoricoPreco.findByPrecoData", query = "SELECT h FROM HistoricoPreco h WHERE h.historicoPrecoPK.precoData = :precoData"),
    @NamedQuery(name = "HistoricoPreco.findByPreco", query = "SELECT h FROM HistoricoPreco h WHERE h.preco = :preco")})
public class HistoricoPreco implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoricoPrecoPK historicoPrecoPK;    
    @Column(name = "preco", precision = 7, scale = 2)
    private BigDecimal preco;
    @JoinColumn(name = "idUnidade", referencedColumnName = "idUnidade", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UnidadeMedida idUnidade;

    public HistoricoPreco() {
    }

    public HistoricoPreco(HistoricoPrecoPK historicoPrecoPK) {
        this.historicoPrecoPK = historicoPrecoPK;
    }

    public HistoricoPreco(String idCodigoBarras, int idEstabelecimento, Date precoData) {
        this.historicoPrecoPK = new HistoricoPrecoPK(idCodigoBarras, idEstabelecimento, precoData);
    }

    public HistoricoPrecoPK getHistoricoPK() {
        return historicoPrecoPK;
    }

    public void setHistoricoPK(HistoricoPrecoPK historicoPrecoPK) {
        this.historicoPrecoPK = historicoPrecoPK;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    public UnidadeMedida getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(UnidadeMedida idUnidade) {
        this.idUnidade = idUnidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historicoPrecoPK != null ? historicoPrecoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoPreco)) {
            return false;
        }
        HistoricoPreco other = (HistoricoPreco) object;
        if ((this.historicoPrecoPK == null && other.historicoPrecoPK != null) || (this.historicoPrecoPK != null && !this.historicoPrecoPK.equals(other.historicoPrecoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.HistoricoPreco[ historicoPrecoPK=" + historicoPrecoPK + " ]";
    }
    
}
