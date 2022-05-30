package com.example.happyprogramming.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Admin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[User]")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
//    @Column(name = "id_user", nullable = false)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone", nullable = true)
    private String phone;
    @Column(name = "DoB", nullable = true)
    private String DoB;
    @Column(name = "sex", nullable = true)
    private boolean sex;
    @Column(name = "avatar", nullable = true)
    private String avatar;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), //ở class nào thì thì joinColumn này sẽ là khóa chính của table mang tên class đó
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;


    @OneToMany(mappedBy = "menteeId")
    private Set<RequestEntity> requestOfMentee;

    @OneToMany(mappedBy = "mentorId")
    private Set<RequestEntity> requestForMentor;

    @OneToMany(mappedBy = "user1")
    private Set<ConversationEntity> user1;

    @OneToMany(mappedBy = "user2")
    private Set<ConversationEntity> user2;

    @OneToMany(mappedBy = "replyUser")
    private Set<ConversationReplyEntity> replyUser;

    @OneToMany(mappedBy = "mentee")
    private Set<CommentAndRateEntity> reviewOfMentee;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private CVEntity mentorId;

    @OneToMany(mappedBy = "users")
    private Set<NotificationEntity> notification;

    @OneToMany(mappedBy = "fromUser")
    private Set<NotificationEntity> notificationFromUser;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;

    }
}
