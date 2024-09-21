package magicalArena;

public class Arena {
	private Player playerA;
    private Player playerB;
    private Dice dice;

    public Arena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.dice = new Dice();
    }

    public void startFight() {
        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerA.getHealth() < playerB.getHealth()) {
                attack(playerA, playerB);
                if (playerB.isAlive()) {
                    attack(playerB, playerA);
                }
            } else {
                attack(playerB, playerA);
                if (playerA.isAlive()) {
                    attack(playerA, playerB);
                }
            }
        }

        if (playerA.isAlive()) {
            System.out.println("Player A wins!");
        } else {
            System.out.println("Player B wins!");
        }
    }

    private void attack(Player attacker, Player defender) {
        int attackRoll = dice.roll();
        int defendRoll = dice.roll();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defendStrength = defender.getStrength() * defendRoll;

        int damage = attackDamage - defendStrength;
        if (damage > 0) {
            defender.reduceHealth(damage);
        }

        System.out.println("Attacker rolls: " + attackRoll + ", Defender rolls: " + defendRoll);
        System.out.println("Damage dealt: " + damage + ", Defender health: " + defender.getHealth());
    }

}
