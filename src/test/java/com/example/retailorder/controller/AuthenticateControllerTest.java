package com.example.retailorder.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.example.retailorder.model.AuthenticationRequest;
import com.example.retailorder.service.UserDetailService;
import com.example.retailorder.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthenticateController.class)
public class AuthenticateControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserDetailService userDetailsService;
	@MockBean
	private JwtUtil jwtTokenUtil;
	@MockBean
	private AuthenticationManager authenticationManager;

	@Test
	public void testGetTokenValidRequest() throws Exception {
		AuthenticationRequest user = new AuthenticationRequest("Ram", "Password1");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(post("/authenticate/token").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetTokenInValidRequest() throws Exception {
		AuthenticationRequest user = new AuthenticationRequest("Ram", "Password1");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(post("/authenticate/token").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
