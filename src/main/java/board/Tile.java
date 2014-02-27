package board;

import pieces.Piece;
import pieces.Type;

public class Tile {

    private boolean attacked;
    private boolean defended;
    private int     attackingTotal;
    private int     row;
    private int     column;

    private String  textRepresentation = "";

    private Piece   occupiedPiece;

    public Tile() {

    }

    public int getAttackingTotal() {
        return attackingTotal;
    }

    public int getColumn() {
        return column;
    }

    public Piece getOccupiedPiece() {
        return occupiedPiece;
    }

    public int getRow() {
        return row;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public boolean isDefended() {
        return defended;
    }

    public boolean isOccupied() {
        return occupiedPiece != null;
    }

    public void setAttacked( boolean attacked ) {
        this.attacked = attacked;
    }

    public void setAttackingTotal( int attackingTotal ) {
        this.attackingTotal = attackingTotal;
    }

    public void setColumn( int column ) {
        this.column = column;
    }

    public void setDefended( boolean defended ) {
        this.defended = defended;
    }

    public void setOccupiedPiece( Piece occupiedPiece ) {
        this.occupiedPiece = occupiedPiece;
        this.textRepresentation = shortName( occupiedPiece );
    }

    public void setRow( int row ) {
        this.row = row;
    }

    public void setTextRepresentation( String textRepresentation ) {
        this.textRepresentation = textRepresentation;
    }

    @Override
    public String toString() {
        return this.textRepresentation;
    }

    private String shortName( Piece p ) {
        if ( p == null ) {
            return "";
        }
        String ret = "" + p.getColor().toString().charAt( 0 );
        Type t = p.getType();
        switch ( t ) {
            case PAWN:
                ret += "P";
                break;
            case ROOK:
                ret += "R";
                break;
            case KNIGHT:
                ret += "N";
                break;
            case BISHOP:
                ret += "B";
                break;
            case QUEEN:
                ret += "Q";
                break;
            case KING:
                ret += "K";
                break;
        }
        return ret;
    }
}
