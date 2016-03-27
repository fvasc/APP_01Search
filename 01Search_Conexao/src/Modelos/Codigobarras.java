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

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Codigobarras implements Serializable {

	private static final long serialVersionUID = 1L;
	private String idCodigoBarras;
	private String nome;
	private String imagem;
	private Date dataCadastro;
	private Date dataLiberacao;
	private String descricao;
	private Marca idMarca;
	private Subdepartamento idSubdepartamento;
	private Integer qtdComprada;
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
