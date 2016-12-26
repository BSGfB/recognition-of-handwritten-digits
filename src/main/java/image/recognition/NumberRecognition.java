package image.recognition;

import image.filter.MonochromeFilter;
import image.number.ImageNumber;
import image.text.TextItem;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NumberRecognition {
    public static ImageNumber[] getNumbersFromImage(BufferedImage img) {

        BufferedImage nextImage = new MonochromeFilter(180).applyFilter(img);
        BufferedImage[] rows = Tool.getRows(nextImage);

        List<ImageNumber> numbers = new LinkedList<ImageNumber>();
        for (int i = 0; i < rows.length; i++) {
            BufferedImage[] characters = Tool.getColumns(img);

            for (int j = 0; j < characters.length; j++) {
                ImageNumber num = new ImageNumber();
                num.setRowPosition(i);
                num.setColumnPosition(j);
                num.setImg(characters[j]);

                numbers.add(num);
            }
        }

        return (ImageNumber[]) numbers.toArray();
    }

/*
    public static List<List<TextItem>> getNumbersFromImage2(BufferedImage img) {
        BufferedImage nextImage = new MonochromeFilter(120).applyFilter(img);

        TextItem imgItem = new TextItem();
        imgItem.setImg(nextImage);
        imgItem.setX(0);
        imgItem.setY(0);

        java.util.List<TextItem> rows = NTool.getRows(imgItem);

        List<List<TextItem>> numbers = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            java.util.List<TextItem> characters = NTool.getColumns(rows.get(i));

            numbers.add(characters);
        }

        return numbers;
    }
*/


    public static List<List<TextItem>> getNumbersFromImage2(BufferedImage img) {
        BufferedImage nextImage = new MonochromeFilter(120).applyFilter(img);

        TextItem imgItem = new TextItem();
        imgItem.setImg(nextImage);
        imgItem.setX(0);
        imgItem.setY(0);

        java.util.List<TextItem> rows = NTool.getRows(imgItem);

        List<List<TextItem>> numbers = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            java.util.List<TextItem> characters = NTool.getColumns(rows.get(i));

            java.util.List<TextItem> rowNumbers = new ArrayList<>();

            for(int j = 0; j < characters.size(); j++) {
                java.util.List<TextItem> parts = NTool.getRows(characters.get(j));

                int maxI = 0;
                for(int k = 0; k < parts.size(); k++) {
                    if(parts.get(maxI).getImg().getHeight() > parts.get(k).getImg().getHeight()) {
                        maxI = k;
                    }
                }
                rowNumbers.add(parts.get(maxI));
            }

            numbers.add(rowNumbers);
        }

        return numbers;
    }

}
