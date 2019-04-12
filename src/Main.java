import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Graph g = new Graph();
        g.addNode("hall", "darkness, only darkness");
        g.addNode("closet", "so many blankets in such a small closet");
        g.addNode("dungeon", "shackles in the dungeon");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        Player p1 = new Player("Ryan", "cool");
        p1.setCurrentRoom(g.getNode("hall"));

        g.getNode("hall").addItem(new Item("ball", "cool ball"));


        String response = "";
        Scanner in = new Scanner(System.in);

        System.out.println("go, look, add room, take item, drop item or quit");
        do {
            System.out.println("You are currently in the " + p1.getCurrentRoom().getName());
            System.out.println("What do you want to do?");
            response = in.nextLine();

            if (response.equals("go")) {
                go(g,in,p1);
            } else if (response.equals("look")) {
                look(p1);
            } else if (response.equals("add room")) {
                addRoom(g, in, p1);
            } else if (response.equals("take item")) {
                takeItem(g, in, p1);

            } else if (response.equals("drop item")) {
                dropItem(g, in, p1);
            } else if (response.equals("quit")) {
            } else {
                System.out.println("go, look, add room, or quit");
            }

        } while (!response.equals("quit"));

    }

    private static void go(Graph g, Scanner in, Player p1) {
        String response;
        System.out.println("name room");
        response = in.nextLine();
        if (g.getNode(p1.getCurrentRoom().getName()).getNeighbor(response) == null) {
            System.out.println("You can't go there");
        } else {
            p1.setCurrentRoom(g.getNode(response));
        }
    }

    private static void look(Player p1) {
        System.out.println("You see " + p1.getCurrentRoom().getDescription());
        System.out.println("You can pick up " + p1.getCurrentRoom().getItemNames());
        System.out.println("You can go to " + p1.getCurrentRoom().getNeighborNames());
    }

    private static void dropItem(Graph g, Scanner in, Player p1) {
        String response;
        System.out.println("name item");
        response = in.nextLine();
        if (p1.getItem(response) == null) {
            System.out.println("You don't have that item");
        } else {
            p1.getCurrentRoom().addItem(p1.removeItem(response));
        }
    }

    private static void takeItem(Graph g, Scanner in, Player p1) {
        String response;
        System.out.println("name item");
        response = in.nextLine();
        if (p1.getCurrentRoom().getItem(response) == null) {
            System.out.println("That is not in the room");
        } else {
            p1.addItem(p1.getCurrentRoom().removeItem(response));
        }
    }

    private static void addRoom(Graph g, Scanner in, Player p1) {
        String response;
        System.out.println("name room");
        response = in.nextLine();
        String name = new String(response);
        System.out.println("give description");
        response = in.nextLine();
        String description = response;
        g.addNode(name, description);
        g.addDirectedEdge(p1.getCurrentRoom().getName(), name);
    }
}
