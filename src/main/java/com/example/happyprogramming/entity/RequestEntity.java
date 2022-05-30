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
@Table(name = "[request]")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private UserEntity menteeId;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private UserEntity mentorId;

    @Column(length = 1000)
    private String title;

    @Column
    private String budget;

    @Column(name = "delivery_time")
    private String deliveryTime;

    @Column(length = 3000)
    private String content;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "response_mess")
    private String responseMess;

    @Column
    private int status;

    @Column(name = "mentor_name")
    private String mentorName;

    @Column(name="is_received")
    private boolean isReceived;


    @ManyToMany
    @JoinTable(name = "request_skills",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> skills ;


    public String getTitle() {
        return title;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
