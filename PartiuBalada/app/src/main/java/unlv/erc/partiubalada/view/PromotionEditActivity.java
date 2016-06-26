package unlv.erc.partiubalada.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ProgressBar;


import com.google.firebase.database.FirebaseDatabase;


import unlv.erc.partiubalada.Controller.PromotionController;
import unlv.erc.partiubalada.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.model.Promotion;

public class PromotionEditActivity extends AppCompatActivity {

    PromotionController promotionController;
    private FirebaseDatabase database;
    public ProgressBar progressBar;

    private EditText nameParty;
    private Spinner typePromotion;
    private EditText promotionDescription;

    Promotion promotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_edit);

        Intent intent = getIntent();
        promotion = (Promotion) intent.getSerializableExtra(Promotion.PROMOTION_SERIALIZABLE_KEY);

        Log.i("Promotion Name", promotion.getPartyName());

        startComponents();

        setTextOnComponents(promotion);

    }


    private void startComponents(){

            nameParty = (EditText) findViewById(R.id.editTextPartyIdEdit);
            promotionDescription = (EditText)findViewById(R.id.editTextDescriptionEdit);
            typePromotion = (Spinner)findViewById(R.id.spinnerTypePromotionEdit);

    }

    private void setTextOnComponents(Promotion promotion) {
        nameParty.setText(promotion.getPartyName());
        promotionDescription.setText(promotion.getPromotionDescription());
        typePromotion.setTag(promotion.getType());

    }

    public void onPromotionEditClicked(View view){

        promotionController = new PromotionController(PromotionEditActivity.this);

        promotion.setPartyName(nameParty.getText().toString());
        promotion.setPromotionDescription(promotionDescription.getText().toString());
        promotion.setType(typePromotion.getSelectedItem().toString());


        promotionController.updatePromotion(promotion);
    }



}
