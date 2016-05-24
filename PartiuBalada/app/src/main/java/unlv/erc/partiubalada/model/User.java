package unlv.erc.partiubalada.model;

public class User {

    private String name;
    private int age;
    private String idUser;
    private String gender;
    private String email;
    private String password;
    private String state;
    private String city;

    public User() {
    }

    public User(String name, int age, String idUser, String gender, String email, String password, String state, String city) {
        this.name = name;
        this.age = age;
        this.idUser = idUser;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.state = state;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}
}
