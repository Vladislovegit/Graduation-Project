package model;

import javax.persistence.*;

@Entity
@Table(name = "CREDENTIALS", catalog = "bot")
public class Credentials {

    private Long id;
    private String url;
    private String login;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credentials_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
