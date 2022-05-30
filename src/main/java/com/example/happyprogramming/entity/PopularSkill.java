package com.example.happyprogramming.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PopularSkill implements Comparable<PopularSkill> {
    private  Long skillId;
    private String skillName;
    private String skillImg;
    private int count;

    @Override
    public int compareTo(PopularSkill o) {
        return this.count<=o.count?1:-1;
    }
}
