package unlv.erc.partiubalada.DAO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.model.User;
import unlv.erc.partiubalada.view.MyRecyclerViewAdapter;
import unlv.erc.partiubalada.view.PartyEditOrDeleteActivity;
import unlv.erc.partiubalada.view.PartyInfo;

import java.util.ArrayList;

public class PartyDAO {
    private static final String MAIN_ACTIVITY = "unlv.erc.partiubalada.view.MainActivity";
    private static final String TAG = "PartyDAO" ;
    private Context context = null;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Party> parties = new ArrayList<Party>();
    private FirebaseAuth mAuth;
    private DatabaseReference partiesReference;

    public PartyDAO() {
        partiesReference = FirebaseDatabase.getInstance().getReference();
        partiesReference.child("Party");

        this.context = mRecyclerView.getContext();
    }

    public PartyDAO(Context context, RecyclerView.Adapter mAdapter, RecyclerView mRecyclerView) {
        this.context = context;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;

        partiesReference = FirebaseDatabase.getInstance().getReference();
        partiesReference.child("Party");
    }

    public void deletePartyFromFirebase(Party party){
        String partyId = party.getIdParty();

        DatabaseReference partyReference = partiesReference.child(partyId);
        partyReference.removeValue();
        Log.i("PartyDAO id", partyId);
        Log.i("PartyDAO name", party.getPartyName());
        Log.i(TAG, "Deletando a balada...");
    }

    public ArrayList<Party> getPartiesArray(){
        return parties;
    }


    private FirebaseDatabase connectToDB() {

        FirebaseDatabase connectionFirebase = FactoryConnection.establishConnection();

        return connectionFirebase;
    }

    public ValueEventListener getPartiesFromFB() {

        ValueEventListener partiesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " parties");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Party party = postSnapshot.getValue(Party.class);
                    parties.add(party);
//                    Log.i("Image", party.getPartyImage());
                }

                setPartiesOnView();

                setOnPartyClickAction();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getUser:onCancelled", databaseError.toException());
            }
        };

        partiesReference.addListenerForSingleValueEvent(partiesListener);

        return partiesListener;
    }

    public void setPartiesOnView() {
        mAdapter = new MyRecyclerViewAdapter(parties, context);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setOnPartyClickAction() {
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("Parties list", " Clicked on Item " + position);

                String callingActivity = context.getClass().getName();
                Party party = parties.get(position);
                Intent intent = null;

                if(callingActivity.equalsIgnoreCase(MAIN_ACTIVITY)) {
                    intent = new Intent(context, PartyInfo.class);
                } else {
                    intent = new Intent(context, PartyEditOrDeleteActivity.class);
                }

                Bundle mBundle = new Bundle();
                mBundle.putSerializable(Party.PARTY_SERIALIZABLE_KEY, party);
                intent.putExtras(mBundle);

                context.startActivity(intent);
            }
        });
    }
}
