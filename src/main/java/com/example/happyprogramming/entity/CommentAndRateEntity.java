package com.example.happyprogramming.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_rate")
public class CommentAndRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private UserEntity mentee;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private CVEntity mentor;

    @Column(name = "comment",length = 2000)
    private String comment;

    @Column
    private int rate;

    @Column(name = "created_date")
    private String createdDate;


}
