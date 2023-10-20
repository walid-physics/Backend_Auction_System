package miu.cs545.auctionsystem.service.implService;

import miu.cs545.auctionsystem.exceptions.EmailAlreadyExistException;
import miu.cs545.auctionsystem.model.VerificationCode;
import miu.cs545.auctionsystem.service.UserService;
import miu.cs545.auctionsystem.model.Role;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.repository.UserRepo;
import miu.cs545.auctionsystem.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private VerificationCodeService verificationCodeService;

    private JavaMailSender javaMailSender;

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, JavaMailSender javaMailSender, VerificationCodeService verificationCodeService){
        this.userRepo=userRepo;
        this.javaMailSender = javaMailSender;
        this.verificationCodeService=verificationCodeService;
    }
    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public User addUser(User user) throws Exception {
        if(user==null){
            throw new NullPointerException("User is null i can't save them.");
        }
        user.setRole(new ArrayList<>());
        String email = user.getEmail();
        List<String> usersEmail = userRepo.findAll().stream().map(u -> u.getEmail()).collect(Collectors.toList());

        for(String em: usersEmail){
            if(usersEmail.equals(user)){
                throw new EmailAlreadyExistException("Email already exist");
            }
        }

        Integer randomInteger = new Random().nextInt(9000) + 1000;
        System.out.println("\n"+randomInteger+"\n");

        System.setProperty("javax.net.debug", "all");

        sendEmail(email,randomInteger.toString() + "\n"+
                "Click <a href=\"https://http://localhost:8080/verify/"+ user.getEmail()+"/"+ randomInteger+" \">here</a> to visit Example.com.","checking Code");
        VerificationCode verificationCode = new VerificationCode(LocalTime.now(),randomInteger,user);

        User user1 = userRepo.save(user);
        verificationCodeService.saveVerificationCode(verificationCode);
        user.setActive(false);


        return user1;
    }

    @Override
    public User updateUser(User user) {

        //do it the proper way
   /*     User user1 = getUserById(user.getId());
        user1.setRole(user.getRoles());
        user1.*/
        return userRepo.save(user);
    }

    @Override
    public User loadUserByEmail(String email){
        return userRepo.getUserByEmail(email);
    }

    @Override
    @Async
    public void sendEmail(String email, String message, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setTo(email);
        //simpleMailMessage.setFrom("chess.pmp@gmail.com");
        // Add this line before sending the email
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void resendCode(String email) {
        User user = loadUserByEmail(email);
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user);
        Integer randomInteger = new Random().nextInt(9000) + 1000;
        sendEmail(email,randomInteger.toString() + "\n"+
                "Click <a href=\"https://http://localhost:8080/verify/"+ user.getEmail()+"/"
                + randomInteger+" \">here</a> to visit Example.com.","checking Code");
        verificationCode.setCode(randomInteger);
        verificationCode.setRegistrationDate(LocalTime.now());
        verificationCodeService.saveVerificationCode(verificationCode);

    }

    @Override
    public void verifyAccount(String email, Integer code) {
        User user = loadUserByEmail(email);
        LocalTime registerTime = verificationCodeService.getVerificationCodeByUser(user).getRegistrationDate();
        if(LocalTime.now().isAfter(registerTime.plusMinutes(10))){
            System.out.println("code expired");
            return;
        }

        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user);
        if(verificationCode== null){
            throw new NullPointerException("the VerificationCode row of this user is null");
        }
        if(verificationCode.getCode().equals(code)){
            user.setActive(true);
            userRepo.save(user);
            verificationCodeService.deleteVerificationCodeByUser(user);
        }
        else{
            System.out.println("Incorrect code");
        }
    }

    @Override
    public void resendCode(User user) {

    }

/*    @Override
    public void resendCode(User user) {
        Integer randomInteger = new Random().nextInt(9000) + 1000;

        sendEmail(user.getEmail(), randomInteger.toString() + "\n"+
                "Click <a href=\"https://http://localhost:8080/verify/"+ user.getEmail()+"/"+ randomInteger+" \">here</a> to visit Example.com.","checking Code");
        VerificationCode verificationCode = new VerificationCode(LocalTime.now(),randomInteger,user);

        verificationCodeService.updateVerificationCode(verificationCode);

    }*/


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = loadUserByEmail(email);
        if(user != null){
            System.out.println(
                    new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword()
                    , mapRolesToAuthorities(user.getRoles())));
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword()
            , mapRolesToAuthorities(user.getRoles()));
        }
        return null;
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
