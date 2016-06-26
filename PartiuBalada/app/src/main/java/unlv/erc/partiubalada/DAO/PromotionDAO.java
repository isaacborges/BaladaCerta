package unlv.erc.partiubalada.DAO;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import unlv.erc.partiubalada.model.NormalUser;
import unlv.erc.partiubalada.model.Promotion;
import unlv.erc.partiubalada.view.PromotionCreateActivity;
import unlv.erc.partiubalada.view.SignUpActivity;
import com.google.firebase.database.DatabaseReference;

public class PromotionDAO {


    private FirebaseDatabase connectToDB() {

        FirebaseDatabase connectionFirebase = FactoryConnection.establishConnection();

        return connectionFirebase;
    }


    public void savePromotionOnFireBase(final PromotionCreateActivity activity, final Promotion promotion) {


        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("promotions").child(promotion.getPartyId()).setValue(promotion);

        Toast.makeText(activity.getApplicationContext(), "Your Promotion has been Created", Toast.LENGTH_LONG).show();


    }





}
