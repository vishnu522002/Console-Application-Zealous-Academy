package ZealousAcademy;

import java.io.*;
import java.util.*;

public class ZealousAcademyAccess implements ZealousAction,Runnable
{
	Scanner scan=new Scanner(System.in);
	static String field;
	File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Zealous_Academy_ConsoleApp\\src\\main\\java\\StudentData.txt");
	FileOutputStream fos = null;
	ObjectOutputStream oos = null;
	FileInputStream fis = null;
	ObjectInputStream ois = null;
	ArrayList<ZealousStdDetails> academy= null;
	
	public ZealousAcademyAccess()
	{
		//These are the data that stored in the file.
/*		academy.add(new ZealousStdDetails("Vishnu", "C#", "Razak", 1, 3.0, 30000.0));
		academy.add(new ZealousStdDetails("Thilak", "Python", "Annamalai", 3, 1.0, 20000.0));
		academy.add(new ZealousStdDetails("Thivin", "Java", "Manoj", 4, 2.0, 10000.0));
		academy.add(new ZealousStdDetails("Pradeep", "SQL", "ManojKumar", 2, 1.5, 15000.0));
*/	
	}
	
	@Override
	synchronized public void run() 
	{
		System.out.println("Welcome "+Thread.currentThread().getName());
		try
		{
			showChoice();
		}
		catch(InputMismatchException exe)
		{
			System.out.println(exe+ "\nPlease enter only given input: ");
			showChoice();
		}
	}

	public void affect() throws IOException
	{
		fos = new FileOutputStream(file);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(academy);
		oos.close();
		fos.close();
	}
	
	public void fetch() throws IOException, ClassNotFoundException
	{
		fis = new FileInputStream(file);
		ois = new ObjectInputStream(fis);
		academy = (ArrayList<ZealousStdDetails>)ois.readObject();
		ois.close();
		fis.close();
		
	}
	
	@Override
	public void addnewstudentdetails(ZealousStdDetails zealous) 
	{
		Scanner scanY = new Scanner(System.in);
			 try
			 {
				zealous = new ZealousStdDetails(scanY.next(),scanY.next(),scanY.next(),scanY.nextInt(),scanY.nextDouble(),scanY.nextDouble());
				fetch();
				academy.add(zealous);
				affect();
				System.out.println(zealous.getStudentName()+" has been added.");
				return;
			 }
			 catch(InputMismatchException exe)
			 {
				 System.out.println(exe+"\nPlease enter format(string,string,string,integer,decimal,decimal):");
				 addnewstudentdetails(null);
			 }
		     catch (IOException e) 
			 {
		    	 e.printStackTrace();
		     }
			 catch (ClassNotFoundException e) 
			 {
					e.printStackTrace();
			 }
	
	}
	
