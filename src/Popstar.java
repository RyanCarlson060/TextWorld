
import java.util.ArrayList;

public class Popstar extends Creature {

    public Popstar(Graph.Node room, Player p) {
        currentRoom = room;
        player = p;
    }

    @Override
    protected Graph.Node move() {
        currentRoom.removeCreature(this);
        if ((!currentRoom.equals(player.getCurrentRoom())) && (currentRoom.getRoomNearPlayerWithin2Steps(player.getCurrentRoom()) != null)) {
            currentRoom = currentRoom.getRoomNearPlayerWithin2Steps(player.getCurrentRoom());
        } else if (!currentRoom.equals(player.getCurrentRoom())) {
            currentRoom = currentRoom.getRandomNeighbor();
        }
        currentRoom.addCreature(this);
        return currentRoom;
    }


    @Override
    protected void act() {
        if (currentRoom.equals(player.getCurrentRoom())) {
            player.loseHealth(4);
        }
    }
}

