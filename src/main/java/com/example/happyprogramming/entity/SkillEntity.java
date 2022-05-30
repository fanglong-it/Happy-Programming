package com.example.happyprogramming.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@Entity
@Data
@Table(name = "skill")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "status")
    private String status;

    @Column(name = "img")
    private String img;

    @ManyToMany(mappedBy = "skills")
    private Set<CVEntity> cvEntitySet;

    @ManyToMany(mappedBy = "skills")
    private Set<RequestEntity>  requestEntities;

    public SkillEntity(Long id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public SkillEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }
}
