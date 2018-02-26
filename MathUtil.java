/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smartgirl
 */
public class MathUtil {

    public static int INF = Integer.MAX_VALUE;

    public static int getMinValueIndex(int[] values) {
        if (values == null) {
            return -1;
        }
        int minValue = INF;
        int minIndex = -1;
        for (int i1 = 0; i1 < values.length; i1++) {
            if (values[i1] < minValue) {
                minValue = values[i1];
                minIndex = i1;
            }
        }
        return minIndex;
    }

    public static int[][] enumAllPossibleArray(int customNumber) {
        if (customNumber < 1) {
            return null;
        }
        List<Integer> uniqueIndices = new ArrayList<>();
        int[][] enumMatrix = {{0}};
        uniqueIndices.add(0);
        for (int i1 = 1; i1 < customNumber; i1++) {
//            System.out.println("Enumerate=" + i1);
            enumMatrix = extendPathIndexMatrix(enumMatrix, uniqueIndices, i1);
            uniqueIndices.add(i1);
        }
        return enumMatrix;
    }

    public static int[][] replaceMatrixElement(int[][] indexMatrix, int oldIndex, int newIndex) {
        if (oldIndex == newIndex) {
            return indexMatrix;
        }
        int row = indexMatrix.length;
        int col = indexMatrix[0].length;
        int[][] newIndexMatrix = new int[row][col];
        for (int ir = 0; ir < row; ir++) {
            for (int ic = 0; ic < col; ic++) {
                if (indexMatrix[ir][ic] == oldIndex) {
                    newIndexMatrix[ir][ic] = newIndex;
                } else {
                    newIndexMatrix[ir][ic] = indexMatrix[ir][ic];
                }
            }
        }
        return newIndexMatrix;
    }

    public static int[][] addOneColumn2Matrix(int[][] indexMatrix, int addIndex) {
        if (indexMatrix == null) {
            return null;
        }
        int row = indexMatrix.length;
        int col = indexMatrix[0].length + 1;
        int[][] newIndexMatrix = new int[row][col];
        for (int ir = 0; ir < row; ir++) {
            newIndexMatrix[ir][0] = addIndex;
            for (int ic = 1; ic < col; ic++) {
                newIndexMatrix[ir][ic] = indexMatrix[ir][ic - 1];
            }
        }
        return newIndexMatrix;
    }

    public static int[][] concateMultiMatrix(List<int[][]> accumMatrix, int row, int col) {
        if (accumMatrix.isEmpty()) {
            return null;
        }
        int[][] concateMatrix = new int[accumMatrix.size() * row][col];
        for (int k = 0; k < accumMatrix.size(); k++) {
            int[][] tMatrix = accumMatrix.get(k);
            for (int i1 = 0; i1 < row; i1++) {
                for (int j1 = 0; j1 < col; j1++) {
                    concateMatrix[k * row + i1][j1] = tMatrix[i1][j1];
//                    System.out.print(String.format("%d\t", concateMatrix[k * row + i1][j1]));
                }
//                System.out.print("\n");
            }
        }

        return concateMatrix;
    }

    public static int[][] extendPathIndexMatrix(int[][] indexMatrix, List<Integer> uniqueIndices, int exIndex) {
        int[][] extMatrix;
        if (indexMatrix == null) {
            extMatrix = new int[1][1];
            extMatrix[0][0] = exIndex;
            return extMatrix;
        }
        List<int[][]> accumMatrix = new ArrayList<>();
        int[][] initMatrix = addOneColumn2Matrix(indexMatrix, exIndex);
        accumMatrix.add(initMatrix);
        for (int i1 = 0; i1 < uniqueIndices.size(); i1++) {
            int[][] tMatrix = replaceMatrixElement(indexMatrix, uniqueIndices.get(i1), exIndex);
            accumMatrix.add(addOneColumn2Matrix(tMatrix, uniqueIndices.get(i1)));
        }
        return concateMultiMatrix(accumMatrix, initMatrix.length, initMatrix[0].length);
    }

    public static void main(String[] args) {
        MathUtil.enumAllPossibleArray(5);
//        int[][] indexMatrix = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};
//        int[][] newIndexMatrix = math.extendPathIndexMatrix(indexMatrix, new int[]{0, 1, 2}, 3);
    }

}
