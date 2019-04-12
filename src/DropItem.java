import java.util.Scanner;

public class DropItem extends Command {

    public DropItem(Graph g, Scanner in, Player p1) {
        super(g, in, p1);
    }

    @Override
    public void execute() {
        String response;
        System.out.println("name item");
        response = in.nextLine();
        if (p1.getItem(response) == null) {
            System.out.println("You don't have that item");
        } else {
            p1.getCurrentRoom().addItem(p1.removeItem(response));
        }
    }
}
