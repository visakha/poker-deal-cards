package com.example.demo.data;

/**
 * @author Vamsi Vegi
 * @date 1/7/2020
 */
public enum Suit {

    //SPADES("\u2660\uFE0F"), HEARTS("\u2665\uFE0F"), DIAMONDS("\u2666\uFE0F"), CLUBS("\u2663\uFE0F");
    SPADES("\u2660"), HEARTS("\u2665"), DIAMONDS("\u2666"), CLUBS("\u2663");

    private final String icon;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
