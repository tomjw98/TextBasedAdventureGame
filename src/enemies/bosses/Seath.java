package enemies.bosses;

import enemies.Enemy;

public class Seath extends Enemy {

    public Seath() {
        super(500, 50, 10, 30, 100, 3);
    }

    public String toString() {
        return "Seath The Scaleless";
    }
}
