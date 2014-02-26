package movement;

import java.util.ArrayList;
import java.util.BitSet;

import board.Board;
import board.Location;

public class HorizontalMovement {

	public static ArrayList<Location> getHorizontalMoves(Board board, int row,
			int col) {
		return getHorizontalMoves( board, row, col, false );
	}

	public static ArrayList<Location> getHorizontalMoves(Board board, int row,
			int col, boolean checkThreats) {
		BitSet bitSet = new BitSet( 4 );
		bitSet.flip( 0, 4 );
		BitSet compareEmpty = new BitSet( 4 );
		ArrayList<Location> possibleTiles = new ArrayList<Location>( );
		for ( int i = 1; i < 8; i++ ) {

			// BitSet 0
			if ( (row + i < 8 && col < 8) && (row + i >= 0 && col >= 0)
					&& bitSet.get( 0 ) ) {
				possibleTiles.add( new Location( row + i, col ) );
				if ( board.getSquare( row + i, col ).isOccupied( )
						&& !checkThreats ) {
					bitSet.set( 0, false );
				}
			} else {
				bitSet.set( 0, false );
			}

			// BitSet 1
			if ( (row - i < 8 && col < 8) && (row - i >= 0 && col >= 0)
					&& bitSet.get( 1 ) ) {
				possibleTiles.add( new Location( row - i, col ) );
				if ( board.getSquare( row - i, col ).isOccupied( )
						&& !checkThreats ) {
					bitSet.set( 1, false );
				}
			} else {
				bitSet.set( 1, false );
			}

			// BitSet 1
			if ( (col - i < 8) && (col - i >= 0) && bitSet.get( 2 ) ) {
				possibleTiles.add( new Location( row, col - i ) );
				if ( board.getSquare( row, col - i ).isOccupied( )
						&& !checkThreats ) {
					bitSet.set( 2, false );
				}
			} else {
				bitSet.set( 2, false );
			}

			// BitSet 3
			if ( (row < 8 && col + i < 8) && (row >= 0 && col + i >= 0)
					&& bitSet.get( 3 ) ) {
				possibleTiles.add( new Location( row, col + i ) );
				if ( board.getSquare( row, col + i ).isOccupied( )
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
