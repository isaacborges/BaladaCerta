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

import unlv.erc.partiubalada.Controller.UserController;
import unlv.erc.partiubalada.R;

import java.util.Map;

import unlv.erc.partiubalada.model.NormalUser;

public class SignUpActivity extends AppCompatActivity {

    private Firebase myFirebaseRef;
    private UserController userController;
    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText email;
    private EditText password;
    private EditText city;
    private EditText state;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_signup);

        myFirebaseRef =  new Firebase("https://baladacerta.firebaseio.com/");

    }

    @Override
    protected void onStart() {
        super.onStart();
        name = (EditText) findViewById(R.id.edit_text_username);
        age=(EditText) findViewById(R.id.edit_text_age);
        gender= (EditText) findViewById(R.id.edit_text_sex);
        city=(EditText) findViewById(R.id.edit_text_city);
        state=(EditText) findViewById(R.id.edit_text_state);
        email = (EditText) findViewById(R.id.edit_text_new_email);
        password = (EditText) findViewById( R.id.edit_text_new_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_sign_up);
    }


    protected void setUpUser(){
        userController = new UserController();
        userController.getUser().setName(name.getText().toString());
        userController.getUser().setAge(age.getText().toString());
        userController.getUser().setGender(gender.getText().toString());
        userController.getUser().setCity(city.getText().toString());
        userController.getUser().setState(state.getText().toString());
        userController.getUser().setEmail(email.getText().toString());
        userController.getUser().setPassword(password.getText().toString());
    }

    public void onSignUpClicked(View view){

        setUpUser();

        userController.saveUser(userController, SignUpActivity.this);

        Toast.makeText(getApplicationContext(), "Your Account has been Created", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Please Login With your Email and Password", Toast.LENGTH_LONG).show();
        finish();

    }
}
