package model;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Player {
    private final String name;
    private final Color color;
    private final boolean isBot;
    private int armiesToPlace;
    private List<Country> territoriesOwned;

    public Player(String name, Color color, boolean isBot) {
        this.name = name;
        this.color = color;
        this.isBot = isBot;
        this.territoriesOwned = new ArrayList<>();
        this.armiesToPlace = 0;
    }

    public void addTerritory(Country country) {
        if (!territoriesOwned.contains(country)) {
            territoriesOwned.add(country);
            country.setOwner(this);
        }
    }

    public void removeTerritory(Country country) {
        if (territoriesOwned.remove(country)) {
            // Only set an owner to null if this player actually owned it
            if (country.getOwner() == this) {
                country.setOwner(null);
            }
        }
    }

    // Calculates how many reinforcements the player gets (Risk Rule: Territories / 3)
    public void calculateTurnIncome() {
        int income = Math.max(3, territoriesOwned.size() / 3);
        this.armiesToPlace += income;
    }

    public String getName() { return name; }
    public Color getColor() { return color; }
    public boolean isBot() { return isBot; }
    public List<Country> getTerritoriesOwned() { return territoriesOwned; }
    public int getArmiesToPlace() { return armiesToPlace; }
    public void removeArmies(int amount) { this.armiesToPlace -= amount; }
}