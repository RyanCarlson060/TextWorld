import java.util.ArrayList;

public class Chicken extends Creature {
    public Chicken(Graph.Node room, Player p) {
        currentRoom = room;
        player = p;
    }

    @Override
    protected Graph.Node move() {
        currentRoom.removeCreature(this);
        currentRoom = currentRoom.getRandomNeighbor();
        currentRoom.addCreature(this);
        return currentRoom;
    }

    @Override
    protected void act() {
        if (currentRoom.equals(player.getCurrentRoom())) player.loseHealth(0.05);
    }
}
