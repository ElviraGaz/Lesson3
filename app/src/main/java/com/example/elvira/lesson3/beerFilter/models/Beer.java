package com.example.elvira.lesson3.beerFilter.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Timur Valiev
 */
public class Beer {

    private long id;

    private String name;

    @SerializedName("tagline")
    private String tagLine;

    @SerializedName("first_brewed")
    private String firstBrewed;

    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    /**
     * Alcohol by volume
     */
    private String abv;

    private String ibu;

    private String srm;

    private String brewed_before;

    private String brewed_after;

    private BeerVolume volume;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBrewed_before() {
        return brewed_before;
    }

    public String getBrewed_after() {
        return brewed_after;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getSrm() {
        return srm;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }

    public BeerVolume getVolume() {
        return volume;
    }

    public void setVolume(BeerVolume volume) {
        this.volume = volume;
    }

}
