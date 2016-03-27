/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Nathan
 */
@Entity
@Table(name = "login", catalog = "01search", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Login"})})
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByIdLogin", query = "SELECT l FROM Login l WHERE l.idLogin = :idLogin"),
    @NamedQuery(name = "Login.findByLogin", query = "SELECT l FROM Login l WHERE l.login = :login"),
    @NamedQuery(name = "Login.findBySenha", query = "SELECT l FROM Login l WHERE l.senha = :senha"),
    @NamedQuery(name = "Login.findByPerfil", query = "SELECT l FROM Login l WHERE l.perfil = :perfil")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idLogin")
    private Integer idLogin;
    
    @Column(name = "Login")
    private String login;
    
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "perfil")
    private String perfil;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idLogin", fetch = FetchType.EAGER)
    private Estabelecimento estabelecimento;

    public Login() {
    }

    public Login(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Login(Integer idLogin, String login, String senha, String perfil) {
        this.idLogin = idLogin;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
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
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Login[ idLogin=" + idLogin + " ]";
    }
    
}
