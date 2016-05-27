package unlv.erc.partiubalada.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import unlv.erc.partiubalada.Controller.PartyController;
import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.model.User;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Party> parties = new ArrayList<Party>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getParties();
    }



    public void getParties() {

        PartyController partyController = new PartyController();

        Firebase partiesReference = new Firebase("https://baladacerta.firebaseio.com/Parties");

        ValueEventListener event = partyController.getParties();
        parties = partyController.getPatiesArray();

        partiesReference.addValueEventListener(event);

        setPartiesOnView();

        setOnPartyClickAction();

    }


    private void setOnPartyClickAction() {
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                Party party = parties.get(position);

                Intent intent = new Intent(MainActivity.this, PartyInfo.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(Party.PARTY_SERIALIZABLE_KEY, party);
                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });
    }

    public void setPartiesOnView() {
        mAdapter = new MyRecyclerViewAdapter(parties, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
