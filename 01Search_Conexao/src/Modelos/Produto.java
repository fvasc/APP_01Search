/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;


public class Produto implements Serializable {
	
    private static final long serialVersionUID = 1L;
    protected ProdutoPK produtoPK;
    private BigDecimal preco;
    private String descricao;
    private String localizacao;
    private Integer destaque;
    private Codigobarras codigobarras;
    private UnidadeMedida idUnidade;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.produtoPK == null && other.produtoPK != null) || (this.produtoPK != null && !this.produtoPK.equals(other.produtoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Produto[ produtoPK=" + produtoPK + " ]";
    }
    
}
