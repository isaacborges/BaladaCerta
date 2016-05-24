package unlv.erc.partiubalada.view;
import com.firebase.client.Firebase;

/**
 * Created by dandaraaranha on 23/05/16.
 */

public class CustomApplication extends android.app.Application {

        @Override
        public void onCreate() {
            super.onCreate();
            Firebase.setAndroidContext(this);
        }
}
