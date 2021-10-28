package org.example;

import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("beans.xml file loaded");
        StudentDAO studentDao = context.getBean("studentDAO" , StudentDAO.class);
        System.out.println(studentDao);
        studentDao.selectAllRows();

        context.close();

        //context.registerShutdownHook();
    }
}
