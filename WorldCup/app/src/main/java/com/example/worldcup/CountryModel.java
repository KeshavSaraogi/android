package com.example.worldcup;

public class CountryModel {

    private String countryName, cupWins;
    private int flagImage;

    public CountryModel(String countryName, String cupWins, int flagImage) {
        this.countryName = countryName;
        this.cupWins = cupWins;
        this.flagImage = flagImage;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCupWins(String cupWins) {
        this.cupWins = cupWins;
    }

    public String getCupWins() {
        return cupWins;
    }

    public void setFlagImage(int flagImage) {
        this.flagImage = flagImage;
    }

    public int getFlagImage() {
        return flagImage;
    }
}
