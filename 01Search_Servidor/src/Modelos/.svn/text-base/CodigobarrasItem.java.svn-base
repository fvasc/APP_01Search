package Modelos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "codigobarras_item", catalog = "01search", schema = "")
public class CodigobarrasItem implements Serializable {

	private static final long serialVersionUID = 1L;

	 @EmbeddedId
	 protected CodigobarrasItemPK codigobarrasItemPK;

	public CodigobarrasItemPK getCodigobarrasItemPK() {
		return codigobarrasItemPK;
	}

	public void setCodigobarrasItemPK(CodigobarrasItemPK codigobarrasItemPK) {
		this.codigobarrasItemPK = codigobarrasItemPK;
	}


}
