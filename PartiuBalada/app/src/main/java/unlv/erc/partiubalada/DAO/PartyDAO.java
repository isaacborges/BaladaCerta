package unlv.erc.partiubalada.DAO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.view.MainActivity;
import unlv.erc.partiubalada.view.MyRecyclerViewAdapter;
import unlv.erc.partiubalada.view.PartyInfo;

import java.util.ArrayList;
import java.util.List;

public class PartyDAO {

    private Context context;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Party> parties = new ArrayList<Party>();

    public PartyDAO() {

    }

    public PartyDAO(Context context, RecyclerView.Adapter mAdapter, RecyclerView mRecyclerView) {

        this.context = context;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;

    }

    public ArrayList<Party> getPartiesArray(){

        return parties;

    }

    private Firebase connectToDB() {

        Firebase connection = FactoryConnection.establishConnection();

        return connection;

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

                setPartiesOnView();

                setOnPartyClickAction();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
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
                Party party = parties.get(position);

                Intent intent = new Intent(context, PartyInfo.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(Party.PARTY_SERIALIZABLE_KEY, party);
                intent.putExtras(mBundle);

                context.startActivity(intent);
            }
        });
    }
}
