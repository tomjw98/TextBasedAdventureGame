package enemies.bosses;

import enemies.Enemy;

public class Nito extends Enemy {

    public Nito() {
        super(300, 45, 10, 30, 100, 3);
    }

    public String toString() {
        return "Gravelord Nito";
    }
}
