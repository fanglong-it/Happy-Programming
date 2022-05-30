package com.example.happyprogramming.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="conversation")
public class ConversationEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_1")
    private UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "user_2")
    private UserEntity user2;

    @Column
    private int status;

    @Column
    private String time;

    @OneToMany(mappedBy = "conversationId")
    private Set<ConversationReplyEntity> conversationReply;

}
