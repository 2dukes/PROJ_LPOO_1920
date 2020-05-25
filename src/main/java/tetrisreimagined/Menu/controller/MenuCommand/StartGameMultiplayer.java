package tetrisreimagined.Menu.controller.MenuCommand;

import tetrisreimagined.Menu.controller.MenuController;

import java.io.IOException;

public class StartGameMultiplayer extends MenuCommand {
    @Override
    public boolean execute(MenuController menuController) throws IOException {
        menuController.startGameMultiPlayer();
        return true;
    }
}