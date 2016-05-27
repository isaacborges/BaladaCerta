package unlv.erc.partiubalada.DAO;

import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import unlv.erc.partiubalada.model.NormalUser;
import unlv.erc.partiubalada.view.SignUpActivity;

/**
 * Created by goliveira on 5/8/16.
 */
public class NormalUserDAO {

    private Firebase myFirebaseRef;

    public void saveUserOnFireBase(final SignUpActivity activity, final NormalUser user) {

        myFirebaseRef =  new Firebase("https://baladacerta.firebaseio.com/");

        myFirebaseRef.createUser(
                user.getEmail(),
                user.getPassword(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        user.setIdUser(stringObjectMap.get("uid").toString());
                        user.saveUser();
                        myFirebaseRef.unauth();
                        Toast.makeText(activity.getApplicationContext(), "Your Account has been Created", Toast.LENGTH_LONG).show();
                        Toast.makeText(activity.getApplicationContext(), "Please Login With your Email and Password", Toast.LENGTH_LONG).show();
                        //activity.progressBar.setVisibility(View.GONE);
                        activity.finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(activity.getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        //activity.progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }
}
