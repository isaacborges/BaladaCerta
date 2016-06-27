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

    private EditText editTextPartyName;
    private EditText editTextDescription;
    private Spinner typePromotion;

    private String partyName;
    private String promotionType;
    private String promotionDescpription;

    private Promotion promotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_edit);

        Intent intent = getIntent();
        promotion = (Promotion)intent.getSerializableExtra(Promotion.PROMOTION_SERIALIZABLE_KEY);

        /*promotion = new Promotion();
        promotion.setPartyName("Testando");
        promotion.setPromotionDescription("Testing");
        promotion.setType("type");*/


        startComponents();

        setTextOnComponents(promotion);
    }



    private void startComponents() {
        editTextPartyName=(EditText)findViewById(R.id.editTextPartyIdEdit);
        editTextDescription=(EditText)findViewById(R.id.editTextDescriptionEdit);

    }

    private void setTextOnComponents(Promotion promotion) {
        editTextPartyName.setText(promotion.getPartyName());
        editTextDescription.setText(promotion.getPromotionDescription());

    }

    private void getEditTextInformations() {
        partyName = editTextPartyName.getText().toString();
        promotionDescpription = editTextDescription.getText().toString();

    }

    private void updatePromotionObject() {
        getEditTextInformations();

        promotion.setPartyName(partyName);
        promotion.setPromotionDescription(promotionDescpription);

    }

    public void onUpdatePromotionClicked(View view) {
        updatePromotionObject();

        PromotionController promotionController = new PromotionController(PromotionEditActivity.this);
        promotionController.updatePromotion(promotion);

        //String dialogText = "A promoção foi atualizada com sucesso!";

       // showDialog(dialogText);
    }

    public void onDeletePartyClicked(View view) {
        PromotionController promotionController = new PromotionController(PromotionEditActivity.this);
       // promotionController.deletePromotion(promotion);

        String dialogText = "A promoção foi deletada com sucesso!";

        //showDialog(dialogText);
    }

   /*
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
    }*/
}
