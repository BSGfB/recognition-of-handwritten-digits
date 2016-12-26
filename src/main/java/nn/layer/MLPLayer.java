package nn.layer;

import nn.settings.MLPExecutSettings;

public class MLPLayer implements Layer {
    private MLPExecutSettings settings = null;

    public MLPExecutSettings getSettings() {
        return settings;
    }

    public void setSettings(MLPExecutSettings settings) {
        this.settings = settings;
    }

    public float[] computeOutput(float[] inputArray) throws NullPointerException {
        if(settings == null)
            throw new NullPointerException("MLPExecutSettings is null");

        float[][] weightMatrix = settings.getWeightMatrix();
        float[] thresholdArray = settings.getThresholdArray();

        float[] outputArray = new float[weightMatrix.length];

        for(int i = 0; i < outputArray.length; i++) {
            outputArray[i] = 0.f;

            for(int j = 0; j < inputArray.length; j++)
                outputArray[i] += weightMatrix[i][j] * inputArray[j];

            outputArray[i] = settings.getActivationFunction().getValue(outputArray[i] - thresholdArray[i]);

        }

        return outputArray;
    }
}
