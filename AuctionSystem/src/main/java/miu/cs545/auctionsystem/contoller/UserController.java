package miu.cs545.auctionsystem.contoller;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
@CrossOrigin("http://localhost:3000/")
public class UserController {
    private final UserService userService;

    @PostMapping("/customer")
    public User addCustomer(@RequestBody User user) throws Exception {
        return userService.addCustomer(user);
    }

    @PostMapping("/seller")
    public User addSeller(@RequestBody User user) throws Exception {
        return userService.addCustomer(user);
    }

    @PutMapping("/verify/{email}/{code}")
    public void verifyAccount(@PathVariable String email, @PathVariable Integer code){
        userService.verifyAccount(email,code);
    }


}
