package com.nav.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.nav.entity.User;

@WebService
public class BasicDataServiceImpl {
	public User getUserByName(@WebParam(name = "name") String name) {  
        User user = new User();  
        user.setId(123);  
        user.setName(name);  
        user.setAddress("china");  
        user.setEmail(name + "@test.com");  
        return user;  
    }  
}
