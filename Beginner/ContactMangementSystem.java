package Beginner;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactMangementSystem {
	static ArrayList<Detail> list1=new ArrayList<>();
	static int Id=1;
	public static int getVerify(Scanner sc) {
		int choice=1;
		boolean valid=false;
		while(!valid) {
			try {
				choice=sc.nextInt();
				valid=true;
			}
			catch(Exception e) {
				System.out.println("Invalid Input!!!! Try Again");
				sc.next();
			}
		}
		return choice;
	}
	static Detail findId(int id) {
		for(Detail de:list1) {
			if(de.getId()==id) {
				return de;
			}
		}
		return null;
	}
	public static void addC(Scanner sc) {
		sc.nextLine();
		System.out.println("Enter the name:");
		String name=sc.nextLine();
		System.out.println("Enter the phone Number:");
		String number=sc.nextLine();
		System.out.println("Enter the G-mail:");
		String gmail=sc.nextLine();
		Detail d=new Detail(Id++,name,number,gmail);
		list1.add(d);
		System.out.println("Contact:"+d);
		
	}
	public static void viewC(Scanner sc) {
		System.out.println("Enter the ID:");
		int id=getVerify(sc);
		Detail d=findId(id);
		if(d!=null) {
			System.out.println("Contact Found: "+d);
		}
		else {
			System.out.println("Contact Not Found");
		}
	}
	public static void updateC(Scanner sc) {
		System.out.println("Enter the ID:");
		int id=getVerify(sc);
		Detail d=findId(id);
		if(d!=null) {
			sc.nextLine();
			System.out.println("Enter new Name:");
			String name=sc.nextLine();
			System.out.println("Enter new Phone Number");
			String number=sc.nextLine();
			System.out.println("Enter new G-mail");
			String gmail=sc.nextLine();
			d.setName(name);
			d.setNumber(number);
			d.setGmail(gmail);
			System.out.println("Contact Updated:"+d);
		}
		else {
			System.out.println("Contact Not Found!!!!!");
		}
	}
	public static void deleteC(Scanner sc) {
		System.out.println("Enter ID for Delete");
		int id=getVerify(sc);
		Detail d=findId(id);
		if(d!=null) {
			list1.remove(d);
			System.out.println("Contact is Removed Succesfully");
		}
		else {
			System.out.println("Contact Not Found");
		}
	}
	public static void displayC() {
		if(list1.isEmpty()) {
			System.out.println("No Records!!!!");
		}
		else {
			for(Detail d:list1) {
				System.out.println(d);
			}
		}
	}

	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	boolean a=true;
	while(a) {
	System.out.println("\t\t\tCONTACT MANAGEMENT SYSTEM:");
	System.out.println("Choose The Operation:");
	System.out.println("1.Add Contact");
	System.out.println("2.View Contact");
	System.out.println("3.Update Contact");
	System.out.println("4.Delete Contact");
	System.out.println("5.Display All Contacts");
	System.out.println("6.Exit");
	int choice=getVerify(sc);
	switch(choice) {
	case 1:
		addC(sc);
		break;
	case 2:
		viewC(sc);
		break;
	case 3:
		updateC(sc);
		break;
	case 4:
		deleteC(sc);
		break;
	case 5:
		displayC();
		break;
	case 6:
		System.out.println("Exiting..........");
		a=false;
		break;
	default:
		System.out.println("Invalid Choice");
	}
	}
	sc.close();
	}
}
class Detail{
	private int id;
	private String name;
	private String number;
	private String gmail;
	Detail(int id,String name,String number,String gmail){
		this.id=id;
		this.name=name;
		this.number=number;
		this.gmail=gmail;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String toString(){
		return "ID="+id+",Name="+name+",Number="+number+",G-mail="+gmail+".";
	}
	
}


