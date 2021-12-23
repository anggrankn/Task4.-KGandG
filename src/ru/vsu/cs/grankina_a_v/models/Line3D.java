/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vsu.cs.grankina_a_v.models;

import ru.vsu.cs.grankina_a_v.math.VectorThree;
import ru.vsu.cs.grankina_a_v.third.IModel;
import ru.vsu.cs.grankina_a_v.third.PolyLine3D;

import java.util.Arrays;
import java.util.List;

/**
 * Описывает трёхмерный отрезок
 * @author Alexey
 */
public class Line3D implements IModel {
    private VectorThree p1, p2;

    public Line3D(VectorThree p1, VectorThree p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return Arrays.asList(new PolyLine3D(Arrays.asList(p1, p2), false));
    }
}
