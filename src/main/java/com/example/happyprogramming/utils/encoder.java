package com.example.happyprogramming.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.beans.Encoder;

public class encoder{
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPass="Cunplong115@";
        String newPass=bCryptPasswordEncoder.encode(rawPass);
        System.out.println(""+newPass);
    }
}
