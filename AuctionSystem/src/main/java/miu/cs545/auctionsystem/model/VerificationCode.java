package miu.cs545.auctionsystem.model;

import jakarta.mail.internet.HeaderTokenizer;
import jakarta.persistence.*;
import org.springframework.security.core.token.Token;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalTime registrationDate;

    private Integer code;

    @OneToOne
    //@Column(name = "user_id")
    private User user;

    public VerificationCode(Integer id, LocalTime registrationDate, Integer code, User user) {
        this.user = user;
        this.id = id;
        this.registrationDate = registrationDate;
        this.code = code;
    }
    public VerificationCode(LocalTime registrationDate, Integer code, User user) {
        this.user = user;
        this.registrationDate = registrationDate;
        this.code = code;
    }
    public VerificationCode() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
