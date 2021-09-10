package kokome.billing.profile.repository;

import kokome.billing.profile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */
public interface ProfileRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findByUsername(String username);
    List<User> findUsersByRoleName(String name);

}
