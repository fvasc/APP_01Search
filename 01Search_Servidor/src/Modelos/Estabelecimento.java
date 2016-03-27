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
@Table(name = "estabelecimento", catalog = "01search", schema = "", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "idLogin" }),
		@UniqueConstraint(columnNames = { "cnpj" }) })
@NamedQueries({
		@NamedQuery(name = "Estabelecimento.findAll", query = "SELECT e FROM Estabelecimento e"),
		@NamedQuery(name = "Estabelecimento.findByIdEstabelecimento", query = "SELECT e FROM Estabelecimento e WHERE e.idEstabelecimento = :idEstabelecimento"),
		@NamedQuery(name = "Estabelecimento.findByCnpj", query = "SELECT e FROM Estabelecimento e WHERE e.cnpj = :cnpj"),
		@NamedQuery(name = "Estabelecimento.findByNome", query = "SELECT e FROM Estabelecimento e WHERE e.nome = :nome"),
		@NamedQuery(name = "Estabelecimento.findByDescricao", query = "SELECT e FROM Estabelecimento e WHERE e.descricao = :descricao") })
public class Estabelecimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstabelecimento")
	private Integer idEstabelecimento;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "logo")
	private String logo;

	@XStreamOmitField
	@JoinColumn(name = "idLogin", referencedColumnName = "idLogin")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Login idLogin;

	@Transient
	private BigDecimal precoListaCorrente;

	public Estabelecimento() {
	}

	public Estabelecimento(Integer idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public Estabelecimento(Integer idEstabelecimento, String cnpj) {
		this.idEstabelecimento = idEstabelecimento;
		this.cnpj = cnpj;
	}

	public BigDecimal getPrecoListaCorrente() {
		return precoListaCorrente;
	}

	public void setPrecoListaCorrente(BigDecimal precoListaCorrente) {
		this.precoListaCorrente = precoListaCorrente;
	}

	public Integer getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Integer idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Login getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Login idLogin) {
		this.idLogin = idLogin;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEstabelecimento != null ? idEstabelecimento.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Estabelecimento)) {
			return false;
		}
		Estabelecimento other = (Estabelecimento) object;
		if ((this.idEstabelecimento == null && other.idEstabelecimento != null)
				|| (this.idEstabelecimento != null && !this.idEstabelecimento
						.equals(other.idEstabelecimento))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
