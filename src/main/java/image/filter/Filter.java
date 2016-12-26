package image.filter;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage applyFilter(BufferedImage img);
}
