package unlv.erc.partiubalada.model;

import java.io.Serializable;
import java.lang.String;

public class Party implements Serializable{
    public  final static String PARTY_SERIALIZABLE_KEY = "party_key";
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
    private Promotion[] promotions;

    public Party() {}

    public Party(String partyName, String partyImage, int idParty, float latitude,
                 float longitude, String type, float price, String startTime, String endTime,
                 String locality, float amountOfStars) {
        this.setPartyName(partyName);
        this.setPartyImage(partyImage);
        this.setIdParty(idParty);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setType(type);
        this.setPrice(price);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setLocality(locality);
        this.setAmountOfStars(amountOfStars);
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyImage() {
        return partyImage;
    }

    public void setPartyImage(String partyImage) {
        this.partyImage = partyImage;
    }

    public int getIdParty() {
        return idParty;
    }

    public void setIdParty(int idParty) {
        this.idParty = idParty;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getAmountOfStars() {
        return amountOfStars;
    }

    public void setAmountOfStars(float amountOfStars) {
        this.amountOfStars = amountOfStars;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Promotion[] getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotion[] promotions) {
        this.promotions = promotions;
    }
}
