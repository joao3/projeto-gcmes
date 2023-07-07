package dupradosantini.sostoolbackend.controllers;

import dupradosantini.sostoolbackend.domain.AppUser;
import dupradosantini.sostoolbackend.domain.dtos.RoleHistoryDto;
import dupradosantini.sostoolbackend.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@CrossOrigin("*")
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> findById(@PathVariable Integer id){
        AppUser obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> findAll(){
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser obj){
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        if (Pattern.matches(emailRegex, obj.getEmail())) {
            AppUser newAppUser = userService.createUser(obj);
            return ResponseEntity.ok().body(newAppUser);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{userId}/role-history")
    public ResponseEntity<List<RoleHistoryDto>> getRoleHistory(@PathVariable Integer userId){
        List<RoleHistoryDto> returnList = userService.findUserRoleHistory(userId);
        return ResponseEntity.ok().body(returnList);
    }

    @GetMapping("/{workspaceId}/current-members")
    public ResponseEntity<Set<AppUser>> getCurrentWorkspaceMembers(@PathVariable Integer workspaceId){
        var users = this.userService.findCurrentWorkspaceUsers(workspaceId);
        return ResponseEntity.ok().body(users);
    }
}

