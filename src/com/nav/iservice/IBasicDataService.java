package com.nav.iservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.nav.entity.User;

@WebService  
public interface IBasicDataService {

	public User getUserByName(@WebParam(name = "name") String name); 
}
