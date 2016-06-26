package unlv.erc.partiubalada.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class PartyEditOrDeleteActivity extends AppCompatActivity {
    private EditText editTextPartyName;
    private EditText editTextLocation;
    private EditText editTextPartyLatitude;
    private EditText editTextPartyLongitude;
    private EditText editTextPartyPrice;
    private EditText editTextPartyInitialTime;
    private EditText editTextPartyEndTime;
    private String partyName;
    private String partyLocation;
    private String partyLatitude;
    private String partyLongitude;
    private String partyPrice;
    private String partyInitialTime;
    private String partyEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_edit_or_delete);

        Intent intent = getIntent();
        Party party = (Party) intent.getSerializableExtra(Party.PARTY_SERIALIZABLE_KEY);

        Log.i("Party Name", party.getPartyName());

        startComponents();

        setTextOnComponents(party);
    }

    private void setTextOnComponents(Party party) {
        editTextPartyName.setText(party.getPartyName());
        editTextLocation.setText(party.getLocality());
        editTextPartyLatitude.setText(party.getLatitude().toString());
        editTextPartyLongitude.setText(party.getLongitude().toString());
        editTextPartyPrice.setText(party.getPrice());
        editTextPartyInitialTime.setText(party.getStartTime());
        editTextPartyEndTime.setText(party.getEndTime());
    }

    private void startComponents() {
        editTextPartyName = (EditText) findViewById(R.id.editTextPartyName);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextPartyLatitude = (EditText) findViewById(R.id.editTextPartyLatitude);
        editTextPartyLongitude = (EditText) findViewById(R.id.editTextPartyLongitude);
        editTextPartyPrice = (EditText) findViewById(R.id.editTextPartyPrice);
        editTextPartyInitialTime = (EditText) findViewById(R.id.editTextPartyInitialTime);
        editTextPartyEndTime = (EditText) findViewById(R.id.editTextPartyEndTime);
    }

    private void getEditTextInformations() {
        partyName = editTextPartyName.getText().toString();
        partyLocation = editTextLocation.getText().toString();
        partyLatitude = editTextPartyLatitude.getText().toString();
        partyLongitude = editTextPartyLongitude.getText().toString();
        partyPrice = editTextPartyPrice.getText().toString();
        partyInitialTime = editTextPartyInitialTime.getText().toString();
        partyEndTime = editTextPartyEndTime.getText().toString();
    }

    public void onUpdatePartyClicked(View view) {
        getEditTextInformations();
    }

    public void onDeletePartyClicked(View view) {

    }
}
