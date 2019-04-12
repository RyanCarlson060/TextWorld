import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Graph {
    private HashMap<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<String, Node>();
    }

    public void addNode(String name, String description) {
        nodes.put(name, new Node(name, description));
    }

    public void addDirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public class Node {
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private HashMap<String, Item> items;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private Node(String name, String description) {
            neighbors = new HashMap<String, Node>();
            this.name = name;
            this.description = description;
            items = new HashMap<>();
        }

        public HashMap<String, Item> getItems() {
            return items;
        }

        public String getItemNames() {
            String s = "";
            for (String name : items.keySet()) {
                s = s + name + ", ";
            }
            if (s.equals("")) return "none";
            return s;
        }


        public Item removeItem(String name) {
            return items.remove(name);
        }

        public void addItem(Item item) {
            items.put(item.getName(), item);
        }



        public String getName() {
            return name;
        }

        private void addNeighbor(Node neighbor) {
            neighbors.put(neighbor.getName(), neighbor);
        }

        public String getNeighborNames() {
            String s = "";
            for (String name : neighbors.keySet()) {
                s = s + name + ", ";
            }
            if (s.equals("")) return "none";
            return s;
        }

        public Node getNeighbor(String name) {
            return neighbors.get(name);
        }

        public Item getItem(String response) {
            return items.get(response);
        }
    }
}

