package unlv.erc.partiubalada.model;

import java.lang.String;

public class Party {

    private String partyName;
    private int idParty;
    private float latitude;
    private float longitude;
    private String type;
    private float price;
    private String startTime;
    private String endTime;
    private float amountOfStars;

    public Party(String partyName, int idParty, float latitude, float longitude, String type, float price, String startTime, String endTime, float amountOfStars) {
        this.partyName = partyName;
        this.idParty = idParty;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amountOfStars = amountOfStars;
    }

    public float getAmountOfStars() {
        return amountOfStars;
    }

    public String getPartyName() {
        return partyName;
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
}
