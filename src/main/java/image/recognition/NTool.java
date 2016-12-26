package image.recognition;

import image.text.TextItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class NTool {
    public static java.util.List<TextItem> getRows(TextItem item) {
        float[] rowGist = getRowGist(item.getImg());

        float averageBrightness = 0.0f;
        for (int i = 0; i < rowGist.length; i++)
            averageBrightness += rowGist[i];

        averageBrightness /= rowGist.length;
        averageBrightness *= 0.3f;

        java.util.List<TextItem> rows = new LinkedList<TextItem>();

        for (int i = 0; i < rowGist.length - 2; i++) {

            if ((rowGist[i] + rowGist[i + 1] + rowGist[i] + 2) / 3 > averageBrightness) {
                int start = i;

                while (i < item.getImg().getHeight() && i < rowGist.length && rowGist[i] > averageBrightness) {
                    ++i;
                }

                if (start == i)
                    continue;

                BufferedImage rowImage = item.getImg().getSubimage(0, start, item.getImg().getWidth(), i - start);

                TextItem row = new TextItem();
                row.setImg(rowImage);
                row.setX(item.getX());
                row.setY(item.getY() + start);

                rows.add(row);
            }
        }

        return rows;
    }

    public static java.util.List<TextItem> getColumns(TextItem item) {
        float[] columnGist = getColumnGist(item.getImg());

        float averageBrightness = 0.0f;
        for (int i = 0; i < columnGist.length; i++)
            averageBrightness += columnGist[i];
        averageBrightness /= columnGist.length;
        averageBrightness *= 0.3f;


        java.util.List<TextItem> columns = new LinkedList<TextItem>();

        for (int i = 2; i < item.getImg().getWidth() && i < columnGist.length - 2; i++) {
            if (columnGist[i] > averageBrightness) {
                int start = i;

                while (i < item.getImg().getWidth() - 2 && i < columnGist.length - 2) {
                    float aveSum = (columnGist[i + 1] + columnGist[i + 2] + columnGist[i - 1] + columnGist[i - 2]) / 4.0f;

                    if (columnGist[i] < averageBrightness && aveSum < averageBrightness) {
                        break;
                    }

                    ++i;
                }

                BufferedImage columnImage = item.getImg().getSubimage(start, 0, i - start, item.getImg().getHeight());

                TextItem column = new TextItem();
                column.setImg(columnImage);
                column.setX(item.getX() + start);
                column.setY(item.getY());

                columns.add(column);
            }
        }

        return columns;
    }

    public static float[] getRowGist(BufferedImage img) {
        float[] rowGist = new float[img.getHeight()];

        for (int i = 0; i < img.getHeight(); i++) {
            rowGist[i] = 0.0f;

            for (int j = 0; j < img.getWidth(); j++) {
                Color pixel = new Color(img.getRGB(j, i));
                rowGist[i] += 255.f - (0.21f * pixel.getRed() + 0.72f * pixel.getGreen() + 0.07f * pixel.getBlue());
            }

            rowGist[i] /= img.getWidth();
        }

        return rowGist;
    }

    public static float[] getColumnGist(BufferedImage img) {
        float[] columnGist = new float[img.getWidth()];

        for (int i = 0; i < img.getWidth(); i++) {
            columnGist[i] = 0.0f;

            for (int j = 0; j < img.getHeight(); j++) {
                Color pixel = new Color(img.getRGB(i, j));
                columnGist[i] += 255.f - (0.21f * pixel.getRed() + 0.72f * pixel.getGreen() + 0.07f * pixel.getBlue());
            }

            columnGist[i] /= img.getHeight();
        }

        return columnGist;
    }
}
