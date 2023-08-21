package tmpAnton.signinservise;

import com.organisation.vacationplanning.database.entities.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tmpAnton.cookieservise.TokensUserBD;

import java.io.Serializable;

@Entity
@Table(name = "registeredUsersBD")
public class RegisteredUsersBD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "hash_password")
    private String hash_password;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "userRole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(mappedBy = "registeredUsersBD", cascade = CascadeType.ALL)
    private TokensUserBD tokensUserBD;
    @OneToOne(mappedBy = "registeredUsersBD", cascade = CascadeType.ALL)
    private Employee employee;

    public RegisteredUsersBD(String login, String email, String hash_password) {
        this.login = login;
        this.email = email;
        this.hash_password = hash_password;
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

    public TokensUserBD getTokensUserBD() {
        return tokensUserBD;
    }

    public void setTokensUserBD(TokensUserBD tokensUserBD) {
        this.tokensUserBD = tokensUserBD;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
