package app.sample.repository;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "USER")
public class User {

    public enum Gender {
        MALE, FEMALE
    }

    public enum Status {
        ACTIVE, INACTIVE, BLOCK
    }

    @Id
    @Column(name = "USER_ID", length = 100)
    private String userId;

    @Column(name = "USER_NAME", length = 50)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_GENDER", length = 20)
    private Gender userGender;

    @Column(name = "USER_AGE")
    private Integer userAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_STATUS", length = 20)
    private Status userStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(Gender userGender) {
        this.userGender = userGender;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Status getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Status userStatus) {
        this.userStatus = userStatus;
    }
}
