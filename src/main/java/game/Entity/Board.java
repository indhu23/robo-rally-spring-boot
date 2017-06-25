package game.Entity;

/**
 * Created by Anand on 5/30/2017.
 */
public class Board {
    Size size;
    Fields []fields;

    public Size getSize() {
        return size;
    }

    /**
     * set the size of the board (length*width)
     * @param size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     *
     * @return returns the description of all fileds in the board
     */
    public Fields[] getFields() {
        return fields;
    }

    /**
     * set the description of all fields in the board
     * @param fields
     */
    public void setFields(Fields[] fields) {
        this.fields = fields;
    }
}
