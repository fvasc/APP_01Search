package Modelos;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodigobarrasItemPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "idCodigoBarras", nullable = false)
	private String idCodigobarras;

	@Basic(optional = false)
	@Column(name = "idItem", nullable = false)
	private int idItem;

	public String getIdCodigobarras() {
		return idCodigobarras;
	}

	public void setIdCodigobarras(String idCodigobarras) {
		this.idCodigobarras = idCodigobarras;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

}
