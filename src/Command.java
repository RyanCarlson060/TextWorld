import java.util.Scanner;

public abstract class Command {
    Graph g;
    Scanner in;
    Player p1;

    public abstract void execute();

    public Command(Graph g, Scanner in, Player p1) {
        this.g = g;
        this.in = in;
        this.p1 = p1;
    }
}
