package unlv.erc.partiubalada.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ProgressBar;


import com.google.firebase.database.FirebaseDatabase;


import unlv.erc.partiubalada.Controller.PromotionController;
import unlv.erc.partiubalada.R;

public class PromotionCreateActivity extends AppCompatActivity {

    PromotionController promotionController;
    private FirebaseDatabase database;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_create);

        database = FirebaseDatabase.getInstance();

        promotionController = new PromotionController(PromotionCreateActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        promotionController.startComponents();
    }


    public void onCreatePromotionClicked(View view){
        promotionController.savePromotion();
    }


}
