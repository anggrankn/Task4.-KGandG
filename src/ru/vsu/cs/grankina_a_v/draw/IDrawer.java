/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vsu.cs.grankina_a_v.draw;

import ru.vsu.cs.grankina_a_v.third.PolyLine3D;
import java.util.Collection;

/**
 * Интерфейс, описывающий в общем виде процесс рисования полилинии
 * @author Alexey
 */
public interface IDrawer {
    /**
     * Очищает область заданным цветом
     * @param color цвет
     */
    public void clear(int color);
    
    /**
     * Рисует все полилинии
     * @param polyline набор рисуемых полилиний.
     */
    public void draw(Collection<PolyLine3D> polyline);
}
