package com.pos.UnitTest;

import java.util.List;

import com.pos.entity.User;
import com.pos.service.BasicDataServiceImpl;

public class UnitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicDataServiceImpl u=new BasicDataServiceImpl();
		List<User> o=u.getUserList("ZHY");
		for(User tmp:o){
            System.out.println("result,id:"+tmp.getId()+",name:"+tmp.getName());
        }

	}
}
