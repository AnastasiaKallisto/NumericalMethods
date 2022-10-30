import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GaussTest {
    /**
     * sign_law принимает значения:
     * -1: какое-то распределение знаков
     * 0: какое-то распределение знаков
     *
     * lambda_law принимает значения:
     * 1: какое-то распределение чисел
     * 2: какое-то распределение чисел
     *
     * variant принимает значения:
     * 0: симметричная матрица
     * 1: матрица простой структуры
     * 2: одна Жорданова клетка 2х2 при минимальном с.з.
     *
     * schema всегда принимает значение 1*/


    @Test
    public void simpleTest() {
        double[] solutions = {-1, 13, 9};
        double[][] matrix = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}};
        double[] result = {3, 5, 4};
        Gauss gauss = new Gauss(matrix, solutions);
        double[] res = gauss.getResult();
        System.out.println("   X    ");
        for (int i = 0; i < 3; i++)
            System.out.print(result[i] + " ");
        System.out.println("\n   RES   ");
        for (int i = 0; i < 3; i++)
            System.out.print(res[i] + " ");
        assertArrayEquals(result, res, 1E-6);
    }

    @Test
    public void testDeterminantIs0() {
        double[] x = {2, 1, -3, 5};
        double[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        double[] b = {15, 35, 55, 75};
        Gauss gauss = new Gauss(matrix, b);
        double[] res = gauss.getResult();
        System.out.println("   X    ");
        for (int i = 0; i < 4; i++)
            System.out.print(x[i] + " ");
        System.out.println("\n   RES   ");
        for (int i = 0; i < 4; i++)
            System.out.print(res[i] + " ");
        assertArrayEquals(x, res, 1E-6);
    }

    @Test
    public void simpleTest3() {
        double[] solutions = {1, 4};
        double[][] matrix = {{1, 2}, {3,1}};
        double[] result = {1.4, -0.2};

        Gauss gauss = new Gauss(matrix, solutions);
        double[] res = gauss.getResult();
        assertArrayEquals(result, res, 1E-6);
    }

    // задаем матрицу с помощью генератора - альфа, бета, две пустых матрицы на вход
    // задаем точное решение вида x_i = sin i //норма близка к 1
    // задаем вектор правой части уравнения
    // алгоритмом ищем приближенное решение
    @Test
    public void genTestSimmetrical(){
        int n = 10;
        double ALPHA = 1E-3;
        double BETA = 1.0;
        double[][] A = new double[n][n];
        double[][] A_inv = new double[n][n];
        Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 0, 1); // симметричная
        System.out.println();
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = Math.sin(i);
        }
        double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
        Gauss gauss = new Gauss(A, b);
        double[] res = gauss.getResult();
        TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, res, x);
        Main.printHeadOfTable();
        Main.printString(ALPHA, BETA, n, tableFunctions);
        assertArrayEquals(x, res, 1E-7);
    }

    @Test
    public void genTestSimmetrical2(){
        System.out.println("Simmetrical matrix");
        int n = 10;
        Main.printHeadOfTable();
        double BETA = 10;
        for (int j = 1; j < 15; j++) {
            double ALPHA = Math.pow(10, -j);
            double[][] A = new double[n][n];
            double[][] A_inv = new double[n][n];
            Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 0, 1); // симметричная
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Math.sin(i);
            }
            double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
            Gauss gauss = new Gauss(A, b);
            double[] res = gauss.getResult();
            TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, res, x);
            Main.printString(ALPHA, BETA, n, tableFunctions);
        }
    }

    @Test
    public void genTestSimpleStructure(){
        System.out.println("Simple Structure matrix");
        int n = 10;
        Main.printHeadOfTable();
        double BETA = 10;
        for (int j = 1; j < 15; j++) {
            double ALPHA = Math.pow(10, -j);
            double[][] A = new double[n][n];
            double[][] A_inv = new double[n][n];
            Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 1, 1); // матрица простой структуры
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Math.sin(i);
            }
            double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
            Gauss gauss = new Gauss(A, b);
            double[] res = gauss.getResult();
            TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, res, x);
            Main.printString(ALPHA, BETA, n, tableFunctions);
        }
    }

    @Test
    public void genTestSimpleStructureN(){
        System.out.println("Simple Structure matrix");
        int n;
        Main.printHeadOfTable();
        double ALPHA = Math.pow(10, -3);
        double BETA = 10;
        for (int j = 1; j < 10; j++) {
            n = (int)Math.pow(2, j);
            double[][] A = new double[n][n];
            double[][] A_inv = new double[n][n];
            Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 1, 1); // матрица простой структуры
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Math.sin(i);
            }
            double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
            Gauss gauss = new Gauss(A, b);
            double[] res = gauss.getResult();
            TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, res, x);
            Main.printString(ALPHA, BETA, n, tableFunctions);
        }
    }

    @Test
    public void genTestSimmetricalN(){
        System.out.println("Simmetrical matrix");
        int n;
        Main.printHeadOfTable();
        double ALPHA = Math.pow(10, -3);
        double BETA = 10;
        for (int j = 1; j < 10; j++) {
            n = (int)Math.pow(2, j);
            double[][] A = new double[n][n];
            double[][] A_inv = new double[n][n];
            Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 0, 1); // матрица симметр
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Math.sin(i);
            }
            double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
            Gauss gauss = new Gauss(A, b);
            double[] res = gauss.getResult();
            TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, res, x);
            Main.printString(ALPHA, BETA, n, tableFunctions);
        }
    }
/*
    @Test
    public void genTestJord(){
        System.out.println("Jord 2x2 matrix");
        int n = 10;
        Main.printHeadOfTable();
        double BETA = 10;
        for (int j = 1; j < 15; j++) {
            double ALPHA = Math.pow(10, -j);
            double[][] A = new double[n][n];
            double[][] A_inv = new double[n][n];
            Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 2, 1); // матрица  с жорд кл 2х2
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Math.sin(i);
            }
            double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
            Gauss gauss = new Gauss(A, b);
            double[] res = gauss.getResult();
            TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, res, x);
            Main.printString(ALPHA, BETA, n, tableFunctions);
        }
    } */


    @Test
    public void simpleTest2() {

        double[] solutions = {1, 2, 0};
        double[][] matrix = {{4, 2, -1}, {5, 3, -2}, {3, 2, -3}};
        double[] result = {-1, 3, 1};

        Gauss gauss = new Gauss(matrix, solutions);
        double[] res = gauss.getResult();
        assertArrayEquals(result, res, 1E-10);
    }

    @Test
    public void simpleTest4() {
        double[][] matrix = {{1,2,3},{4,6,6},{7,8,10}};
        double[] x = {2, 0, 1};
        double[] b = MatrixFunctions.multiplyMatrixOnVector(matrix, x);

        Gauss gauss = new Gauss(matrix, b);
        double[] res = gauss.getResult();
        assertArrayEquals(x, res, 1E-10);
    }

}
