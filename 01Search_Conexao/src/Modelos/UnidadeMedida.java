/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;

public class UnidadeMedida implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private Integer idUnidade;
    private String descricao;

    public UnidadeMedida() {
    }

    public UnidadeMedida(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidade != null ? idUnidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeMedida)) {
            return false;
        }
        UnidadeMedida other = (UnidadeMedida) object;
        if ((this.idUnidade == null && other.idUnidade != null) || (this.idUnidade != null && !this.idUnidade.equals(other.idUnidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Unidademedida[ idUnidade=" + idUnidade + " ]";
    }
    
}
