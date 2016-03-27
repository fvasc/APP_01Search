package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "historico_Item_Lista", catalog = "01search", schema = "")
public class HistoricoItemLista implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected HistoricoItemListaPK historicoItemListaPK;

	@Column(name = "quantidade")
	private Integer quantidade;

	public HistoricoItemLista() {
	}

	public HistoricoItemLista(HistoricoItemListaPK historicoItemListaPK) {
		this.historicoItemListaPK = historicoItemListaPK;
	}

	public HistoricoItemListaPK getHistoricoItemListaPK() {
		return historicoItemListaPK;
	}

	public void setHistoricoItemListaPK(
			HistoricoItemListaPK historicoItemListaPK) {
		this.historicoItemListaPK = historicoItemListaPK;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
