package kokome.billing.profile;

import kokome.billing.profile.entity.User;
import kokome.billing.profile.repository.ProfileRepository;
import kokome.billing.profile.service.impl.ProfileServiceImpl;
import kokome.billing.role.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static kokome.billing.util.KnownRoles.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Test
    public void testSaveUser(){
        User user = new User(1L,
                "Oaitse, ",
                "Kokome",
                "75167171",
                "kokomeodk@gmail.com",
                "kokomeodk@gmail.com",
                "kokome",
                new Role(1L,"Customer", 30F),
                LocalDate.of(2021,9,9));

        when(profileRepository.save(user)).thenReturn(user);
        User savedUsed = profileService.saveUser(user);

        Assert.assertNotNull(savedUsed);
        Assert.assertEquals(user, savedUsed);
        Assert.assertEquals(user.getUsername(), savedUsed.getUsername());
        verify(profileRepository).save(user);

    }

    @Test
    public void testFindUserByUsername(){

        User user = new User(1L,
                "Oaitse, ",
                "Kokome",
                "75167171",
                "kokomeodk@gmail.com",
                "kokomeodk@gmail.com",
                "kokome",
                new Role(1L,"Customer", 30F),
                LocalDate.of(2021,9,9));

        User user1 = new User(2L,
                "Clark, ",
                "Kent",
                "76750096",
                "clark@gmail.com",
                "clark@gmail.com",
                "clark",
                new Role(1L,"Customer", 30F),
                LocalDate.of(2021,9,9));

        when(profileRepository.findByUsername(user.getUsername())).thenReturn(user);
        User savedUsed = profileService.findUserByUsername(user.getUsername());

        Assert.assertNotNull(savedUsed);
        Assert.assertEquals(user, savedUsed);
        Assert.assertEquals(user.getUsername(), savedUsed.getUsername());
        verify(profileRepository).findByUsername(user.getUsername());

    }

    @Test
    public void testFindAllUsers(){
        User user = new User(1L,
                "Oaitse, ",
                "Kokome",
                "75167171",
                "kokomeodk@gmail.com",
                "kokomeodk@gmail.com",
                "kokome",
                new Role(1L,"Customer", 30F),
                LocalDate.of(2021,9,9));

        User user1 = new User(2L,
                "Clark, ",
                "Kent",
                "76750096",
                "clark@gmail.com",
                "clark@gmail.com",
                "clark",
                new Role(1L,"AFFILIATE", 30F),
                LocalDate.of(2021,9,9));
        List<User> users = new ArrayList<>();
        users.addAll(List.of(user,user1));

        when(profileRepository.findAll()).thenReturn(users);
        List<User> savedUsers = profileService.findAllUsers();

        Assert.assertNotNull(savedUsers);
        Assert.assertEquals(users, savedUsers);
        Assert.assertEquals(users.get(0).getUsername(), savedUsers.get(0).getUsername());
        verify(profileRepository).findAll();
    }

    @Test
    public void testFindUsersByRole(){

        User user = new User(1L,
                "Oaitse, ",
                "Kokome",
                "75167171",
                "kokomeodk@gmail.com",
                "kokomeodk@gmail.com",
                "kokome",
                new Role(1L,"Customer", 30F),
                LocalDate.of(2021,9,9));

        User user1 = new User(2L,
                "Clark, ",
                "Kent",
                "76750096",
                "clark@gmail.com",
                "clark@gmail.com",
                "clark",
                new Role(1L,"AFFILIATE", 30F),
                LocalDate.of(2021,9,9));

        User user2 = new User(1L,
                "Bruce, ",
                "Wayne",
                "75167171",
                "kokomeodk@gmail.com",
                "wayne@wayne.com",
                "wayne",
                new Role(1L,"EMPLOYEE", 30F),
                LocalDate.of(2021,9,9));

        User user3 = new User(2L,
                "Tonny, ",
                "Stark",
                "76750096",
                "stark@gmail.com",
                "stark@gmail.com",
                "stark",
                new Role(1L,"AFFILIATE", 30F),
                LocalDate.of(2021,9,9));
        List<User> usersEmployees = new ArrayList<>();
        usersEmployees.add(user2);

        when(profileRepository.findUsersByRoleName(EMPLOYEE)).thenReturn(usersEmployees);
        List<User> savedUsers = profileService.findUsersByRoleName(EMPLOYEE);

        Assert.assertNotNull(savedUsers);
        Assert.assertEquals(usersEmployees, savedUsers);
        Assert.assertEquals(usersEmployees.get(0).getUsername(), savedUsers.get(0).getUsername());
        verify(profileRepository).findUsersByRoleName(EMPLOYEE);
    }
}
