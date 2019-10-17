package com.android.popcorn;

import java.util.List;

public class FarmerListItem {

    private int id;
    private int enroll_no;
    private String enroll_center;
    private String farm;
    private String crop;
    private String name;
    private String region;
    private int sort;
    private String crop_image;
    private String profile_image;
    private List followers;
    private int account;

    public FarmerListItem(){}

    public FarmerListItem(int id, int enroll_no, String enroll_center, String farm, String crop, String name, String region, int sort, String crop_image, String profile_image, List followers, int account){
        this.id = id;
        this.enroll_no = enroll_no;
        this.enroll_center = enroll_center;
        this.farm = farm;
        this.crop = crop;
        this.name = name;
        this.region = region;
        this.sort = sort;
        this.crop_image = crop_image;
        this.profile_image = profile_image;
        this.followers = followers;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnroll_no() {
        return enroll_no;
    }

    public void setEnroll_no(int enroll_no) {
        this.enroll_no = enroll_no;
    }

    public String getEnroll_center() {
        return enroll_center;
    }

    public void setEnroll_center(String enroll_center) {
        this.enroll_center = enroll_center;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCrop_image() {
        return crop_image;
    }

    public void setCrop_image(String crop_image) {
        this.crop_image = crop_image;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public List getFollowers() {
        return followers;
    }

    public void setFollowers(List followers) {
        this.followers = followers;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}
