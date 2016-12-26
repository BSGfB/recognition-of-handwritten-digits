package nn.settings;

import nn.activation.ActivationFunction;

public class MLPExecutSettings {
    private float[][] weightMatrix = null;
    private float[] thresholdArray = null;
    private ActivationFunction activationFunction = null;

    public float[][] getWeightMatrix() {
        return weightMatrix;
    }

    public void setWeightMatrix(float[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
    }

    public float[] getThresholdArray() {
        return thresholdArray;
    }

    public void setThresholdArray(float[] thresholdArray) {
        this.thresholdArray = thresholdArray;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }
}
