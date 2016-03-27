/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "departamento", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findByIdDepartamento", query = "SELECT d FROM Departamento d WHERE d.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "Departamento.findByNome", query = "SELECT d FROM Departamento d WHERE d.nome = :nome"),
    @NamedQuery(name = "Departamento.findBySubdepartamento", query = "SELECT s.idDepartamento FROM Subdepartamento s WHERE s.idSubdepartamento = :idSubdepartamento"),
    @NamedQuery(name = "Departamento.findByNomeLike", query = "SELECT d FROM Departamento d WHERE d.nome like '%:nome%'")})
public class Departamento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartamento")
    private Integer idDepartamento;
    
    @Column(name = "nome")
    private String nome;

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
        return "modelos.Departamento[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
