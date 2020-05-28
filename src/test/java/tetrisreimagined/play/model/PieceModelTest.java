package tetrisreimagined.play.model;

import org.junit.jupiter.api.Test;
import tetrisreimagined.play.model.Pieces.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PieceModelTest {

    private PieceModel iBlock;
    private PieceModel jBlock;
    private PieceModel lBlock;
    private PieceModel oBlock;
    private PieceModel sBlock;
    private PieceModel tBlock;
    private PieceModel zBlock;

    public void setup() {
        iBlock = new IBlockModel();
        jBlock = new JBlockModel();
        lBlock = new LBlockModel();
        oBlock = new OBlockModel();
        sBlock = new SBlockModel();
        tBlock = new TBlockModel();
        zBlock = new ZBlockModel();
    }

    @Test
    public void getMinXPosition1() {
        setup();
        assert (0 == iBlock.getMinXPosition().getX());
    }

    @Test
    public void getMinXPosition2() {
        setup();
        assertEquals(0, oBlock.getMinXPosition().getX());
    }

    @Test
    public void getMaxXPosition1() {
        setup();
        assertEquals(7, iBlock.getMaxXPosition().getX());
    }

    @Test
    public void getMaxXPosition2() {
        setup();
        assertEquals(3, oBlock.getMaxXPosition().getX());
    }

    @Test
    public void getMinYPosition1() {
        setup();
        assertEquals(0, jBlock.getMinYPosition().getY());
    }

    @Test
    public void getMinYPosition2() {
        setup();
        assertEquals(0, zBlock.getMinYPosition().getY());
    }

    @Test
    public void getMaxYPosition1() {
        setup();
        assertEquals(3, jBlock.getMaxYPosition().getY());
    }

    @Test
    public void getMaxYPosition2() {
        setup();
        assertEquals(3, zBlock.getMaxYPosition().getY());
    }

    @Test
    public void getNumBlocks1() {
        setup();
        assertEquals(16, lBlock.getNumBlocks());
    }

    @Test
    public void getNumBlocks2() {
        setup();
        assertEquals(16, sBlock.getNumBlocks());
    }

    @Test
    public void getNumBlocks3() {
        setup();
        assertEquals(16, tBlock.getNumBlocks());
    }

    @Test
    public void getNumBlocks4() {
        setup();
        assertEquals(16, oBlock.getNumBlocks());
    }

    @Test
    public void getBlocks() {
        setup();

        List<Block> blocks = new ArrayList<>();
        Block blockMock = mock(Block.class);
        blocks.add(blockMock);

        PieceModel pieceModelMock = mock(PieceModel.class);

        when(pieceModelMock.getBlocks()).thenReturn(blocks);

        assertEquals(blocks, pieceModelMock.getBlocks());
    }
}
