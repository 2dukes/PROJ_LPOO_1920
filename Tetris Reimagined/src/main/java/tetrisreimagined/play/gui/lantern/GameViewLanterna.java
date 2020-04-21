package tetrisreimagined.play.gui.lantern;


import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Piece;
import tetrisreimagined.play.observer.Observer;

import java.io.IOException;

public class GameViewLanterna implements Observer<ArenaModel> {

    private Screen screen;
    private int width, height;
    private TextGraphics graphics;

    public GameViewLanterna(int width, int height) throws IOException {
        try {
            this.width = width;
            this.height = height;

            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.graphics = this.screen.newTextGraphics();
            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this.screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changed(ArenaModel arena) {
        drawAll(arena);
    }

    public void drawAll(ArenaModel arena) {
        try {
            this.screen.clear();

            drawPiece(arena.getCurrentPiece());

            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPiece(Piece piece) {
        for (Block block: piece.getBlocks()) {
            drawBlock(block);
        }
    }

    public void drawBlock(Block block) { // change this later
        graphics.setForegroundColor(TextColor.Factory.fromString(block.getColor().getCode()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(block.getPosition().getX(), block.getPosition().getY()), "X");
    }

    @Override
    public COMMAND getCommand() throws IOException {
        while (true) {
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.ArrowUp) return COMMAND.UP;
            if (key.getKeyType() == KeyType.ArrowRight) return COMMAND.RIGHT;
            if (key.getKeyType() == KeyType.ArrowDown) return COMMAND.DOWN;
            if (key.getKeyType() == KeyType.ArrowLeft) return COMMAND.LEFT;
            if (key.getKeyType() == KeyType.EOF) return COMMAND.EOF;
        }
    }

}
