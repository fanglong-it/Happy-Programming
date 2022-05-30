package com.example.happyprogramming.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name="from_user")
    private UserEntity fromUser;

    @Column(name = "[content]",length = 2000)
    private String content;

    @Column(name = "created_date")
    private String createdDate;

    @Column
    private String link;

    @Column
    private int status;

}
