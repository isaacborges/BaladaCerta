package unlv.erc.partiubalada.view;

import android.content.Context;
import android.graphics.Typeface;
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

import java.util.ArrayList;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static final String MAIN_ACTIVITY = "unlv.erc.partiubalada.view.MainActivity";
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Party> mDataset;
    private static MyClickListener myClickListener;
    private static Context context;
    private int lastPosition = -1;
    private View view;
    private String callingActivity;
    private static Party party;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView partyName;
        TextView partyLocation;
        ImageView partyImage;
        ImageButton deleteParty;
        ImageButton editParty;
        RatingBar partyRating;
        LinearLayout cardRoot;
        String callingActivity;

        public DataObjectHolder(View itemView, String callingActivity) {
            super(itemView);
            this.callingActivity = callingActivity;

            partyName = (TextView) itemView.findViewById(R.id.partyName);
            partyLocation = (TextView) itemView.findViewById(R.id.partyLocation);
            partyImage = (ImageView) itemView.findViewById(R.id.partyImage);
            cardRoot = (LinearLayout) itemView.findViewById(R.id.card_root);

            if (callingActivity.equalsIgnoreCase(MAIN_ACTIVITY)) {
                partyRating = (RatingBar) itemView.findViewById(R.id.partyRating);
            } else {
                deleteParty = (ImageButton) itemView.findViewById(R.id.deleteParty);
                editParty = (ImageButton) itemView.findViewById(R.id.editParty);
            }

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

    public MyRecyclerViewAdapter(ArrayList<Party> myDataset, Context context) {
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

        party = mDataset.get(position);

        if(partyExists() == true) {

            holder.partyName.setText(party.getPartyName());
            holder.partyName.setTypeface(openSans);

            holder.partyLocation.setText(party.getLocality());
            holder.partyLocation.setTypeface(openSans);

            if (callingActivity.equalsIgnoreCase(MAIN_ACTIVITY)) {
                holder.partyRating.setRating(Float.parseFloat(party.getAmountOfStars()));
            } else {
                //nothing to do
            }
        }else{
            view.setVisibility(View.GONE);
        }

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
    private boolean partyExists() {
        boolean exists = false;

        if(party.getPartyName().length() > 0) {
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

    public void addItem(Party dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    private void verifyCallerActivity(ViewGroup parent) {
        if (callingActivity.equalsIgnoreCase(MAIN_ACTIVITY)) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_row, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_row_crud, parent, false);
        }
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