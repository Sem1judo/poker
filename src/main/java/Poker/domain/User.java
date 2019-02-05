package Poker.domain;

import java.time.LocalDate;

public class User {

private int id;
private String login;
private String password;
private String email;
private double balance;
private LocalDate created;
private LocalDate updated;


    public User() {
        this.id = 0;
        this.login = "";
        this.password = "";
        this.email = "";
        this.balance = 0;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
