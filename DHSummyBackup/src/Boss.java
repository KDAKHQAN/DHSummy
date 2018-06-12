import javax.swing.*;

/**
 * this class controls and dictates all the attributes attached to the boss in the game
 */
class Boss extends GameThing {
    //Declaration of variables used in this class
    //Constructor creates and sets the boss on the screen and calls the method that makes the boss attack
    int counter = 0;
    private MainCharacter MC;

    Boss(JFrame home) {
        super(home, home.getWidth() - Resource.bossRest.getIconWidth() + 50, home.getHeight() - Resource.bossRest.getIconHeight() - 56, Resource.bossRest);
//        Attack(MC);
        HP = 100;
    }

    //one of the bosses attacks, launches three damaging projectiles each at different angles
    private void HeartProjectileAttack(JFrame Home, MainCharacter MC) {
        setIcon(Resource.bossKissing);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 46, 5, MC);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 13, 10, MC);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 10, 15, MC);

    }

    //another boss attack, spawns a minion that rushes the main character, damaging him if they collide
    private void CreepAttack(JFrame home) {
        setIcon(Resource.creepFace);
        new Minion(home, MC);
    }

    //Method that calls each of the attacks every time interval
    public void Attack() {
        Timer Attack = new Timer(500, e -> {
            counter++;
            if (counter == 5) {
                setIcon(Resource.bossRest);
            }
            if (counter == 20) {
                HeartProjectileAttack(home, MC);
            }
            if (counter == 27) {
                setIcon(Resource.bossRest);
            }
            if (counter == 35) {
                CreepAttack(home);
                counter = 0;
            }
        });
        Attack.start();
    }

    public void setMC(MainCharacter ding ){
        MC = ding;
    }
}
