package unlv.erc.partiubalada.view;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import unlv.erc.partiubalada.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = (EditText) findViewById(R.id.edit_text_email_id);
        userPassword = (EditText) findViewById(R.id.edit_text_password);

        setFontFamilyOnView();
    }

    private void setFontFamilyOnView() {
        Button buttonSignIn = (Button) findViewById(R.id.button_sign_in);
        TextView doYouHaveAccount = (TextView) findViewById(R.id.doYouHaveAccount);
        TextView signUp = (TextView) findViewById(R.id.signUp);

        Typeface openSans = Typeface.createFromAsset(getAssets(), "OpenSans-CondLight.ttf");
        Typeface openSansBold = Typeface.createFromAsset(getAssets(), "OpenSans-CondBold.ttf");

        userEmail.setTypeface(openSansBold);
        userPassword.setTypeface(openSansBold);
        buttonSignIn.setTypeface(openSans);
        doYouHaveAccount.setTypeface(openSansBold);
        signUp.setTypeface(openSansBold);
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
