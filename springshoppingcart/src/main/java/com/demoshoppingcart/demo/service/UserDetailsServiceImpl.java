package com.demoshoppingcart.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demoshoppingcart.demo.dao.AccountDAO;
import com.demoshoppingcart.demo.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account = accountDAO.findAccount(userName);
		
		if(account == null) {
			throw new UsernameNotFoundException("User "+userName+" was notfound in the database");
		}
		
		//employee, manager 
		String role = account.getUserRole();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//
		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		authorities.add(authority);
		
		boolean enabled = account.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLooked = true;
		
		UserDetails userDetails = new User(account.getUserName(), 
				account.getEncrytedPassWord(),enabled, accountNonExpired, 
				credentialsNonExpired,accountNonLooked, authorities);
		
		return userDetails;
	}
	

}
