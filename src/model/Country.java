package model;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private Player owner;
    private int armyCount;
    // This List represents the "Edges" connecting this Node to others
    private List<Country> neighbors;

    public Country(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
        this.armyCount = 0;
        this.owner = null; // Initially unowned
    }

    // --- Graph Connection Logic ---
    public void addNeighbor(Country neighbor) {
        // Prevent duplicate connections or connecting to self
        if (neighbor != null && !this.neighbors.contains(neighbor) && neighbor != this) {
            this.neighbors.add(neighbor);
            // Since the graph is undirected (borders go both ways), connect back:
            neighbor.getNeighbors().add(this);
        }
    }

    // --- Standard Getters/Setters ---
    public String getName() { return name; }

    public int getArmyCount() { return armyCount; }
    public void setArmyCount(int armyCount) { this.armyCount = armyCount; }

    public Player getOwner() { return owner; }
    public void setOwner(Player owner) { this.owner = owner; }

    public List<Country> getNeighbors() { return neighbors; }

    // Vital for debugging: Prints the name instead of "model.Country@5f150435"
    @Override
    public String toString() {
        return name;
    }
}