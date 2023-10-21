package miu.cs545.auctionsystem.contoller;

import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.UserService;
import miu.cs545.auctionsystem.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class TestController {


    private UserService userService;
    private VerificationCodeService verificationCodeService;

    @Autowired
    public TestController(UserService userService, VerificationCodeService verificationCodeService){
        this.userService = userService;
        this.verificationCodeService=verificationCodeService;
    }


    @GetMapping("")
    public String welcome(){
        return "Hello";
    }

    @GetMapping("/hello")
    public String welcome2(){
        return "Hello";
    }

    @PostMapping("/send")
    public void sendEmail(){
        userService.sendEmail("chess.pmp@gmail.com","hi","welcome");
    }

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) throws Exception {
        //return userService.addUser(user);
        return null;
    }

    @PutMapping("/verify/{email}/{code}")
    public void verifyAccount(@PathVariable String email, @PathVariable Integer code){
        //LocalTime registerTime = verificationCodeService.getVerificationCodeByUser()
        userService.verifyAccount(email,code);
    }

    @PutMapping("/verify/{email}")
    public void resendCode(@PathVariable String email){
        //LocalTime registerTime = verificationCodeService.getVerificationCodeByUser()
        userService.resendCode(email);
    }


    @PutMapping("/resend")
    public void resendCode(@RequestBody User user){
        userService.resendCode(user);
    }
}
