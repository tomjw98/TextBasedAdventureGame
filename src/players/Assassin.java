package players;

import java.util.Timer;
import java.util.TimerTask;

public class Assassin extends Player {

    public Assassin() {
        super(80, 50, 30, 4, 5, 30,
                50, true);
    }

    @Override
    public void buff() {
            setCritChance(50);
            System.out.println("You used your buff! Critical hit chance increased to 50% for 30 seconds.");
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

