package image.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MonochromeFilter implements Filter {
    private int level = 150;

    public MonochromeFilter(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public BufferedImage applyFilter(BufferedImage img) {
        BufferedImage outImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color pixel = new Color(img.getRGB(i, j));
                int br = (int) (0.21 * pixel.getRed() + 0.72 * pixel.getGreen() + 0.07 * pixel.getBlue());
                outImg.setRGB(i, j, br < level ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }

        return outImg;
    }
}
