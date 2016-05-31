package unlv.erc.partiubalada.model;
import com.firebase.client.Firebase;

public class NormalUser extends User{


    public NormalUser() {
    }

    public NormalUser(String name, String age, String idUser, String gender, String email, String password, String state, String city) {
        super(name, age, idUser, gender, email, password, state, city);
    }

    public void saveUser() {
        Firebase myFirebaseRef = new Firebase("https://baladacerta.firebaseio.com/");
        myFirebaseRef = myFirebaseRef.child("users").child(getIdUser());
        myFirebaseRef.setValue(this);
    }


}
