package unlv.erc.partiubalada.view;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import unlv.erc.partiubalada.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    public void onPartiesButtonClicked(View view) {
        Intent intent = new Intent(AccountActivity.this, PartyCRUDActivity.class);
        startActivity(intent);
    }

    public void onPartiesClicked(View view) {
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
