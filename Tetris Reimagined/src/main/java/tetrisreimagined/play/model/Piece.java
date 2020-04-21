package tetrisreimagined.play.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    // Names of pieces:
    // https://tetris.fandom.com/wiki/Tetromino

    protected List<Block> blocks;
    protected Color color;

    public Piece() {
        this.blocks = new ArrayList<>();
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }
}