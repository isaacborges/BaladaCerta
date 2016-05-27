package unlv.erc.partiubalada.DAO;

import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import unlv.erc.partiubalada.Controller.UserController;
import android.widget.ProgressBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import unlv.erc.partiubalada.Controller.UserController;
import unlv.erc.partiubalada.R;

import java.util.Map;

import unlv.erc.partiubalada.model.NormalUser;
import unlv.erc.partiubalada.view.MainActivity;
import unlv.erc.partiubalada.view.SignUpActivity;

/**
 * Created by goliveira on 5/8/16.
 */
public class NormalUserDAO {


    public void saveUserOnFireBase(final UserController user, final SignUpActivity activity) {

        //this.pbar = pbar;

        final Firebase myFirebaseRef =  new Firebase("https://baladacerta.firebaseio.com/");

        myFirebaseRef.createUser(
                user.getUser().getEmail(),
                user.getUser().getPassword(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        user.getUser().setIdUser(stringObjectMap.get("uid").toString());
                        user.getUser().saveUser();
                        myFirebaseRef.unauth();

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        //Toast.makeText(activity.getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        //pbar.setVisibility(View.GONE);
                    }
                }
        );



    }
}
