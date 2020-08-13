package com.eniso.tama.configuration.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.configuration.jwt.JwtUtils;
import com.eniso.tama.configuration.payload.JwtResponse;
import com.eniso.tama.configuration.payload.LoginRequest;
import com.eniso.tama.configuration.payload.MessageResponse;
import com.eniso.tama.configuration.payload.SignupRequest;
import com.eniso.tama.configuration.service.UserDetailsImpl;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.entity.User;
import com.eniso.tama.repository.RoleRepository;
import com.eniso.tama.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository ;

	@Autowired
	PasswordEncoder encoder ;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getPassword()) ;
		System.out.println(loginRequest.getEmail()) ;
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		System.out.println(userDetails) ;
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 	
												 roles));
	}

	@PostMapping( "/signup" )
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	
		
		

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getAddress(),
							 signUpRequest.getPhoneNumber(),
							  null);
		//System.out.println(encoder.encode(signUpRequest.getPassword())) ;
		//System.out.println(encoder.encode(signUpRequest.getPhoneNumber())) ;
		
		
		
		Set<String> strRoles = signUpRequest.getRole();
		System.out.println(user.getEmail()) ;
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByRole(Roles.MANAGER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "MANAGER":
					Role adminRole = roleRepository.findByRole(Roles.MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role Manager is not found."));
					roles.add(adminRole);

					break;
				case "TRAINER":
					Role modRole = roleRepository.findByRole(Roles.TRAINER)
							.orElseThrow(() -> new RuntimeException("Error: Role Trainer is not found."));
					roles.add(modRole);
					break ; 
				case "ENTREPRISE":
					Role entrRole = roleRepository.findByRole(Roles.ENTREPRISE)
							.orElseThrow(() -> new RuntimeException("Error: Role Enterprise is not found."));
					roles.add(entrRole);

					break;
				case "INSTITUTION":
					Role instRole = roleRepository.findByRole(Roles.INSTITUTION)
							.orElseThrow(() -> new RuntimeException("Error: Role Institution is not found."));
					roles.add(instRole);

					
				default:
					Role userRole = roleRepository.findByRole(Roles.MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role Default is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		
		userRepository.save(user);
		System.out.println(user.getPhoneNumber()) ;
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
