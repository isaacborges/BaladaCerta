package unlv.erc.partiubalada.model;

import java.util.ArrayList;

public class Administrator extends  Promoter {

   private ArrayList<String> listOfPrmoters;



    public Administrator(ArrayList<String> listOfPrmoters) {
        this.listOfPrmoters = listOfPrmoters;
    }

    public Administrator(ArrayList<String> listOfParty, ArrayList<String> listOfPrmoters) {
        super(listOfParty);
        this.listOfPrmoters = listOfPrmoters;
    }

    public Administrator(String name, int age, int idUser, String gender, String email, String password, ArrayList<String> listOfParty, ArrayList<String> listOfPrmoters) {
        super(name, age, idUser, gender, email, password, listOfParty);
        this.listOfPrmoters = listOfPrmoters;
    }

    public Administrator(int age, int idUser, String email, String password, ArrayList<String> listOfParty, ArrayList<String> listOfPrmoters) {
        super(age, idUser, email, password, listOfParty);
        this.listOfPrmoters = listOfPrmoters;
    }


    public Administrator() {
    }


    public ArrayList<String> getListOfPrmoters() {
        return listOfPrmoters;
    }

    public void setListOfPrmoters(ArrayList<String> listOfPrmoters) {
        this.listOfPrmoters = listOfPrmoters;
    }
}
