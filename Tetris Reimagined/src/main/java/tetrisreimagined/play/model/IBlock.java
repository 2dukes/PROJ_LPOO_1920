package tetrisreimagined.play.model;

public class IBlock extends PieceModel {

    public IBlock() {
        super();
        this.color = new Color("cyan", "#00FFFF");

        blocks.add(new Block(new Position(0, 0), this.color));
        blocks.add(new Block(new Position(0, 1), this.color));
        blocks.add(new Block(new Position(0, 2), this.color));
        blocks.add(new Block(new Position(0, 3), this.color));
    }


}
