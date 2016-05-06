package unlv.erc.partiubalada.model;

public class Promotion {

    private int promotionCode;
    private String type;
    private String description;
    private int promotionImage;
    private Party party;

    public Promotion(int promotionCode, String type, String description, int promotionImage, Party party) {
        this.promotionCode = promotionCode;
        this.type = type;
        this.description= description;
        this.promotionImage = promotionImage;
        this.party = party;
    }

    public Promotion(int promotionCode, String description, int promotionImage, Party party) {
        this.promotionCode = promotionCode;
        this.description= description;
        this.promotionImage = promotionImage;
        this.party = party;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPromotionImage() {
        return promotionImage;
    }

    public void setPromotionImage(int promotionImage) {
        this.promotionImage = promotionImage;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}