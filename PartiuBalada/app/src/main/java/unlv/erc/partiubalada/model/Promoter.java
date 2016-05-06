package unlv.erc.partiubalada.model;

import java.util.ArrayList;

/**
 * Created by Dandara on 03/04/2016.
 */
public class Promoter extends User {


    private ArrayList<String> listOfParty;

    public Promoter() {

    }

    public Promoter(ArrayList<String> listOfParty) {
        this.listOfParty = listOfParty;
    }

    public Promoter(String name, int age, int idUser, String gender, String email, String password, ArrayList<String> listOfParty) {
        super(name, age, idUser, gender, email, password);
        this.listOfParty = listOfParty;
    }

    public Promoter(int age, int idUser, String email, String password, ArrayList<String> listOfParty) {
        super(age, idUser, email, password);
        this.listOfParty = listOfParty;
    }


    public ArrayList<String> getListOfParty() {
        return listOfParty;
    }

    public void setListOfParty(ArrayList<String> listOfParty) {
        this.listOfParty = listOfParty;
    }
}
