/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "subdepartamento", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Subdepartamento.findAll", query = "SELECT s FROM Subdepartamento s"),
    @NamedQuery(name = "Subdepartamento.findByIdSubdepartamento", query = "SELECT s FROM Subdepartamento s WHERE s.idSubdepartamento = :idSubdepartamento"),
    @NamedQuery(name = "Subdepartamento.findByIdDepartamento", query = "SELECT s FROM Subdepartamento s WHERE s.idDepartamento.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "Subdepartamento.findByNome", query = "SELECT s FROM Subdepartamento s WHERE s.nome = :nome"),
    @NamedQuery(name = "Subdepartamento.findByNomeLike", query = "SELECT s FROM Subdepartamento s WHERE s.nome = '%:nome%'")})
public class Subdepartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubdepartamento")
    private Integer idSubdepartamento;

    @Column(name = "nome")
    private String nome;
    
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento")
    @ManyToOne(fetch = FetchType.EAGER)
    private Departamento idDepartamento;
    
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
