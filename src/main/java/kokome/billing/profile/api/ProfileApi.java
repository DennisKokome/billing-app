package kokome.billing.profile.api;

import kokome.billing.profile.entity.User;
import kokome.billing.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */

@RestController
@RequestMapping(path = {"/api/profile"})
@RequiredArgsConstructor
public class ProfileApi {
    private final ProfileService profileService;

    @PostMapping("/v1/save")
    public ResponseEntity<User> saveUSer(@RequestBody User user){
        return ResponseEntity.ok().body(profileService.saveUser(user));
    }

    @GetMapping("/v1/byUsername/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(profileService.findUserByUsername(username));
    }

    @GetMapping("/v1/all")
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.ok().body(profileService.findAllUsers());
    }

    @GetMapping("/v1/byRole/{name}")
    public ResponseEntity<List<User>> findUsersByRole(@PathVariable String name){
        return ResponseEntity.ok().body(profileService.findUsersByRoleName(name));
    }
}
