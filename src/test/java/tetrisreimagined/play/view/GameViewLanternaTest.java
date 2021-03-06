package tetrisreimagined.play.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.play.controller.Commands.*;
import tetrisreimagined.play.controller.Pieces.PieceController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.*;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

import java.io.IOException;


public class GameViewLanternaTest {

    GameViewLanterna view;
    TerminalScreen screen;
    TextGraphics graphics;

    @BeforeEach
    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        LanternaHandler handler = new LanternaHandler(screen, graphics, 70, 35);
        view = new GameViewLanterna(handler);
    }

    @Test
    public void testDrawPiece() {
        PieceModel pieceModel = new OBlockModel();

        view.drawPiece(pieceModel);

        // A peça OBlock é composta por 16 blocos amarelos (code color = "#FFFF00")
        Mockito.verify(graphics, Mockito.times(16)).setBackgroundColor(TextColor.Factory.fromString("#FFFF00"));

        // verificar a posição inicial de cada bloco unitário da peça
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(i, j), " ");
            }
        }
    }

    @Test
    public void testDrawScore() {
        view.drawScore(0, 0, 10);

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 0), "10");
    }

    @Test
    public void testInitialDraw() {
        view.initialDraw();

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(view.getRealWidth() - 47, 1), new TerminalSize(10, 1), ' ');
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - 47, 1), "NEXT PIECE", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - 47, 10), "HOLD PIECE", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - 45, 25), "SCORE", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - (view.getRealWidth()/3), view.getHeight()/2), "SINGLE PLAYER", SGR.BOLD);
    }

    @Test
    public void testDrawBigScore() throws IOException {
        view.drawBigScore(0, 0, 25);

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth()/2 - 8, 5), "TETRIS REIMAGINED", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(2, 0), "FINAL SCORE: ", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(14, 0), "25", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth()/2 - 10, 25), "Press Enter To Continue", SGR.BOLD);
    }

    @Test
    public void testDrawNextPiece() {
        PieceModel pieceModel = new TBlockModel();

        view.drawNextPiece(pieceModel, 0, 0);

        for(Block block: pieceModel.getBlocks())
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(block.getPosition().getX(), block.getPosition().getY()), " ");

    }

    @Test
    public void testDrawHoldPiece() {
        PieceModel pieceModel = new TBlockModel();

        view.drawHoldPiece(pieceModel, 0, 0);

        for(Block block: pieceModel.getBlocks())
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(block.getPosition().getX(), block.getPosition().getY()), " ");

    }

    @Test
    public void testDrawAll() throws IOException {
        ArenaModel arenaModel = new ArenaModel();
        PieceModel pieceModel1 = new OBlockModel();
        PieceModel pieceModel4 = new ZBlockModel();
        PieceModel pieceModel5 = new JBlockModel();
        PieceModel pieceModel6 = new IBlockModel();

        arenaModel.setCurrentPieceModel(pieceModel6);
        arenaModel.setHoldPieceToDisplay(pieceModel4);
        arenaModel.setNextPieceToDisplay(pieceModel5);
        arenaModel.setScore(125);

        view.drawAll(arenaModel);

        // Score
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth()-44, 29), "125");

        // Hold Piece
        for(Block block: pieceModel4.getBlocks())
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(block.getPosition().getX() + view.getRealWidth() - 45, block.getPosition().getY() + 15), " ");

        // Next Piece
        for(Block block: pieceModel5.getBlocks())
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(block.getPosition().getX() + view.getRealWidth() - 45, block.getPosition().getY() + 3), " ");

        // CurrentPiece
        Mockito.verify(graphics, Mockito.times(16)).setBackgroundColor(TextColor.Factory.fromString("#00FFFF"));
        for(Block block: pieceModel6.getBlocks())
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(block.getPosition().getX(), block.getPosition().getY()), " ");

        // Arena Blocks (blocos que já tocaram no 'chão')
        PieceController pieceController = new PieceController(pieceModel1);
        pieceController.setStartPosition(view);
        for (int i = 0; i < view.getHeight() + 100; i++) {
            pieceController.makeCurrentPieceFall(view, arenaModel);
        }
        arenaModel.addPiece(pieceController.getPieceModel());
        for (int i = 0; i < pieceModel1.getBlocks().size(); i++) {
            assert (arenaModel.getArenaBlocks().contains(pieceModel1.getBlocks().get(i)));
        }

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();

    }

    @Test
    public void testCloseTerminal() throws IOException {
        view.closeTerminal();

        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void testGetCommandMoveRight() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.ArrowRight);
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof MoveRight);
    }

    @Test
    public void testGetCommandMoveLeft() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof MoveLeft);
    }

    @Test
    public void testGetCommandMoveDown() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.ArrowDown);
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof MoveDown);
    }

    @Test
    public void testGetCommandRotateClockWise() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof RotateClockWise);
    }

    @Test
    public void testGetCommandRotateCounterClockWise() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(keyStrokeMock1.getCharacter()).thenReturn('z');
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof RotateCounterClockWise);
    }

    @Test
    public void testGetCommandHardDrop() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(keyStrokeMock1.getCharacter()).thenReturn(' ');
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof HardDrop);
    }

    @Test
    public void testGetCommandPauseGame() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Enter);
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof PauseGame);
    }

    @Test
    public void testGetCommandHold() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(keyStrokeMock1.getCharacter()).thenReturn('c');
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof Hold);
    }

    @Test
    public void testGetCommandExitTerminal() throws IOException {
        ArenaModel arenaModel = new ArenaModel();

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.pollInput()).thenReturn(keyStrokeMock1);

        PieceCommand command = view.getCommand(arenaModel);

        assert (command instanceof ExitTerminal);
    }
}
