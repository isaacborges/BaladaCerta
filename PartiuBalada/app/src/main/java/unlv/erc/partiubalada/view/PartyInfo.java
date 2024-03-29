package unlv.erc.partiubalada.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class PartyInfo extends AppCompatActivity {
    private ImageView partyImage;
    private RatingBar partyRating;
    private TextView partyName;
    private TextView partyLocation;
    private TextView partyLocationTittle;
    private TextView partyLocationDescription;
    private TextView partyLineUpTittle;
    private TextView partyLineUp;
    private TextView partyTicketsTittle;
    private TextView partyTicketFemaleTittle;
    private TextView partyTicketFemalePrice;
    private TextView partyTicketMaleTittle;
    private TextView partyTicketMalePrice;
    private Button evaluateParty;
    private Typeface openSans;
    private Typeface openSansBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_info);

        Intent intent = getIntent();
        Party party = (Party) intent.getSerializableExtra(Party.PARTY_SERIALIZABLE_KEY);

        setPartyFlyer(party);

        setTypefaceOnViewTexts(party);

        createRatingDilag(party);
    }

    private void createRatingDilag(final Party party) {
        evaluateParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog rankDialog = new Dialog(PartyInfo.this, R.style.FullHeightDialog);
                RatingBar ratingBar = new RatingBar(getApplicationContext());

                rankDialog = new Dialog(PartyInfo.this, R.style.FullHeightDialog);
                rankDialog.setContentView(R.layout.rank_dialog);
                rankDialog.setCancelable(true);
                ratingBar = (RatingBar) rankDialog.findViewById(R.id.dialogRatingbar);

                TextView text = (TextView) rankDialog.findViewById(R.id.rankDialogText1);
                text.setTypeface(openSansBold);

                Button updateButton = (Button) rankDialog.findViewById(R.id.rankDialogButton);
                updateButton.setTypeface(openSans);
                final Dialog finalRankDialog = rankDialog;
                final RatingBar finalRatingBar = ratingBar;
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalRankDialog.dismiss();

                        Log.i("Amount of stars", finalRatingBar.getRating() + "");

                        Intent intent = new Intent(PartyInfo.this, CheckLocation.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable(Party.PARTY_SERIALIZABLE_KEY, party);
                        intent.putExtras(mBundle);

                        startActivity(intent);
                    }


                });

                rankDialog.show();
            }
        });
    }

    private void setTypefaceOnViewTexts(Party party) {
        openSans = Typeface.createFromAsset(getAssets(), "OpenSans-CondLight.ttf");
        openSansBold = Typeface.createFromAsset(getAssets(), "OpenSans-CondBold.ttf");

        partyName = (TextView) findViewById(R.id.partyName);
        partyName.setText(party.getPartyName());
        partyName.setTypeface(openSansBold);

        partyLocation = (TextView) findViewById(R.id.partyLocation);
        partyLocation.setText(party.getLocality());
        partyLocation.setTypeface(openSans);

        partyLocationTittle = (TextView) findViewById(R.id.partyLocationTittle);
        partyLocationTittle.setTypeface(openSansBold);

        partyLocationDescription = (TextView) findViewById(R.id.partyLocationDesciption);
        partyLocationDescription.setTypeface(openSans);

        partyLineUpTittle = (TextView) findViewById(R.id.partyLineUpTittle);
        partyLineUpTittle.setTypeface(openSansBold);

        partyLineUp = (TextView) findViewById(R.id.partyLineUp);
        partyLineUp.setTypeface(openSans);

        partyTicketsTittle = (TextView) findViewById(R.id.partyTicketsTittle);
        partyTicketsTittle.setTypeface(openSansBold);

        partyTicketFemaleTittle = (TextView) findViewById(R.id.partyTicketFemaleTittle);
        partyTicketFemaleTittle.setTypeface(openSans);

        partyTicketFemalePrice = (TextView) findViewById(R.id.partyTicketFemalePrice);
        partyTicketFemalePrice.setTypeface(openSans);

        partyTicketMaleTittle = (TextView) findViewById(R.id.partyTicketMaleTittle);
        partyTicketMaleTittle.setTypeface(openSans);

        partyTicketMalePrice = (TextView) findViewById(R.id.partyTicketMalePrice);
        partyTicketMalePrice.setTypeface(openSans);

        evaluateParty = (Button) findViewById(R.id.evaluateParty);
        evaluateParty.setTypeface(openSans);
    }

    private void setPartyFlyer(final Party party) {
        partyImage = (ImageView) findViewById(R.id.partyImage);
        partyRating = (RatingBar) findViewById(R.id.partyRating);

        partyRating.setRating(Float.parseFloat(party.getAmountOfStars()));

        setImageOnBanner(party);
    }

    private void setImageOnBanner(final Party party) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://project-8420821685282639830.appspot.com");

        StorageReference partyImageRef = storageRef.child("images/party"+party.getIdParty());

        final long ONE_MEGABYTE = 1024 * 1024;
        partyImageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap partyImageBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                partyImage.setImageBitmap(partyImageBitmap);
                partyImage.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("Failed to get image", exception.toString());
            }
        });
    }

    public void onAccountClicked(View view) {
        Intent intent = new Intent(PartyInfo.this, AccountActivity.class);
        startActivity(intent);
    }

    public void onPartiesButtonClicked(View view) {
        Intent intent = new Intent(PartyInfo.this, PartyCRUDActivity.class);
        startActivity(intent);
    }

    public void onPartiesClicked(View view) {
        Intent intent = new Intent(PartyInfo.this, MainActivity.class);
        startActivity(intent);
    }
}
