package movement;

import java.util.ArrayList;

import junit.framework.Assert;
import pieces.Color;
import pieces.Piece;
import pieces.Type;
import board.Board;
import board.Location;
import board.Tile;

public class KingMovement {

	private static int	FIRST_ROW_COL	= 0;
	private static int	LAST_ROW_COL	= 7;

	public static ArrayList<Location> getKingMoves(Board board, int row,
			int col, boolean threatsOnly) {
		Piece piece = board.getSquare( row, col ).getOccupiedPiece( );
		ArrayList<Tile> attackedTiles = new ArrayList<Tile>( );
		if ( !threatsOnly ) {
			attackedTiles = board.getAllAttackedTiles( true, piece.getColor( ) );
		}

		Assert.assertTrue( piece != null && piece.getType( ).equals( Type.KING ) );
		ArrayList<Location> ret = new ArrayList<Location>( );
		int[][] legalMoves = {
				{
						row, col - 1
				}, {
						row, col + 1
				}, {
						row + 1, col + 1
				}, {
						row + 1, col - 1
				}, {
						row + 1, col
				}, {
						row - 1, col + 1
				}, {
						row - 1, col - 1
				}, {
						row - 1, col
				}

		};
		for ( int[] curr : legalMoves ) {
			if ( checkLegal( curr[0], curr[1] )
					&& !attackedTiles.contains( board.getSquare( curr[0],
							curr[1] ) ) ) {
				ret.add( new Location( curr[0], curr[1] ) );
			}
		}

		// Castling moves
		if ( !attackedTiles.contains( board.getSquare( piece.getLocation( ) ) )
				&& piece.isFirstMove( ) ) {
			int row_col = piece.getColor( ) == Color.WHITE ? FIRST_ROW_COL
					: LAST_ROW_COL;
			Piece occupiedFirstRowCol = board
					.getSquare( row_col, FIRST_ROW_COL ).getOccupiedPiece( );
			if ( occupiedFirstRowCol != null
					&& occupiedFirstRowCol.isFirstMove( ) ) {
				// Check to see nothing is occupying the spaces between king
				// and rook
				if ( !KingMovement.isOccupiedOrThreatened( attackedTiles,
						board.getSquare( row_col, 1 ),
						board.getSquare( row_col, 2 ),
						board.getSquare( row_col, 3 ) ) ) {
					ret.add( new Location( row_col, 2 ) );
				}
				Piece occupiedLastRowCol = board.getSquare( row_col,
						LAST_ROW_COL ).getOccupiedPiece( );
				if ( occupiedLastRowCol != null
						&& occupiedLastRowCol.isFirstMove( ) ) {
					// Check to see nothing is occuping the spaces between king
					// and rook
					if ( !KingMovement.isOccupiedOrThreatened( attackedTiles,
							board.getSquare( row_col, 5 ),
							board.getSquare( row_col, 6 ) ) ) {
						ret.add( new Location( row_col, 6 ) );
					}

				}
			}

		}
		return ret;
	}

	public static boolean isOccupiedOrThreatened(ArrayList<Tile> threatened,
			Tile... tiles) {

		for ( Tile t : tiles ) {
			if ( t.isOccupied( ) || threatened.contains( t ) ) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkLegal(int row, int col) {
		if ( row < 8 && row >= 0 && col < 8 && col >= 0 ) {
			return true;
		} else {
			return false;
		}
	}

}
