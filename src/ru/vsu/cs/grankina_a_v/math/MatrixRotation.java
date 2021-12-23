package ru.vsu.cs.grankina_a_v.math;

/**
 * Класс, совершающий действия над матрицей 3 на 3
 */
public class MatrixRotation {
    private float[][] matrix;

    public MatrixRotation() {
        this.matrix = new float[3][3];
    }

    /**
     * Методы, находящие угол между векторами
     * @param v1 - первый трехметный вектор (точка в трехметном пространстве)
     * @param v2 - второй трехметный вектор (точка в трехметном пространстве)
     * @return
     */

    public static float getAngleOX(VectorThree v1, VectorThree v2){
        if(v1.equals(v2)) return 0;
        float dy = Math.abs(v1.getY() - v2.getY());
        float dz = Math.abs(v1.getZ() - v2.getZ());
        return (float) Math.acos(dy / Math.sqrt(dz * dz + dy * dy));
    }

    public static float getAngleOY(VectorThree v1, VectorThree v2){
        if(v1.equals(v2)) return 0;
        float dx = Math.abs(v1.getX() - v2.getX());
        float dz = Math.abs(v1.getZ() - v2.getZ());
        return (float) Math.acos(dx / Math.sqrt(dx * dx + dz * dz));
    }

    public static float getAngleOZ(VectorThree v1, VectorThree v2){
        if(v1.equals(v2)) return 0;
        float dx = Math.abs(v1.getX() - v2.getX());
        float dy = Math.abs(v1.getY() - v2.getY());
        return (float) Math.acos(dx / Math.sqrt(dx * dx + dy * dy));
    }

    /**
     * Методы, совершающие поворот матрицы
     * @param v - значение трехметного вектора (точки в трехметном пространстве)
     * @param angle - значение угла (в трехмерном пространстве)
     * @return
     */

    public static VectorThree rotationOnX(VectorThree v, float angle){
        float newX = v.getX();
        float newY = (float)(v.getY() * Math.cos(angle) - v.getZ() * Math.sin(angle));
        float newZ = (float)(v.getY() * Math.sin(angle) + v.getZ() * Math.cos(angle));
        return new VectorThree(newX, newY, newZ);
    }

    public static VectorThree rotationOnY(VectorThree v, float angle){
        float newX = (float)(v.getX() * Math.cos(angle) - v.getZ() * Math.sin(angle));
        float newY = v.getY();
        float newZ = (float)(v.getX() * Math.sin(angle) + v.getZ() * Math.cos(angle));
        return new VectorThree(newX, newY, newZ);
    }

    public static VectorThree rotationOnZ(VectorThree v, float angle){
        float newX = (float)(v.getX() * Math.cos(angle) - v.getY() * Math.sin(angle));
        float newY = (float)(v.getX() * Math.sin(angle) + v.getY() * Math.cos(angle));
        float newZ = v.getZ();
        return new VectorThree(newX, newY, newZ);
    }
}
