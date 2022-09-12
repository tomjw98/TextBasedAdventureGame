package players;

import java.util.Random;

public abstract class Player {

    private int playerHealth, playerBaseDamage, playerTotalDamage, playerDodgeRate, numOfPotions, playerArmourRating,
            critChance, healAmount;
    private boolean canBuff;

    private Random randomNum = new Random();

    Player(int playerHealth, int playerBaseDamage, int playerDodgeRate, int numOfPotions,
           int playerArmourRating, int critChance, int healAmount, boolean canBuff) {
        this.playerHealth = playerHealth;
        this.playerBaseDamage = playerBaseDamage;
        this.playerDodgeRate = playerDodgeRate;
        this.numOfPotions = numOfPotions;
        this.playerArmourRating = playerArmourRating;
        this.critChance = critChance;
        this.healAmount = healAmount;
        this.canBuff = canBuff;
    }

    public abstract void buff();

    public void printPlayerStats() {
        System.out.println("----- Your character's stats are: -----");
        System.out.println("Health: " + getPlayerHealth());
        System.out.println("Damage: " + getPlayerBaseDamage());
        System.out.println("Dodge Rate: " + getPlayerDodgeRate());
        System.out.println("Number of Potions: " + getNumOfPotions());
        System.out.println("Armour Rating: " + getPlayerArmourRating());
        System.out.println("Crit Chance: " + getCritChance());
        System.out.println("Heal Amount: " + getHealAmount());
    }

    public void genPlayerTotalDamage() {
        double range = 0.2;
        setPlayerTotalDamage((int) (getPlayerBaseDamage() * (1 - range / 2 + randomNum.nextInt(100)
                * range / 100)));
    }

    public boolean canDodge() {
        int dodgeChance = randomNum.nextInt(100);
        return !(dodgeChance > this.getPlayerDodgeRate());
    }

    public boolean isCrit() {
        return randomNum.nextInt(100) <= getCritChance();
    }

    public void drinkPotion() {
        this.setPlayerHealth(getPlayerHealth() + this.getHealAmount());
        System.out.println("You used a potion, health increased to " + this.getPlayerHealth());
        this.setNumOfPotions(this.getNumOfPotions() - 1);
        System.out.println("You have: " + this.getNumOfPotions() + " potions remaining");
    }

    public int getPlayerHealth() {
        return this.playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public int getPlayerBaseDamage() {
        return this.playerBaseDamage;
    }

    public void setPlayerBaseDamage(int playerBaseDamage) {
        this.playerBaseDamage = playerBaseDamage;
    }

    public int getPlayerTotalDamage() {
        return this.playerTotalDamage;
    }

    public void setPlayerTotalDamage(int playerTotalDamage) {
        this.playerTotalDamage = playerTotalDamage;
    }

    public int getPlayerDodgeRate() {
        return this.playerDodgeRate;
    }

    public int getNumOfPotions() {
        return this.numOfPotions;
    }

    public void setNumOfPotions(int numOfPotions) {
        this.numOfPotions = numOfPotions;
    }

    public int getPlayerArmourRating() {
        return this.playerArmourRating;
    }

    public boolean isCanBuff() {
        return this.canBuff;
    }

    public void setCanBuff(boolean canBuff) {
        this.canBuff = canBuff;
    }

    public int getCritChance() {
        return this.critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public int getHealAmount() {
        return this.healAmount;
    }
}
