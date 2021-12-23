package ru.vsu.cs.grankina_a_v.functions;

public class ExponentFunction implements IFunction {
    @Override
    public float complete(float percent) {
        return (float) Math.abs(Math.exp(percent * Math.PI));
    }
}
