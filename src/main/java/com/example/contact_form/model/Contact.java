package com.example.contact_form.model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Table(name="contact")
public class Contact {

    @Id
//    @GeneratedValue(strategy = GenerationType)
    @Column(name="id")
    private long id;

    @Column(name="title")
    @NotEmpty(message = "タイトルを入力してください。")
    private String title;

    @Column(name="content")
    @NotEmpty(message = "内容を入力してください")
    private String content;

    @Column(name="us_name")
    @NotEmpty(message="名前を入力してください")
    private String userName;

    @Column(name="us_address")
    private String userAddress;

    @Column(name="tell")
    private String tel;

    @Column(name="mail_address")
    @NotEmpty(message="メールアドレスを入力してください")
    private String mailAddress;

    @Column(name="status")
    private String status;

    @Column(name="create_at", nullable = true)
    private Timestamp createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
