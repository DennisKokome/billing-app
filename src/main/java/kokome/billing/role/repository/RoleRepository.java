package kokome.billing.role.repository;

import kokome.billing.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oaitse on Sep, 10, 2021
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
