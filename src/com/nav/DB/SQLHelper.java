package com.nav.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nav.util.ConfigHelper;

public class SQLHelper {
	//�������  
    private static Connection ct = null; 
    //������������preparedstatement���statement  
    private static PreparedStatement ps = null;  
    private static ResultSet rs = null;
    
    private static CallableStatement cs = null;  
    public static CallableStatement getCs()  
    {  
        return cs;  
    }  
    
  //�������ݿ�Ĳ���  
    private static String url = "";  
    private static String username = "";  
    private static String driver = "";  
    private static String passwd = "";
//    private static Properties  pp = null;  
//    private static InputStream fis = null;
    
  //����������ֻ��Ҫһ�Σ��þ�̬�����  
    static  
    {  
        try  
        {  
            //��dbinfo.properties  
//            pp = new Properties();  
//            fis=SQLHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");  
//            pp.load(fis);  
            url = ConfigHelper.getDbUrl();  
            driver = ConfigHelper.getDbDriver();  
            username = ConfigHelper.getDbUserName();  
            passwd = ConfigHelper.getDbPassWord();  
             
            Class.forName(driver);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
//            try  
//            { fis.close();}  
//            catch(IOException e) {e.printStackTrace();}  
//            fis = null;//��������վ����ʰ  
        }  
         
    }  
    //�õ�����  
    public static Connection getConnection()  
        {  
            try  
            {ct = DriverManager.getConnection(url,username,passwd);}  
            catch(Exception e) {e.printStackTrace();}  
            return ct;  
        }  
    
    
    /**

     * ��ɾ�ġ�Add��Del��Update��

     *

     * @param sql

     * @return int

     */

    public static int executeNonQuery(String sql) {

        int result = 0;
        try {

            ct=getConnection();  
            ps=ct.prepareStatement(sql); 
            result = ps.executeUpdate();  

        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException(e.getMessage());  

        } finally {
        }
        return result;
    } 
    
    /**

     * ��ɾ�ġ�Add��Delete��Update��

     *

     * @param sql

     * @param obj

     * @return int

     */

    public static int executeNonQuery(String sql, String[] parameters) {

        int result = 0;
        try {

        	ct=getConnection();  
            //ct.setAutoCommit(false);  
            ps=ct.prepareStatement(sql);  
            if(parameters!=null)  
            {  
                for(int i=0;i<parameters.length;i++)  
                {  
                    ps.setString(i+1,parameters[i]);  
                }  
            }  
            result = ps.executeUpdate();  

        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException(e.getMessage()); 

        } finally {

        }
        return result;
    } 
    
    /**

     * �顾Query��

     *

     * @param sql

     * @return ResultSet

     */ 
    public static ResultSet executeQuery(String sql)  
    {  
        try  
        {  
            ct=getConnection();  
            ps=ct.prepareStatement(sql); 
            rs = ps.executeQuery();  
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
            throw new RuntimeException(e.getMessage());  
        }  
        finally  
        {  
             
        }  
        return rs;  
    }
    
    /**

     * �顾Query��

     *

     * @param sql

     * @param String[]	String[] str = new String[]{"����"}; 

     * @return ResultSet

     */ 
    public static ResultSet executeQuery(String sql,String[] parameters)  
    {  
        try  
        {  
            ct=getConnection();  
            ps=ct.prepareStatement(sql);  
            if(parameters!=null)  
            {  
                for(int i=0;i<parameters.length;i++)  
                {  
                    ps.setString(i+1,parameters[i]);  
                }  
            }  
            rs = ps.executeQuery();  
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
            throw new RuntimeException(e.getMessage());  
        }  
        finally  
        {  
             
        }  
        return rs;  
    }
    
  //*************callPro1�洢���̺���1*************     
    public static CallableStatement callPro1(String sql,String[] parameters)  
    {  
        try{  
            ct = getConnection();  
            cs = ct.prepareCall(sql);  
            if(parameters!=null){  
                for(int i=0;i<parameters.length;i++){  
                 cs.setObject(i+1,parameters[i]);  
                }  
            }     
            cs.execute();  
        }  
        catch(Exception e) { e.printStackTrace(); throw new RuntimeException(e.getMessage());}  
        finally  
        { close(rs,cs,ct);}  
        return cs;  
    } 
    
    public static void close(ResultSet rs,Statement ps,Connection ct)  
    {  
        //�ر���Դ(�ȿ����)  
        if(rs!=null)  
        {  
            try  
            {  
                rs.close();  
            }  
            catch(SQLException e)  
            {  
                e.printStackTrace();  
            }  
            rs=null;  
        }  
        if(ps!=null)  
        {  
            try  
            {  
                ps.close();  
            }  
            catch(SQLException e)  
            {  
                e.printStackTrace();  
            }  
            ps=null;  
        }  
        if(null!=ct)  
        {  
            try  
            {  
                ct.close();  
            }  
            catch(SQLException e)  
            {  
                e.printStackTrace();  
            }  
            ct=null;  
        }  
    }  
}
