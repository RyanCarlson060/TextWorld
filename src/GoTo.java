
import java.util.Scanner;

public class GoTo extends Command {

    public GoTo(Graph g, Scanner in, Player p1) {
        super(g,in,p1);
    }

    @Override
    public void execute() {
        String response;
        System.out.println("name room");
        response = in.nextLine();
        if (g.getNode(p1.getCurrentRoom().getName()).getNeighbor(response) == null) {
            System.out.println("You can't go there");
        } else {
            p1.setCurrentRoom(g.getNode(response));
        }
    }

}

