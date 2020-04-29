package tetrisreimagined.play.rules.commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceController;

public class MoveDown extends PieceCommand {
    private Observer<ArenaModel> gui;
    private ArenaModel gameModel;
    private PieceModel pieceModel;

    public MoveDown(PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;
        this.pieceModel = pModel;
    }

    @Override
    public boolean execute(PieceController currentPieceController) {
        if(currentPieceController.canGoDown(gui, gameModel)) {
            for (Block block: this.pieceModel.getBlocks())
                block.setPosition(block.getPosition().down());
            gameModel.setScore(gameModel.getScore() + currentPieceController.getPieceModel().getNumBlocks() *(this.gameModel.getLevel() + 1));
            return true;
        }
        return false;
    }

}