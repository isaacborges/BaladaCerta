package unlv.erc.partiubalada.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import unlv.erc.partiubalada.Controller.PartyController;
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
    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_edit_or_delete);

        Intent intent = getIntent();
        party = (Party) intent.getSerializableExtra(Party.PARTY_SERIALIZABLE_KEY);

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

    private void updatePartyObject() {
        getEditTextInformations();

        party.setPartyName(partyName);
        party.setLocality(partyLocation);
        party.setLatitude(partyLatitude);
        party.setLongitude(partyLongitude);
        party.setPrice(partyPrice);
        party.setStartTime(partyInitialTime);
        party.setEndTime(partyEndTime);
    }

    public void onUpdatePartyClicked(View view) {
        updatePartyObject();

        PartyController partyController = new PartyController(PartyEditOrDeleteActivity.this);
        partyController.updateParty(party);

        String dialogText = "A balada foi atualizada com sucesso!";

        showDialog(dialogText);
    }

    public void onDeletePartyClicked(View view) {
        PartyController partyController = new PartyController(PartyEditOrDeleteActivity.this);
        partyController.deleteParty(party);

        String dialogText = "A balada foi deletada com sucesso!";

        showDialog(dialogText);
    }

    private void showDialog(String dialogText) {
        Dialog updatePartyDialog = new Dialog(PartyEditOrDeleteActivity.this, R.style.FullHeightDialog);

        updatePartyDialog = new Dialog(PartyEditOrDeleteActivity.this, R.style.FullHeightDialog);
        updatePartyDialog.setContentView(R.layout.normal_dialog);
        updatePartyDialog.setCancelable(true);
        TextView text = (TextView) updatePartyDialog.findViewById(R.id.normalDialogText);

        text.setText(dialogText);

        Button updateButton = (Button) updatePartyDialog.findViewById(R.id.rank_dialog_button);
        final Dialog finalRankDialog = updatePartyDialog;
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalRankDialog.dismiss();

                Intent intent = new Intent(PartyEditOrDeleteActivity.this, PartyCRUDActivity.class);
                startActivity(intent);
            }
        });

        updatePartyDialog.show();
    }
}
