package unlv.erc.partiubalada.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import unlv.erc.partiubalada.Controller.PromotionController;
import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Promotion;


public class PromotionCRUDActivity extends AppCompatActivity {

    private ArrayList<Promotion> promotions = new ArrayList<Promotion>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_crud);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromotionCRUDActivity.this, PromotionCreateActivity.class);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getPromotions();
    }

    public void getPromotions() {
        PromotionController promotionController = new PromotionController(PromotionCRUDActivity.this, mAdapter, mRecyclerView);

        DatabaseReference partiesReference = database.getReference("promotions");

        ValueEventListener event = promotionController.getPromotions();
        promotions = promotionController.getPromotionsArray();

        partiesReference.addValueEventListener(event);
    }

    public void onAccountClicked(View view) {
        Intent intent = new Intent(PromotionCRUDActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void onPromotionAddButton(View view) {
        Intent intent = new Intent(PromotionCRUDActivity.this, PromotionCreateActivity.class);
        startActivity(intent);
    }

    public void onPromotionEditButton(View view) {
        Intent intent = new Intent(PromotionCRUDActivity.this, PromotionEditActivity.class);
        startActivity(intent);
    }


}

