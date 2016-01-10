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
                //获取数据库表结构   
                ResultSetMetaData meta=rs.getMetaData();   
                T obj=null;   
                while(rs.next())   
                {   
                    //获取formbean实例对象   
                    obj=(T) Class.forName(cls.getName()).newInstance();   
                    //循环获取指定行的每一列的信息   
                    for(int i=1;i<=meta.getColumnCount();i++)   
                    {   
                        //当前列名   
                        String colName=meta.getColumnName(i);   
                        //将列名第一个字母大写（为什么加+""呢？为了把char类型转换为String类型。replace的参数是String类型。）   
                        colName=colName.replace(colName.charAt(0)+"", new String(colName.charAt(0)+"").toUpperCase());   
                        //设置方法名   
                        String methodName="set"+colName;   
                        //获取当前位置的值，返回Object类型   
                        Object value=rs.getObject(i);   
                        //利用反射机制，生成setXX()方法的Method对象并执行该setXX()方法。   
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
