import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name, description;
    private HashMap<String, Item> items;
    private Graph.Node currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new HashMap<String, Item>();
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
}
