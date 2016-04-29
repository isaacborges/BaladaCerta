package unlv.erc.partiubalada.view;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.model.User;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Party> parties = new ArrayList<Party>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataFromFB(), MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Party> getDataFromFB() {
        float latitudeTest = (float) 123.5;
        float longitudeTest = 456;
        float priceTest = 50;
        int idTest = 1;
        String startTimeTest = "10h";
        String endTimeTest = "1h";

        parties.add(new Party("Pode ou Não Pode", "podeounaopode", idTest, latitudeTest,
                longitudeTest,
                "Sertanejo", priceTest, startTimeTest, endTimeTest, "Roda do Chopp(Agora)",
                (float) 5));

        parties.add(new Party("Zeze di Camargo e Luciano", "zezedicamargo", idTest, latitudeTest,
                longitudeTest, "Sertanejo", priceTest, startTimeTest, endTimeTest, "Espaço Villa " +
                "Mix(Agora)", (float) 4));


        Party p1 = new Party("Bloco do Primeiro Beijo", "ensaioprimeirobj", idTest, latitudeTest,
                longitudeTest, "Sertanejo", priceTest, startTimeTest, endTimeTest, "Clube do " +
                "Congresso(Agora)", (float) 3);

        parties.add(p1);

        return parties;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }


}
