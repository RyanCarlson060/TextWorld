
import java.util.ArrayList;

public class Wumpus extends Creature {
    public Wumpus(Graph.Node room, Player p) {
        currentRoom = room;
        player = p;
    }

    @Override
    protected Graph.Node move() {
        currentRoom.removeCreature(this);
        if ((currentRoom.getRoomNearPlayerWithin2Steps(player.getCurrentRoom()) == null) || (currentRoom.getRoomNotWithin2StepsOfPlayer(player.getCurrentRoom()) == null)) {
            currentRoom = currentRoom.getRandomNeighborBesides(player.getCurrentRoom());
        }else{
            currentRoom = currentRoom.getRoomNotWithin2StepsOfPlayer(player.getCurrentRoom());
        }
        currentRoom.addCreature(this);
        return currentRoom;
    }

    @Override
    protected void act() {
        if (currentRoom.equals(player.getCurrentRoom())) {
            player.heal();
        }
    }
}

