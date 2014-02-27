package board;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

import pieces.Piece;

public class SetupBoardTest {

    private Board board;

    @Test
    public void setupBoard() {
        board = new Board();
        board.setupGame();
        printMoves( board.getPieces().toArray() );
    }

    void printMoves( Object... piece ) {

        ArrayList<Tile> t = new ArrayList<Tile>();
        for ( Object p : piece ) {
            Piece cur = (Piece) p;
            t.addAll( cur.getAllMoves() );

        }
        Assert.assertEquals(
                             t.get( 0 ),
                             board.getSquare(
                                              t.get( 0 ).getRow(),
                                              t.get( 0 ).getColumn() ) );
        System.out.println( "-------TESTTESTTEST----------" );
        for ( Iterator<Tile> i = t.iterator(); i.hasNext(); ) {
            Tile tile = i.next();
            Assert.assertTrue( board.getSquare( tile.getRow(), tile.getColumn() ) == tile );
            tile.setTextRepresentation( tile.getTextRepresentation() + "X" );
            Assert.assertTrue( tile.getTextRepresentation().endsWith( "X" ) );
            Assert.assertTrue( board.getSquare( tile.getRow(), tile.getColumn() ).getTextRepresentation().endsWith(
                                                                                                                    "X" ) );
            // System.out.println(tile.getRow() + 1 + " " + (tile.getColumn() +
            // 1));
        }
        System.out.println( board.textDraw() );
        System.out.println( "-------TESTTESTTEST----------" );
    }

}
