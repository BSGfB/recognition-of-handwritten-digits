package image.number;

import java.awt.image.BufferedImage;

public class ImageNumber {
    private int rowPosition = 0;
    private int columnPosition = 0;

    private BufferedImage img = null;
    private int number = -1;

    @Override
    public String toString() {
        return "ImageNumber{" +
                "rowPosition=" + rowPosition +
                ", columnPosition=" + columnPosition +
                ", img=" + img +
                ", number=" + number +
                '}';
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
