package unlv.erc.partiubalada.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;
import unlv.erc.partiubalada.model.Promotion;

public class PromotionActivity extends AppCompatActivity {

    private List<Promotion> promotions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpromotion);

        getDataFromFB();

        createList();

    }


    private void createList() {
        ArrayAdapter<Promotion> adapter = new ListAdapterPromotion();
        ListView list = (ListView) findViewById(R.id.promotionListView);
        list.setAdapter(adapter);
    }

    public void getDataFromFB() {

        float latitudeTest = (float) 123.5;
        float longitudeTest = 456;
        float priceTest = 50;
        int idTest = 1;
        String startTimeTest = "10h";
        String endTimeTest = "1h";

        promotions.add(new Promotion(101,"50% de desconto no ingresso para os clientes do App #BaladaCerta",R.drawable.sale,new Party("Pode ou Não Pode", "podeounaopode", idTest, latitudeTest,
                longitudeTest,
                "Sertanejo", priceTest, startTimeTest, endTimeTest, "Roda do Chopp(Agora)",
                (float) 5)));

        promotions.add(new Promotion(102, "Desconto Ingresso", "50% de desconto no ingresso para os clientes do App #BaladaCerta",R.drawable.sale,new Party("Zeze di Camargo e Luciano", "zezedicamargo", idTest, latitudeTest,
                longitudeTest, "Sertanejo", priceTest, startTimeTest, endTimeTest, "Espaço Villa " +
                "Mix(Agora)",(float) 4)));

    }

    private class ListAdapterPromotion extends ArrayAdapter<Promotion> {
        public ListAdapterPromotion() {
            super(PromotionActivity.this, R.layout.promotionlayout, promotions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = convertView;

            Typeface openSans;

            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.promotionlayout, null);
            }

            Promotion currentPromotion = promotions.get(position);


            ImageView promotionImage = (ImageView) itemView.findViewById(R.id.promotionImage);
            promotionImage.setImageResource(currentPromotion.getPromotionImage());

            TextView promotionParty = (TextView) itemView.findViewById(R.id.partyName);
            promotionParty.setText(currentPromotion.getParty().getPartyName());

            TextView promotionDescription = (TextView) itemView.findViewById(R.id.promotionDescription);
            promotionDescription.setText(currentPromotion.getDescription());


            return itemView;
        }

    }

}
