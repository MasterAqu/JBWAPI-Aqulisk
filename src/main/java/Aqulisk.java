import bwapi.*;
import bwta.BWTA;

class Aqulisk extends DefaultBWListener {
    private BWClient bwClient;

    public Player self;

    @Override
    public void onStart() {
        Game game = bwClient.getGame();
        game.setLocalSpeed(10);
        BWTA.readMap(game);
        BWTA.analyze();

    }

    @Override
    public void onFrame() {
        Game game = bwClient.getGame();
        game.drawTextScreen(10, 10, "Hello World!");
        game.drawTextScreen(10, 20,
                "Resources: " +
                        self.minerals() + " minerals, " +
                        self.gas() + " gas");
    }

    public static void main(String[] args) {
        Aqulisk bot = new Aqulisk();
        bot.bwClient = new BWClient(bot);
        bot.bwClient.startGame();
    }
}