package Poker.domain;

import java.time.LocalDateTime;

public class User {

private int id;
private String login;
private String password;
private String email;
private double balance;
private LocalDateTime created;
private LocalDateTime updated;


    public User() {
        this.id = 0;
        this.login = "";
        this.password = "";
        this.email = "";
        this.balance = 0;
    }

    public User(int id, String login, String password, String email, double balance, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.created = created;
        this.updated = updated;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
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
