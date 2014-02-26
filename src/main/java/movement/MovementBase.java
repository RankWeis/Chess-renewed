package movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;
import pieces.Color;
import pieces.Type;
import board.Board;
import board.Location;
import board.Tile;
import board.TileComparator;

public class MovementBase {

	public static ArrayList<Tile> getMovements(Board board, Color color,
			Type t, Location l) {
		return getMovements( board, color, t, l, false );

	}

	public static ArrayList<Tile> getMovements(Board board, Color color,
			Type t, Location l, boolean attacksOnly) {
		ArrayList<Location> movements = null;
		int row = l.getRow( );
		int col = l.getCol( );
		switch ( t ) {
		case PAWN:
			movements = PawnMovement.getAdjacentMoves( board, color, row, col,
					attacksOnly );
			break;
		case ROOK:
			movements = HorizontalMovement.getHorizontalMoves( board, row, col,
					attacksOnly );
			break;
		case KNIGHT:
			movements = KnightMovement.getLMoves( row, col );
			break;
		case BISHOP:
			movements = DiagonalMovement.getDiagonalMoves( board, row, col,
					attacksOnly );
			break;
		case KING:
			movements = KingMovement
					.getKingMoves( board, row, col, attacksOnly );
			break;
		case QUEEN:
			movements = HorizontalMovement.getHorizontalMoves( board, row, col,
					attacksOnly );
			movements.addAll( DiagonalMovement.getDiagonalMoves( board, row,
					col, attacksOnly ) );
			break;
		}

		ArrayList<Tile> ret = filterMovements( board, l, movements, color );
		if ( ret != null && !ret.isEmpty( ) ) {
			Assert.assertEquals( ret.get( 0 ), board.getSquare( ret.get( 0 )
					.getRow( ), ret.get( 0 ).getColumn( ) ) );
		}

		return ret;

	}

	private static ArrayList<Tile> filterMovements(Board board, Location l,
			List<Location> locations, Color color) {

		ArrayList<Tile> moves = new ArrayList<Tile>( );

		for ( Location location : locations ) {
			Tile tile = board.getSquare( location );
			// You can't take a king or your own pieces
			if ( ! ( tile.isOccupied( ) && ( tile.getOccupiedPiece( )
					.getColor( ).equals( color ) ) ) ) {
				moves.add( tile );
			}
		}
		Collections.sort( moves, new TileComparator( ) );
		return moves;

	}

}
