/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@Table(name = "usuario", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT c FROM Usuario c"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT c FROM Usuario c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findListas", query = "SELECT c.listas FROM Usuario c WHERE c.idUsuario = :idUsuario"),
    //@NamedQuery(name = "Usuario.findItens", query = "SELECT c.itens FROM Usuario c WHERE  c.idUsuario = :idUsuario  and  i in(c.itens)"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT c FROM Usuario c WHERE c.nome = :nome")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    
    @Column(name = "nome")
    private String nome;
    
    @JoinColumn(name = "idLogin", referencedColumnName = "idLogin")
    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne(fetch = FetchType.EAGER)
    private Login idLogin;
    
    @JoinTable(name = "lista_usuario", joinColumns = {
            @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")}, inverseJoinColumns = {
            @JoinColumn(name = "idLista", referencedColumnName = "idLista")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Lista> listas = new ArrayList<Lista>();
	
    @JoinTable(name = "usuario_item", joinColumns = {
            @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")}, inverseJoinColumns = {
            @JoinColumn(name = "idItem", referencedColumnName = "idItem")})
    @ManyToMany(fetch = FetchType.LAZY)
	private List<Item> itens = new ArrayList<Item>();
    

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Lista> getListas(){
    	return listas;
    }
    
    public void setListas(List<Lista> listas){
    	this.listas = listas;
    }
   
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Login getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Login idLogin) {
        this.idLogin = idLogin;
    }

    public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Cliente[ idUsuario=" + idUsuario + " ]";
    }
    
}
