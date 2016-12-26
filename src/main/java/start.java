import image.recognition.NumberRecognition;
import image.text.TextItem;
import nn.executor.MLPExecutor;
import nn.layer.Layer;
import nn.parser.NNParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class start {
    public static void main(String[] args) {
        Layer[] layers = NNParser.parse(new File("nn.txt"));
        MLPExecutor executor = new MLPExecutor();
        executor.setLayers(layers);

        if (args.length < 1) {
            System.out.println("[ERROR] Invalid number of parameters.");
            return;
        }

        File fileImage = new File(args[0]);
        if (!fileImage.isFile()) {
            System.out.println("[ERROR] Not file.");
            return;
        }

        BufferedImage img = null;
        try {
            img = ImageIO.read(fileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img == null) {
            System.out.println("[ERROR] File isn't image.");
            return;
        }

        java.util.List<java.util.List<TextItem>> numbers = NumberRecognition.getNumbersFromImage2(img);

        Graphics2D gImg = img.createGraphics();
        gImg.setColor(Color.RED);

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.get(i).size(); j++) {
                /**
                 * Draw rect for each number.
                 */
                int x = numbers.get(i).get(j).getX();
                int y = numbers.get(i).get(j).getY();
                int w = numbers.get(i).get(j).getImg().getWidth();
                int h = numbers.get(i).get(j).getImg().getHeight();
                gImg.drawRect(x, y, w, h);


                /**
                 * Scaled image.
                 */
                BufferedImage numImg = numbers.get(i).get(j).getImg();
                BufferedImage scaled = new BufferedImage(28, 28, numImg.getType());
                Graphics2D g = scaled.createGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 28, 28);

                int f = (int) Math.round((h > w ? h: w) / 20.0f);
                int nH = h / f;
                int nW = w / f;
                int nX = (28 - nW) / 2;
                int nY = (28 - nH) / 2;

                g.drawImage(numImg, nX, nY, nW, nH, null);
                g.dispose();

                /**
                 * Neural network.
                 */
                float[] inputData = transform(scaled);
                float[] resData = executor.computeOutput(inputData);

                System.out.println(nH + "\t" + nW + "\t" + nX + "\t" + nY);
                System.out.println(h + "\t" + w + "\t" + x + "\t" + y);

                int iN = 0;
                for (int k = 0; k < resData.length; k++) {
                    System.out.println(k + " " + resData[k]);

                    if (resData[k] > resData[iN]) {
                        iN = k;
                    }
                }

                System.out.println("Res: " + iN);
                gImg.drawString("" + iN ,x, y);

            }
        }
        gImg.dispose();

        try {
            ImageIO.write(img, "png", new File("Result.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int toGray(Color pixel) {
        return (int) (0.21f * pixel.getRed() + 0.72f * pixel.getGreen() + 0.07f * pixel.getBlue());
    }

    private static float[] transform(BufferedImage img) {
        float[] outputArray = new float[img.getHeight() * img.getWidth()];

        for (int i = 0, k = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++, k++) {
                Color pixel = new Color(img.getRGB(j, i));
                outputArray[k] = (float) (255 - toGray(pixel)) / 255.0f;

                System.out.print(255 - toGray(pixel) > 0 ? 1 : 0);
            }
            System.out.println();
        }

        return outputArray;
    }
}
