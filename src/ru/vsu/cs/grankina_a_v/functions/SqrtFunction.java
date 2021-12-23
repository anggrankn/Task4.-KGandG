package ru.vsu.cs.grankina_a_v.functions;

public class SqrtFunction implements IFunction {
    @Override
    public float complete(float percent) {
        return (float) Math.sqrt(percent * Math.PI);
    }
}
