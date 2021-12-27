package com.project.users.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "This field is mandatory!")
    @Column(name = "user_name",unique = true, nullable = false, length = 20)
    private String userName;

    @Email(message = "E-mail is not in valid format.")
    @NotEmpty(message = "This field is mandatory!")
    @Column(nullable = false, unique = true, length = 45)

    private String email;

    @NotEmpty(message = "This field is mandatory!")
    @Column(nullable = false, length = 64)
    // The main use of this part of pattern is to add special chars set to password -> (?=.*[@#$%^&+=])
    //copy and paste it inside string below
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",
            message = "Please use at least one upper case, one digit with at least 8 characters")
    private String password;

    @NotEmpty(message = "Please retype password!")
    private String confirmPassword;

    public User(){}

    public User(Long id,String userName, String email, String password, String confirmPassword){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
