package Details;

/**
 *
 * @author Victor Amorim
 */
public class Student {
    
    private String id;
    private String fName;
    private String surname;
    private int phone;
    private String address;
    private String email;
    private String password;
    private String payment;
    private String info;
    private double totalAttendance;

    public Student(){
    
    }
    
    public Student(double senha){
    
    }
   public Student(String fName, String surname){
   this.fName = fName;
   this.surname = surname;
   }
    
    public Student(String id,String fName,String surname){
        this.id = id;
        this.fName = fName;
        this.surname = surname;
    }
    
    public Student(String id,String fName, String surname,int phone,String address,String email){
    
        this.id = id;
        this.fName = fName;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    
    public Student(String id, String fName, String surname, int phone, String address, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public Student(String id, String fName, String surname, int phone, String address, String email, String password, String payment, String info, double totalAttendance) {
        this.id = id;
        this.fName = fName;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.payment = payment;
        this.info = info;
        this.totalAttendance = totalAttendance;
    }

    public double getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(double totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

   
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
    @Override
    public String toString() {
        return "ID: " + id + "    Name: " + fName + " " + surname ;
    }


    public String detailsStudent(){
    return String.format("ID: %s \nName: %s %s\nEmail: %s", id,fName,surname,email);
    }
    
    

    
    
    
}
