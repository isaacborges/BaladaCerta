package unlv.erc.partiubalada.model;

import java.io.Serializable;

public class Promotion implements Serializable {
    public  final static String PROMOTION_SERIALIZABLE_KEY = "promotion_key";
    private String type;
    private String partyName;
    private String promotionDescription;


    public Promotion(){
    }

    public Promotion(String promotionCode, String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }
}