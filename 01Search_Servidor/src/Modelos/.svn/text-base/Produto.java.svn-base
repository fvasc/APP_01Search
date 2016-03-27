/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 
 * @author Nathan
 */
@Entity
@Table(name = "produto", catalog = "01search", schema = "")
@NamedQueries({
		@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
		@NamedQuery(name = "Produto.findByIdEstabelecimento", query = "SELECT p FROM Produto p WHERE p.produtoPK.idEstabelecimento = :idEstabelecimento"),
		@NamedQuery(name = "Produto.findByDestaque", query = "SELECT p FROM Produto p WHERE p.produtoPK.idEstabelecimento = :idEstabelecimento and p.destaque <> 0"),
		@NamedQuery(name = "Produto.findByIdCodigoBarras", query = "SELECT p FROM Produto p WHERE p.produtoPK.idCodigoBarras = :idCodigoBarras"),
		@NamedQuery(name = "Produto.findByIdProdutoPK", query = "SELECT p FROM Produto p WHERE p.produtoPK = :produtoPK"),
		@NamedQuery(name = "Produto.findByPreco", query = "SELECT p FROM Produto p WHERE p.preco = :preco"),
		@NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao"),
		@NamedQuery(name = "Produto.findByLocalizacao", query = "SELECT p FROM Produto p WHERE p.localizacao = :localizacao") })
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected ProdutoPK produtoPK;

	@Column(name = "preco")
	private BigDecimal preco;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "localizacao")
	private String localizacao;

	@Column(name = "destaque")
	private Integer destaque;

	@JoinColumn(name = "idCodigoBarras", referencedColumnName = "idCodigoBarras", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Codigobarras codigobarras;

	@XStreamOmitField
	@JoinColumn(name = "idUnidade", referencedColumnName = "idUnidade")
	// @ManyToOne(optional = true, fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	private UnidadeMedida idUnidade;

	@XStreamOmitField
	@JoinColumn(name = "idEstabelecimento", referencedColumnName = "idEstabelecimento", insertable = false, updatable = false)
	// @ManyToOne(optional = false, fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	private Estabelecimento estabelecimento;

	public Produto() {
	}

	public Produto(ProdutoPK produtoPK) {
		this.produtoPK = produtoPK;
	}

	public Produto(int idEstabelecimento, String idCodigoBarras) {
		this.produtoPK = new ProdutoPK(idEstabelecimento, idCodigoBarras);
	}

	public ProdutoPK getProdutoPK() {
		return produtoPK;
	}

	public void setProdutoPK(ProdutoPK produtoPK) {
		this.produtoPK = produtoPK;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Integer getDestaque() {
		return destaque;
	}

	public void setDestaque(Integer destaque) {
		this.destaque = destaque;
	}

	public Codigobarras getCodigobarras() {
		return codigobarras;
	}

	public void setCodigobarras(Codigobarras codigobarras) {
		this.codigobarras = codigobarras;
	}

	public UnidadeMedida getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(UnidadeMedida idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (produtoPK != null ? produtoPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Produto)) {
			return false;
		}
		Produto other = (Produto) object;
		if ((this.produtoPK == null && other.produtoPK != null)
				|| (this.produtoPK != null && !this.produtoPK
						.equals(other.produtoPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelos.Produto[ produtoPK=" + produtoPK + " ]";
	}

}
