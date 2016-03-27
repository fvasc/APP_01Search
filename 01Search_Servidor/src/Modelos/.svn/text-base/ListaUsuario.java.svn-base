package Modelos;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class ListaUsuario implements Serializable {
	
	private Lista lista;
	
	private Usuario usuario;
	
	
    public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaUsuario other = (ListaUsuario) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "modelos.ListaUsuario[ idLista=" + lista + ", idUsuario=" + usuario + " ]";
    }
}
