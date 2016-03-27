package Modelos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioItemPK  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "idItem", nullable = false)
	private Integer idItem;

	@Basic(optional = false)
	@Column(name = "idUsuario", nullable = false)
	private Integer idUsuario;

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
