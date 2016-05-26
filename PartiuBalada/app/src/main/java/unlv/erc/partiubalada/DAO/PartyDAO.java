package unlv.erc.partiubalada.DAO;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.AbstractCollection;
import java.util.ArrayList;

import unlv.erc.partiubalada.Controller.PartyController;
import unlv.erc.partiubalada.model.Party;


public class PartyDAO {

    Context context;
    ArrayList<Party> parties = new ArrayList<Party>();



    //implementar singleton!!!

    PartyDAO(Context context) {
        this.context = context;
    }


    public static ArrayList<Party> getPartiesFromFB() {
        Firebase partiesReference = new Firebase("https://baladacerta.firebaseio.com/Parties");

        partiesReference.addValueEventListener(new ValueEventListener() {
            public AbstractCollection<Party> parties;

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " parties");

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Party party = postSnapshot.getValue(Party.class);
                    parties.add(party);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return null;
    }
}
