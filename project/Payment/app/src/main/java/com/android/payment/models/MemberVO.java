package com.android.payment.models;

public class MemberVO {

    private String name;
    private int sgroup;
    private int pay1;
    private int pay2;
    private int pay3;
    private String date;
    private int tempexcept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSgroup() {
        return sgroup;
    }

    public void setSgroup(int sgroup) {
        this.sgroup = sgroup;
    }

    public int getPay1() {
        return pay1;
    }

    public void setPay1(int pay1) {
        this.pay1 = pay1;
    }

    public int getPay2() {
        return pay2;
    }

    public void setPay2(int pay2) {
        this.pay2 = pay2;
    }

    public int getPay3() {
        return pay3;
    }

    public void setPay3(int pay3) {
        this.pay3 = pay3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTempexcept() {
        return tempexcept;
    }

    public void setTempexcept(int tempexcept) {
        this.tempexcept = tempexcept;
    }

}
