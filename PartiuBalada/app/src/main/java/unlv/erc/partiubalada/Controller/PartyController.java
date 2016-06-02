package unlv.erc.partiubalada.Controller;

import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.DAO.PartyDAO;

import java.util.ArrayList;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;


import android.content.Context;
import android.support.v7.widget.RecyclerView;



public class PartyController {
    public  final static String PARTY_SERIALIZABLE_KEY=Party.PARTY_SERIALIZABLE_KEY;
    private PartyDAO partyDAO;
    private Party party;
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;


    public PartyController(Context context, RecyclerView.Adapter mAdapter, RecyclerView mRecyclerView){

        this.partyDAO = new PartyDAO(context, mAdapter, mRecyclerView);
        this.party = new Party();
        this.context = context;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;

    }

    public ValueEventListener getParties() {

        ValueEventListener event = this.partyDAO.getPartiesFromFB();

        return event;
    }

    public ArrayList<Party> getPatiesArray(){

        ArrayList<Party> parties = this.partyDAO.getPartiesArray();

        return parties;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

}
