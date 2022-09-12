package enemies.bosses;

import enemies.Enemy;
import players.Player;

public class BedOfChaos extends Enemy {

    private int roundsUntilExplosion;

    public BedOfChaos() {
        super(400, 30, 5, 20, 100, 3);
        this.roundsUntilExplosion = 5;
    }

    public String toString() {
        return "The Bed Of Chaos";
    }
}
