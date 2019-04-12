
import java.util.Scanner;

public class TakeItem extends Command {

    public TakeItem(Graph g, Scanner in, Player p1) {
        super(g, in, p1);
    }

    @Override
    public void execute() {
        String response;
        System.out.println("name item");
        response = in.nextLine();
        if (p1.getCurrentRoom().getItem(response) == null) {
            System.out.println("That is not in the room");
        } else {
            p1.addItem(p1.getCurrentRoom().removeItem(response));
        }
    }
}

