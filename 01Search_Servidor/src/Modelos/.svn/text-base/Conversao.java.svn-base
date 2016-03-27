/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Nathan
 */
@Entity
@Table(name = "conversao", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Conversao.findAll", query = "SELECT c FROM Conversao c"),
    @NamedQuery(name = "Conversao.findByIdConversao", query = "SELECT c FROM Conversao c WHERE c.idConversao = :idConversao"),
    @NamedQuery(name = "Conversao.findByFator", query = "SELECT c FROM Conversao c WHERE c.fator = :fator")})
public class Conversao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idConversao", nullable = false)
    private Integer idConversao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fator", precision = 8, scale = 4)
    private BigDecimal fator;
    @JoinColumn(name = "idUnidadeSecundaria", referencedColumnName = "idUnidade", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UnidadeMedida idUnidadeSecundaria;
    @JoinColumn(name = "idUnidadePrincipal", referencedColumnName = "idUnidade", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UnidadeMedida idUnidadePrincipal;

    public Conversao() {
    }

    public Conversao(Integer idConversao) {
        this.idConversao = idConversao;
    }

    public Integer getIdConversao() {
        return idConversao;
    }

    public void setIdConversao(Integer idConversao) {
        this.idConversao = idConversao;
    }

    public BigDecimal getFator() {
        return fator;
    }

    public void setFator(BigDecimal fator) {
        this.fator = fator;
    }

    public UnidadeMedida getIdUnidadeSecundaria() {
        return idUnidadeSecundaria;
    }

    public void setIdUnidadeSecundaria(UnidadeMedida idUnidadeSecundaria) {
        this.idUnidadeSecundaria = idUnidadeSecundaria;
    }

    public UnidadeMedida getIdUnidadePrincipal() {
        return idUnidadePrincipal;
    }

    public void setIdUnidadePrincipal(UnidadeMedida idUnidadePrincipal) {
        this.idUnidadePrincipal = idUnidadePrincipal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConversao != null ? idConversao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversao)) {
            return false;
        }
        Conversao other = (Conversao) object;
        if ((this.idConversao == null && other.idConversao != null) || (this.idConversao != null && !this.idConversao.equals(other.idConversao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Conversao[ idConversao=" + idConversao + " ]";
    }
    
}
