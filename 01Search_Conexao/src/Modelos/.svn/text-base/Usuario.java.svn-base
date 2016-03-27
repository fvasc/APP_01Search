
package Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private Integer idUsuario;
    private String nome;
    private Login idLogin;
    private List<Lista> listas = new ArrayList<Lista>();
    private List<Item> itens = new ArrayList<Item>();

    public List<Lista> getListas(){
    	return listas;
    }
    
    public void setListas(List<Lista> listas){
    	this.listas = listas;
    }
    
	public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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
