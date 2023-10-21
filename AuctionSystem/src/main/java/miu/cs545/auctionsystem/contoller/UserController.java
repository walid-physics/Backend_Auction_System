package miu.cs545.auctionsystem.contoller;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
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


}
