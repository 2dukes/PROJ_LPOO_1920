package tetrisreimagined.play.model;

import java.util.ArrayList;

public class OBlock extends Piece {

    public OBlock() {
        this.blocks = new ArrayList<>();
        this.color = new Color("yellow", "#FFFF00");

        this.blocks.add(new Block(new Position(0, 0), this.color));
        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 0), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
    }
}
