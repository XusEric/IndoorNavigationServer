package com.pos.iservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.pos.entity.User;

@WebService  
public interface IBasicDataService {

	public User getUserByName(@WebParam(name = "name") String name); 
	public  List<User> getUserList(@WebParam(name = "name") String name);  
}
