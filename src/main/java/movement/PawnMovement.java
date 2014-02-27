package movement;

import java.util.ArrayList;

import pieces.Color;
import pieces.Piece;
import pieces.Type;
import board.Board;
import board.Location;
import board.Tile;

class PawnMovement {

    public static ArrayList<Location> getAdjacentMoves( Board board,
            Color color, int row, int col ) {
        return getAdjacentMoves( board, color, row, col, false );
    }

    public static ArrayList<Location> getAdjacentMoves( Board board,
            Color color, int row, int col, boolean attacksOnly ) {
        ArrayList<Location> ret = new ArrayList<Location>();
        int direction = color == Color.WHITE ? 1 : -1;
        int playersBackRow = color == Color.WHITE ? 1 : 6;
        int opponentsBackRow = color == Color.WHITE ? 6 : 1;
        int enPassantRow = color == Color.WHITE ? 4 : 3;

        int[][] legalAttacks = { {row + direction, col + 1},
            {row + direction, col - 1}

        };
        for ( int[] curr : legalAttacks ) {
            int curRow = curr[0];
            int curCol = curr[1];
            if ( checkLegal( curRow, curCol ) ) {
                Location loc = new Location( curRow, curCol );
                Tile t = board.getSquare( loc );
                if ( t.isOccupied() && t.getOccupiedPiece().getColor() != color ) {
                    ret.add( loc );
                    continue;
                }
                if ( row == enPassantRow ) {
                    if ( enPassantPossible(
                                            board.getSquare( row, curCol ).getOccupiedPiece(),
                                            opponentsBackRow ) ) {
                        ret.add( loc );
                    }
                }
            }
        }

        if ( !attacksOnly ) {
            if ( checkLegal( row + direction, col ) ) {
                Location forward = new Location( row + direction, col );
                if ( !board.getSquare( forward ).isOccupied() ) {
                    ret.add( new Location( row + direction, col ) );
                }
            }
            if ( row == playersBackRow ) {
                if ( checkLegal( row + 2 * direction, col ) ) {
                    Location ahead = new Location( row + 2 * direction, col );
                    if ( !board.getSquare( ahead ).isOccupied() ) {
                        ret.add( new Location( row + 2 * direction, col ) );
                    }
                }
            }
        }
        return ret;
    }

    private static boolean enPassantPossible( Piece p, int opponentsBackRow ) {
        if ( p != null && Type.PAWN.equals( p.getType() ) ) {
            if ( p.getLastTile().getRow() == opponentsBackRow ) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkLegal( int row, int col ) {
        return row < 8 && row >= 0 && col < 8 && col >= 0;
    }

}
