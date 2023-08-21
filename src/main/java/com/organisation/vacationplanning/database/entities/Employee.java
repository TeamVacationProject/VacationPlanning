package com.organisation.vacationplanning.database.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tmpAnton.signinservise.RegisteredUsersBD;
import tmpAnton.signinservise.UserRole;

import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "login")
    private String login;
    @Column(name = "company")
    private String company;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registered_user_id")
    private RegisteredUsersBD registeredUsersBD;

    public Employee(Long id, String surname, String firstName, String patronymic) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public Employee(String surname, String firstName, String patronymic, String login, String company, UserRole userRole) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.login = login;
        this.company = company;
        this.userRole = userRole;
    }

    public Employee() {
    }

    public Employee(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public RegisteredUsersBD getRegisteredUsersBD() {
        return registeredUsersBD;
    }

    public void setRegisteredUsersBD(RegisteredUsersBD registeredUsersBD) {
        this.registeredUsersBD = registeredUsersBD;
    }
}
