/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.List;

public class Subdepartamento implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private Integer idSubdepartamento;
    private String nome;
    private Departamento idDepartamento;
    private List<Codigobarras> codigobarras;

    public Subdepartamento() {
    }

    public Subdepartamento(Integer idSubdepartamento) {
        this.idSubdepartamento = idSubdepartamento;
    }

    public Integer getIdSubdepartamento() {
        return idSubdepartamento;
    }

    public void setIdSubdepartamento(Integer idSubdepartamento) {
        this.idSubdepartamento = idSubdepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public List<Codigobarras> getCodigobarras() {
		return codigobarras;
	}

	public void setCodigobarras(List<Codigobarras> codigobarras) {
		this.codigobarras = codigobarras;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubdepartamento != null ? idSubdepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subdepartamento)) {
            return false;
        }
        Subdepartamento other = (Subdepartamento) object;
        if ((this.idSubdepartamento == null && other.idSubdepartamento != null) || (this.idSubdepartamento != null && !this.idSubdepartamento.equals(other.idSubdepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
