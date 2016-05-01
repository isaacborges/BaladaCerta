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
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Party> mDataset;
    private static MyClickListener myClickListener;
    private Context context;
    private int lastPosition = -1;
    private View view;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView partyName;
        TextView partyLocation;
        ImageView partyImage;
        RatingBar partyRating;
        LinearLayout cardRoot;

        public DataObjectHolder(View itemView) {
            super(itemView);
            partyName = (TextView) itemView.findViewById(R.id.partyName);
            partyLocation = (TextView) itemView.findViewById(R.id.partyLocation);
            partyRating = (RatingBar) itemView.findViewById(R.id.partyRating);
            partyImage = (ImageView) itemView.findViewById(R.id.partyImage);
            cardRoot = (LinearLayout) itemView.findViewById(R.id.card_root);

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
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Typeface openSans = Typeface.createFromAsset(context.getAssets(),
                "OpenSans-CondLight" +
                        ".ttf");
        holder.partyName.setText(mDataset.get(position).getPartyName());
        holder.partyName.setTypeface(openSans);

        holder.partyLocation.setText(mDataset.get(position).getLocality());
        holder.partyLocation.setTypeface(openSans);

        holder.partyRating.setRating(mDataset.get(position).getAmountOfStars());

        String background = mDataset.get(position).getPartyImage();
        int imageID = holder.itemView.getResources().getIdentifier(background, "drawable", "unlv.erc.partiubalada");
        holder.partyImage.setImageResource(imageID);
        holder.partyImage.setScaleType(ImageView.ScaleType.FIT_XY);

        setAnimation(view, position);
    }


    public void addItem(Party dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}