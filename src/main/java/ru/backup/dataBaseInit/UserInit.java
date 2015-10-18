package ru.backup.dataBaseInit;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.backup.domain.user.Role;
import ru.backup.domain.user.User;
import ru.backup.service.user.UserService;


@Component
public class UserInit {

    private static final Logger logger = LoggerFactory.getLogger(UserInit.class);

    @Autowired
    private UserService userService;

    @PostConstruct
    @Transactional
    private void init() {
    	createUser("roma", "1234", Role.USER);
    	createUser("admin", "1234", Role.ADMIN);
    }
    
    private void createUser(String username, String password, Role role)
    {
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword( new BCryptPasswordEncoder().encode(password));
    	user.setRole(role);
    	userService.save(user);
    }

}