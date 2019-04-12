
import java.util.ArrayList;
import java.util.HashMap;


public class Graph {
    private HashMap<String, Node> nodes;
    private ArrayList<Creature> creatures;
    private String[] roomNameList = {"hall", "dungeon", "closet", "ballroom", "kitchen", "bathroom", "office", "living room", "gym"};
    private String[] descriptionList = {"cool", "warm", "dark", "bright", "interesting stuff", "lots of toys", "awesome", "boring stuff"};

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void addCreature(Creature c) {
        creatures.add(c);
        c.getRoom().addCreature(c);
    }

    public Graph() {
        nodes = new HashMap<String, Node>();
        creatures = new ArrayList<>();
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

    public void addRandomCreatures(Player p1) {
        for (int i = 0; i < (int) (Math.random() * 200) + 50; i++) {
            Creature c = new Chicken(getRandomRoom(), p1);
            this.addCreature(c);
        }

        for (int i = 0; i < (int) (Math.random() * 75) + 25; i++) {
            Creature c = new Wumpus(getRandomRoom(), p1);
            this.addCreature(c);
        }

        for (int i = 0; i < (int) (Math.random() * 10) + 1; i++) {
            Creature c = new Popstar(getRandomRoom(), p1);
            this.addCreature(c);
        }


    }

    public Node getRandomRoom() {
        ArrayList<Node> nodeList = new ArrayList<Node>(nodes.values());
        return nodeList.get((int) (nodeList.size() * Math.random()));
    }

    public void generateRoomStructure() {
        for (int i = 0; i < 8; i++) {
            String name;
            do {
                name = roomNameList[(int) (Math.random() * roomNameList.length)];
            } while (nodes.get(name) != null);
            String description = descriptionList[(int) (Math.random() * descriptionList.length)];
            addNode(name, description);
            if (nodes.size() > 1) {
                addUndirectedEdge(getRandomRoomBesides(name), name);
            }
        }
    }

    private String getRandomRoomBesides(String name) {
        Node room;
        do {
            room = getRandomRoom();
        } while ((room.getName()).equals(name));
        return room.getName();
    }

    public class Node {
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private HashMap<String, Item> items;
        private int numChickens;
        private int numWumpus;
        private int numPopstars;

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
            numChickens = 0;
            numWumpus = 0;
            numPopstars = 0;
        }

        public void addCreature(Creature c) {
            if (c instanceof Chicken) {
                numChickens++;
            }
            if (c instanceof Wumpus) {
                numWumpus++;
            }
            if (c instanceof Popstar) {
                numPopstars++;
            }
        }

        public int getNumChickens() {
            return numChickens;
        }


        public int getNumWumpus() {
            return numWumpus;
        }


        public int getNumPopstars() {
            return numPopstars;
        }


        public void removeCreature(Creature c) {
            if (c instanceof Chicken) {
                numChickens--;
            }
            if (c instanceof Wumpus) {
                numWumpus--;
            }
            if (c instanceof Popstar) {
                numPopstars--;
            }
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

        public ArrayList<Node> getNeighbors() {
            return new ArrayList<Node>(neighbors.values());
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

        public Node getRandomNeighbor() {
            ArrayList<Graph.Node> rooms = this.getNeighbors();
            if (rooms.size() > 0) {
                return rooms.get((int) (Math.random() * rooms.size()));
            }
            return this;
        }

        public boolean isNeighbor(Node currentRoom) {
            if (this.getNeighbors().contains(currentRoom)) return true;
            return false;
        }

        public Node getRandomNeighborBesides(Node currentRoom) {
            ArrayList<Node> rooms = new ArrayList<>();
            if (isNeighbor(currentRoom)) {
                rooms = createRoomsListWithout(currentRoom, getNeighbors());
            } else {
                return getRandomNeighbor();
            }
            if (rooms.size() > 0) {
                return rooms.get((int) (Math.random() * rooms.size()));
            }

            return this;
        }

        private ArrayList<Node> createRoomsListWithout(Node currentRoom, ArrayList<Node> rooms) {
            ArrayList<Node> result = new ArrayList<>();
            for (Node n : rooms) {
                if (!n.equals(currentRoom)) {
                    result.add(n);
                }
            }
            return result;

        }

        public Node getRoomNearPlayerWithin2Steps(Node playerRoom) {
            for (Node n : getNeighbors()) {
                if (n.equals(playerRoom)) return n;
            }
            for (Node n : getNeighbors()) {
                for (Node neighbor : n.getNeighbors()) {
                    if (neighbor.equals(playerRoom)) return n;
                }
            }
            return null;
        }

        public Node getRoomNotWithin2StepsOfPlayer(Node playerRoom) {
            for (Node n : getNeighbors()) {
                if (!n.equals(playerRoom)) {
                    for (Node neighbor : n.getNeighbors()) {
                        if (!neighbor.equals(playerRoom)) return n;
                    }
                }
            }
            return null;
        }
    }
}

