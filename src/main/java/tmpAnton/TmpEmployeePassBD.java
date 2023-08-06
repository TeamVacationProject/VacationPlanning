package tmpAnton;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tmpEmployeePassBD")
public class TmpEmployeePassBD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "HASH_PASSWORD")
    private String HASH_PASSWORD;
    @Column(name = "E_MAIL")
    private String E_MAIL;

    public TmpEmployeePassBD() {
    }

    public TmpEmployeePassBD(int id, String login, String hashPassword, String email) {
        this.id = id;
        this.login = login;
        this.HASH_PASSWORD = hashPassword;
        this.E_MAIL = email;
    }

    @Override
    public String toString() {
        return "TmpEmployeePassBD{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", HASH_PASSWORD='" + HASH_PASSWORD + '\'' +
                ", E_MAIL='" + E_MAIL + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return HASH_PASSWORD;
    }

    public void setHashPassword(String hashPassword) {
        this.HASH_PASSWORD = hashPassword;
    }

    public String getEmail() {
        return E_MAIL;
    }

    public void setEmail(String email) {
        this.E_MAIL = email;
    }
}
