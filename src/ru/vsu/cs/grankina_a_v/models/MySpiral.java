package ru.vsu.cs.grankina_a_v.models;

import ru.vsu.cs.grankina_a_v.functions.*;
import ru.vsu.cs.grankina_a_v.math.*;
import ru.vsu.cs.grankina_a_v.third.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, описывающий пружину в трехмерном пространстве
 */
public class MySpiral implements IModel {
    private final int countOfTurns;
    private final int countOfPointsPerTurn;
    private final float radius;
    private final float thickness;
    private final int countOfPointsPerTick;
    IFunction radiusFunc;
    IFunction stepFunc = new DefaultFunction();

    /**
     * Конструктор основных параметров пружины
     * @param countOfTurns - количество витков в пружине
     * @param countOfPointsPerTurn - количество вершин в пружине
     * @param radius - радиус пружины
     * @param thickness - толщина пружины
     * @param countOfPointsPerTick - количество линий, задающих один виток в пружине
     * @param radiusFunc - функция радиуса пружины
     */
    public MySpiral(int countOfTurns, int countOfPointsPerTurn, float radius, float thickness,
                    int countOfPointsPerTick, IFunction radiusFunc) {
        this.countOfTurns = countOfTurns;
        this.countOfPointsPerTurn = countOfPointsPerTurn;
        this.radius = radius;
        this.thickness = thickness;
        this.countOfPointsPerTick = countOfPointsPerTick;
        this.radiusFunc = radiusFunc;
    }

    /**
     * Метод по созданию пружины, описанный при помощи математических формул
     */
    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();
        VectorThree[] carcass = new VectorThree[countOfTurns * (countOfPointsPerTurn + 1)];

        float currentZ = 0;
        float currentX;
        float currentY;
        float currentRad = 0;

        int counter = 0;

        float radIncr = (float)(2 * Math.PI / countOfPointsPerTurn );
        float step = 0.2f;
        float zIncr = step / countOfPointsPerTurn;
        float zConstIncr = thickness * 2 / countOfPointsPerTurn;
        float percent;

        for (int i = 0; i < this.countOfTurns; i++) {
            for (int j = 0; j <= countOfPointsPerTurn; j++) {
                percent = (float) (i * countOfPointsPerTurn + j) / carcass.length;
                currentX = (float) (Math.cos(currentRad) * radius) * radiusFunc.complete(percent) + thickness;
                currentY = (float) (Math.sin(currentRad) * radius) * radiusFunc.complete(percent) + thickness;

                carcass[counter] = new VectorThree(currentX, currentY, currentZ);
                currentRad += radIncr;
                currentZ += zIncr * stepFunc.complete(percent) + zConstIncr;
                counter++;
            }
        }

        VectorThree[][] section = new VectorThree[carcass.length][countOfPointsPerTick];

        radIncr = (float)(2 * Math.PI / countOfPointsPerTick);

        float angleX = MatrixRotation.getAngleOX(carcass[0], carcass[1]) ;
        float angleY = MatrixRotation.getAngleOY(carcass[0], carcass[1]) ;

        float angleZ = 0;
        float angleZIncr = (float)(Math.PI * 2 / (carcass.length / countOfTurns - 1)) ;
        VectorThree temp;

        for (int i = 0; i < section.length; i++) {
            currentRad = 0;

            for (int j = 0; j < countOfPointsPerTick; j++) {
                currentX = (float) Math.sin(currentRad) * thickness;
                currentY = 0;
                currentZ = (float) Math.cos(currentRad) * thickness;

                temp = new VectorThree(currentX, currentY, currentZ);

                temp = MatrixRotation.rotationOnY(temp, angleY);
                temp = MatrixRotation.rotationOnX(temp, angleX);
                temp = MatrixRotation.rotationOnZ(temp, angleZ);

                section[i][j] = new VectorThree(temp.getX() + carcass[i].getX(), temp.getY() + carcass[i].getY(),
                        temp.getZ() + carcass[i].getZ());

                currentRad += radIncr;
            }

            angleZ += angleZIncr;
        }

        for (int i = 0; i < section.length - 1; i++) {
            for (int j = 0; j < section[i].length - 1; j++) {

                lines.add(new PolyLine3D(Arrays.asList(new VectorThree(section[i][j].getX(), section[i][j].getY(), section[i][j].getZ()),
                        new VectorThree(section[i+1][j].getX(), section[i+1][j].getY(), section[i+1][j].getZ()),
                        new VectorThree(section[i+1][j+1].getX(), section[i+1][j+1].getY(), section[i+1][j+1].getZ()),
                        new VectorThree(section[i][j+1].getX(), section[i][j+1].getY(), section[i][j+1].getZ())), true));
            }
            lines.add(new PolyLine3D(Arrays.asList(new VectorThree(section[i][0].getX(), section[i][0].getY(), section[i][0].getZ()),
                    new VectorThree(section[i+1][0].getX(), section[i+1][0].getY(), section[i+1][0].getZ()),
                    new VectorThree(section[i+1][section[i].length - 1].getX(), section[i+1][section[i].length - 1].getY(), section[i+1][section[i].length - 1].getZ()),
                    new VectorThree(section[i][section[i].length - 1].getX(), section[i][section[i].length - 1].getY(), section[i][section[i].length - 1].getZ())), true));
        }
        return lines;
    }
}