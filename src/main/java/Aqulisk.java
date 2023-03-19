import bwapi.*;


public class Aqulisk extends DefaultBWListener {
    BWClient bwClient;
    Game game;

    @Override
    public void onStart() {
        game = bwClient.getGame();
        game.setLocalSpeed(10);
    }

    @Override
    public void onFrame() {
        Player self = game.self();
        game.drawTextScreen(20, 20,
                self.getName() + ": Minerals - " + self.minerals()
                        + ", Gas - " + self.gas() + ".");
        game.drawTextScreen(30, 30, "Java\nTrainee!");
    }


    public void onUnitComplete(Unit unit) {
        if (unit.getType().isWorker()) {
            Unit closestMineral = null;
            int closestDistance = Integer.MAX_VALUE;
            for (Unit mineral : game.getMinerals()) {
                int distance = unit.getDistance(mineral);
                if (distance < closestDistance) {
                    closestMineral = mineral;
                    closestDistance = distance;
                }
            }
            // Gather the closest mineral
            unit.gather(closestMineral);
        }
    }

    void run() {
        bwClient = new BWClient(this);
        bwClient.startGame();
    }

    public static void main(String[] args) {
        new Aqulisk().run();
    }
}