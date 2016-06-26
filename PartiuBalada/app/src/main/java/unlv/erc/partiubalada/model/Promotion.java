package unlv.erc.partiubalada.model;

public class Promotion {
    public  final static String PROMOTION_SERIALIZABLE_KEY = "promotion_key";
    private String promotionCode;
    private String type;
    private String partyName;
    private String promotionDescription;


    public Promotion(){
    }

    public Promotion(String promotionCode, String type) {
        this.promotionCode = promotionCode;
        this.type = type;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
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

    public void setPartyName(String partyId) {
        this.partyName = partyId;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }
}