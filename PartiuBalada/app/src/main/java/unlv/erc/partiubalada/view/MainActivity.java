package unlv.erc.partiubalada.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class MainActivity extends AppCompatActivity {

    private List<Party> parties = new ArrayList<Party>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromFB();

        createList();
    }

    private void createList() {
        ArrayAdapter<Party> adapter = new myListAdapter();
        ListView list = (ListView) findViewById(R.id.partyListView);
        list.setAdapter(adapter);
    }

    public void getDataFromFB() {
        float latitudeTest = (float) 123.5;
        float longitudeTest = 456;
        float priceTest = 50;
        int idTest = 1;
        String startTimeTest = "10h";
        String endTimeTest = "1h";

        parties.add(new Party("Pode ou Não Pode", "PartyImage", idTest, latitudeTest, longitudeTest,
                "Sertanejo", priceTest, startTimeTest, endTimeTest, (float) 5));

        parties.add(new Party("Zeze di Camargo e Luciano", "PartyImage", idTest,latitudeTest,
                longitudeTest, "Sertanejo", priceTest, startTimeTest, endTimeTest, (float)4));

        parties.add(new Party("Bloco do Primeiro Beijo", "PartyImage", idTest, latitudeTest,
                longitudeTest, "Sertanejo", priceTest, startTimeTest, endTimeTest, (float) 3));
    }

    private class myListAdapter extends ArrayAdapter<Party> {
        public myListAdapter() {
            super(MainActivity.this, R.layout.partylistlayout, parties);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.partylistlayout, parent, false);
            }

            //reversing the order of the list and setting it on the Actvity
            Party currentParty = parties.get(parties.size() - (position + 1));

            Log.i("party name", currentParty.getPartyName());

            TextView partyName = (TextView) itemView.findViewById(R.id.partyName);
            partyName.setText(currentParty.getPartyName());

//            String background = currentParty.getPartyImage();
//            int drawableID = getResources().getIdentifier(background, "drawable", getPackageName());
//            itemView.setBackgroundResource(drawableID);

            RatingBar makeRate = (RatingBar) itemView.findViewById(R.id.partyRatingBar);
            makeRate.setRating(currentParty.getAmountOfStars());

            return itemView;
        }

    }
}
