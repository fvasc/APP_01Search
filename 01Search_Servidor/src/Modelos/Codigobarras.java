/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@Table(name = "codigobarras", catalog = "01search", schema = "")
@NamedQueries({
		@NamedQuery(name = "Codigobarras.findAll", query = "SELECT c FROM Codigobarras c"),
		@NamedQuery(name = "Codigobarras.findByIdCodigoBarras", query = "SELECT c FROM Codigobarras c WHERE c.idCodigoBarras = :idCodigoBarras"),
		@NamedQuery(name = "Codigobarras.findByNome", query = "SELECT c FROM Codigobarras c WHERE c.nome = :nome"),
		@NamedQuery(name = "Codigobarras.findByNomeLike", query = "SELECT c FROM Codigobarras c WHERE c.nome LIKE :nome"),
		@NamedQuery(name = "Codigobarras.findByIdSubdepartamento", query = "SELECT s FROM Codigobarras s WHERE s.idSubdepartamento.idSubdepartamento = :idSubdepartamento order by nome"),
		@NamedQuery(name = "Codigobarras.findByImagem", query = "SELECT c FROM Codigobarras c WHERE c.imagem = :imagem"),
		@NamedQuery(name = "Codigobarras.findByDataCadastro", query = "SELECT c FROM Codigobarras c WHERE c.dataCadastro = :dataCadastro"),
		@NamedQuery(name = "Codigobarras.findByDataLiberacao", query = "SELECT c FROM Codigobarras c WHERE c.dataLiberacao = :dataLiberacao"),
		@NamedQuery(name = "Codigobarras.findBySubDepartamento", query = "SELECT c FROM Codigobarras c WHERE c.idSubdepartamento = :idSubdepartamento"),
		@NamedQuery(name = "Codigobarras.findMarcaByIdCodigobarras", query = "SELECT c.idMarca FROM Codigobarras c WHERE c.idCodigoBarras = :idCodigoBarras"),
		@NamedQuery(name = "Codigobarras.findByDescricaoLike", query = "SELECT c FROM Codigobarras c WHERE c.descricao LIKE '%:descricao%'") })
public class Codigobarras implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "idCodigoBarras", nullable = false)
	private String idCodigoBarras;

	@Column(name = "nome")
	private String nome;

	@Column(name = "imagem")
	private String imagem;

	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "data_liberacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLiberacao;

	@Column(name = "descricao")
	private String descricao;

	@JoinColumn(name = "idMarca", referencedColumnName = "idMarca", nullable = false)
	// @JoinColumn(name = "idMarca", referencedColumnName = "idMarca")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	// @ManyToOne(fetch = FetchType.LAZY)
	private Marca idMarca;

	@JoinColumn(name = "idSubdepartamento", referencedColumnName = "idSubdepartamento", nullable = false)
	// @JoinColumn(name = "idSubdepartamento", referencedColumnName =
	// "idSubdepartamento")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	// @ManyToOne(fetch = FetchType.LAZY)
	private Subdepartamento idSubdepartamento;

	@Transient
	private Integer qtdComprada = 0;

	@Transient
	private BigDecimal precoCorrente;

	public Codigobarras() {
	}

	public Codigobarras(String idCodigoBarras) {
		this.idCodigoBarras = idCodigoBarras;
	}

	public BigDecimal getPrecoCorrente() {
		return precoCorrente;
	}

	public void setPrecoCorrente(BigDecimal precoCorrente) {
		this.precoCorrente = precoCorrente;
	}

	public Integer getQtdComprada() {
		return qtdComprada;
	}

	public void setQtdComprada(Integer qtdComprada) {
		this.qtdComprada = qtdComprada;
	}

	public String getIdCodigoBarras() {
		return idCodigoBarras;
	}

	public void setIdCodigoBarras(String idCodigoBarras) {
		this.idCodigoBarras = idCodigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Marca getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Marca idMarca) {
		this.idMarca = idMarca;
	}

	public Subdepartamento getIdSubdepartamento() {
		return idSubdepartamento;
	}

	public void setIdSubdepartamento(Subdepartamento idSubdepartamento) {
		this.idSubdepartamento = idSubdepartamento;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCodigoBarras != null ? idCodigoBarras.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Codigobarras)) {
			return false;
		}
		Codigobarras other = (Codigobarras) object;
		if ((this.idCodigoBarras == null && other.idCodigoBarras != null)
				|| (this.idCodigoBarras != null && !this.idCodigoBarras
						.equals(other.idCodigoBarras))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
