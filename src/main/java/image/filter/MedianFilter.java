package image.filter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MedianFilter implements Filter {
    public BufferedImage applyFilter(BufferedImage img) {
        BufferedImage outImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        int[] R = new int[9];
        int[] B = new int[9];
        int[] G = new int[9];

        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {

                for (int n = i - 1, k = 0; n < i + 2; n++) {
                    for (int m = j - 1; m < j + 2; m++, k++) {
                        Color pixel = new Color(img.getRGB(n, m));

                        R[k] = pixel.getRed();
                        G[k] = pixel.getGreen();
                        B[k] = pixel.getBlue();
                    }
                }

                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);

                outImg.setRGB(i, j, new Color(R[3], B[4], G[5]).getRGB());
            }
        }

        return outImg;
    }
}
