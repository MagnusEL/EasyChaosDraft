package com.magnusel.easychaosdraft;

import java.util.ArrayList;

/**
 * Created by magnus on 09.10.17.
 */

public class Player {

    private String name;
    private ArrayList<Booster> boosterPool = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Booster> getBoosterPool() {
        return boosterPool;
    }

    public void setBoosterPool(ArrayList<Booster> boosterPool) {
        this.boosterPool = boosterPool;
    }

    public void recieveBooster(Booster theBooster) {
        this.boosterPool.add(theBooster);
    }

    @Override
    public String toString() {

        StringBuilder boosters = new StringBuilder();

        for(int i = 0; i <= boosterPool.size() - 1; i++) {
            boosters.append(boosterPool.get(i).getBoosterName() + ", ");
        }

        return this.name + " gets the " + boosters + " boosters";
    }
}
