package com.example.school.authentication;

import java.util.List;

import com.example.school.services.interfaces.ICourseService;
import com.example.school.viewModels.Interfaces.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.AuthGroup;
import com.example.school.repositories.AuthGroupRepository;
import com.example.school.services.interfaces.IUserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private final AuthGroupRepository authGroupRepository;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ICourseService courseService;
	
	private UserViewModel currentUser;

	public MyUserDetailsService( AuthGroupRepository authGroupRepository) {
		super();
		this.authGroupRepository = authGroupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserViewModel currentUser = this.userService.findUserVMByUsername(email);
		List<AuthGroup> currentAuthGroups;

		if (currentUser == null) {
			throw new UsernameNotFoundException(email);
		}

		currentAuthGroups = this.authGroupRepository.findByEmail(email);
		
		return new MyUserPrincipal(currentUser, currentAuthGroups);
	}

}
