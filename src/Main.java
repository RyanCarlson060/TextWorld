import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Command> commands = new HashMap<>();

        Graph g = new Graph();
        g.addNode("hall", "darkness, only darkness");
        g.addNode("closet", "so many blankets in such a small closet");
        g.addNode("dungeon", "shackles in the dungeon");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        Player p1 = new Player("Ryan", "cool");
        p1.setCurrentRoom(g.getNode("hall"));

        g.getNode("hall").addItem(new Item("ball", "cool ball"));

        for (int i = 0; i < 200; i++) {
            Creature c = new Chicken(g.getNode("hall"), p1);
            g.addCreature(c);
        }
        for (int i = 0; i < 100; i++) {
            Creature c = new Chicken(g.getNode("closet"), p1);
            g.addCreature(c);
        }
        for (int i = 0; i < 150; i++) {
            Creature c = new Chicken(g.getNode("dungeon"), p1);
            g.addCreature(c);
        }

        for (int i = 0; i < 50; i++) {
            Creature c = new Wumpus(g.getNode("hall"), p1);
            g.addCreature(c);
        }

        for (int i = 0; i < 150; i++) {
            Creature c = new Wumpus(g.getNode("dungeon"), p1);
            g.addCreature(c);
        }

        for (int i = 0; i < 100; i++) {
            Creature c = new Wumpus(g.getNode("closet"), p1);
            g.addCreature(c);
        }

        for (int i = 0; i < 2; i++) {
            Creature c = new Popstar(g.getNode("closet"), p1);
            g.addCreature(c);
        }

        String response = "";
        Scanner in = new Scanner(System.in);
        initCommands(commands, g, in, p1);

        System.out.println("go, look, add room, take item, drop item or quit");
        do {
            System.out.println("You are currently in the " + p1.getCurrentRoom().getName());
            System.out.println("What do you want to do?");
            response = in.nextLine();
            Command command = lookupCommand(response, commands);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("go, look, take item, drop item, add room, or quit");
            }


            moveCreatures(g);
            creaturesAct(g);
            if (!p1.isAlive()) {
                response = ("quit");
                System.out.println("You died");
            }

        } while (!response.equals("quit"));

    }

    private static Command lookupCommand(String response, HashMap<String, Command> commands) {
        return commands.get(response);
    }

    private static void initCommands(HashMap<String, Command> commands, Graph g, Scanner in, Player p1) {
        commands.put("take item", new TakeItem(g, in, p1));
        commands.put("drop item", new DropItem(g, in, p1));
        commands.put("go", new GoTo(g, in, p1));
        commands.put("look", new Look(g, in, p1));
        commands.put("add room", new AddRoom(g, in, p1));
        commands.put("quit", new Quit(g, in, p1));
    }

    private static void creaturesAct(Graph g) {
        ArrayList<Creature> creatures = g.getCreatures();
        for (Creature c : creatures) {
            c.act();

        }
    }

    private static void moveCreatures(Graph g) {
        ArrayList<Creature> creatures = g.getCreatures();
        for (Creature c : creatures) {
            c.move();

        }

    }
}



