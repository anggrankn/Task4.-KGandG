/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.vsu.cs.grankina_a_v.math;

/**
 * Класс, представляющий четырёхмерный вектор для проведения матричных вчислений над трёхмерными точками.
 * @author Alexey
 */
public class VectorFour {
    private float[] values; /*Значения хранятся в виде массива из трёх элементов*/
    
    /**
     * Создаёт экземпляр вектора на основе значений четырёх составляющих
     * @param x первая составляющая, описывающая X-координату
     * @param y вторая составляющая, описывающая Y-координату
     * @param z третья составляющая, описывающая Z-координату
     * @param w четвёртая составляющая, описывающая нормализующее значение
     */
    public VectorFour(float x, float y, float z, float w) {
        values = new float[]{x, y, z, w};
    }
    
    /**
     * Создаёт экземпляр вектора на основе значений трёх составляющих.
     * Четвёртая берётся равной нулю.
     * @param x первая составляющая, описывающая X-координату
     * @param y вторая составляющая, описывающая Y-координату
     * @param z третья составляющая, описывающая Z-координату
     */
    public VectorFour(float x, float y, float z) {
        this(x, y, z, 0);
    }
    
    /**
     * Создаёт экземпляр четырёхмерного вектора на основе трёхмерного вектора
     * и четвёртой составляющей W.
     * @param v экземпляр трёхмерного вектора
     * @param w четвёртая составляющая.
     */
    public VectorFour(VectorThree v, float w) {
        this(v.getX(), v.getY(), v.getZ(), w);
    }
    
    /**
     * Создаёт экземпляр четырёхмерного вектора на основе трёхмерного вектора
     * Четвёртая составляющая W принимается равной нулю.
     * @param v исходный трёхмерный вектор.
     */
    public VectorFour(VectorThree v) {
        this(v, 0);
    }
    
    /**
     * Скрытый конструктор, который создаёт вектор на основе массива.
     * Данный конструктор будет удобен для использования внутри класса.
     * В связи с этим, данный конструктор просто сохраняет принятый массив
     * без дополнительных обработок и проверок.
     * @param array массив с данными
     */
    private VectorFour(float[] array) {
        this.values = array;
    }
    
    /**
     * Создаёт новый четырёхмерный вектор, все компоненты которого равны нулю.
     * @return нулевой четырёхмерный вектор.
     */
    public static VectorFour zero() {
        return new VectorFour(new float[4]);
    }
    
    /**
     * Умножае текущие вектор на число.
     * @param number число, на которое умножается текущий вектор
     * @return новый вектор, который является результатом умножения.
     */
    public VectorFour mul(float number) {
        float[] array = new float[4];
        for (int i = 0; i < array.length; i++)
            array[i] = number * this.at(i);
        return new VectorFour(array);
    }
    
    /**
     * Складывает текущий вектор с другим.
     * @param other вектор, с которым происходит сложение
     * @return рещультирующий вектор.
     */
    public VectorFour add(VectorFour other) {
        float[] array = new float[4];
        for (int i = 0; i < array.length; i++)
            array[i] = this.at(i) + other.at(i);
        return new VectorFour(array);
    }
    
    /**
     * Очень маленькое число для сравнений
     */
    private static final float EPSILON = 1e-10f;
    
    /**
     * Возвращает нормализованный по W четырёхмерный вектор.
     * Если W равен 0, то возвращается копия исходного вектора.
     * Если W не равен 1, то возвращается новй вектор, составляющие которого разделены на W.
     * @return новый нормализованный вектор.
     */
    public VectorFour normalized() {
        if (Math.abs(getW()) < EPSILON)
            return new VectorFour(this.getX(), this.getY(), this.getZ(), 0);
        return new VectorFour(this.getX() / this.getW(), this.getY() / this.getW(), this.getZ() / this.getW(), 1);
    }
    
    /**
     * Создаёт трёхмерный вектор, X, Y и Z составляющие которого равны
     * соответствующим значениям нормализованного четырёхмерного вектора.
     * @return новый трёхмерный вектор.
     */
    public VectorThree asVector3() {
        VectorFour n = this.normalized();
        return new VectorThree(n.getX(), n.getY(), n.getZ());
    }

    /**
     * X-составляющая вектора
     * @return X-составляющая вектора
     */
    public float getX() {
        return values[0];
    }

    /**
     * Y-составляющая вектора
     * @return Y-составляющая вектора
     */
    public float getY() {
        return values[1];
    }

    /**
     * Z-составляющая вектора
     * @return Z-составляющая вектора
     */
    public float getZ() {
        return values[2];
    }
    
    /**
     * W-составляющая вектора
     * @return W-составляющая вектора
     */
    public float getW() {
        return values[3];
    }
    
    /**
     * Метод, возвращающий составляющую вектора по порядковому номеру
     * @param idx порядковый номер
     * @return значение составляющей вектора
     */
    public float at(int idx) {
        return values[idx];
    }
}
