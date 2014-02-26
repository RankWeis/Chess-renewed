package movement;

import java.util.ArrayList;
import java.util.BitSet;

import board.Board;
import board.Location;

public class DiagonalMovement {

	public static ArrayList<Location> getDiagonalMoves(Board board, int row,
			int col) {
		return getDiagonalMoves( board, row, col, false );
	}

	/**
	 * Need to return tiles which, for row = x and col = y (x-1, y-1), (x-2,
	 * y-2) etc. (x-1, y+1), (x-2, y+2) etc. (x+1, y-1), (x+2, y-2) etc. (x+1,
	 * y+1), (x+2, y+2) etc.
	 * 
	 * @param row
	 * @param col
	 */
	public static ArrayList<Location> getDiagonalMoves(Board board, int row,
			int col, boolean checkThreats) {

		BitSet bitSet = new BitSet( 4 );
		bitSet.flip( 0, 4 );
		BitSet compareEmpty = new BitSet( 4 );
		ArrayList<Location> possibleTiles = new ArrayList<Location>( );
		for ( int i = 1; i < 8; i++ ) {

			// BitSet 0
			if ( (row + i < 8 && col + i < 8) && (row + i >= 0 && col + i >= 0)
					&& bitSet.get( 0 ) ) {
				possibleTiles.add( new Location( row + i, col + i ) );
				if ( board.getSquare( row + i, col + i ).isOccupied( )
						&& !checkThreats ) {
					bitSet.set( 0, false );
				}
			} else {
				bitSet.set( 0, false );
			}

			// BitSet 1
			if ( (row + i < 8 && col - i < 8) && (row + i >= 0 && col - i >= 0)
					&& bitSet.get( 1 ) ) {
				possibleTiles.add( new Location( row + i, col - i ) );
				if ( board.getSquare( row + i, col - i ).isOccupied( )
						&& !checkThreats ) {
					bitSet.set( 1, false );
				}
			} else {
				bitSet.set( 1, false );
			}

			// BitSet 2
			if ( (row - i < 8 && col + i < 8) && (row - i >= 0 && col + i >= 0)
					&& bitSet.get( 2 ) ) {
				possibleTiles.add( new Location( row - i, col + i ) );
				if ( board.getSquare( row - i, col + i ).isOccupied( )
						&& !checkThreats ) {
					bitSet.set( 2, false );
				}
			} else {
				bitSet.set( 2, false );
			}

			// BitSet 3
			if ( (row - i < 8 && col - i < 8) && (row - i >= 0 && col - i >= 0)
					&& bitSet.get( 3 ) ) {
				possibleTiles.add( new Location( row - i, col - i ) );
				if ( board.getSquare( row - i, col - i ).isOccupied( )
						&& !checkThreats ) {
					bitSet.set( 3, false );
				}
			} else {
				bitSet.set( 3, false );
			}

			if ( bitSet.equals( compareEmpty ) ) {
				break;
			}
		}
		return possibleTiles;

	}

}
