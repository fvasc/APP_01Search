/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "unidademedida", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Unidademedida.findAll", query = "SELECT u FROM UnidadeMedida u"),
    @NamedQuery(name = "Unidademedida.findByIdUnidade", query = "SELECT u FROM UnidadeMedida u WHERE u.idUnidade = :idUnidade"),
    @NamedQuery(name = "Unidademedida.findByDescricao", query = "SELECT u FROM UnidadeMedida u WHERE u.descricao = :descricao")})
public class UnidadeMedida implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idUnidade")
    private Integer idUnidade;
    
    @Column(name = "descricao")
    private String descricao;
    
    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "idUnidade")
    private List<ItemLista> ItensListas;*/

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
/*
    public List<ItemLista> getItensListas() {
		return ItensListas;
	}

	public void setItensListas(List<ItemLista> itensListas) {
		ItensListas = itensListas;
	}*/

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
