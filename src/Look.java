
import java.util.Scanner;

public class Look extends Command {

    public Look(Graph g, Scanner in, Player p1) {
        super(g, in, p1);
    }

    @Override
    public void execute() {
        System.out.println("You see " + p1.getCurrentRoom().getDescription());
        System.out.println("There are " + p1.getCurrentRoom().getNumChickens() + " chickens");
        System.out.println("There are " + p1.getCurrentRoom().getNumWumpus() + " wompuses");
        System.out.println("There are " + p1.getCurrentRoom().getNumPopstars() + " popstars");
        System.out.println("You can pick up " + p1.getCurrentRoom().getItemNames());
        System.out.println("You can go to " + p1.getCurrentRoom().getNeighborNames());
    }
}




