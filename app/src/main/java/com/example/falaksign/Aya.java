package com.example.falaksign;

public class Aya {

    private String ayaText;
    private int ayaSound;

    public Aya(String ayaText, int ayaSound) {
        this.ayaText = ayaText;
        this.ayaSound = ayaSound;
    }

    public Aya() {
    }

    public String getAyaText() {
        return ayaText;
    }

    public void setAyaText(String ayaText) {
        this.ayaText = ayaText;
    }

    public int getAyaSound() {
        return ayaSound;
    }

    public void setAyaSound(int ayaSound) {
        this.ayaSound = ayaSound;
    }
}
