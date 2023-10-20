package miu.cs545.auctionsystem.contoller;

import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.RoleService;
import miu.cs545.auctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService){
        this.userService=userService;
        this.roleService=roleService;
    }

    @PostMapping("/verify_customer")
    public User verifyCustomer(String email){
        User user = userService.loadUserByEmail(email);
        user.addRoles(roleService.getRoleByName("USER"));
        return userService.updateUser(user);
    }
    @PostMapping("/verify_seller")
    public User verifySeller(String email){
        User user = userService.loadUserByEmail(email);
        user.addRoles(roleService.getRoleByName("SELLER"));
        return userService.updateUser(user);
    }






}
