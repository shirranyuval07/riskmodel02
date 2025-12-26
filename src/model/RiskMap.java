package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RiskMap {
    // The "Graph" container: Maps a country's name (String) to the actual Node object (Country)
    private final Map<String, Country> world;

    public RiskMap() {
        this.world = new HashMap<>();
        initializeGraph();
    }

    // This method builds the specific shape of the world (Nodes + Edges)
    private void initializeGraph() {
        // --- 1. NORTH AMERICA (Nodes) ---
        createNode("Alaska");
        createNode("Northwest Territory");
        createNode("Greenland");
        createNode("Alberta");
        createNode("Ontario");
        createNode("Quebec");
        createNode("Western United States");
        createNode("Eastern United States");
        createNode("Central America");

        // --- 2. SOUTH AMERICA (Nodes) ---
        createNode("Venezuela");
        createNode("Peru");
        createNode("Brazil");
        createNode("Argentina");

        // ... (You would add Europe, Africa, Asia, Australia here) ...
        // For this example, let's connect the Americas.

        // --- CONNECTIONS (Edges) ---
        // North America Internal
        addConnection("Alaska", "Northwest Territory");
        addConnection("Alaska", "Alberta");
        addConnection("Northwest Territory", "Greenland");
        addConnection("Northwest Territory", "Alberta");
        addConnection("Northwest Territory", "Ontario");
        addConnection("Greenland", "Ontario");
        addConnection("Greenland", "Quebec");
        addConnection("Alberta", "Ontario");
        addConnection("Alberta", "Western United States");
        addConnection("Ontario", "Quebec");
        addConnection("Ontario", "Western United States");
        addConnection("Ontario", "Eastern United States");
        addConnection("Quebec", "Eastern United States");
        addConnection("Western United States", "Eastern United States");
        addConnection("Western United States", "Central America");
        addConnection("Eastern United States", "Central America");

        // South America Internal
        addConnection("Venezuela", "Peru");
        addConnection("Venezuela", "Brazil");
        addConnection("Peru", "Brazil");
        addConnection("Peru", "Argentina");
        addConnection("Brazil", "Argentina");

        // Inter-Continental Bridges
        addConnection("Central America", "Venezuela"); // N. America <-> S. America
        // addConnection("Greenland", "Iceland"); // N. America <-> Europe (Need Europe nodes first)
        // addConnection("Alaska", "Kamchatka");  // N. America <-> Asia (Need Asia nodes first)
    }

    // Helper to create a Node
    private void createNode(String name) {
        world.put(name, new Country(name));
    }

    // Helper to create an Edge (Undirected)
    // This updates the 'neighbors' list inside the Country objects
    public void addConnection(String country1, String country2) {
        Country node1 = world.get(country1);
        Country node2 = world.get(country2);

        // Safety check: Only connect if both exist
        if (node1 != null && node2 != null) {
            node1.addNeighbor(node2);
        } else {
            System.err.println("Error: Cannot connect " + country1 + " to " + country2 + " (One is missing)");
        }
    }

    // Graph Traversal / Accessors
    public Country getCountry(String name) {
        return world.get(name);
    }

    public Collection<Country> getAllCountries() {
        return world.values();
    }
}