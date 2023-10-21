package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.Role;
import org.springframework.stereotype.Service;


public interface RoleService {
    public Role getRoleByName(String name);
}
