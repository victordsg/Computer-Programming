
package Details;

/**
 *
 * @author Victor Amorim
 */
public class Course {
    
    private String id;
    private String studentFName;
    private String studentSurname;
    private String courseName;
    private int totalClasses;
    private int yearCollege;
    private int attend;
    private double totalAttendance;
    private double examResult;
    private int idTable;

  

  public Course(){
  
  }

    public Course(String id, String studentFName, String studentSurname, String courseName, int totalClasses, int yearCollege, int attend, double totalAttendance, double examResult, int idTable) {
        this.id = id;
        this.studentFName = studentFName;
        this.studentSurname = studentSurname;
        this.courseName = courseName;
        this.totalClasses = totalClasses;
        this.yearCollege = yearCollege;
        this.attend = attend;
        this.totalAttendance = totalAttendance;
        this.examResult = examResult;
        this.idTable = idTable;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    } 
  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
 

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

 
    public double getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getYearCollege() {
        return yearCollege;
    }

    public void setYearCollege(int yearCollege) {
        this.yearCollege = yearCollege;
    }

    public int getAttend() {
        return attend;
    }

    public void setAttend(int attend) {
        this.attend = attend;
    }

    public double getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(double totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public double getExamResult() {
        return examResult;
    }

    public void setExamResult(double examResult) {
        this.examResult = examResult;
    }

    public String getStudentFName() {
        return studentFName;
    }

    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", studentFName=" + studentFName + ", studentSurname=" + studentSurname + ", courseName=" + courseName + ", totalClasses=" + totalClasses + ", yearCollege=" + yearCollege + ", attend=" + attend + ", totalAttendance=" + totalAttendance + ", examResult=" + examResult + '}';
    }

   
    
    

    
    
    
}
