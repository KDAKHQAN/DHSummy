import javax.swing.*;

class Boss extends GameThing {
    int HP = 100, counter = 0;

    Boss(JFrame home) {
        super(home, home.getWidth() - Resource.bossRest.getIconWidth() + 50, home.getHeight() - Resource.bossRest.getIconHeight() - 56, Resource.bossRest);
        Attack();
    }

    private void HeartProjectileAttack(JFrame Home) {
        setIcon(Resource.bossKissing);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 46, 5);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 13, 10);
        new BossProjectile(Home, home.getHeight() - Resource.bossRest.getIconHeight() + 10, 15);

    }

    private void CreepAttack(JFrame home) {
        setIcon(Resource.creepFace);
        new Minion(home);
    }

    private void Attack() {
        Timer Attack = new Timer(500, e -> {
            counter++;
            if (counter == 5) {
                setIcon(Resource.bossRest);
            }
            if (counter == 20) {
                HeartProjectileAttack(home);
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
}
