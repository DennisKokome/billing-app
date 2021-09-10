package kokome.billing.profile.service.impl;

import kokome.billing.profile.entity.User;
import kokome.billing.profile.repository.ProfileRepository;
import kokome.billing.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    @Override
    public User saveUser(User user) {
        return profileRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return profileRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return profileRepository.findAll();
    }

    @Override
    public List<User> findUsersByRoleName(String name) {
        return profileRepository.findUsersByRoleName(name);
    }
}
