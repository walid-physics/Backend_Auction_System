package miu.cs545.auctionsystem.contoller;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAll()
    {
       return userService.getUsers();
    }
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
