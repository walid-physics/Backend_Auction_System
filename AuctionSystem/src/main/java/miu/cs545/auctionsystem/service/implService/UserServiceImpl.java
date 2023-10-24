package miu.cs545.auctionsystem.service.implService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.exceptions.EmailAlreadyExistException;
import miu.cs545.auctionsystem.model.VerificationCode;
import miu.cs545.auctionsystem.repository.RoleRepo;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final VerificationCodeService verificationCodeService;

    private final JavaMailSender javaMailSender;

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private  Role customerRole;
    private Role sellerRole;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, JavaMailSender javaMailSender, VerificationCodeService verificationCodeService
    ,RoleRepo roleRepo,PasswordEncoder passwordEncoder){
        this.userRepo=userRepo;
        this.javaMailSender = javaMailSender;
        this.verificationCodeService=verificationCodeService;
        this.roleRepo=roleRepo;
        this.passwordEncoder=passwordEncoder;
        this.customerRole = roleRepo.findByName("customer");
        this.sellerRole= roleRepo.findByName("seller");


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
    public User addCustomer(User user) throws Exception {
        user.setRole(new ArrayList<>());
        user.getRoles().add (customerRole);

        return addUser(user);
    }

    @Override
    public User addSeller(User user) throws Exception {
        user.setRole(new ArrayList<>());
        user.getRoles().add(customerRole);
        user.getRoles().add(sellerRole);
        return addUser(user);
    }


    public User addUser(User user) throws Exception {
        if(user==null){
            throw new NullPointerException("User is null .");
        }
       // user.setRole(new ArrayList<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        String email = user.getEmail();
        //List<String> usersEmail = userRepo.findAll().stream().map(u -> u.getEmail()).collect(Collectors.toList());
        User userByEmail = userRepo.getUserByEmail(user.getEmail());
        if(userByEmail!=null)
        {
            throw new EmailAlreadyExistException("Email already exist");
        }


        Integer randomInteger = new Random().nextInt(9000) + 1000;
        System.out.println("\n"+randomInteger+"\n");

        System.setProperty("javax.net.debug", "all");

        sendEmail(email,"verification Code For Auction System " + "\n"+
                randomInteger.toString() + "\n"
               ,"checking Code");
        VerificationCode verificationCode = new VerificationCode(LocalTime.now(),randomInteger,user);
        user.setBalance(0.0);
        User savedUser = userRepo.save(user);
        verificationCodeService.saveVerificationCode(verificationCode);



        return savedUser;
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
        return userRepo.getUserByEmailAndActive(email,true);
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
        User user = userRepo.getUserByEmail(email);
        LocalTime registerTime = verificationCodeService.getVerificationCodeByUser(user).getRegistrationDate();
        if(LocalTime.now().isAfter(registerTime.plusMinutes(10))){
            System.out.println("code expired");
            return;
        }

        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user);
        if(verificationCode== null){
            throw new NullPointerException("the Verification Code row of this user is null");
        }
        if(verificationCode.getCode().equals(code)){
            user.setActive(true);
            userRepo.save(user);
            verificationCodeService.deleteVerificationCodeByUser(user);
            System.out.println("Verification");
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

/*
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
    }*/


}
