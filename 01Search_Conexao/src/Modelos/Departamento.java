/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.List;

public class Departamento implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private Integer idDepartamento;
    private String nome;
    private List<Subdepartamento> subdepartamentos;
    
    public Departamento() {
    }

    public Departamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Subdepartamento> getSubdepartamento() {
		return subdepartamentos;
	}

	public void setSubdepartamento(List<Subdepartamento> subdepartamento) {
		this.subdepartamentos = subdepartamento;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
