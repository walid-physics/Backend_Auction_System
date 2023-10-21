package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService   {



    public List<User> getUsers();
    public User getUserById(Integer id);

    public void deleteUserById(Integer id);

    public User addCustomer(User user) throws Exception;
    public User addSeller(User user) throws Exception;
    public User updateUser(User user);
    public User loadUserByEmail(String email);

    public void sendEmail(String email, String message, String subject);

    public void resendCode(String email);

    public void verifyAccount(String email, Integer code);
    public void resendCode(User user);

}
