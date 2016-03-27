/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "marca", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findByIdMarca", query = "SELECT m FROM Marca m WHERE m.idMarca = :idMarca"),
    @NamedQuery(name = "Marca.findByNome", query = "SELECT m FROM Marca m WHERE m.nome = :nome"),
    @NamedQuery(name = "Marca.findByNomeLike", query = "SELECT m FROM Marca m WHERE m.nome = '%:nome%'")})
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMarca", nullable = false)
    private Integer idMarca;
    
    @Column(name = "nome")
    private String nome;
    
    public Marca() {
    }

    public Marca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
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
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Marca[ idMarca=" + idMarca + " ]";
    }
    
}