	@Override
	public void Listallstudentdetails()
	{
		try {
			fetch();
			Iterator<ZealousStdDetails> i = academy.iterator();
			while(i.hasNext())
			{
				System.out.println(i.next());
			}
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
			
		
	}

	@Override
	public void updateStudentdetails(String StudentName) 
	{
	 try
	 {
		fetch();
		try
		 {
			Vector<ZealousStdDetails> list = new Vector<ZealousStdDetails>();
			list.addAll(academy);
			for(int index=0;index<academy.size();index++)
			{
			 if( list.get(index).getStudentName().equalsIgnoreCase(StudentName))
			 {
				ZealousStdDetails zealous = list.get(index);
				
				System.out.println("Enter the field name you want to update->name,incharge,timing: ");
				String input = scan.next();
				 
				switch(input.toLowerCase())
				{
				case "name":
					System.out.println("Enter the name: ");
					String name = scan.next();
					zealous.setStudentName(name);
					System.out.println("Your values is updated.");
					break;
					
				case "incharge":
					System.out.println("Enter the Incharge name: ");
					String inchargeName = scan.next();
					zealous.setStudentIncharge(inchargeName);
					System.out.println("Your values is updated.");
					break;
					
				case "timing":
					System.out.println("Enter the Student timing: ");
					double timing = scan.nextDouble();
					zealous.setStudentHours(timing);
					System.out.println("Your values is updated.");
					break;
					
				default:
					throw new ZealousException();
				}
				affect();
				return;
			 }
			}
			throw new ZealousException();
		 }
		 catch(ZealousException exe)
		 {
			 System.out.println(exe+" Please enter valid input");
			 System.out.println("Enter the name of the Student you want to update:");
			 updateStudentdetails(scan.next());
		 }
	 	
	 	} 
	 catch (ClassNotFoundException | IOException e) 
	 {
		e.printStackTrace();
	 }
	}

	@Override
	public void searchStudentdetails(String name) 
	{
	 try
	 {
		for(ZealousStdDetails zeal:academy)
		{
			if(zeal.getStudentName().equalsIgnoreCase(name))
			{
				System.out.println(toReadableData(zeal));
				System.out.println();
				return;
			}
		}
		throw new ZealousException();
	 }
	 catch(ZealousException exe)
	 {
		 System.out.println(exe+" \nPlease enter correct name: ");
		 searchStudentdetails(scan.next());
	 }
	}

	@Override
	public void SortStudentdetails() 
	{
		field = scan.next();
	 try
	 {
		TreeSet<ZealousStdDetails> tlist=new TreeSet<ZealousStdDetails>();
		tlist.addAll(academy);
		if(field.equalsIgnoreCase("name") || field.equalsIgnoreCase("incharge") || field.equalsIgnoreCase("course") || field.equalsIgnoreCase("price") || field.equalsIgnoreCase("id"))
		{
			System.out.println("Your values are sorted here by "+field+" field:");
			for(ZealousStdDetails zeal:tlist)
			{
				System.out.println(zeal.toString());
			}
			return;
		}
		throw new ZealousException();
	 }
	 catch(ZealousException exe)
	 {
		 System.out.println(exe+"\nPlease enter only available fields: ");
		 SortStudentdetails();
	 }
	}

	@Override
	public void DeleteStudentdetails(String name) 
	{
		try {
			
			 try
			 {
				fetch();
				for(ZealousStdDetails zeal:academy)
				{
					if(zeal.getStudentName().equalsIgnoreCase(name))
					{
						System.out.println("The data of "+zeal.getStudentName()+" has been deleted.");
						academy.remove(zeal);
						affect();
						return;
					}
				}
				throw new ZealousException();
			 }
			 catch(ZealousException exe)
			 {
				 System.out.println(exe+"\nPlease enter correct Student name");
				 DeleteStudentdetails(scan.next());
			 }
		} 
		catch (ClassNotFoundException | IOException e1) 
		{
			e1.printStackTrace();
		}
	
	}
	
	public String toReadableData(ZealousStdDetails zeal)
	{
		return "----------------------------\nStudent Name: "+zeal.getStudentName()+"\nCourse: "+zeal.getStudentTechnology()
				+"\nIncharge: "+zeal.getStudentIncharge()+"\nID no: "+zeal.getStudentIdNo()+"\nTiming: "+zeal.getStudentHours()+" hours"
				+"\nCoursePrice: "+zeal.getStudentCourseprice()+"\n----------------------------";
	}
	
	public void showChoice()
	{	
		Scanner scan1 = new Scanner(System.in);
		do
		{
			System.out.println("which process to do? \n1.List \n2.Add new Student details \n3.Update \n4.Delete \n5.Search \n6.Sort \n7.Exit");
			int menu=scan1.nextInt();
			switch(menu)
			{	
			case 1:
				System.out.println("your values is listed");
				Listallstudentdetails();
				break;
			case 2:
				System.out.println("Enter the values to add:");
				addnewstudentdetails(null);
				break;
			case 3:
				System.out.println("Enter the name of the Student you want to update:");
				updateStudentdetails(scan.next());
				break;
			case 4:
				System.out.println("Enter the name of the Student to Delete:");
				DeleteStudentdetails(scan.next());
				break;
			case 5:
				System.out.println("Enter the Student name you want to search:");
				searchStudentdetails(scan.next());
				break;
			case 6:
				System.out.println("Enter the field to sort:");
				System.out.println("Available fields: Name, Course, Price, Incharge");
				SortStudentdetails();
				break;
			case 7:
				return;
			}
		}
		while(true);
	}
}