import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTest {
    @Test
    public void simpleTest() {
        double[][] matrix = {{2, 5, 7}, {6, 3, 4}, {5, -2, -3}};
        double[][] exact = {{1, -1, 1}, {-38, 41, -34}, {27, -29, 24}};

        MatrixInversionReflectionMethod method = new MatrixInversionReflectionMethod(matrix);
        double[][] res = method.getA_Inversion();

        assertTrue(MatrixFunctions.equals(res, exact));
    }

    @Test
    public void simpleTest1() {
        double[][] matrix = {{2, 3, 1}, {-2, 1, 0}, {1, 2, -2}};
        double[] b = {3, -2, -1};
        double[] expected = {1, 0, 1};

        double[][] inverseMatrix = MatrixFunctions.calculateInverseMatrix(matrix);
        double[] actual = MatrixFunctions.multiplyMatrixOnVector(inverseMatrix, b);

        assertArrayEquals(expected, actual, 1E-6);

    }

    @Test
    public void simpleTest2() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[][] inversionMatrix = {{-2, 1}, {1.5, -0.5}};

        MatrixInversionReflectionMethod method = new MatrixInversionReflectionMethod(matrix);
        double[][] res = method.getA_Inversion();
        int a=123;
        assertTrue(MatrixFunctions.equals(res, inversionMatrix));
    }
}
