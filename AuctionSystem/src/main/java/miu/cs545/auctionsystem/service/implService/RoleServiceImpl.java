package miu.cs545.auctionsystem.service.implService;

import miu.cs545.auctionsystem.model.Role;
import miu.cs545.auctionsystem.repository.RoleRepo;
import miu.cs545.auctionsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo){
        this.roleRepo=roleRepo;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepo.findByName(name);
    }
}
