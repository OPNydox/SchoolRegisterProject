package com.example.school.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.AuthGroup;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.User;
import com.example.school.exceptions.ValueException;
import com.example.school.repositories.AuthGroupRepository;
import com.example.school.repositories.StudentRepository;
import com.example.school.repositories.UserRepository;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.services.interfaces.IUserService;
import com.example.school.utilities.Verificator;
import com.example.school.viewModels.StudentViewModel;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private final AuthGroupRepository authGroupRepository;
	
	@Autowired
	private IUserService userService;
	
	private User currentUser;
	
	private String currentUsername;
	
	private List<AuthGroup> currentAuthGroups;
	
	public MyUserDetailsService( AuthGroupRepository authGroupRepository) {
		super();
		this.authGroupRepository = authGroupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUserPrincipal userDetails;
		this.currentUsername = username;
		
		findUser();
		throwExceptionIfUserEmpty();
		findAuthGroups();
		userDetails = createUserDetails();
		clearFields();
		
		return userDetails;
	}
	
	private void findUser() {
		String username = this.currentUsername;
		this.currentUser = this.userService.findUserByUsername(username);
	}
	
	private void throwExceptionIfUserEmpty () {
		if (isCurrentUserEmpty()) {
			String userName = this.currentUsername;
			clearFields();
			throw new UsernameNotFoundException(userName); 
		}
	}
	
	private void findAuthGroups() {
		this.currentAuthGroups = this.authGroupRepository.findByEmail(this.currentUsername);
	}
	
	private boolean isCurrentUserEmpty() {
		if (Verificator.isEmpty(this.currentUser)) {
			return true;
		}
		
		return false;
	}
	
	private MyUserPrincipal createUserDetails() {
		return new MyUserPrincipal(this.currentUser, this.currentAuthGroups);
	}
	
	private void clearFields() {
		this.currentAuthGroups = null;
		this.currentUser = null;
		this.currentUsername = null;
	}

}
