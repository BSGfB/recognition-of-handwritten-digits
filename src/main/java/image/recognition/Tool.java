package image.recognition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Tool {
    public static BufferedImage[] getRows(BufferedImage img) {
        float[] rowGist = getRowGist(img);

        float averageBrightness = 0.0f;
        for (int i = 0; i < rowGist.length; i++)
            averageBrightness += rowGist[i];

        averageBrightness /= rowGist.length;
        averageBrightness *= 0.3f;

        java.util.List<BufferedImage> rows = new LinkedList<BufferedImage>();

        for (int i = 0; i < rowGist.length - 2; i++) {

            if ((rowGist[i] + rowGist[i + 1] + rowGist[i] + 2) / 3 > averageBrightness) {
                int start = i;

                while (i < img.getHeight() && i < rowGist.length && rowGist[i] > averageBrightness) {
                    ++i;
                }

                if (start == i)
                    continue;

                BufferedImage newRow = img.getSubimage(0, start, img.getWidth(), i - start);
                rows.add(newRow);
            }
        }

        return (BufferedImage[]) rows.toArray();
    }

    public static BufferedImage[] getColumns(BufferedImage img) {
        float[] columnGist = getColumnGist(img);

        float averageBrightness = 0.0f;
        for (int i = 0; i < columnGist.length; i++)
            averageBrightness += columnGist[i];
        averageBrightness /= columnGist.length;

        java.util.List<BufferedImage> columns = new LinkedList<BufferedImage>();

        for (int i = 2; i < img.getWidth() && i < columnGist.length - 2; i++) {
            if (columnGist[i] > averageBrightness) {
                int start = i;

                while (i < img.getWidth() - 2 && i < columnGist.length - 2) {
                    float aveSum = (columnGist[i + 1] + columnGist[i + 2] + columnGist[i - 1] + columnGist[i - 2]) / 4.0f;

                    if (columnGist[i] < averageBrightness && aveSum < averageBrightness) {
                        break;
                    }

                    ++i;
                }

                BufferedImage newRow = img.getSubimage(start, 0, i - start, img.getHeight());
                columns.add(newRow);
            }
        }

        return (BufferedImage[]) columns.toArray();
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
