
package Details;

/**
 *
 * @author Victor Amorim
 */
public class Faculty {
    
    private String id;
    private String fName;
    private String surname;
    private int phone;
    private String address;
    private String email;
    private String password;

    public Faculty(){
    
    }
    
    public Faculty(String id,String fName,String surname){
        this.id = id;
        this.fName = fName;
        this.surname = surname;
    }
    
    public Faculty(String id, String fName, String surname, int phone, String address, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
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

    public int getPhone() {
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

    @Override
    public String toString() {
        return "ID: " + id + "    Name: " + fName + " " + surname ;
    }
    
    
}
