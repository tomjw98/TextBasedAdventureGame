package enemies;

import game.Main;
import players.Player;

import java.util.Random;

public abstract class Enemy {

    private int enemyHealth;
    private final int enemyArmourRating, enemyDodgeRate, enemyBaseDamage, potionDropChance, numOfPotionsDropped;
    private int enemyTotalDamage;
    private final Random randomNum = new Random();

    public Enemy(int enemyHealth, int enemyArmourRating, int enemyDodgeRate, int enemyBaseDamage,
                 int potionDropChance, int numOfPotionsDropped) {
        this.enemyHealth = enemyHealth;
        this.enemyArmourRating = enemyArmourRating;
        this.enemyDodgeRate = enemyDodgeRate;
        this.enemyBaseDamage = enemyBaseDamage;
        this.potionDropChance = potionDropChance;
        this.numOfPotionsDropped = numOfPotionsDropped;
    }

    public void genEnemyTotalDamage() {
        double range = 0.2;
        this.enemyTotalDamage = (int) (getEnemyBaseDamage() * (1 - range / 2 + randomNum.nextInt(100)
                * range / 100));
    }

    public boolean canDodge() {
        int dodgeChance = randomNum.nextInt(100);
        return !(dodgeChance > this.getEnemyDodgeRate());
    }

    public void dropPotion(Player player) {
        int dropChance = randomNum.nextInt(100);
        if (getPotionDropChance() >= dropChance) {
            System.out.println(this + " dropped " + getNumOfPotionsDropped() + " potions!!");
            player.setNumOfPotions(player.getNumOfPotions() + getNumOfPotionsDropped());
            System.out.println("You now have: " + player.getNumOfPotions() + " potions remaining!");
        }
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public int getEnemyArmourRating() {
        return enemyArmourRating;
    }

    public int getEnemyDodgeRate() {
        return enemyDodgeRate;
    }

    public int getEnemyBaseDamage() {
        return enemyBaseDamage;
    }

    public int getEnemyTotalDamage() {
        return enemyTotalDamage;
    }

    public void setEnemyTotalDamage(int EnemyTotalDamage) {
        this.enemyTotalDamage = EnemyTotalDamage;
    }

    public int getPotionDropChance() {
        return this.potionDropChance;
    }

    public int getNumOfPotionsDropped() {
        return this.numOfPotionsDropped;
    }
}
