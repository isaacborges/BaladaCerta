package unlv.erc.partiubalada.Controller;

import android.content.Intent;
import android.widget.EditText;

import unlv.erc.partiubalada.DAO.PromotionDAO;
import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Promotion;
import unlv.erc.partiubalada.view.LoginActivity;
import unlv.erc.partiubalada.view.PromotionCreateActivity;

public class PromotionController {

    private Promotion promotion;
    private EditText idParty;
    private EditText promotionType;
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

    }

    protected void setUpPromotion() {
        promotion = new Promotion();

    }


    public void savePromotion() {

        setUpPromotion();

        promotionDAO = new PromotionDAO();

        promotionDAO.savePromotionOnFireBase(activity, promotion);

    }




}
