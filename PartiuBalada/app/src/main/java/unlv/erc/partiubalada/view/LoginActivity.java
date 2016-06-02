package unlv.erc.partiubalada.view;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import unlv.erc.partiubalada.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        userEmail = (EditText) findViewById(R.id.edit_text_email_id);
        userPassword = (EditText) findViewById(R.id.edit_text_password);

        setFontFamilyOnView();
    }

    private void setFontFamilyOnView() {
        Button buttonSignIn = (Button) findViewById(R.id.button_sign_in);
        TextView doYouHaveAccount = (TextView) findViewById(R.id.doYouHaveAccount);
        TextView signUp = (TextView) findViewById(R.id.signUp);
        TextView bannerTittle = (TextView) findViewById(R.id.bannerTittle);

        Typeface openSans = Typeface.createFromAsset(getAssets(), "OpenSans-CondLight.ttf");
        Typeface openSansBold = Typeface.createFromAsset(getAssets(), "OpenSans-CondBold.ttf");

        userEmail.setTypeface(openSansBold);
        userPassword.setTypeface(openSansBold);
        buttonSignIn.setTypeface(openSans);
        doYouHaveAccount.setTypeface(openSansBold);
        signUp.setTypeface(openSansBold);
        bannerTittle.setTypeface(openSans);
    }

    public void onSignUpClicked(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onLoginClicked(View view) {
        String email = String.valueOf(userEmail.getText());
        String password = String.valueOf(userPassword.getText());

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Email e/ou senha incorretos!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showError() {
        userPassword.setError("Nome de usuário e/ou senha estão incorretos!");
    }
}
