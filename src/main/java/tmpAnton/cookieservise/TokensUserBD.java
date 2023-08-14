package tmpAnton.cookieservise;

import jakarta.persistence.*;
import tmpAnton.signinservise.RegisteredUsersBD;
import tmpAnton.signinservise.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tokensUserBD")
public class TokensUserBD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "expires_at")
    private LocalDateTime dateTimeExpiresAt;

    @OneToOne
    @JoinColumn(name = "registered_user_id")
    private RegisteredUsersBD registeredUsersBD;

    public TokensUserBD(String login) {
        this.login = login;
        uuid = UUID.randomUUID().toString();
        dateTimeExpiresAt = LocalDateTime.now().plusHours(1);
    }

    public TokensUserBD() {

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDateTimeExpiresAt() {
        return dateTimeExpiresAt;
    }

    public void setDateTimeExpiresAt(LocalDateTime dateTimeExpiresAt) {
        this.dateTimeExpiresAt = dateTimeExpiresAt;
    }

    public RegisteredUsersBD getRegisteredUsersBD() {
        return registeredUsersBD;
    }

    public void setRegisteredUsersBD(RegisteredUsersBD registeredUsersBD) {
        this.registeredUsersBD = registeredUsersBD;
    }
}
