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

import unlv.erc.partiubalada.R;

import java.util.Map;

import unlv.erc.partiubalada.model.NormalUser;

public class SignUpActivity extends AppCompatActivity {

    private Firebase myFirebaseRef;
    private NormalUser user;
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
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Creates a reference for  your Firebase database
        myFirebaseRef =  new Firebase("https://baladacerta.firebaseio.com/");
    }
}
