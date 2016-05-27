package unlv.erc.partiubalada.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import unlv.erc.partiubalada.Controller.NormalUserController;
import unlv.erc.partiubalada.R;

import java.util.Map;

import unlv.erc.partiubalada.model.NormalUser;

public class SignUpActivity extends AppCompatActivity {

    NormalUserController userController;
    private Firebase myFirebaseRef;
    public ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_signup);

        myFirebaseRef =  new Firebase("https://baladacerta.firebaseio.com/");
        userController = new NormalUserController(SignUpActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        userController.startComponents();
    }


    public void onSignUpClicked(View view){

        userController.saveUser();

    }
}