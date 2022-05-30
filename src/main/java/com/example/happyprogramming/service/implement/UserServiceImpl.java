package com.example.happyprogramming.service.implement;
import com.example.happyprogramming.entity.RoleEntity;
import com.example.happyprogramming.entity.UserEntity;
import com.example.happyprogramming.repository.RoleRepository;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.NotificationService;
import com.example.happyprogramming.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NotificationService notificationService;

    public UserServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void register(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        HashSet<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_MENTEE"));
        user.setRoles(roles);
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        userRepo.save(user);
        sendVerificationEmail(user, siteURL);
    }

    @Override
    public void sendVerificationEmail(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String toAddress = user.getEmail();
        String fromAddress = "hieunthe150001@fpt.edu.vn";
        String senderName = "Happry-Programming";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Happy Programming.";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public boolean verify(String verificationCode) {
        UserEntity user = userRepo.findByVerificationCode(verificationCode);

        if (user == null) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepo.save(user);
            notificationService.welcomeNotification(user);
            return true;
        }
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.findByEmail(email) == null ? true : false;
    }

    @Override
    public void changePassword(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        userRepo.save(user);
        sendEmailChangePassword(user, siteURL);
    }

    @Override
    public void sendEmailChangePassword(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String toAddress = user.getEmail();
        String fromAddress = "hieunthe150001@fpt.edu.vn";
        String senderName = "Happry-Programming";
        String subject = "Please change your password";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to change your password:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Happy Programming.";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteURL + "/do-change-password?code=" + user.getVerificationCode() + "&email=" + user.getEmail();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public void doResetPassword(String email, String newPassword) {
        UserEntity user = userRepo.findByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    @Override
    public boolean doChangePassword(String newpPassword, String oldPassword, UserEntity user) {
        UserEntity currentUser = userRepo.findByEmail(user.getEmail());
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newpPassword));
            userRepo.save(currentUser);
            return true;
        } else
            return false;

    }

    @Override
    public UserEntity saveAvatar(MultipartFile avatar, String email) throws IOException {
        UserEntity user = userRepo.findByEmail(email);
        Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
        Path staticPath = Paths.get("src\\main\\resources\\static");
        Path imagePath = Paths.get("img");
        String pathAvatar = avatar.getOriginalFilename().toString();
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(avatar.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(avatar.getBytes());
        }
        user.setAvatar("/img/" + pathAvatar);
        userRepo.save(user);
        return user;

    }

    @Override
    public int totalUsers() {
        int count = roleRepository.findByName("ROLE_MENTEE").getUsers().size()
                    +roleRepository.findByName("ROLE_MENTOR").getUsers().size();
        System.out.println(count);
        return count;
    }


    @Override
    public ArrayList<UserEntity> getUsers() {
        ArrayList<UserEntity> users = new ArrayList<>();
        RoleEntity role = roleRepository.getById(1L);
        users = userRepo.getUserEntityByRolesEquals(role);
        return users;
    }
}