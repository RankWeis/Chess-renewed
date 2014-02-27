package board;

public class Location {

    private int row;
    private int col;

    public Location( int row, int col ) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj instanceof Location ) {
            Location that = ( Location ) obj;
            return this.row == that.row && this.col == that.col;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (String.valueOf( row ) + String.valueOf( col)).hashCode();
    }


    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public void setCol( int col ) {
        this.col = col;
    }

    public void setRow( int row ) {
        this.row = row;
    }
}
