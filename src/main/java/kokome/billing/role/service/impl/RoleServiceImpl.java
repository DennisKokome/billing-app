package kokome.billing.role.service.impl;

import kokome.billing.role.service.RoleService;
import kokome.billing.role.entity.Role;
import kokome.billing.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by oaitse on Sep, 10, 2021
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
