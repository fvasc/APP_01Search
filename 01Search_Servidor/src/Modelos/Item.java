package Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@Table(name = "item", catalog = "01search", schema = "")
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByIdItem", query = "SELECT i FROM Item i WHERE i.idItem = :idItem"),
    @NamedQuery(name = "Item.findCodigobarrasByIdItem", query = "SELECT i.codigoBarras FROM Item i WHERE i.idItem = :idItem"),
    @NamedQuery(name = "Item.findByIdLista", query = "SELECT i FROM Item i WHERE i.idItem = :idItem"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.nome = :nome"),
    @NamedQuery(name =  "Item.findByUsuario", query = "SELECT i FROM Item i, Usuario u, UsuarioItem ui WHERE ui.usuarioItemPK.idItem = i.idItem" +
    		" and ui.usuarioItemPK.idUsuario = u.idUsuario and u.idUsuario = :idUsuario and i.ativo = true"),
    @NamedQuery(name = "Item.findByPeriodicidade", query = "SELECT i FROM Item i WHERE i.periodicidade = :periodicidade"),
    @NamedQuery(name = "Item.findByValidade", query = "SELECT i FROM Item i WHERE i.validade = :validade")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private Integer idItem;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "periodicidade")
    private Integer periodicidade;
    
    @Column(name = "validade")
    @Temporal(TemporalType.DATE)
    private Date validade;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @XStreamOmitField
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_item", joinColumns = {
            @JoinColumn(name = "idItem", referencedColumnName = "idItem")}, inverseJoinColumns = {
            @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")})
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "codigobarras_item", joinColumns = {
            @JoinColumn(name = "idItem", referencedColumnName = "idItem")}, inverseJoinColumns = {
            @JoinColumn(name = "idCodigoBarras", referencedColumnName = "idCodigoBarras")})
    private List<Codigobarras> codigoBarras = new ArrayList<Codigobarras>();

    public Item() {
    }

    public Item(Integer idItem) {
        this.idItem = idItem;
    }

    public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.nome = descricao;
    }

    public Integer getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(Integer periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Codigobarras> getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(List<Codigobarras> codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Item[ idItem=" + idItem + " ]";
    }
    
}