package main;

import model.RiskMap;
import model.Country;
import model.Player;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RISK GAME ENGINE TEST ===\n");

        // 1. Initialize the World (The Graph + The HashMap Index)
        // This runs the code that builds all nodes and connections
        RiskMap worldMap = new RiskMap();
        System.out.println("✓ Map initialized successfully.");

        // 2. PRACTICE: Using the HashMap (The Index)
        // Scenario: A player wants to click on "Brazil". We need to find the Object.
        String targetName = "Brazil";
        Country brazil = worldMap.getCountry(targetName);

        if (brazil != null) {
            System.out.println("\n--- Looking up '" + targetName + "' in HashMap ---");
            System.out.println("Found Node: " + brazil.toString()); // Prints "Brazil"
        }

        // 3. PRACTICE: Using the Graph (The Arcs/Edges)
        // Scenario: Can we attack from Brazil? Let's see who it touches.
        System.out.println("\n--- Checking Neighbors (Graph Traversal) ---");
        assert brazil != null;
        System.out.println("Neighbors of " + brazil.getName() + ":");

        for (Country neighbor : brazil.getNeighbors()) {
            System.out.println(" -> " + neighbor.getName());
        }

        // 4. PRACTICE: Verify Logic
        // Let's verify a connection exists both ways (Undirected Graph)
        Country argentina = worldMap.getCountry("Argentina");
        boolean connected = argentina.getNeighbors().contains(brazil);
        System.out.println("\n--- Integrity Check ---");
        System.out.println("Is Argentina connected back to Brazil? " + connected);

        // 5. PRACTICE: The "Whole World" (Iterating the HashMap values)
        System.out.println("\n--- World Overview ---");
        System.out.println("Total Countries defined: " + worldMap.getAllCountries().size());

        // Let's give the first player a random country to prove ownership works
        Player player1 = new Player("Napoleon", Color.RED, false);
        brazil.setOwner(player1);
        System.out.println("Owner of Brazil is now: " + brazil.getOwner().getName());
    }
}