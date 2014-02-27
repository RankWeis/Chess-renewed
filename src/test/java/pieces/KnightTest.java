package pieces;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.Tile;

public class KnightTest {

    private Board       board     = new Board();
    private final Piece knight    = new Piece( Type.KNIGHT, Color.WHITE );
    private final Piece knightTwo = new Piece( Type.KNIGHT, Color.WHITE );
    private final Piece pawn      = new Piece( Type.PAWN, Color.WHITE );
    private final Piece bPawn     = new Piece( Type.PAWN, Color.BLACK );
    private final Piece king      = new Piece( Type.KING, Color.WHITE );

    @Before
    public void setUp() {
        Piece.resetBoard();
        board = Piece.getBoard();
    }

    @Test
    public void rookTest() {
        board.setPiece( knight, 1, 3 );
        printMoves( knight );

    }

    @Test
    public void rookTestWithOtherPieces() {
        board.setPiece( knight, 1, 3 );
        board.setPiece( pawn, 2, 3 );
        printMoves( knight );
    }

    @Test
    public void rookTestWithOtherColorPieces() {
        board.setPiece( knight, 1, 3 );
        board.setPiece( bPawn, 3, 2 );
        board.setPiece( pawn, 3, 4 );
        board.setPiece( knightTwo, 5, 3 );
        printMoves( knight, knightTwo );
    }

    @Test
    public void rookTestWithKing() {
        board.setPiece( knight, 1, 3 );
        board.setPiece( king, 2, 3 );
        printMoves( knight, king );
    }

    void printMoves( Piece... piece ) {

        ArrayList<Tile> t = new ArrayList<Tile>();
        for ( Piece p : piece ) {
            t.addAll( p.getAllMoves() );

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
