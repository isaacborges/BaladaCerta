package unlv.erc.partiubalada.model;

public class Promotion {

    private int promotionCode;
    private String type;
    private Party party;

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
}