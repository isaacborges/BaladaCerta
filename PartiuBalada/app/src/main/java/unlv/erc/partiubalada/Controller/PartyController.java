package unlv.erc.partiubalada.Controller;

import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.DAO.PartyDAO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;

/**
 * Created by goliveira on 5/8/16.
 */
public class PartyController {

    PartyDAO partyDAO;

    public PartyController(){
        partyDAO = new PartyDAO();
    }

    public ValueEventListener getParties() {

        ValueEventListener event = partyDAO.getPartiesFromFB();

        return event;
    }

    public ArrayList<Party> getPatiesArray(){

        ArrayList<Party> parties = partyDAO.getPartiesArray();

        return parties;

    }

}
