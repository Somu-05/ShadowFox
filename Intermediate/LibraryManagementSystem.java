package Intermediate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LibraryManagementSystem {
	
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		DatabaseConnector.createTables();
	    while(true) {
		
		System.out.println("1.Register");
		System.out.println("2.Login");
		System.out.println("3.Exit");
		System.out.println("Enter your choice:");
		int choice=sc.nextInt();
		sc.nextLine();
		switch(choice) {
		case 1:
			registerUser();
			break;
		case 2:
			if(loginUser()) {
				manageBooks();
			}
			break;
		case 3:
			System.out.println("LogOut!!!");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Choice!!!!!");
		}
	}
}
	private static void registerUser() {
		System.out.println("Enter Username:");
		String username=sc.nextLine();
		System.out.println("Enter password:");
		String password=sc.nextLine();
		String query="INSERT INTO USERS(USERNAME,PASSWORD) VALUES(?,?);";
		try(Connection con=DatabaseConnector.connect();
				PreparedStatement psmt=con.prepareStatement(query)){
			psmt.setString(1, username);
			psmt.setString(2, password);
			psmt.executeUpdate();
			System.out.println("User Registered Successfully!");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	private static boolean loginUser() {
		System.out.println("Enter Username:");
		String username=sc.nextLine();
		System.out.println("Enter password:");
		String password=sc.nextLine();
		String query="SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?;";
		try(Connection con=DatabaseConnector.connect();
				PreparedStatement psmt=con.prepareStatement(query)){
				psmt.setString(1, username);
				psmt.setString(2, password);
				ResultSet rs=psmt.executeQuery();
				if(rs.next()) {
					System.out.println("Login successful");
					return true;
				}
				else {
					System.out.println("Invalid User");
					return false;
				}
	}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	private static void manageBooks() {
		while(true) {
			System.out.println("1.Add Book");
			System.out.println("2.View Book");
			System.out.println("3.Update Book");
			System.out.println("4.Delete Book");
			System.out.println("5.Logout");
			System.out.println("Enter your choice");
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				addBook();
				break;
			case 2:
				viewBook();
				break;
			case 3:
				updatebook();
				break;
			case 4:
				deletebook();
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid Chioice!!!Try Again....");
			}
		}
	}
	private static void addBook() {
		System.out.println("Enter Book title:");
		String title=sc.nextLine();
		System.out.println("Enter the book Authoe:");
		String author=sc.nextLine();
		String query="INSERT INTO BOOKS (TITLE,AUTHOR,AVAILABLE) VALUES(?,?,?);";
		try(Connection con=DatabaseConnector.connect();
				PreparedStatement psmt=con.prepareStatement(query)){
			psmt.setString(1, title);
			psmt.setString(2,author);
			psmt.setInt(3, 1);
			psmt.executeUpdate();
			System.out.println("Book Added Successfully....");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void viewBook() {
		String query="SELECT * FROM BOOKS";
		try(Connection con=DatabaseConnector.connect();
				Statement smt=con.createStatement();
				ResultSet rs=smt.executeQuery(query)){
			while(rs.next()) {
				int id=rs.getInt("id");
				String title=rs.getString("title");
				String author=rs.getString("author");
				boolean available=rs.getInt("available")==1;
				System.out.println("ID: "+id+", Title: "+title+", Author: "+author+", Available: "+available);;
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void updatebook() {
		System.out.println("Enter the Book ID to update:");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter new Title:");
		String title=sc.nextLine();
		System.out.println("Enter new Author:");
		String author=sc.nextLine();
		String query="UPDATE BOOKS SET TITLE=?,AUTHOR=? WHERE ID=?;";
		 try (Connection conn = DatabaseConnector.connect();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setString(1, title);
	            pstmt.setString(2, author);
	            pstmt.setInt(3, id);
	            pstmt.executeUpdate();
	            System.out.println("Book updated successfully.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	private static void deletebook() {
		System.out.println("Enter Book ID to Delete:");
		int id=sc.nextInt();
		sc.nextLine();
		String query="DELETE FROM BOOKS WHERE ID=?;";
		try (Connection conn = DatabaseConnector.connect();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	            System.out.println("Book deleted successfully.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
}
class DatabaseConnector{
	private static String url="jdbc:mysql://localhost:3306/LIBRARY";
	private static String user="barsee";
	private static String pass="2326";
	
	public static Connection connect() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void createTables() {
		String usertable="CREATE TABLE IF NOT EXISTS users ("
				+ "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "username VARCHAR(255) NOT NULL, "
				+ "password VARCHAR(255) NOT NULL);";
		String booktable="CREATE TABLE IF NOT EXISTS books("
				+ "id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "title VARCHAR(255) NOT NULL, " 
				+ "author VARCHAR(255) NOT NULL, " 
				+ "available INT NOT NULL);";
		try(Connection con=connect();
				Statement smt=con.createStatement()) {
			smt.execute(usertable);
			smt.execute(booktable);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
