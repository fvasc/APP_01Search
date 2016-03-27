/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;


public class Lista implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private Integer idLista;
    private String nome;
	private Boolean ativo;
    private Integer versao;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Integer versaoCompra;
    private Integer qtd_usuarios;
    private Integer qtd_itens;

	public Lista() {
    }

    public Lista(Integer idLista) {
        this.idLista = idLista;
    }


	public Integer getQtd_usuarios() {
		return qtd_usuarios;
	}

	public void setQtd_usuarios(Integer qtd_usuarios) {
		this.qtd_usuarios = qtd_usuarios;
	}

	public Integer getQtd_itens() {
		return qtd_itens;
	}

	public void setQtd_itens(Integer qtd_itens) {
		this.qtd_itens = qtd_itens;
	}

	public Integer getVersaoCompra() {
		return versaoCompra;
	}

	public void setVersaoCompra(Integer versaoCompra) {
		this.versaoCompra = versaoCompra;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLista != null ? idLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.idLista == null && other.idLista != null) || (this.idLista != null && !this.idLista.equals(other.idLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	 return nome;
    }
    
}
