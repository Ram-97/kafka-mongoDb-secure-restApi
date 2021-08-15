package com.example.retailorder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retailorder.model.AuthenticationRequest;
import com.example.retailorder.model.AuthenticationResponse;
import com.example.retailorder.service.UserDetailService;
import com.example.retailorder.util.JwtUtil;

@RestController
@RequestMapping("authenticate")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private UserDetailService userDetailsService;
	
    private static final Logger logger = 
            LoggerFactory.getLogger(AuthenticateController.class);
	

	@PostMapping("token")
	public ResponseEntity<?> getToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		logger.info("Controller: getToken action entered");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch(BadCredentialsException ex) {
			throw new Exception("Incorrect username or password", ex);
		}
		
		UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtTokenUtil.generateToken(userDetails);
		logger.info("Controller: getToken action exited");
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
