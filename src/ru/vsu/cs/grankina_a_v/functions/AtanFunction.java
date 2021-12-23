package ru.vsu.cs.grankina_a_v.functions;

public class AtanFunction implements IFunction {
    @Override
    public float complete(float percent) {
        return (float) Math.abs(Math.atan(percent * Math.PI));
    }
}
