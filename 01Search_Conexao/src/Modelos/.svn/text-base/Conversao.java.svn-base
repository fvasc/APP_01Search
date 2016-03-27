/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;

public class Conversao implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private Integer idConversao;
    private BigDecimal fator;
    private UnidadeMedida idUnidadeSecundaria;
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
