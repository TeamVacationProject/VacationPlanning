package tmpAnton.signinservise;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "registeredUsersBD")
public class RegisteredUsersBD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "hash_password")
    private String hash_password;
    @Column(name = "email")
    private String email;
    @Column(name = "userRole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public RegisteredUsersBD(String login, String hash_password, String email) {
        this.login = login;
        this.hash_password = hash_password;
        this.email = email;
        userRole = UserRole.USER;
    }

    public RegisteredUsersBD(String login, String hash_password) {
        this.login = login;
        this.hash_password = hash_password;
    }

    public RegisteredUsersBD() {

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

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
