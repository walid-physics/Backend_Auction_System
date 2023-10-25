package miu.cs545.auctionsystem.contoller;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.model.api.AuthRequest;
import miu.cs545.auctionsystem.model.api.AuthResponse;
import miu.cs545.auctionsystem.service.UserService;
import miu.cs545.auctionsystem.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public List<User> getAll()
    {
       return userService.getUsers();
    }
    @PostMapping("/seller")
    public User addSeller(@RequestBody User user) throws Exception {
        return userService.addCustomer(user);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody User user) throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(user.getEmail());
        authRequest.setPassword(user.getPassword());
        User user1 = userService.addCustomer(user);
        try {

            System.out.println("...........\n"+authRequest +"\n .................");


            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            System.out.println("...........\n"+ authentication.getPrincipal()+"\n .................");
            User u = (User) authentication.getPrincipal();


            System.out.println(u.getEmail());
            String token = jwtTokenUtil.generateToken(u);
            //return token
            return ResponseEntity.ok( new AuthResponse(u.getEmail(),u.getName(),u.getAuthoritiesList().contains("seller") ,token));
        } catch (BadCredentialsException e){
            System.out.println("User Not Found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/verify/{email}/{code}")
    public void verifyAccount(@PathVariable String email, @PathVariable Integer code){
        userService.verifyAccount(email,code);
    }


}
