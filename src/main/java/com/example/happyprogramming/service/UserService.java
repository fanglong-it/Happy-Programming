package com.example.happyprogramming.service;

import com.example.happyprogramming.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@Service
public interface UserService {
    UserEntity findByEmail(String email);
    void register(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void sendVerificationEmail(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    boolean verify(String verificationCode);

    boolean checkEmail(String email);

    void changePassword(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void sendEmailChangePassword(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void doResetPassword(String email,String newPassword);

    boolean doChangePassword(String newPassword,String oldPassword, UserEntity user);

    UserEntity saveAvatar(MultipartFile avatar, String email) throws IOException;

    int totalUsers();

    ArrayList<UserEntity> getUsers();

}
