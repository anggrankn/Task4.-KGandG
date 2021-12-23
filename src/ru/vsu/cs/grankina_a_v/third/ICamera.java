/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vsu.cs.grankina_a_v.third;

import ru.vsu.cs.grankina_a_v.math.VectorThree;

/**
 * Описывает основную функциональность камеры - превращение координат 
 * из мировой системы координат в систему координат камеры.
 * @author Alexey
 */
public interface ICamera {
    /**
     * Преобразует точку из мировой системы координат в систему координат камеры
     * @param v преобразуемая точка
     * @return новая точка
     */
    public VectorThree w2s(VectorThree v);
}
