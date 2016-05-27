package unlv.erc.partiubalada.DAO;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goliveira on 5/8/16.
 */
public class PartyDAO {

    ArrayList<Party> parties = new ArrayList<Party>();

    public PartyDAO() {

    }

    public ArrayList<Party> getPartiesArray(){
        return parties;

    }


    public ValueEventListener getPartiesFromFB() {

        Firebase partiesReference = new Firebase("https://baladacerta.firebaseio.com/Parties");

        return new ValueEventListener() {
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
        };


    }


}
