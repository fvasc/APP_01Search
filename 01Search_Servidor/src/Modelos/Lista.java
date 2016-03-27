/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@Table(name = "lista", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Lista.findAll", query = "SELECT l FROM Lista l"),
    @NamedQuery(name = "Lista.findByIdLista", query = "SELECT l FROM Lista l WHERE l.idLista = :idLista"),
    @NamedQuery(name = "Lista.findByAtivo", query = "SELECT l FROM Lista l WHERE l.ativo = :ativo"),
    @NamedQuery(name = "Lista.findByVersao", query = "SELECT l FROM Lista l WHERE l.versao = :versao"),
    @NamedQuery(name = "Lista.findByUsuario", query = "SELECT l FROM Lista l WHERE l.versao = :versao")})
public class Lista implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLista")
    private Integer idLista;   
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "ativo")
    private Boolean ativo;
    
    @Column(name = "versao")
    private Integer versao;
    
    @XStreamOmitField
    @JoinTable(name = "lista_usuario", joinColumns = {
            @JoinColumn(name = "idLista", referencedColumnName = "idLista")}, inverseJoinColumns = {
            @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    
    @Basic(optional = false)
    @Column(name = "versaoCompra", nullable = false)
    private Integer versaoCompra;
   
    @Formula("(select count(l.idUsuario) from lista_usuario l where l.idLista = idLista) - 1 ")
    private Integer qtd_usuarios;
    
    @Formula("(select count(il.idItem) from item_lista il where il.idLista = idLista)")
    private Integer qtd_itens;
    
    
    
    public Integer getQtd_usuarios() {
		return qtd_usuarios;
	}

	public void setQtd_usuarios(Integer qtd_usuarios) {
		this.qtd_usuarios = qtd_usuarios;
	}

	
    public Integer getQtd_itens() {
		return qtd_itens;
	}

	public void setQtd_itens(Integer qtd_itens) {
		this.qtd_itens = qtd_itens;
	}

	public Lista() {
    }

    public Lista(Integer idLista) {
        this.idLista = idLista;
    }

    public Integer getVersaoCompra() {
		return versaoCompra;
	}

	public void setVersaoCompra(Integer versaoCompra) {
		this.versaoCompra = versaoCompra;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLista != null ? idLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.idLista == null && other.idLista != null) || (this.idLista != null && !this.idLista.equals(other.idLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Lista[ idLista=" + idLista + " ]";
    }
    
}
