/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vsu.cs.grankina_a_v.third;

import ru.vsu.cs.grankina_a_v.math.VectorThree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Полилиния в трёхмерном пространстве.
 * Описывает ломанную в трёхмерном пространстве по опорным точкам
 * @author Alexey
 */
public class PolyLine3D {
    private List<VectorThree> points;
    private boolean closed;

    /**
     * Создаёт новую полилинию на основе трёхмерных точек.
     * @param points список точек-вершин ломанной
     * @param closed признак замкнутостит линии
     */
    public PolyLine3D(Collection<VectorThree> points, boolean closed) {
        this.points = new LinkedList<VectorThree>(points);
        this.closed = closed;
    }

    /**
     * Признак закрытости
     * @return возвращает истину, если линия замкнута, иначе - ложь.
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * Список вершин линии
     * @return возвращает список точек.
     */
    public List<VectorThree> getPoints() {
        return points;
    }
    
    /**
     * Вычисляет среднее арифметическое по оси Z.
     * @return среднее по Z для полилинии.
     */
    public float avgZ() {
        if (points == null || points.size() == 0)
            return 0;
        float sum = 0;
        for (VectorThree v : points)
            sum += v.getZ();
        return sum / points.size();
    }
}
