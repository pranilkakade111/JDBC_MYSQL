package com.JDBC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.ResultSet;
//import com.mysql.cj.protocol.Resultset;
//import com.mysql.cj.xdevapi.Result;
//import com.mysql.cj.xdevapi.Statement;

public class DaoDemo {
	static Scanner sc= new Scanner(System.in);
	static boolean exit=false;
	static Connection connection = null;
	static  Statement statement=null;
	public static void getConnection() 
	{
		String jdbcUrl="jdbc:mysql://localhost:3306/employee?useSSL=false";
		String username="root";
		String password="root";



		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			System.out.println("Connecting to database "+jdbcUrl);
			connection=DriverManager.getConnection(jdbcUrl,username,password);
			System.out.println("done!!!");

		}

		catch(ClassNotFoundException | SQLException  e)
		{
			e.printStackTrace();
		}

	}

	public static void getDisplay() throws SQLException
	{
		new DaoDemo();
		DaoDemo.getConnection();

		try {

			statement= connection.createStatement();
			ResultSet result = statement.executeQuery("select * from employee_system");
			while(result.next())
			{
				System.out.println("Id: " + result.getInt("id"));
				System.out.println("Name: " + result.getString("name"));
				System.out.println("Gender : " +result.getString("gender"));
				System.out.println("Department: " + result.getDouble("salary"));
				System.out.println("addess  : "  +result.getString("adress"));
				System.out.println("contact no " +result.getString("phone_no"));
				System.out.println("Start Date: " + result.getDate("date_join"));

				System.out.println();
				System.out.println("-----------------------------------------------");
				System.out.println();
			}

		}
		catch(SQLException  e)
		{
			e.printStackTrace();
		}


	}

	public static void insertValues() throws SQLException, ClassNotFoundException
	{
		new DaoDemo();
		DaoDemo.getConnection();

		try {
			System.out.println("inserting into the table ");
			statement= connection.createStatement();


			String sql="insert into employee_system(name,gender,salary,adress,phone_no,date_join)\r\n" + 
					"value('Arnav','male',80000,'Kalyan,Maharshtra','70777754456','2019-09-20')";

			statement.executeUpdate(sql);
			System.out.println();
			System.out.println("inserted successfully");
			System.out.println("------------------------------------------------------");
		}


		catch(SQLException  e)
		{
			e.printStackTrace();
		}


	}

	public static void updateValues() throws SQLException
	{
		new DaoDemo();
		DaoDemo.getConnection();


		try 
		{

			System.out.println("updating in table");
			statement= connection.createStatement();

			String  sql = "UPDATE  employee_system set adress='USA' where name='alex';";
			statement.executeUpdate(sql);

			System.out.println();
			System.out.println("updated successfully");
			System.out.println("---------------------------------------------------------");
		}

		catch(SQLException  e)
		{
			e.printStackTrace();
		}


	}

	static void end()
	{
		System.out.println("DONE");
		exit=true;
	}

	public static void  OtherOperation() throws SQLException

	{
		new DaoDemo();
		DaoDemo.getConnection();

		try {
			System.out.println("Modification in table");
			statement = connection.createStatement();
			System.out.println("enter :");
			String sql="select * from employee_system where date_join between '2018-01-01' and '2020-12-31'";


			ResultSet result=statement.executeQuery(sql);

			while(result.next())
			{
				System.out.println("--------------------------------------------------------------------------------");			 
				System.out.println("Id: " + result.getInt("id"));
				System.out.println("Name: " + result.getString("name"));
				System.out.println("Gender : " +result.getString("gender"));
				System.out.println("Department: " + result.getDouble("salary"));
				System.out.println("addess  : "  +result.getString("adress"));
				System.out.println("contact no " +result.getString("phone_no"));
				System.out.println("Start Date: " + result.getDate("date_join"));

				System.out.println("-----------------------------------------------------------------------------------");

			}

			System.out.println("All entities are correct!!!!");
			System.out.println("--------------------------------------------------------------------------------------");
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}


	}

	public static void AlterOperation() throws SQLException, NullPointerException
	{

		DaoDemo.getConnection();
		try 
		{
			System.out.println("Alteration of Table");
			statement=connection.createStatement();
			String sql ="ALTER TABLE employee_system drop COLUMN service_period;";
			statement.executeUpdate(sql);

			ResultSet result=statement.executeQuery(sql);

			System.out.println("tabele foramt changed");
			System.out.println("----------------------------------------------------------------------------------");

		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}

	public static void joinOperation()  throws SQLException
	{
		DaoDemo.getConnection();
		try 
		{
			System.out.println("extracting values from different tables");
			statement=connection.createStatement();
			String sql ="SELECT employee_system.id, employee_system.name, employee_system.salary,employee_system.date_join ,department.dept_name\r\n" + 
					"from department\r\n" + 
					"INNER JOIN employee_system  ON department.id=employee_system.id\r\n" + 
					"order by department.dept_name;";


			ResultSet result=statement.executeQuery(sql);

			while(result.next())
			{
				System.out.println("Id: " + result.getInt("id"));
				System.out.println("Name: " + result.getString("name"));
				System.out.println("salary : " + result.getDouble("salary"));
				System.out.println("Start Date: " + result.getDate("date_join"));
				System.out.println("Department :" +result.getString("dept_name"));

				System.out.println("-----------------------------------------------------------------------------------");

			}

			System.out.println("containts shown from table");
			System.out.println("----------------------------------------------------------------------------------");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public static void  DeleteContent() throws SQLException
	{
		DaoDemo.getConnection();
		try 
		{
			System.out.println("Alteration  of Table");
			statement=connection.createStatement();
			String sql ="DELETE from employee_system where name='puspa'";
			statement.executeUpdate(sql);


			System.out.println("containts deleted  from table");
			System.out.println("----------------------------------------------------------------------------------");

		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}

	public static void closeConnect() throws SQLException

	{ 
		if(statement!=null)
		{
			statement.close();
			System.out.println("the statement is closed");
		}


		if(connection != null)
		{
			connection.close();
			System.out.println("the conection is closed");
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		while(!exit) 
		{
			System.out.println("welcome to employee payroll system");
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("enter your choice");
			System.out.println("[0] for closing the connection");
			System.out.println("[1] for exit");
			System.out.println("[2] for display");
			System.out.println("[3] for inserting the value");
			System.out.println("[4] for updating the values");
			System.out.println("[5] for OTHER UPDATION");
			System.out.println("[6] for altering the table");
			System.out.println("[7] for deleteing contains in table");
			System.out.println("[8] for joinig the table execute");
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("enter the choice of operation");


			int choice = sc.nextInt();

			switch(choice)
			{

			case 0:
				new DaoDemo().closeConnect();
				break;

			case 1 :
				new DaoDemo().end();
				break;


			case 2 :
				new DaoDemo().getDisplay();
				break;

			case 3 :
				new DaoDemo().insertValues();
				break;

			case 4 :
				new DaoDemo().updateValues();
				break;

			case 5 :
				new DaoDemo().OtherOperation();
				break;

			case 6 :
				new DaoDemo().AlterOperation();
				break;

			case 7 :
				new DaoDemo().DeleteContent();
				break;

			case 8 :
				new DaoDemo().joinOperation();
				break;
			}
		}

	}

}
