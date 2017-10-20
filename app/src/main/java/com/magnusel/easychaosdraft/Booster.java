package com.magnusel.easychaosdraft;

/**
 * Created by magnus on 09.10.17.
 */

public class Booster {

    private String boosterName;
    private int boosterPrice;

    public Booster(String boosterName) {
        this.boosterName = boosterName;
    }

    public String getBoosterName() {
        return boosterName;
    }

    public void setBoosterName(String boosterName) {
        this.boosterName = boosterName;
    }

    public int getBoosterPrice() {
        return boosterPrice;
    }

    public void setBoosterPrice(int boosterPrice) {
        this.boosterPrice = boosterPrice;
    }

    @Override
    public String toString() {
        return this.boosterName;
    }
}
