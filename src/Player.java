
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name, description;
    private HashMap<String, Item> items;
    private Graph.Node currentRoom;
    private double health;
    private boolean alive;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new HashMap<String, Item>();
        health = 100;
        alive = true;
    }

    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public Item removeItem(String name) {
        return items.remove(name);
    }

    public HashMap<String, Item> getItems() {
        return items;
    }


    public Graph.Node getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Node newRoom) {
        this.currentRoom = newRoom;
    }


    public Item getItem(String response) {
        return items.get(response);
    }

    public boolean isAlive() {
        return alive;
    }

    public void loseHealth(double damage) {
        health -= damage;
        if (health <= 0) {
            alive = false;
        }
    }

    public void heal() {
        if (health < 100) {
            health += 4;
        } else {
            health = 100;
        }
    }
}





