package unlv.erc.partiubalada.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class PartyEditOrDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_edit_or_delete);

        Intent intent = getIntent();
        Party party = (Party) intent.getSerializableExtra(Party.PARTY_SERIALIZABLE_KEY);

        Log.i("Party Name", party.getPartyName());
    }
}
