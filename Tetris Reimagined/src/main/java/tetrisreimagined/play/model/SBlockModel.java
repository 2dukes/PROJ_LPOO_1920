package tetrisreimagined.play.model;

public class SBlockModel extends PieceModel {

    public SBlockModel() {
        super();
        this.color = new Color("limegreen", "#32CD32");

        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));
        this.blocks.add(new Block(new Position(3, 1), this.color));
        this.blocks.add(new Block(new Position(2, 0), this.color));
        this.blocks.add(new Block(new Position(3, 0), this.color));
        this.blocks.add(new Block(new Position(4, 0), this.color));
        this.blocks.add(new Block(new Position(5, 0), this.color));

    }


}