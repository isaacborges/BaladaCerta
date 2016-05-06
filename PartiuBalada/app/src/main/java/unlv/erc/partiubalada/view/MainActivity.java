package unlv.erc.partiubalada.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
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

        parties.add(new Party("Black Rutz", "racionais", idTest, latitudeTest,
                longitudeTest,
                "Rap", priceTest, startTimeTest, endTimeTest, "Estadio Serejão(Agora)",
                (float) 5));

        parties.add(new Party("Vem pro meu Lounge", "wesleysafadao", idTest, latitudeTest,
                longitudeTest,
                "Forro", priceTest, startTimeTest, endTimeTest, "Estadio Mane Garrincha(Agora)",
                (float) 5));

        parties.add(new Party("Pode ou Não Pode", "podeounaopode", idTest, latitudeTest,
                longitudeTest,
                "Sertanejo", priceTest, startTimeTest, endTimeTest, "Roda do Chopp(Agora)",
                (float) 4));

        parties.add(new Party("Zeze di Camargo e Luciano", "zezedicamargo", idTest, latitudeTest,
                longitudeTest, "Sertanejo", priceTest, startTimeTest, endTimeTest, "Espaço Villa " +
                "Mix(Agora)", (float) 3));


        Party p1 = new Party("Bloco do Primeiro Beijo", "ensaioprimeirobj", idTest, latitudeTest,
                longitudeTest, "Sertanejo", priceTest, startTimeTest, endTimeTest, "Clube do " +
                "Congresso(Agora)", (float) 2.5);

        parties.add(p1);

        return parties;
    }
// Esse é o codigo que coleta os dados do firebase, estes dados devem ser coletados em outra
// tela, esta tela ja deve receber uma lista com todas as festas
//    public ArrayList<Party> getDataFromFB() {
//        Firebase partiesReference = new Firebase("https://baladacerta.firebaseio.com/Parties");
//
//        partiesReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println("There are " + snapshot.getChildrenCount() + " parties");
//                int amountOfParties = 0;
//
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    Party party = postSnapshot.getValue(Party.class);
//                    parties.add(party);
//                    Log.i("Adding party", "number " + amountOfParties);
//                    ((MyRecyclerViewAdapter) mAdapter).addItem(party, amountOfParties);
//                    amountOfParties++;
//                }
//
//                Log.i("creating list with", "this amount of parties = " + amountOfParties);
//                mAdapter = new MyRecyclerViewAdapter(parties, MainActivity.this);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//        });
//
//        return parties;
//    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                Party party = parties.get(position);

                Intent intent = new Intent(MainActivity.this, PartyInfo.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(Party.PARTY_SERIALIZABLE_KEY, party);
                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });
    }


}
