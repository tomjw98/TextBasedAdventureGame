package enemies.bosses;

import enemies.Enemy;
import game.Main;

public class FourKings extends Enemy {

    public FourKings() {
        super(350, 40, 15, 5, 100, 3);
    }

    @Override
    public void genEnemyTotalDamage() {
        for (int i = 0; i < 3; i++) {
            this.setEnemyTotalDamage(this.getEnemyTotalDamage() + super.getEnemyTotalDamage());
        }
    }

    public String toString() {
        return "The 4 Kings";
    }
}
