package miu.cs545.auctionsystem.service.implService;



import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepository.getUserByEmailAndActive (username,true);

          if (user ==null )
          {
              throw  new UsernameNotFoundException("Email: " + username + "Not Found!");
          }
        return user;
    }
}
