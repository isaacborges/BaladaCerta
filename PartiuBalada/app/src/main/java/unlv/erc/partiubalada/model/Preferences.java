package unlv.erc.partiubalada.model;

public class Preferences {

    private String favoritePartyStyle;
    private String favoriteDrinkType;
    private String favoriteMusicStyle;
    private String mostVisitedPlace;

    public Preferences() {
    }

    public Preferences(String favoritePartyStyle, String favoriteDrinkType, String favoriteMusicStyle, String mostVisitedPlace) {
        this.favoritePartyStyle = favoritePartyStyle;
        this.favoriteDrinkType = favoriteDrinkType;
        this.favoriteMusicStyle = favoriteMusicStyle;
        this.mostVisitedPlace = mostVisitedPlace;
    }

    public String getFavoriteDrinkType() {
        return favoriteDrinkType;
    }

    public void setFavoriteDrinkType(String favoriteDrinkType) {
        this.favoriteDrinkType = favoriteDrinkType;
    }

    public String getFavoritePartyStyle() {
        return favoritePartyStyle;
    }

    public void setFavoritePartyStyle(String favoritePartyStyle) {
        this.favoritePartyStyle = favoritePartyStyle;
    }

    public String getFavoriteMusicStyle() {
        return favoriteMusicStyle;
    }

    public void setFavoriteMusicStyle(String favoriteMusicStyle) {
        this.favoriteMusicStyle = favoriteMusicStyle;
    }

    public String getMostVisitedPlace() {
        return mostVisitedPlace;
    }

    public void setMostVisitedPlace(String mostVisitedPlace) {
        this.mostVisitedPlace = mostVisitedPlace;
    }
}
