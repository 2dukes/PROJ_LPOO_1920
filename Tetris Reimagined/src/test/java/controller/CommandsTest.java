package controller;

import org.junit.Before;
import org.junit.Test;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceController;
import tetrisreimagined.play.rules.commands.MoveRight;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandsTest {

    PieceModel pieceModelMock;
    ArenaModel arenaModelMock;
    Observer observerMock;
    PieceController pieceControllerMock;

    @Before
    public void setup() {
        pieceModelMock = mock(PieceModel.class);
        arenaModelMock = mock(ArenaModel.class);
        observerMock = mock(Observer.class);
        pieceControllerMock = mock(PieceController.class);

        when(pieceControllerMock.getPieceModel()).thenReturn(pieceModelMock);

        Block block1 = new Block(new Position(4, 5), new Color("", ""), 1);
        Block block2 = new Block(new Position(4, 6), new Color("", ""), 2);

        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        when(pieceModelMock.getBlocks()).thenReturn(blocks);
    }

    @Test
    public void moveRight() {
        MoveRight moveRight = new MoveRight(pieceModelMock, observerMock, arenaModelMock);
        when(pieceControllerMock.canGoRight(observerMock, arenaModelMock)).thenReturn(true);

        assertTrue(moveRight.execute(pieceControllerMock));

        assertEquals(new Position(6, 5), pieceControllerMock.getPieceModel().getBlocks().get(0).getPosition());
        assertEquals(new Position(6, 6), pieceControllerMock.getPieceModel().getBlocks().get(1).getPosition());
    }

}
