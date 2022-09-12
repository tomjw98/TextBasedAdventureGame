package enemies.mobs;

import enemies.Enemy;

public class Skeleton extends Enemy {

    public Skeleton() {
        super(100, 5, 15, 30, 25, 1);
    }

    public String toString() {
        return "Skeleton";
    }
}
