package unlv.erc.partiubalada.DAO;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import unlv.erc.partiubalada.model.NormalUser;
import unlv.erc.partiubalada.model.Promotion;
import unlv.erc.partiubalada.view.PromotionCreateActivity;
import unlv.erc.partiubalada.view.SignUpActivity;
import com.google.firebase.database.DatabaseReference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.model.User;
import unlv.erc.partiubalada.view.PromotionAdapter;
import unlv.erc.partiubalada.view.PartyInfo;

import java.util.ArrayList;



public class PromotionDAO {

    private static final String TAG = "PartyDAO" ;
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Promotion> promotions = new ArrayList<Promotion>();
    private FirebaseAuth mAuth;
    private DatabaseReference promotionsReference;

    public PromotionDAO() {

    }

    public PromotionDAO(Context context, RecyclerView.Adapter mAdapter, RecyclerView mRecyclerView) {
        this.context = context;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;
    }

    private FirebaseDatabase connectToDB() {

        FirebaseDatabase connectionFirebase = FactoryConnection.establishConnection();

        return connectionFirebase;
    }


    public void savePromotionOnFireBase(final PromotionCreateActivity activity, final Promotion promotion) {


        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("promotions").child(promotion.getPartyName()).setValue(promotion);

        Toast.makeText(activity.getApplicationContext(), "Your Promotion has been Created", Toast.LENGTH_LONG).show();


    }

    public void updatePromotionOnFireBase(final PromotionCreateActivity activity, final Promotion promotion) {


        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("promotions").child(promotion.getPartyName()).setValue(promotion);

        Toast.makeText(activity.getApplicationContext(), "Your Promotion has been Updated", Toast.LENGTH_LONG).show();


    }


    public ArrayList<Promotion> getPromotionsArray(){
        return promotions;
    }



    public ValueEventListener getPromotionsFromFB() {
        promotionsReference = FirebaseDatabase.getInstance().getReference();

        promotionsReference.child("promotions");

        ValueEventListener partiesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " promotions");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Promotion promotion = postSnapshot.getValue(Promotion.class);
                    promotions.add(promotion);
//                    Log.i("Image", party.getPartyImage());
                }

                setPromotionsOnView();
                setOnPartyClickAction();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getUser:onCancelled", databaseError.toException());
            }
        };

        promotionsReference.addListenerForSingleValueEvent(partiesListener);

        return partiesListener;
    }

    public void setPromotionsOnView() {
        mAdapter = new PromotionAdapter(promotions, context);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setOnPartyClickAction() {
        ((PromotionAdapter) mAdapter).setOnItemClickListener(new PromotionAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("Promotions list", " Clicked on Item " + position);

                String callingActivity = context.getClass().getName();
                Promotion promotion = promotions.get(position);
                Intent intent = null;



                Bundle mBundle = new Bundle();
                //mBundle.putSerializable(Promotion.PROMOTION_SERIALIZABLE_KEY, promotion);
                intent.putExtras(mBundle);

                context.startActivity(intent);
            }
        });
    }




}
