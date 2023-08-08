package tmpAnton;

import jakarta.persistence.*;

import java.io.Serializable;

//ToDo Определить структуру БД
@Entity
@Table(name = "tmpEmployeePassBD")
public class TmpEmployeePassBD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "E_MAIL")
    private String E_MAIL;
    @Column(name = "HASH_PASSWORD")
    private String HASH_PASSWORD;
    @Column(name = "LOGIN")
    private String login;

//    @OneToOne //ToDo проверить есть ли связь на таблицу tmpCookiesSessionBD
//    @JoinColumn(name = "id")
//    private TmpCookiesSessionBD tmpCookiesSessionBD;

    public TmpEmployeePassBD() {
    }

    public TmpEmployeePassBD(int id, String E_MAIL, String HASH_PASSWORD, String login) {
        this.id = id;
        this.E_MAIL = E_MAIL;
        this.HASH_PASSWORD = HASH_PASSWORD;
        this.login = login;
    }

//    http://localhost:8080/VacationPlanning_war_exploded/test?id=4&E_MAIL=ee&HASH_PASSWORD=ff&LOGIN=ggg

    public TmpEmployeePassBD(String E_MAIL, String HASH_PASSWORD, String login) {
        this.E_MAIL = E_MAIL;
        this.HASH_PASSWORD = HASH_PASSWORD;
        this.login = login;
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

    public void setEmail(String E_MAIL) {
        this.E_MAIL = E_MAIL;
    }
}
