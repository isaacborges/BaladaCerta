package unlv.erc.partiubalada.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import unlv.erc.partiubalada.Controller.PartyController;
import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class PartyCRUDActivity extends AppCompatActivity {
    private ArrayList<Party> parties = new ArrayList<Party>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_crud);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        database = FirebaseDatabase.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getParties();
    }

    public void getParties() {
        PartyController partyController = new PartyController(PartyCRUDActivity.this, mAdapter, mRecyclerView);

        DatabaseReference partiesReference = database.getReference("Parties");

        ValueEventListener event = partyController.getParties();
        parties = partyController.getPatiesArray();

        partiesReference.addValueEventListener(event);
    }

    public void onAccountClicked(View view) {
        Intent intent = new Intent(PartyCRUDActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void onPartiesClicked(View view) {
        Intent intent = new Intent(PartyCRUDActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
