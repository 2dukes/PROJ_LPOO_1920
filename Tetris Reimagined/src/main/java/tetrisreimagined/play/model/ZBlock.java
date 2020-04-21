package tetrisreimagined.play.model;

import java.util.ArrayList;

public class ZBlock extends Piece {

    public ZBlock() {
        this.blocks = new ArrayList<>();

        this.color = new Color("red", "#FF0000");

        this.blocks.add(new Block(new Position(0, 0), this.color));
        this.blocks.add(new Block(new Position(1, 0), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));
    }
}
