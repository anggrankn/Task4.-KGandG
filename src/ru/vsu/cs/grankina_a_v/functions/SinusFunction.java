package ru.vsu.cs.grankina_a_v.functions;

public class SinusFunction implements IFunction {
    @Override
    public float complete(float percent) {
        float rad = (float) Math.PI * percent;
        return (float) Math.abs(Math.sin(rad));
    }
}
