package kokome.billing.profile.service;

import kokome.billing.profile.entity.User;

import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */
public interface ProfileService {

    User saveUser(User user);
    User findUserByUsername(String username);
    List<User> findAllUsers();
    List<User> findUsersByRoleName(String name);

}
