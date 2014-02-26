package pieces;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import board.Board;
import board.Tile;
import org.junit.rules.TestName;

public class KingTest {

    @Rule
    public TestName name = new TestName();

	Board	board		= new Board( );
	Piece	king		= new Piece( Type.KING, Color.WHITE );
	Piece	blackKing	= new Piece( Type.KING, Color.BLACK );
	Piece	blackRook	= new Piece( Type.ROOK, Color.BLACK );
	Piece	blackRook2	= new Piece( Type.ROOK, Color.BLACK );
	Piece	knightTwo	= new Piece( Type.KNIGHT, Color.WHITE );
	Piece	pawn		= new Piece( Type.PAWN, Color.WHITE );
	Piece	bPawn		= new Piece( Type.PAWN, Color.BLACK );
	Piece	bishop		= new Piece( Type.BISHOP, Color.BLACK );
	Piece	king2		= new Piece( Type.KING, Color.WHITE );
	Piece	rook		= new Piece( Type.ROOK, Color.WHITE );
	Piece	rook2		= new Piece( Type.ROOK, Color.WHITE );
	Piece	bRook		= new Piece( Type.ROOK, Color.BLACK );

	@Test
	public void blackCastleTest() {
		this.board.setPiece( this.blackKing, 7, 4 );
		this.board.setPiece( this.blackRook, 7, 0 );
		this.board.setPiece( this.blackRook2, 7, 7 );
		this.printMoves( this.blackKing );
	}

	@Test
	public void castleTest() {
		this.board.setPiece( this.king, 0, 4 );
		this.board.setPiece( this.rook, 0, 0 );
		this.printMoves( this.king );
	}

	@Test
	public void castleTest2() {
		this.board.setPiece( this.king, 0, 4 );
		this.board.setPiece( this.rook2, 0, 0 );
		this.board.setPiece( this.rook, 0, 7 );
		this.printMoves( this.king );
	}

	@Test
	public void castleTestThroughCheck() {
		this.board.setPiece( this.king, 0, 4 );
		this.board.setPiece( this.rook, 0, 0 );
		this.board.setPiece( this.bishop, 2, 4 );
		this.printMoves( this.king );
	}

	@Test
	public void castleTestThroughCheck2() {
		this.board.setPiece( this.king, 0, 4 );
		this.board.setPiece( this.rook, 0, 0 );
		this.board.setPiece( this.rook2, 0, 7 );
		this.board.setPiece( this.bishop, 2, 4 );
		this.printMoves( this.king );
	}

	@Test
	public void castleTestThroughCheck3() {
		this.board.setPiece( this.king, 0, 4 );
		this.board.setPiece( this.rook, 0, 0 );
		this.board.setPiece( this.rook2, 0, 7 );
		this.board.setPiece( this.bishop, 2, 2 );
		this.printMoves( this.king );
	}

	@Test
	public void rookTest() {
		this.board.setPiece( this.king, 1, 3 );
		this.board.setPiece( this.bishop, 4, 6 );
		this.printMoves( this.king );

	}

	@Test
	public void rookTestWithKing() {
		this.board.setPiece( this.king, 1, 3 );
		this.board.setPiece( this.king2, 2, 3 );
		this.printMoves( this.king, this.king2 );
	}

	@Test
	public void rookTestWithOtherColorPieces() {
		this.board.setPiece( this.king, 1, 3 );
		this.board.setPiece( this.bPawn, 3, 2 );
		this.board.setPiece( this.pawn, 3, 4 );
		this.board.setPiece( this.knightTwo, 5, 3 );
		this.printMoves( this.king, this.knightTwo );
	}

	@Test
	public void rookTestWithOtherPieces() {
		this.board.setPiece( this.king, 1, 3 );
		this.board.setPiece( this.pawn, 2, 3 );
		this.printMoves( this.king );
	}

	@Before
	public void setUp() {
		Piece.resetBoard( );
		this.board = Piece.getBoard( );
	}

	@Test
	public void testCheck() {
		this.board.setPiece( this.king, 0, 4 );
		this.board.setPiece( this.blackKing, 2, 3 );
		this.board.setPiece( this.blackRook, 0, 0 );
		this.printMoves( this.king, this.blackKing );
	}

	@Test
	public void testCheckmate() {
		this.board.setPiece( this.king, 0, 3 );
		this.board.setPiece( this.blackKing, 2, 3 );
		this.board.setPiece( this.blackRook, 0, 0 );
		this.printMoves( this.king, this.blackKing );
	}

	@Test
	public void testNotCheck() {
		this.board.setPiece( this.king, 0, 3 );
		this.board.setPiece( this.blackKing, 2, 4 );
		this.board.setPiece( this.blackRook, 1, 0 );
		this.printMoves( this.king, this.blackKing );
	}

	private void printMoves(Piece... piece) {

		ArrayList<Tile> t = new ArrayList<Tile>( );
		for ( Piece p : piece ) {
			t.addAll( p.getAllMoves( ) );

		}
		Assert.assertEquals( t.get( 0 ), this.board.getSquare( t.get( 0 )
				.getRow( ), t.get( 0 ).getColumn( ) ) );
        String testStr = "-------TESTTESTTEST----------";
		System.out.println( testStr );
        String testName = name.getMethodName();
        String dashes = StringUtils.repeat('-',(testStr.length() - testName.length())/2);
        System.out.println( dashes + testName + dashes);
		for ( Iterator<Tile> i = t.iterator( ); i.hasNext( ); ) {
			Tile tile = i.next( );
			Assert.assertTrue( this.board.getSquare( tile.getRow( ),
					tile.getColumn( ) ) == tile );
			tile.setTextRepresentation( tile.getTextRepresentation( ) + "X" );
			Assert.assertTrue( tile.getTextRepresentation( ).endsWith( "X" ) );
			Assert.assertTrue( this.board
					.getSquare( tile.getRow( ), tile.getColumn( ) )
					.getTextRepresentation( ).endsWith( "X" ) );
			// System.out.println(tile.getRow() + 1 + " " + (tile.getColumn() +
			// 1));
		}
		System.out.print( this.board.textDraw( ) );
	}

}
