package dupradosantini.sostoolbackend.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import dupradosantini.sostoolbackend.domain.AppUser;
import dupradosantini.sostoolbackend.services.UserServiceImpl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UserServiceImplTest {

    @Mock
    UserServiceImpl userService;

    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    @Test
    void findById() throws Exception {
        AppUser user = new AppUser("José", "jose@email.com", "123");
        user.setId(1);

        when(userService.findById(anyInt())).thenReturn(user);

        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("José"));
    }

    @Test
    void findAllUsers() throws Exception {
        List<AppUser> userList = new ArrayList<>();

        AppUser user1 = new AppUser("José", "jose@email.com", "123");
        user1.setId(1);

        AppUser user2 = new AppUser("Maria", "maria@email.com", "321");
        user2.setId(2);

        userList.add(user1);
        userList.add(user2);

        when(userService.findAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(userList.size()))
            .andExpect(jsonPath("$[0].name").value("José"))
            .andExpect(jsonPath("$[0].email").value("jose@email.com"))
            .andExpect(jsonPath("$[1].name").value("Maria"))
            .andExpect(jsonPath("$[1].email").value("maria@email.com"));
    }

    @Test
    public void createUser() throws Exception {
        AppUser user = new AppUser("José", "jose@email.com", "123");

        when(userService.createUser(any(AppUser.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content("{ \"name\": \"José\", \"email\": \"jose@email.com\", \"password\": \"123\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("José"))
                .andExpect(jsonPath("$.email").value("jose@email.com"));
    }

    @Test
    public void createUserInvalidEmail() throws Exception {
        
        AppUser user = new AppUser("José", "joseemail.com", "123");

        when(userService.createUser(any(AppUser.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content("{ \"name\": \"José\", \"email\": \"joseemail.com\", \"password\": \"123\" }"))
                .andExpect(status().isBadRequest());
    }
}
