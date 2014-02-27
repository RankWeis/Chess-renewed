package board;

import java.util.ArrayList;

import pieces.Color;
import pieces.Piece;
import pieces.Type;

@SuppressWarnings({"SF_SWITCH_NO_DEFAULT"})
public class Board {

    private static final ArrayList<Piece> checkedPieces = new ArrayList<Piece>();

    public static Location convertAlgebraic( String algebraicLocation ) {
        algebraicLocation = algebraicLocation.toUpperCase();
        assert (algebraicLocation.length() == 2);
        int col = algebraicLocation.charAt( 0 ) - 65;
        int row = Integer.parseInt( algebraicLocation.substring( 1, 2 ) ) - 1;
        return new Location( row, col );

    }

    /**
     * The chessboard - organized by Tile[row][column].
     */
    private Tile[][]         board  = new Tile[8][8];

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    public Board() {
        Piece.setBoard( this );
        for ( int i = 0; i < 8; i++ ) {
            for ( int j = 0; j < 8; j++ ) {
                Tile tile = new Tile();
                tile.setRow( i );
                tile.setColumn( j );
                this.board[i][j] = tile;

            }
        }
    }

    public ArrayList<Tile> getAllAttackedTiles( boolean minusKing, Color c ) {
        ArrayList<Tile> t = new ArrayList<Tile>();
        for ( Piece p : this.pieces ) {
            if ( checkedPieces.contains( p ) || p.getColor() == c ) {
                continue;
            }
            checkedPieces.add( p );
            t.addAll( p.getAllAttacks() );
        }
        checkedPieces.clear();
        return t;
    }

    ArrayList<Piece> getColorPieces( Color color ) {
        ArrayList<Piece> ret = new ArrayList<Piece>();
        for ( Piece p : this.pieces ) {
            if ( p.getColor().equals( color ) ) {
                ret.add( p );
            }
        }
        return ret;
    }

    public ArrayList<Piece> getColorTypePieces( Color c, Type t ) {
        ArrayList<Piece> ret = new ArrayList<Piece>();
        for ( Piece p : this.getColorPieces( c ) ) {
            if ( p.getType().equals( t ) ) {
                ret.add( p );
            }
        }
        return ret;
    }

    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }

    public ArrayList<Piece> getPiecesAttackingSquare( Color c, Tile tile ) {
        ArrayList<Piece> ret = new ArrayList<Piece>();
        for ( Piece p : this.getColorPieces( c ) ) {
            if ( p.getAllMoves().contains( tile ) ) {
                ret.add( p );
            }
        }
        return ret;
    }

    public Tile getSquare( int row, int col ) {
        if ( row >= 0 && row < 8 && col >= 0 && row < 8 ) {
            return this.board[row][col];
        } else {
            return null;
        }
    }

    public Tile getSquare( Location location ) {
        return this.getSquare( location.getRow(), location.getCol() );
    }

    Type getTypeForCol( int col ) {
        Type t = null;
        switch ( col ) {

        // Rooks
            case 0:
            case 7:
                t = Type.ROOK;
                break;

            // Knights
            case 1:
            case 6:
                t = Type.KNIGHT;
                break;

            // Bishops
            case 2:
            case 5:
                t = Type.BISHOP;
                break;

            case 3:
                t = Type.QUEEN;
                break;
            case 4:
                t = Type.KING;
                break;
        }
        return t;
    }

    public boolean movePiece( Piece piece, String algebraicLocation ) {
        Location loc = convertAlgebraic( algebraicLocation );

        return this.movePiece( piece, this.getSquare( loc ) );
    }

    public boolean movePiece( Piece piece, Tile tile ) {
        Tile oldTile = this.getSquare( piece.getLocation() );
        Piece capturedPiece = tile.getOccupiedPiece();
        if ( Type.PAWN.equals( piece.getType() ) ) {
            if ( capturedPiece == null
                 && oldTile.getColumn() != tile.getColumn() ) {
                // En Passant
                Tile capturedTile = this.getSquare(
                                                    oldTile.getRow(),
                                                    tile.getColumn() );
                capturedPiece = capturedTile.getOccupiedPiece();
                capturedTile.setOccupiedPiece( null );
            }
        }

        tile.setOccupiedPiece( piece );

        oldTile.setOccupiedPiece( null );
        tile.setOccupiedPiece( piece );

        piece.move();
        piece.setLastTile( oldTile );
        piece.setLocation( new Location( tile.getRow(), tile.getColumn() ) );

        return capturedPiece != null;
    }

    public void setBoard( Tile[][] board ) {
        this.board = board;
    }

    public boolean setPiece( Piece piece, int row, int col ) {
        if ( this.getSquare( row, col ).isOccupied() ) {
            return false;
        } else {
            Location loc = new Location( row, col );
            if ( !this.pieces.contains( piece ) ) {
                this.pieces.add( piece );
            }
            piece.setLocation( loc );
            Tile tile = this.getSquare( row, col );
            tile.setOccupiedPiece( piece );
            return true;
        }
    }

    public void setPieces( ArrayList<Piece> pieces ) {
        this.pieces = pieces;
    }

    public void setupGame() {
        for ( int i = 0; i < 2; i++ ) {
            Color color = Color.WHITE;
            int piecesRow = 0;
            int pawnRow = 1;
            if ( i == 1 ) {
                color = Color.BLACK;
                piecesRow = 7;
                pawnRow = 6;
            }
            for ( int j = 0; j < 8; j++ ) {
                Type t = this.getTypeForCol( j );
                Piece majorPiece = new Piece( t, color,
                                              new Location( piecesRow, j ) );
                Piece pawn = new Piece( Type.PAWN, color,
                                        new Location( pawnRow, j ) );

                this.board[piecesRow][j].setOccupiedPiece( majorPiece );
                this.board[pawnRow][j].setOccupiedPiece( pawn );
                this.pieces.add( majorPiece );
                this.pieces.add( pawn );

            }
        }
    }

    public String textDraw() {
        StringBuilder rep = new StringBuilder();
        rep.append( "________________________________________________________\n" );
        for ( int i = 7; i >= 0; i-- ) {
            for ( int j = 0; j < 8; j++ ) {
                rep.append( "|").append(String.format( "%3s", this.board[i][j].toString() ) );
            }
            rep.append( "|" );
            rep.append( '\n' );
        }
        ArrayList<Tile> blackAttackedSquares = this.getAllAttackedTiles(
                                                                         false,
                                                                         Color.WHITE );
        ArrayList<Tile> whiteAttackedSquares = this.getAllAttackedTiles(
                                                                         false,
                                                                         Color.BLACK );
        for ( Piece p : this.pieces ) {
            if ( p.getType().equals( Type.KING ) ) {
                if ( p.getColor().equals( Color.WHITE ) ) {
                    if ( blackAttackedSquares.contains( this.getSquare( p.getLocation() ) ) ) {
                        if ( p.getAllMoves().isEmpty() ) {
                            System.out.println( "Checkmate!" );
                        } else {
                            System.out.println( "White is in check!" );
                        }
                    }
                } else {

                    if ( whiteAttackedSquares.contains( this.getSquare( p.getLocation() ) ) ) {
                        if ( p.getAllMoves().isEmpty() ) {
                            System.out.println( "Checkmate!" );
                        } else {
                            System.out.println( "Black is in check!" );
                        }
                    }
                }
            }
        }
        rep.append( "_______________________________________________________\n" );
        return rep.toString();
    }
}
