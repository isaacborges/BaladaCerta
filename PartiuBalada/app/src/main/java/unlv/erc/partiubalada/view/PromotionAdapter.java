package unlv.erc.partiubalada.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import unlv.erc.partiubalada.Controller.PartyController;
import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.model.Promotion;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.DataObjectHolder> {


    private static String LOG_TAG = "PromotionAdapter";
    private ArrayList<Promotion> mDataset;
    private static MyClickListener myClickListener;
    private Context context;
    private int lastPosition = -1;
    private View view;
    private String callingActivity;
    private static Promotion promotion;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView partyName;
        TextView promotionType;
        TextView promotionDescription;
        ImageView promotionImage;
        LinearLayout cardRoot;
        String callingActivity;
        ImageButton deleteParty;
        ImageButton editParty;


        public DataObjectHolder(View itemView, String callingActivity) {
            super(itemView);
            this.callingActivity = callingActivity;

            partyName = (TextView) itemView.findViewById(R.id.partyNamePromotion);
            promotionType = (TextView) itemView.findViewById(R.id.promotionType);
            promotionDescription = (TextView) itemView.findViewById(R.id.promotionDescription);
            promotionImage = (ImageView) itemView.findViewById(R.id.promotionImage);
            cardRoot = (LinearLayout) itemView.findViewById(R.id.card_rootpromotion);


            deleteParty = (ImageButton) itemView.findViewById(R.id.deletePromotion);
            editParty = (ImageButton) itemView.findViewById(R.id.editPromotion);

//                editParty.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.i("editParty", String.valueOf(partyName.getText()));
//                    }
//                });
//
//                deleteParty.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        PartyController partyController = new PartyController();
//
//                        partyController.deleteParty(party);
//                        Toast.makeText(context, "Deletando a balada..."+party.getIdParty(), Toast.LENGTH_LONG).show();
//                    }
//                });

            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public PromotionAdapter(ArrayList<Promotion> myDataset, Context context) {
        this.mDataset = myDataset;
        this.context = context;
        this.callingActivity = context.getClass().getName();

        Log.i(LOG_TAG, "Setting list on: " + context.getClass().getName());
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        verifyCallerActivity(parent);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view, callingActivity);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Typeface openSans = Typeface.createFromAsset(context.getAssets(),
                "OpenSans-CondLight" +
                        ".ttf");

        promotion = mDataset.get(position);

       // if(promotionExists() == true) {

            holder.partyName.setText(promotion.getPartyName());
            holder.partyName.setTypeface(openSans);

            holder.promotionType.setText(promotion.getType());
            holder.promotionType.setTypeface(openSans);

            holder.promotionDescription.setText(promotion.getPromotionDescription());
            holder.promotionDescription.setTypeface(openSans);


       // }else{
           // view.setVisibility(View.GONE);
        //}

//        String background = party.getPartyImage();

//        Log.i("Adapter background", background);

//        int imageID = holder.itemView.getResources().getIdentifier(background, "drawable", "unlv.erc.partiubalada");
//        holder.partyImage.setImageResource(imageID);
//        holder.partyImage.setScaleType(ImageView.ScaleType.FIT_XY);

        setAnimation(view, position);
    }

    /*
     * verifying if the party existis using its name. It was needed because the application is
     * setting items on the view without any information and that are not on Firebase.
     */
    private boolean promotionExists() {
        boolean exists = false;

        if(promotion.getPartyName().length() > 0) {
            exists = true;
        } else {
            exists = false;
        }

        return exists;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(Promotion dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    private void verifyCallerActivity(ViewGroup parent) {

            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_row_promotioncrud, parent, false);

    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}