package players;

public class Cleric extends Player {

    public Cleric() {
        super(125, 40, 15, 5, 15, 10,
                60, true);
    }

    @Override
    public void buff() {
            setPlayerHealth(200);
            System.out.println("You used your buff! Health increased to 200");
            setCanBuff(false);
    }
}
