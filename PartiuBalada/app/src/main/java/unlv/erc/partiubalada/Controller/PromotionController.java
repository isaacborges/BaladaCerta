package unlv.erc.partiubalada.Controller;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;

import unlv.erc.partiubalada.DAO.PromotionDAO;
import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Promotion;
import unlv.erc.partiubalada.view.LoginActivity;
import unlv.erc.partiubalada.view.PromotionCreateActivity;
import unlv.erc.partiubalada.view.PromotionEditActivity;
import java.util.ArrayList;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;


import android.content.Context;
import android.support.v7.widget.RecyclerView;


public class PromotionController {

    public  final static String PROMOTION_SERIALIZABLE_KEY=Promotion.PROMOTION_SERIALIZABLE_KEY;
    private Promotion promotion;
    private EditText nameParty;
    private Spinner typePromotion;
    private EditText promotionDescription;
    PromotionDAO promotionDAO;
    PromotionCreateActivity activity;
    PromotionEditActivity activityEdit;
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;

    public PromotionController(PromotionCreateActivity activity) {
        this.activity = activity;
    }
    public PromotionController(PromotionEditActivity activity) {
        this.activityEdit = activity;
    }


    public PromotionController(Context context, RecyclerView.Adapter mAdapter, RecyclerView mRecyclerView){

        this.promotionDAO = new PromotionDAO(context, mAdapter, mRecyclerView);
        this.promotion = new Promotion();
        this.context = context;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;

    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void startComponents() {

        nameParty = (EditText) activity.findViewById(R.id.editTextPartyId);
        promotionDescription = (EditText) activity.findViewById(R.id.editTextDescription);
        typePromotion = (Spinner) activity.findViewById(R.id.spinnerTypePromotion);

    }

    protected void setUpPromotion() {

        promotion = new Promotion();

        promotion.setPartyName(nameParty.getText().toString());
        promotion.setPromotionDescription(promotionDescription.getText().toString());
        promotion.setType(typePromotion.getSelectedItem().toString());

    }


    public void savePromotion() {

        setUpPromotion();

        promotionDAO = new PromotionDAO();

        promotionDAO.savePromotionOnFireBase(activity, promotion);

    }

    public void updatePromotion(Promotion promotion) {

        promotionDAO = new PromotionDAO();

        promotionDAO.updatePromotionOnFireBase(activityEdit, promotion);

    }

    public void deletePromotion(Promotion promotion){
        PromotionDAO partyDAO = new PromotionDAO();

        partyDAO.deletePromotionOnFirebase(activityEdit, promotion);
    }


    public ValueEventListener getPromotions() {

        ValueEventListener event = this.promotionDAO.getPromotionsFromFB();

        return event;
    }

    public ArrayList<Promotion> getPromotionsArray(){

        ArrayList<Promotion> promotions = this.promotionDAO.getPromotionsArray();

        return promotions;
    }

}
