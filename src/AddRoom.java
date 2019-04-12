import java.util.Scanner;

public class AddRoom extends Command {

    public AddRoom(Graph g, Scanner in, Player p1) {
        super(g, in, p1);
    }

    @Override
    public void execute() {
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

