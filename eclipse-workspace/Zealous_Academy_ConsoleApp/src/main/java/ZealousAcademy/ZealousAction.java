package ZealousAcademy;

public interface ZealousAction 
{
	public void addnewstudentdetails(ZealousStdDetails zealous);
	public void Listallstudentdetails();
	public void updateStudentdetails(String name);
	public void searchStudentdetails(String technology);
	public void SortStudentdetails();
	public void DeleteStudentdetails(String name);
}
