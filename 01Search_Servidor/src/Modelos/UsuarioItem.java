package Modelos;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "usuario_item", catalog = "01search", schema = "")
public class UsuarioItem {

	@EmbeddedId
	 protected UsuarioItemPK usuarioItemPK;
	
	@JoinColumn(name = "idItem", referencedColumnName = "idItem", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Item item;

	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	public UsuarioItem() {
		
	}

	public UsuarioItemPK getUsuarioItemPK() {
		return usuarioItemPK;
	}

	public void setUsuarioItemPK(UsuarioItemPK usuarioItemPK) {
		this.usuarioItemPK = usuarioItemPK;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		UsuarioItem other = (UsuarioItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
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
		return "UsuarioItem [item=" + item + ", usuario=" + usuario + "]";
	}

}