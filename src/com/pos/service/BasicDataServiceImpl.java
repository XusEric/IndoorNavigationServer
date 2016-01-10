package com.pos.service;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.pos.DB.SQLHelper;
import com.pos.entity.User;
import com.pos.iservice.IBasicDataService;

@WebService
public class BasicDataServiceImpl implements IBasicDataService {
	public User getUserByName(@WebParam(name = "name") String name) {  
        User user = new User();  
        user.setId(123);  
        user.setName(name);  
        user.setAddress("china");  
        user.setEmail(name + "@test.com");  
        return user;  
    }  
	

    public List<User> getUserList(@WebParam(name = "name") String name) {  
    	String sql="select * from Test where name=?";
    	String[] paramer=new String[]{name};
        ResultSet r=SQLHelper.executeQuery(sql,paramer);
        List<User> u=toList(r,User.class);
        return u;  
    }  
    

    @SuppressWarnings("finally")
	public <T>List<T> toList(ResultSet rs,Class cls)   
    {   
            List<T> list = new ArrayList<T>();   
            try  
            {                
                //��ȡ���ݿ��ṹ   
                ResultSetMetaData meta=rs.getMetaData();   
                T obj=null;   
                while(rs.next())   
                {   
                    //��ȡformbeanʵ������   
                    obj=(T) Class.forName(cls.getName()).newInstance();   
                    //ѭ����ȡָ���е�ÿһ�е���Ϣ   
                    for(int i=1;i<=meta.getColumnCount();i++)   
                    {   
                        //��ǰ����   
                        String colName=meta.getColumnName(i);   
                        //��������һ����ĸ��д��Ϊʲô��+""�أ�Ϊ�˰�char����ת��ΪString���͡�replace�Ĳ�����String���͡���   
                        colName=colName.replace(colName.charAt(0)+"", new String(colName.charAt(0)+"").toUpperCase());   
                        //���÷�����   
                        String methodName="set"+colName;   
                        //��ȡ��ǰλ�õ�ֵ������Object����   
                        Object value=rs.getObject(i);   
                        //���÷�����ƣ�����setXX()������Method����ִ�и�setXX()������   
                        Method method=obj.getClass().getMethod(methodName, value.getClass());   
                        method.invoke(obj, value);                
                    }   
                    list.add(obj);   
                }   
            }catch(Exception ex)   
            {   
                ex.printStackTrace();   
            }finally{   
                return list;   
            }   
      }
}
