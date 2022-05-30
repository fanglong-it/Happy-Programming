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
@Table(name="conversation_reply")
public class ConversationReplyEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reply_user")
    private UserEntity replyUser;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conversationId;

    @Column
    private String context;

    @Column
    private String time;

    @Column
    private int status;


}
