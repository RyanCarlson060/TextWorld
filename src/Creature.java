public abstract class Creature {
    protected Graph.Node currentRoom;
    protected Player player;

    protected abstract Graph.Node move();
    public Graph.Node getRoom(){
        return currentRoom;
    }

    protected abstract void act();
}

