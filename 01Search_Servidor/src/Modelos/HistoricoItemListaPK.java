package Modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Embeddable
public class HistoricoItemListaPK implements Serializable {

	@Basic(optional = false)
	@Column(name = "idLista", nullable = false)
	private int idLista;
	@Basic(optional = false)
	@Column(name = "idItem", nullable = false)
	private int idItem;
	@Basic(optional = false)
	@Column(name = "idEstabelecimento", nullable = false)
	private int idEstabelecimento;
	@Basic(optional = false)
	@Column(name = "compraData", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date compraData;
	@Basic(optional = false)
	@Column(name = "versaoCompra", nullable = false)
	private Integer versaoCompra;

	public HistoricoItemListaPK() {
	}

	public Integer getVersaoCompra() {
		return versaoCompra;
	}

	public void setVersaoCompra(Integer versao_compra) {
		this.versaoCompra = versao_compra;
	}

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(int idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public Date getCompraData() {
		return compraData;
	}

	public void setCompraData(Date compraData) {
		this.compraData = compraData;
	}
}
