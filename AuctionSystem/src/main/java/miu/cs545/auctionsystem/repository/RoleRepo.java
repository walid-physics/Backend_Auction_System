package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.Role;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}
