package ru.kata.spring.boot_security.demo.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;

@Component
public class DbInit {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    private void postConstruct() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
        Collection<Role> roleAdmin = new HashSet<>();
        Collection<Role> roleUser = new HashSet<>();

        roleAdmin.add(roleService.findByRoleName("ROLE_ADMIN"));
        roleUser.add(roleService.findByRoleName("ROLE_USER"));

        User admin = new User();
        admin.setName("admin");
        admin.setLastname("Ivanov");
        admin.setRole(roleAdmin);
        admin.setAge(28);
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");

        User user = new User();
        user.setName("user");
        user.setLastname("Petrov");
        user.setRole(roleUser);
        user.setAge(18);
        user.setEmail("user@gmail.com");
        user.setPassword("user");

        userService.addNewUser(user);
        userService.addNewUser(admin);
    }
}
