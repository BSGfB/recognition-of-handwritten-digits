package nn.parser;

import nn.activation.SigmoidActivationFunction;
import nn.layer.Layer;
import nn.layer.MLPLayer;
import nn.settings.MLPExecutSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NNParser {
    public static Layer[] parse(File file) {
        Layer[] layers = null;

        try {
            Scanner sc = new Scanner(file);
            int size = sc.nextInt();
            layers = new Layer[size];

            System.out.println(size);

            for (int i = 0; i < size; i++) {
                MLPExecutSettings settings = new MLPExecutSettings();

                String fun = sc.next();

                System.out.println(fun);

                settings.setActivationFunction(new SigmoidActivationFunction());

                int sizeT = sc.nextInt();
                float[] tArray = new float[sizeT];

                for (int j = 0; j < sizeT; j++)
                    tArray[j] = sc.nextFloat();
                settings.setThresholdArray(tArray.clone());

                System.out.println(sizeT);

                int wH = sc.nextInt();
                int wW = sc.nextInt();
                float[][] wMatrix = new float[wH][];

                for (int j = 0; j < wH; j++) {
                    wMatrix[j] = new float[wW];

                    for (int k = 0; k < wW; k++) {
                        wMatrix[j][k] = sc.nextFloat();
                    }
                }
                settings.setWeightMatrix(wMatrix.clone());
                layers[i] = new MLPLayer();
                ((MLPLayer)layers[i]).setSettings(settings);
                System.out.println(wH + " " + wW);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return layers;
    }
}
