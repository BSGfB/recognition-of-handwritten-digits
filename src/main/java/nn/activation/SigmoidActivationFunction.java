package nn.activation;

public class SigmoidActivationFunction implements ActivationFunction {
    public float getValue(float x) {
        return (float) (1.0f / (1.0f + Math.exp(-x)));
    }

    public float getDerivativeValue(float x) {
        float y = getValue(x);
        return y * (1.0f - y);
    }

    @Override
    public String toString() {
        return "SigmoidActivationFunction";
    }
}
