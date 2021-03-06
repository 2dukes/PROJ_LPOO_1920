package tetrisreimagined.play.controller.Pieces;

import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Commands.MoveDown;
import tetrisreimagined.play.controller.Commands.RotateClockWise;
import tetrisreimagined.play.controller.Commands.RotateCounterClockWise;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.*;
import tetrisreimagined.play.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PieceController {

    private final PieceModel pieceModel;

    public PieceController(PieceModel pieceModel) {
        this.pieceModel = pieceModel;
    }

    public PieceModel getPieceModel() {
        return pieceModel;
    }

    public PieceModel getPieceModelRaw() {
        if (pieceModel instanceof IBlockModel)
            return new IBlockModel();
        if (pieceModel instanceof JBlockModel)
            return new JBlockModel();
        if (pieceModel instanceof LBlockModel)
            return new LBlockModel();
        if (pieceModel instanceof OBlockModel)
            return new OBlockModel();
        if (pieceModel instanceof SBlockModel)
            return new SBlockModel();
        if (pieceModel instanceof TBlockModel)
            return new TBlockModel();
        if (pieceModel instanceof ZBlockModel)
            return new ZBlockModel();
        return new NullPieceModel();
    }

    public void makeCurrentPieceFall(Observer<ArenaModel> gui, ArenaModel gameModel) {
        if (canGoDown(gui, gameModel))
            new MoveDown(pieceModel, gui, gameModel, false).execute(this);
    }

    public boolean canGoRight(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (gameModel.positionHasBlock(block.getPosition().right()))
                return false;
        }
        return pieceModel.getMaxXPosition().getX() + 1 < gui.getWidth();
    }

    public boolean canGoLeft(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (gameModel.positionHasBlock(block.getPosition().left()))
                return false;
        }
        return pieceModel.getMinXPosition().getX() > 0;
    }

    public boolean canGoDown(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (gameModel.positionHasBlock(block.getPosition().down()))
                return false;
        }
        return pieceModel.getMaxYPosition().getY() + 1 < gui.getHeight();
    }

    public Block getBlockById(int id) {
        for(Block block: pieceModel.getBlocks()) {
            if(block.getId() == id)
                return block;
        }
        return null;
    }

    public int getBlockId(Position position) {
        for(Block block: pieceModel.getBlocks()) {
            if(block.getPosition().equals(position))
                return block.getId();
        }
        return 0;
    }

    public boolean pieceCanRotate(Observer<ArenaModel> gui, ArenaModel gameModel) {
        boolean canRotate = true;
        List<Position> blockPositions = new ArrayList<>();
        RotateClockWise rotateCW = new RotateClockWise(pieceModel, gui, gameModel);
        rotateCW.rotatePiece(this);
        for (Block block: pieceModel.getBlocks()) {
            blockPositions.add(block.getPosition());
        }
        for (Position position: blockPositions) {
            boolean isOutOfLimits = position.getX() >= gui.getWidth() || position.getX() < 0 || position.getY() > gui.getHeight() || position.getY() < 0;
            if (gameModel.positionHasBlock(position) || isOutOfLimits) {
                canRotate = false;
                break;
            }
        }
        RotateCounterClockWise rotateCCW = new RotateCounterClockWise(pieceModel, gui, gameModel);
        rotateCCW.rotatePiece(this);
        return canRotate;
    }

    public void setStartPosition(Observer<ArenaModel> gui) {

        int dx = (gui.getWidth() - this.pieceModel.getMinXPosition().getX())/4 - 2;
        int dy = (this.pieceModel.getMaxYPosition().getY())-4;

        for (int i = 0; i < dx; i++) {
            for (Block block: this.pieceModel.getBlocks())
                block.setPosition(block.getPosition().right());
        }

        for (int i = 0; i < dy; i++) {
            for (Block block: this.pieceModel.getBlocks())
                block.setPosition(block.getPosition().up());
        }
    }
}