package pieces;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.Tile;

public class BishopTest {
	Board	board;
	Piece	bishop		= new Piece( Type.BISHOP, Color.WHITE );
	Piece	pawn		= new Piece( Type.PAWN, Color.WHITE );
	Piece	king		= new Piece( Type.KING, Color.BLACK );

	Piece	blackPawn	= new Piece( Type.PAWN, Color.BLACK );

	@Before
	public void setUp() {
		Piece.resetBoard( );
		board = Piece.getBoard( );
	}

	@Test
	public void bishopTest() {
		board.setPiece( bishop, 1, 3 );
		printMoves( bishop );

	}

	@Test
	public void bishopTestWithWhitePieces() {
		board.setPiece( bishop, 1, 3 );
		board.setPiece( pawn, 2, 4 );
		printMoves( bishop );
	}

	@Test
	public void bishopTestWithBlacjPieces() {
		board.setPiece( bishop, 1, 3 );
		board.setPiece( blackPawn, 2, 4 );
		printMoves( bishop );
	}

	@Test
	public void bishopTestWithKing() {
		board.setPiece( bishop, 1, 3 );
		board.setPiece( king, 2, 4 );
		printMoves( bishop );
	}

	public void printMoves(Piece piece) {
		ArrayList<Tile> ret = piece.getAllMoves( );
		Assert.assertEquals( ret.get( 0 ), board.getSquare( ret.get( 0 )
				.getRow( ), ret.get( 0 ).getColumn( ) ) );
		System.out.println( "-------TESTTESTTEST----------" );
		for ( Iterator<Tile> i = ret.iterator( ); i.hasNext( ); ) {
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
