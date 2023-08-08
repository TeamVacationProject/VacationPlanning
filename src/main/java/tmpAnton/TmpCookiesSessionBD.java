package tmpAnton;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "tmpCookiesSessionBD")
public class TmpCookiesSessionBD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "COOKIES")
    private String cookies;
    @Column(name = "TIME_START")
    private String timeStart;
    @Column(name = "TIME_END")
    private String timeEnd;

//    @OneToOne(mappedBy = "tmpCookiesSessionBD")
//    private TmpEmployeePassBD tmpEmployeePassBD;

    public TmpCookiesSessionBD() {
    }

    public TmpCookiesSessionBD(int id, String login, String cookies, String timeStart, String timeEnd) {
        this.id = id;
        this.login = login;
        this.cookies = cookies;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public TmpCookiesSessionBD(String login, String cookies, String timeStart, String timeEnd) {
        this.login = login;
        this.cookies = cookies;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "TmpCookiesSessionBD{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", cookies='" + cookies + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\''
//                ", tmpEmployeePassBD=" + tmpEmployeePassBD +
//                '}';
        ;
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

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
