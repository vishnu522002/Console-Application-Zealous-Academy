package ZealousAcademy;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ZealousStdDetails implements Comparable<ZealousStdDetails>,Serializable
{

	private String studentName;
	private String studentTechnology;
	private String studentIncharge;
	private int studentIdNo;
	private double studentHours;
	private double studentCourseprice;
	
	@Override
	public int compareTo(ZealousStdDetails o) 
	{
		if(ZealousAcademyAccess.field==(null))
		{
			return Integer.compare(this.studentIdNo, o.studentIdNo);
		}	
		if(ZealousAcademyAccess.field.equalsIgnoreCase("name"))
		{
			return this.studentName.compareTo(o.studentName);
		}
		else if(ZealousAcademyAccess.field.equalsIgnoreCase("incharge"))
		{
			return this.studentIncharge.compareTo(o.studentIncharge);
		}
		else if(ZealousAcademyAccess.field.equalsIgnoreCase("course"))
		{
			return this.studentTechnology.compareTo(o.studentTechnology);
		}
		else if(ZealousAcademyAccess.field.equalsIgnoreCase("price"))
		{
			return Double.compare(this.studentCourseprice, o.studentCourseprice);
		}
		return 0;
	}
	
	public String toString()
	{
		return "[Student Name: "+studentName+",\tID No: "+studentIdNo+",\tCourse: "+studentTechnology+",\tIncharge: "+studentIncharge+",\tTiming: "+studentHours+" hrs, CoursePrice: "+studentCourseprice+"]";
	}
}
