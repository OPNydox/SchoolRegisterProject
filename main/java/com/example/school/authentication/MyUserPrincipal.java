package com.example.school.authentication;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.StudentViewModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.AuthGroup;
import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.User;

@Service
public class MyUserPrincipal implements UserDetails {
	
	private UserViewModel user;
	
	private List<AuthGroup> authGroups;
	
	public MyUserPrincipal( List<AuthGroup> authGroups) {
		super();
		//this.user = user;
		this.authGroups = authGroups;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.authGroups == null) {
			return Collections.emptySet();
		}
		
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
		authGroups.forEach(group -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
		});
		
		return grantedAuthorities;
	}

	public UserViewModel getUser() {
		return user;
	}

	public void setUser(UserViewModel user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		String mail = this.user.getEmail();
		return mail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
