package unlv.erc.partiubalada.DAO;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import unlv.erc.partiubalada.model.NormalUser;
import unlv.erc.partiubalada.view.SignUpActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;


public class NormalUserDAO {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    private FirebaseDatabase connectToDB() {

        FirebaseDatabase connectionFirebase = FactoryConnection.establishConnection();

        return connectionFirebase;
    }


    public void saveUserOnFireBase(final SignUpActivity activity, final NormalUser user) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if(task.isSuccessful()) {
                            Toast.makeText(activity.getApplicationContext(), "Your Account has been Created", Toast.LENGTH_LONG).show();
                            Toast.makeText(activity.getApplicationContext(), "Please Login With your Email and Password", Toast.LENGTH_LONG).show();
                            activity.finish();
                        }  {
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
