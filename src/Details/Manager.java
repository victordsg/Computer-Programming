
package Details;

/**
 *
 * @author Victor Amorim
 */
public class Manager {
    
    private String id;
    private String fName;
    private String surname;
    private int phone;
    private String address;
    private String password;

    public Manager(){
    
    }
    
    public Manager(String fName, String surname){
        this.fName = fName;
        this.surname = surname;
    }
    
    
    public Manager(String id, String fName, String surname, int phone, String address, String password) {
        this.id = id;
        this.fName = fName;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" + "id=" + id + ", fName=" + fName + ", surname=" + surname + ", phone=" + phone + ", address=" + address + ", password=" + password + '}';
    }
    
    
    
    
}
