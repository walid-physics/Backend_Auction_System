package miu.cs545.auctionsystem.contoller;



import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.model.api.AuthRequest;
import miu.cs545.auctionsystem.model.api.AuthResponse;
import miu.cs545.auctionsystem.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @PostMapping("/customer/login")
    public ResponseEntity<?> customerLogin(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            User u = (User) authentication.getPrincipal();
            System.out.println(u.getEmail());
            if(!u.getAuthoritiesList().contains("customer"))
            {
                throw  new BadCredentialsException("UNAUTHORIZED SELLER");
            }
            System.out.println(u.getEmail());
            String token = jwtTokenUtil.generateToken(u);
            //return token
            return ResponseEntity.ok(new AuthResponse(u.getEmail(), token));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //failed -> throw unauthorized
    }

    @PostMapping("/seller/login")
    public ResponseEntity<?> SellerLogin(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            User u = (User) authentication.getPrincipal();
            if(!u.getAuthoritiesList().contains("seller"))
            {
                throw  new BadCredentialsException("UNAUTHORIZED SELLER");
            }
            String token = jwtTokenUtil.generateToken(u);
            //return token
            return ResponseEntity.ok(new AuthResponse(u.getEmail(), token));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //failed -> throw unauthorized
    }
}