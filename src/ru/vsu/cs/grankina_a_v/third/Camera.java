/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vsu.cs.grankina_a_v.third;

import ru.vsu.cs.grankina_a_v.math.*;

/**
 * Описывает класс камеры, который хранит матрицы преобразования мировой системы координат в систему координат камеры.
 * @author Alexey
 */
public class Camera implements ICamera {
    private MatrixFour translate, rotate, scale, projection;

    /**
     * Создаёт простую камеру
     */
    public Camera() {
        translate = MatrixFour.one();
        rotate = MatrixFour.one();
        scale = MatrixFour.one();
        projection = MatrixFour.one();
    }
    
    /**
     * Метод, преобразуюший точку из меировой системы координат в систему координат камеры.
     * Сначала к вектору применяется масштаб(S), далее поворот(R), потом перенос(T) и в конце - проекция(P).
     * r = P * T * R * S * v
     * @param v вектор, который надо перевести
     * @return новый вектор
     */
    @Override
    public VectorThree w2s(VectorThree v) {
        return projection.mul(
            translate.mul(
                rotate.mul(
                    scale.mul(
                        new VectorFour(v, 1)
                    )
                )
            )
        ).asVector3();
    }
    
    public void modifyProjection(MatrixFour dp) {
        this.projection = dp.mul(this.projection);
    }

    public MatrixFour getProjection() {
        return projection;
    }

    public void setProjection(MatrixFour projection) {
        this.projection = projection;
    }

    public void modifyRotate(MatrixFour dp) {
        this.rotate = dp.mul(this.rotate);
    }
    
    public MatrixFour getRotate() {
        return rotate;
    }

    public void setRotate(MatrixFour rotate) {
        this.rotate = rotate;
    }

    public void modifyScale(MatrixFour dp) {
        this.scale = dp.mul(this.scale);
    }
    
    public MatrixFour getScale() {
        return scale;
    }

    public void setScale(MatrixFour scale) {
        this.scale = scale;
    }

    public void modifyTranslate(MatrixFour dp) {
        this.translate = dp.mul(this.translate);
    }
    
    public MatrixFour getTranslate() {
        return translate;
    }

    public void setTranslate(MatrixFour translate) {
        this.translate = translate;
    }
}
