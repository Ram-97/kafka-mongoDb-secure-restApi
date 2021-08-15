package com.example.retailorder.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.retailorder.model.AuthenticationRequest;
import com.example.retailorder.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthenticationRequest user = userRepo.findByUsername(username);
		if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
		return new User(user.getUsername(),user.getPassword(),new ArrayList<GrantedAuthority>());
	}
}
