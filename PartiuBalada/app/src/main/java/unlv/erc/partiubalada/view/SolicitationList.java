package unlv.erc.partiubalada.view;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Solicitation;

public class SolicitationList extends Activity {

    private ArrayList<Solicitation> solicitations = new ArrayList<Solicitation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.solicitationlist);
    }

}
