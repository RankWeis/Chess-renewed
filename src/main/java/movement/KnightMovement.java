package movement;

import java.util.ArrayList;

import board.Location;

class KnightMovement {

    public static ArrayList<Location> getLMoves( int row, int col ) {
        ArrayList<Location> ret = new ArrayList<Location>();
        int[][] legalMoves = { {row + 1, col + 2}, {row + 1, col - 2},
            {row + 2, col + 1}, {row + 2, col - 1}, {row - 1, col + 2},
            {row - 1, col - 2}, {row - 2, col + 1}, {row - 2, col - 1}

        };
        for ( int[] curr : legalMoves ) {
            if ( checkLegal( curr[0], curr[1] ) ) {
                ret.add( new Location( curr[0], curr[1] ) );
            }
        }
        return ret;
    }

    private static boolean checkLegal( int row, int col ) {
        return row < 8 && row >= 0 && col < 8 && col >= 0;
    }

}
