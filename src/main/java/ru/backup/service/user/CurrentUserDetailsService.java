package ru.backup.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.backup.domain.user.CurrentUser;
import ru.backup.domain.user.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", username)));;
		
		System.out.println(user.toString());
		return new CurrentUser(user);
	}

}
