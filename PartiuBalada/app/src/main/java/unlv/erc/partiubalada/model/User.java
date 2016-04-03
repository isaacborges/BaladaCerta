package unlv.erc.partiubalada.model;

public class User {

    private String name;
    private int age;
    private int idUser;
    private String gender;
    private String email;
    private String password;


    public User(String name, int age, int idUser, String gender, String email, String password) {
        this.name = name;
        this.age = age;
        this.idUser = idUser;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public User( int age, int idUser, String email, String password) {
        this.age = age;
        this.idUser = idUser;
        this.email = email;
        this.password = password;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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
}
