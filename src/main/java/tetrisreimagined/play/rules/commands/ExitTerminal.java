package tetrisreimagined.play.rules.commands;

import tetrisreimagined.play.rules.Pieces.PieceController;

public class ExitTerminal extends PieceCommand {

    @Override
    public boolean execute(PieceController currentPieceController) {
        return true;
    }

}
