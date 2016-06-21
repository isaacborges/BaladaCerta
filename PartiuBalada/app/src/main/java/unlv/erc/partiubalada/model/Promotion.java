package unlv.erc.partiubalada.model;

public class Promotion {

    private int promotionCode;
    private String type;
    private Party party;
    private String promotionDescription;


    public Promotion(){}

    public Promotion(int promotionCode, String type) {
        this.promotionCode = promotionCode;
        this.type = type;
    }

    public int getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(int promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }
}