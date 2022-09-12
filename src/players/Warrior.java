package players;

import java.util.Timer;
import java.util.TimerTask;

public class Warrior extends Player {

    public Warrior() {
        super(100, 60, 20, 3, 10, 20,
                40, true);
    }

    @Override
    public void buff() {
            setPlayerBaseDamage(80);
            System.out.println("You used your buff! Damage increased by 33% for 30 seconds.");
            Timer buffTimer = new Timer();
            buffTimer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            setPlayerBaseDamage(60);
                            System.out.println("Your buff has expired!");
                            buffTimer.cancel();
                        }
                    }, 30_000
            );
            setCanBuff(false);
    }
}
