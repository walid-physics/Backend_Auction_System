package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository< User, Integer> {
    public User getUserByEmail(String email);

}
