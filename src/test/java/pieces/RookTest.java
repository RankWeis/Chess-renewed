package pieces;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.Tile;

public class RookTest {

	Board	board	= new Board( );
	Piece	rook	= new Piece( Type.ROOK, Color.WHITE );
	Piece	pawn	= new Piece( Type.PAWN, Color.WHITE );
	Piece	bPawn	= new Piece( Type.PAWN, Color.BLACK );
	Piece	king	= new Piece( Type.KING, Color.WHITE );

	@Before
	public void setUp() {
		Piece.resetBoard( );
		board = Piece.getBoard( );
	}

	@Test
	public void rookTest() {
		board.setPiece( rook, 1, 3 );
		printMoves( rook );

	}

	@Test
	public void rookTestWithOtherPieces() {
		board.setPiece( rook, 1, 3 );
		board.setPiece( pawn, 2, 3 );
		printMoves( rook );
	}

	@Test
	public void rookTestWithOtherColorPieces() {
		board.setPiece( rook, 1, 3 );
		board.setPiece( bPawn, 2, 3 );
		printMoves( rook );
	}

	@Test
	public void rookTestWithKing() {
		board.setPiece( rook, 1, 3 );
		board.setPiece( king, 2, 3 );
		printMoves( rook );
	}

	public void printMoves(Piece piece) {
		ArrayList<Tile> t = piece.getAllMoves( );
		Assert.assertEquals( t.get( 0 ),
				board.getSquare( t.get( 0 ).getRow( ), t.get( 0 ).getColumn( ) ) );
		System.out.println( "-------TESTTESTTEST----------" );
		for ( Iterator<Tile> i = t.iterator( ); i.hasNext( ); ) {
			Tile tile = i.next( );
			Assert.assertTrue( board.getSquare( tile.getRow( ),
					tile.getColumn( ) ) == tile );
			tile.setTextRepresentation( tile.getTextRepresentation( ) + "X" );
			Assert.assertTrue( tile.getTextRepresentation( ).endsWith( "X" ) );
			Assert.assertTrue( board
					.getSquare( tile.getRow( ), tile.getColumn( ) )
					.getTextRepresentation( ).endsWith( "X" ) );
			// System.out.println(tile.getRow() + 1 + " " + (tile.getColumn() +
			// 1));
		}
		System.out.println( board.textDraw( ) );
		System.out.println( "-------TESTTESTTEST----------" );
	}

}
