package com.example.urunlerproje;

import java.util.Date;

public class Urun {



    String imgurl1;
    String imgurl2;
    String imgurl3;
    String urunAdı;
    String acıklama;
    String tarih;

    int like;
    int fiyat;



    public String getImgurl1() {
        return imgurl1;
    }

    public void setImgurl1(String imgurl1) {
        this.imgurl1 = imgurl1;
    }

    public String getImgurl2() {
        return imgurl2;
    }

    public void setImgurl2(String imgurl2) {
        this.imgurl2 = imgurl2;
    }

    public String getImgurl3() {
        return imgurl3;
    }

    public void setImgurl3(String imgurl3) {
        this.imgurl3 = imgurl3;
    }

    public String getUrunAdı() {
        return urunAdı;
    }

    public void setUrunAdı(String urunAdı) {
        this.urunAdı = urunAdı;
    }

    public String getAcıklama() {
        return acıklama;
    }

    public void setAcıklama(String acıklama) {
        this.acıklama = acıklama;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }


    public Urun(){

    }

    public Urun(String urunAdı, String acıklama, int fiyat,String imgurl1,String imgurl2,String imgurl3, String tarih,int like  ) {
        this.like = like;
        this.imgurl1 = imgurl1;
        this.imgurl2 = imgurl2;
        this.imgurl3 = imgurl3;
        this.urunAdı = urunAdı;
        this.acıklama = acıklama;
        this.tarih = tarih;
        this.fiyat = fiyat;
    }



}

