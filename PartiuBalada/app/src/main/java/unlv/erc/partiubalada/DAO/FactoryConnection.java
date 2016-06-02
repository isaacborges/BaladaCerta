package unlv.erc.partiubalada.DAO;

import com.google.firebase.database.FirebaseDatabase;

public class FactoryConnection {


    private static FactoryConnection instance;


    public static FirebaseDatabase establishConnection() {

        FirebaseDatabase connection = getInstance().getConnection();

        return connection;
    }

    private static FactoryConnection getInstance(){

        if(instance == null){

            instance = new FactoryConnection();
        }
        else{
            //Do nothing!
        }

        return instance;
    }


    private FirebaseDatabase getConnection() {

        FirebaseDatabase connection;

        connection =  FirebaseDatabase.getInstance();

        return connection;
    }

}
