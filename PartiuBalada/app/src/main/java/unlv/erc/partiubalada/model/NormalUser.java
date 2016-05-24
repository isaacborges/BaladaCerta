package unlv.erc.partiubalada.model;
import com.firebase.client.Firebase;

/**
 * Created by dandaraaranha on 23/05/16.
 */
public class NormalUser extends User{


    public NormalUser() {
    }

    public NormalUser(String name, int age, String idUser, String gender, String email, String password, String state, String city) {
        super(name, age, idUser, gender, email, password, state, city);
    }

    public void saveUser() {
        Firebase myFirebaseRef = new Firebase("https://baladacerta.firebaseio.com/");
        myFirebaseRef = myFirebaseRef.child("users").child(getIdUser());
        myFirebaseRef.setValue(this);
    }


}
