package unlv.erc.partiubalada.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class PartyInfo extends AppCompatActivity {


    private ImageView partyImage;
    private RatingBar partyRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_info);

        Intent intent = getIntent();
        Party party = (Party) intent.getSerializableExtra(Party.PARTY_SERIALIZABLE_KEY);

        setPartyFlyer(party);
    }

    private void setPartyFlyer(Party party) {
        partyImage = (ImageView) findViewById(R.id.partyImage);
        partyRating = (RatingBar) findViewById(R.id.partyRating);

        partyRating.setRating(party.getAmountOfStars());

        String background = party.getPartyImage();
        int imageID = getResources().getIdentifier(background, "drawable", "unlv.erc.partiubalada");
        partyImage.setImageResource(imageID);
        partyImage.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
