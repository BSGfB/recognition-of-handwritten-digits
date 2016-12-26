package nn.activation;

public interface ActivationFunction {
    float getValue(float x);
    float getDerivativeValue(float x);
}
