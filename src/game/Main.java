package game;

import enemies.Enemy;
import enemies.bosses.*;
import enemies.mobs.BalderKnight;
import enemies.mobs.HollowWarrior;
import enemies.mobs.Skeleton;
import players.Assassin;
import players.Cleric;
import players.Player;
import players.Warrior;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private int numOfRounds = 1;
    private Enemy enemy;
    private Player player;
    private Scanner input = new Scanner(System.in);
    private static Main main = new Main();

    private Random randomNum = new Random();

    public static void main(String[] args) throws IOException {
        main.chooseClass();
    }

    public void chooseClass() {
        // Player selects which class they want to play as. Stats of class are printed
        System.out.println("----- Welcome, please select a player class: -----");
        System.out.println("1: Warrior\n2: Cleric\n3: Assassin");
        String classChoice = input.nextLine().trim();
        switch (classChoice) {
            case "1":
                System.out.println("----- You have chosen Warrior -----");
                player = new Warrior();
                break;
            case "2":
                System.out.println("----- You have chosen Cleric -----");
                player = new Cleric();
                break;
            case "3":
                System.out.println("----- You have chosen Assassin -----");
                player = new Assassin();
                break;
            default:
                System.out.println("----- Invalid class choice! -----");
                chooseClass();
        }
        player.printPlayerStats();
        enemyEncounter();
    }

    public void enemyEncounter() {
        // An enemy is generated using a random number generator
        if (this.numOfRounds % 5 == 0) {
            bossBattle();
        }
        int randomEnemy = randomNum.nextInt(100);
        if (randomEnemy < 50) enemy = new HollowWarrior();
        else if (randomEnemy < 80) enemy = new Skeleton();
        else enemy = new BalderKnight();
        System.out.println("\n##### A " + enemy.toString() + " Appeared! ##### (Round: " + numOfRounds + ")");
        chooseAction();
    }

    public void chooseAction() {
        // Player chooses whether they want to fight, heal or flee. Fleeing ends the game
        System.out.println("----- Please select an action: -----");
        System.out.println("1: Fight\n2: Heal\n3: Buff\n4: Flee");
        String actionChoice = input.nextLine().trim();
        switch (actionChoice) {
            case "1":
                System.out.println("----- You have chosen to fight! -----");
                playerAttacks();
                break;
            case "2":
                System.out.println("----- You have chosen to heal! -----");
                if (player.getNumOfPotions() < 1) {
                    System.out.println("No potions remaining!!!");
                    chooseAction();
                }
                player.drinkPotion();
                enemyAttacks();
                break;
            case "3":
                System.out.println("----- You have chosen to buff! -----");
                if (!player.isCanBuff()) {
                    System.out.println("No buffs remaining!!!");
                    chooseAction();
                }
                player.buff();
                enemyAttacks();
                break;
            case "4":
                System.out.println("----- You have chosen to flee! -----");
                System.out.println("----- You survived " + getNumOfRounds() + " rounds! -----");
                System.out.println("----- Thanks for playing :D -----");
                System.exit(0);
            default:
                System.out.println("----- Invalid option! -----");
                chooseAction();
        }
    }

    public void playerAttacks() {
        // Player attacks the enemy. Player damage is base damage + or - 10%
        player.genPlayerTotalDamage();
        int enemyDamageTaken = player.getPlayerTotalDamage() - enemy.getEnemyArmourRating();
        if (!enemy.canDodge()) {
            if(player.isCrit()) {
                System.out.println("Critical Hit!!!");
                enemyDamageTaken = (int)(enemyDamageTaken * 1.3);
            }
            enemy.setEnemyHealth(enemy.getEnemyHealth() - enemyDamageTaken);
            checkIfEnemyHealthBelowZero();
            System.out.println("You hit the " + enemy.toString() + " for: " + enemyDamageTaken + " damage (HP: "
                                + enemy.getEnemyHealth() + ")");
            checkIfEnemyDead();
        }
        else {
            System.out.println("The enemy dodged your attack!!!");
            enemyAttacks();
        }
    }

    public void enemyAttacks() {
        // Enemy attacks the player. Enemy damage is base damage + or - 10%
        enemy.genEnemyTotalDamage();
        int playerDamageTaken = enemy.getEnemyTotalDamage() - player.getPlayerArmourRating();
        if (!player.canDodge()) {
            player.setPlayerHealth(player.getPlayerHealth() - playerDamageTaken);
            checkIfPlayerHealthBelowZero();
            System.out.println("You were hit for: " + playerDamageTaken + " damage (HP: " + player.getPlayerHealth()
                                + ")");
            checkPlayerHealth();
            checkPlayerHealth();
            System.out.println("----- You have " + player.getPlayerHealth() + " health remaining! -----");
        }
        else {
            System.out.println("You dodged the enemy attack!!!");
            chooseAction();
        }
    }

    public void bossBattle() {
        // Generates a boss enemy, 25% chance for each boss
        int randomBoss = randomNum.nextInt(100);
        if (randomBoss < 25) enemy = new BedOfChaos();
        else if (randomBoss < 50) enemy = new FourKings();
        else if (randomBoss < 75) enemy = new Nito();
        else enemy = new Seath();
        System.out.println("\n##### BOSS BATTLE #####");
        System.out.println("##### "+ enemy.toString() + " Appeared! ##### (Round: " + numOfRounds + ")");
        chooseAction();
    }

    public void checkIfEnemyDead() {
        // Checks if enemy health has reached 0, next round will commence
        if (enemy.getEnemyHealth() == 0) {
            System.out.println("You defeated the " + enemy.toString() + "!!!");
            enemy.dropPotion(player);
            incrementNumOfRounds();
            enemyEncounter();
        }
        else {
            enemyAttacks();
        }
    }

    public void checkIfEnemyHealthBelowZero() {
        if (enemy.getEnemyHealth() < 0) {
            enemy.setEnemyHealth(0);
        }
    }

    public void checkPlayerHealth() {
        // Checks to see if the player is dead. If so, game ends and number of rounds is printed
        if (player.getPlayerHealth() <= 0) {
            System.out.println("You have been defeated by " + enemy.toString() + "!!!");
            System.out.println("----- You survived " + getNumOfRounds() + " rounds! -----");
            System.out.println("----- Thanks for playing :D -----");
            System.exit(1);
        }
        else {
            chooseAction();
        }
    }

    public void checkIfPlayerHealthBelowZero() {
        if (player.getPlayerHealth() < 0) {
            player.setPlayerHealth(0);
        }
    }

    public int getNumOfRounds() {
        return this.numOfRounds;
    }

    public void incrementNumOfRounds() {
        this.numOfRounds++;
    }
}
