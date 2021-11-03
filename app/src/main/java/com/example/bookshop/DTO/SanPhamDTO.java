package com.example.bookshop.DTO;

public class SanPhamDTO {

    int MaSP;
    byte[] ImageSP;
    String TenSP;
    String GiaSP;
    int Sl_SP;
    String MotaSP;

    public SanPhamDTO(int maSP, byte[] imageSP, String tenSP, String giaSP) {
        MaSP = maSP;
        ImageSP = imageSP;
        TenSP = tenSP;
        GiaSP = giaSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public byte[] getImageSP() {
        return ImageSP;
    }

    public void setImageSP(byte[] imageSP) {
        ImageSP = imageSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(String giaSP) {
        GiaSP = giaSP;
    }

    public int getSl_SP() {
        return Sl_SP;
    }

    public void setSl_SP(int sl_SP) {
        Sl_SP = sl_SP;
    }

    public String getMotaSP() {
        return MotaSP;
    }

    public void setMotaSP(String motaSP) {
        MotaSP = motaSP;
    }



}
