package kokome.billing.role.api;

import kokome.billing.profile.entity.User;
import kokome.billing.role.entity.Role;
import kokome.billing.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by oaitse on Sep, 12, 2021
 */
@RestController
@RequestMapping(path = {"/api/role"})
@RequiredArgsConstructor
public class RoleApi {
    private final RoleService roleService;

    @GetMapping("/v1/all")
    public ResponseEntity<List<Role>> findAllRoles(){
        return ResponseEntity.ok().body(roleService.findAllRoles());
    }


}
