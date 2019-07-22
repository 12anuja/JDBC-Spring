package com.stackroute;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class StudentApp
{

    public static void main(String[] args) {


        ApplicationContext ctx=new ClassPathXmlApplicationContext("beans.xml");

        StudentDAO dao=ctx.getBean("sdao",StudentDAO.class);
        int status=dao.saveStudent(new Student(491,"AKSHITA","DELHI"));
       // System.out.println(status);

        dao.updateStudent(new Student(211,"AMAN","MUMBAI"));
        dao.deleteStudent(new Student(491,"AKSHITA","DELHI"));
        System.out.println(dao.getEntrycount());
        

        List<Student> list=dao.getAllStudentss();

        for(Student studobj:list) {
            System.out.println(studobj);
        }

        System.out.print("\n\nNo. of entries in STUDENT table");
        int count = dao.getEntrycount();
        System.out.println(count);




    /*Student s=new Student();
    s.setId(102);
    int status=dao.deleteStudent(student);
    System.out.println(status);*/

    }

}
