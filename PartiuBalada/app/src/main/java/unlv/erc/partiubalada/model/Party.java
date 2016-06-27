package unlv.erc.partiubalada.model;

import android.graphics.Bitmap;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class Party implements Serializable {
    public final static String PARTY_SERIALIZABLE_KEY = "party_key";
    private String partyName = "";
    private Bitmap partyImage = null;
    private String idParty = "0";
    private String latitude = "0";
    private String longitude = "0";
    private String type = "";
    private String price = "0";
    private String startTime = "0:00";
    private String endTime = "0:00";
    private String amountOfStars = "0";
    private String locality = "";
    private Promotion[] promotions = {};

    public Party() {
    }

    public Party(String partyName,  String latitude, String longitude, String type, String price,
                 String startTime, String endTime, String locality, String amountOfStars) {
        this.setPartyName(partyName);
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

    public Bitmap getPartyImage() {
        return partyImage;
    }

    public void setPartyImage(Bitmap partyImage) {
        this.partyImage = partyImage;
    }

    public String getIdParty() {
        return idParty;
    }

    public void setIdParty(String idParty) {
        this.idParty = idParty;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getAmountOfStars() {
        return amountOfStars;
    }

    public void setAmountOfStars(String amountOfStars) {
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("partyName", partyName);
        result.put("idParty", idParty);
        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("type", type);
        result.put("price", price);
        result.put("startTime", startTime);
        result.put("endTime", endTime);
        result.put("amountOfStars", amountOfStars);
        result.put("locality", locality);

        return result;
    }
}
