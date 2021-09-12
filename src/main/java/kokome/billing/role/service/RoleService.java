package kokome.billing.role.service;

import kokome.billing.role.entity.Role;

import java.util.List;

/**
 * Created by oaitse on Sep, 10, 2021
 */
public interface RoleService {
    Role createRole(Role role);
    List<Role> findAllRoles();
}
