package unlv.erc.partiubalada.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import unlv.erc.partiubalada.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {
    EditText userEmail;
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = (EditText) findViewById(R.id.edit_text_email_id);
        userPassword = (EditText) findViewById(R.id.edit_text_password);
    }

    public void onSignUpClicked(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onLoginClicked(View view) {

        Firebase myFirebaseRef =  new Firebase("https://baladacerta.firebaseio.com/");
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar_login);

        progressBar.setVisibility(View.VISIBLE);

        myFirebaseRef.authWithPassword(String.valueOf(userEmail.getText()), String.valueOf(userPassword.getText()),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        showError();
                    }
                });

    }

    private void showError () {
        userPassword.setError("Nome de usuário e/ou senha estão incorretos!");
    }
}
