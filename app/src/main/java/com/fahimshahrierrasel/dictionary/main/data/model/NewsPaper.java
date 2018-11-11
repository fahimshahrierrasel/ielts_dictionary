package com.fahimshahrierrasel.dictionary.main.data.model;

public class NewsPaper {
    private String name;
    private String description;
    private String url;
    private int logo;

    public NewsPaper(String name, String description, String url, int logo) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
