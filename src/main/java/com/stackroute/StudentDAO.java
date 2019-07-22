package com.stackroute;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//DataAccessObject class having Jdbc template
public class StudentDAO
{
    //Template object
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    //Setter for JdbcTemplate object
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate,NamedParameterJdbcTemplate namedJdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }


    //Method to create STUDENT table
   /* public void createTable()
    {
        String query = "create table STUDENT (ID INT,NAME VARCHAR(30), ADDRESS VARCHAR(30)";
        jdbcTemplate.execute(query);
    }

    */

    //Method to insert data in STUDENT table
    public int saveStudent(Student student){

        String query="insert into STUDENT values( '"+student.getId()+"','"+student.getName()+"','"+student.getAddress()+"')";
        return jdbcTemplate.update(query);
    }

    //Method to insert data in STUDENT table using namedparameter JDBC template
    /*public void saveStudent(Student student)
    {
        String sql = "insert into STUDENT values (:id,:name,:address)";
        SqlParameterSource namedParameters = new
                MapSqlParameterSource("id",student.getId()).
                addValue("name",student.getName()).addValue("address",student.getAddress());
        namedJdbcTemplate.update(sql,namedParameters);
    }

     */

    //Method to update a row in STUDENT table
    public int updateStudent(Student student){
        String query="update STUDENT set NAME='"+student.getName()+"',ADDRESS='"+student.getAddress()+"' where ID='"+student.getId()+"' ";
        return jdbcTemplate.update(query);
    }

    //method to delete an entry in STUDENT table
    public int deleteStudent(Student student){
        String query="delete from STUDENT where ID='"+student.getId()+"' ";
        return jdbcTemplate.update(query);
    }

    //method to count no of entries in table
    public int getEntrycount()
    {
        String query = "select count(*) from STUDENT";
        int count=jdbcTemplate.queryForObject(query,Integer.class);
        return count;
    }

    //method to dispaly all entries in STUDENT table
    public List<Student> getAllStudentss()
    {

        return jdbcTemplate.query("select * from STUDENT",new RowMapper<Student>()
        {
            @Override

            public Student mapRow(ResultSet rs, int rownumber) throws SQLException
            {
                Student st = new Student();
                st.setId(rs.getInt(1));
                st.setName(rs.getString(2));
                st.setAddress(rs.getString(3));
                return st;
            }
        });
    }

}
