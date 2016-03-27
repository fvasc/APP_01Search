/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;


public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idComentario;
	private Codigobarras codigobarras;
	private Usuario usuario;
	private String texto;
	

	public Codigobarras getCodigobarras() {
		return codigobarras;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTexto() {
		return texto;
	}

	public Comentario() {
	}

	public Comentario(Integer idUsuario) {
		this.idComentario = idUsuario;
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idUsuario) {
		this.idComentario = idUsuario;
	}

	public void setCodigobarras(Codigobarras codigobarras) {
		this.codigobarras = codigobarras;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idComentario != null ? idComentario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Comentario)) {
			return false;
		}
		Comentario other = (Comentario) object;
		if ((this.idComentario == null && other.idComentario != null)
				|| (this.idComentario != null && !this.idComentario
						.equals(other.idComentario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelos.Cliente[ idUsuario=" + idComentario + " ]";
	}

}
