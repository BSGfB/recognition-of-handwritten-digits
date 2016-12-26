package nn.executor;

import nn.layer.Layer;

public class MLPExecutor implements Executor {
    private Layer[] layers = null;

    public Layer[] getLayers() {
        return layers;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    @Override
    public float[] computeOutput(float[] inputArray) {
        float[] outputArray = inputArray.clone();

        for(int i = 0; i < layers.length; i++) {
            outputArray = layers[i].computeOutput(outputArray);
        }

        return outputArray;
    }
}
