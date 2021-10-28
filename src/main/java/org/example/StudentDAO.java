package org.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
public class StudentDAO
{
    private String driver;
    private String url;
    private String userName;
    private String password;

    Connection con;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        System.out.println("setting driver...");
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url)
    {
        System.out.println("setting url");
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("Inside set username");
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("inside set password");
        this.password = password;
    }

    //@PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        System.out.println("Inside the custom init method");
        createEmployeeDBConnection();
    }

    public void createEmployeeDBConnection() throws ClassNotFoundException, SQLException {

        System.out.println("creating connetion..");
        //load driver
        Class.forName(driver);

        //get a connection
        con = DriverManager.getConnection(url,userName,password);
    }
    public void selectAllRows() throws ClassNotFoundException, SQLException
    {
        System.out.println("Retrieving all employee data");

        //execute query
        Statement stmt = con.createStatement();

        //take result
        ResultSet rs = stmt.executeQuery("select * from employee_table");

        while(rs.next())
        {
            int employeeId = rs.getInt(1);
            String employeeName = rs.getString(2);
            String employeeOccupation = rs.getString(3);
            int employeeAge = rs.getInt(4);

            System.out.println(employeeId + " " + employeeName + " " + employeeOccupation + " " + employeeAge);
        }
    }

    public void deleteEmploeeRecord(int employeeId) throws ClassNotFoundException, SQLException
    {
        //execute query
        Statement stmt = con.createStatement();

        stmt.executeUpdate("delete from employee_table where id = " + employeeId);

        System.out.println("Record deleted with id = " + employeeId);
    }

    //@PreDestroy
    public void destroy() throws SQLException {
        //clean up job
        System.out.println("Inside destroy method");
        closeConnection();
    }
    public void closeConnection() throws SQLException
    {
        con.close();
    }
}
