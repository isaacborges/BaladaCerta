package unlv.erc.partiubalada.model;

import java.lang.String;

public class Party {

    private String partyName;
    private String partyImage;
    private int idParty;
    private float latitude;
    private float longitude;
    private String type;
    private float price;
    private String startTime;
    private String endTime;
    private float amountOfStars;
    private String locality;

    public Party(String partyName, String partyImage, int idParty, float latitude,
                 float longitude, String type, float price, String startTime, String endTime,
                 String locality, float amountOfStars) {
        this.partyName = partyName;
        this.partyImage = partyImage;
        this.idParty = idParty;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.locality = locality;
        this.amountOfStars = amountOfStars;
    }

    public float getAmountOfStars() {
        return amountOfStars;
    }

    public String getPartyName() {
        return partyName;
    }

    public String getPartyImage() {
        return partyImage;
    }

    public int getIdParty() {
        return idParty;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality){
        this.locality = locality;
    }
}
