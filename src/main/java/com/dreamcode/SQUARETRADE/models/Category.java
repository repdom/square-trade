package com.dreamcode.SQUARETRADE.models;

public class Category {
    private String name;
    private String keywords;
    private Category parentCategory;

    public Category(String name, String keywords) {
        this.name = name;
        this.keywords = keywords;
    }
    public Category(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
