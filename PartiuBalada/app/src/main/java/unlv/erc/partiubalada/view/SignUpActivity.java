package unlv.erc.partiubalada.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.FirebaseDatabase;

import unlv.erc.partiubalada.Controller.NormalUserController;
import unlv.erc.partiubalada.R;

public class SignUpActivity extends AppCompatActivity {
    private NormalUserController userController;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseDatabase.getInstance();

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