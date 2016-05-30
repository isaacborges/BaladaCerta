package unlv.erc.partiubalada.DAO;

import com.firebase.client.Firebase;


/**
 * Created by dandaraaranha on 29/05/16.
 */


public class FactoryConnection {


    private static FactoryConnection instance;


    public static Firebase establishConnection() {

        Firebase connection = getInstance().getConnection();

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


    private Firebase getConnection() {

        DataBaseConfig database = new DataBaseConfig();

        Firebase connection;

        connection =  new Firebase(database.getLocal());

        return connection;
    }

}
