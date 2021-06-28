package sg.edu.iss.caps.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

public class MyUserDetails implements UserDetails {
	
	private Users user;

	public MyUserDetails(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Roles roles[] = Roles.values();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		//for(Roles role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().toString()));
		//}
		System.out.println(authorities); //for debugging
		return authorities;
	}      

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
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
		return user.isEnabled();
	}

}
