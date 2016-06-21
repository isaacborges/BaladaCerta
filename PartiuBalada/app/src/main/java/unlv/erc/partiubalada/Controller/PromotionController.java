package unlv.erc.partiubalada.Controller;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;

import unlv.erc.partiubalada.DAO.PromotionDAO;
import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Promotion;
import unlv.erc.partiubalada.view.LoginActivity;
import unlv.erc.partiubalada.view.PromotionCreateActivity;

public class PromotionController {

    private Promotion promotion;
    private EditText idParty;
    private Spinner typePromotion;
    private EditText promotionDescription;
    PromotionDAO promotionDAO;
    PromotionCreateActivity activity;

    public PromotionController(PromotionCreateActivity activity) {
        this.activity = activity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void startComponents() {

        idParty = (EditText) activity.findViewById(R.id.editTextPartyId);
        promotionDescription = (EditText) activity.findViewById(R.id.editTextDescription);
        typePromotion = (Spinner) activity.findViewById(R.id.spinnerTypePromotion);

    }

    protected void setUpPromotion() {

        promotion = new Promotion();

        promotion.setPartyId(idParty.getText().toString());
        promotion.setPromotionDescription(promotionDescription.getText().toString());
        promotion.setType(typePromotion.getSelectedItem().toString());

    }


    public void savePromotion() {

        setUpPromotion();

        promotionDAO = new PromotionDAO();

        promotionDAO.savePromotionOnFireBase(activity, promotion);

    }




}
