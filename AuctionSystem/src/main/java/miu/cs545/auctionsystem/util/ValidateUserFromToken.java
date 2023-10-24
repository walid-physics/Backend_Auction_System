package miu.cs545.auctionsystem.util;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateUserFromToken {

    private final UserService userService;

    public User getUserFromAuthentication() throws Exception {
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadUserByEmail(userName);
        if(user== null)
            throw  new Exception("User not found.");
        return  user;
    }
}
