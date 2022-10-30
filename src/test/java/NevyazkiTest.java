import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NevyazkiTest {
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
        double[] newX = new double[n];
        MethodMinNevyazok m = new MethodMinNevyazok(A, b, newX, 1E-7);
        int count = m.getCount();
        newX = m.getX();
        TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, newX, x);
        Main.printHeadOfTable();
        Main.printString(ALPHA, BETA, n, tableFunctions);
        assertArrayEquals(x, newX, 1E-4);
    }

    // почему-то аналогичный тест не работает с матрицей простой структуры
    @Test
    public void genTestSimmetrical2(){
        System.out.println("Simmetrica; matrix");
        int n = 10;
        Main.printHeadOfTable();
        double BETA = 10;
        for (int j = 1; j < 15; j++) {
            double ALPHA = Math.pow(10, -j);
            double[][] A = new double[n][n];
            double[][] A_inv = new double[n][n];
            Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 0, 1); // матрица симметр
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Math.sin(i);
            }
            double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
            double[] newX = new double[n];
            MethodMinNevyazok m = new MethodMinNevyazok(A, b, newX, 1E-7);
            int count = m.getCount();
            newX = m.getX();
            TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, newX, x);
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
            Gen.mygen(A, A_inv, n, ALPHA, BETA, 1, 2, 0, 1); // матрица простой структуры
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Math.sin(i);
            }
            double[] b = MatrixFunctions.multiplyMatrixOnVector(A, x);
            double[] newX = new double[n];
            MethodMinNevyazok m = new MethodMinNevyazok(A, b, newX, 1E-7);
            int count = m.getCount();
            newX = m.getX();
            TableFunctions tableFunctions = new TableFunctions(b, A, A_inv, newX, x);
            Main.printString(ALPHA, BETA, n, tableFunctions);
        }
    }

}
