import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GaussTest {
    @Test
    public void simpleTest() {
        int size = 3;
        double[] solutions = new double[size];
        solutions[0] = -1;
        solutions[1] = 13;
        solutions[2] = 9;

        double[][] matrix = new double[size][size];
        matrix[0][0] = 3;
        matrix[0][1] = 2;
        matrix[0][2] = -5;
        matrix[1][0] = 2;
        matrix[1][1] = -1;
        matrix[1][2] = 3;
        matrix[2][0] = 1;
        matrix[2][1] = 2;
        matrix[2][2] = -1;

        double[] result = new double[size];
        result[0] = 3;
        result[1] = 5;
        result[2] = 4;

        Gauss gauss = new Gauss(matrix, solutions);
        gauss.accept();
        double[] res = gauss.getResult();

        assertAll(
                () -> assertEquals(res[0], result[0]),
                () -> assertEquals(res[1], result[1]),
                () -> assertEquals(res[2], result[2])
        );
    }

    @Test
    public void simpleTest2() {
        int size = 3;
        double[] solutions = new double[size];
        solutions[0] = 1;
        solutions[1] = 2;
        solutions[2] = 0;

        double[][] matrix = new double[size][size];
        matrix[0][0] = 4;
        matrix[0][1] = 2;
        matrix[0][2] = -1;
        matrix[1][0] = 5;
        matrix[1][1] = 3;
        matrix[1][2] = -2;
        matrix[2][0] = 3;
        matrix[2][1] = 2;
        matrix[2][2] = -3;

        double[] result = new double[size];
        result[0] = -1;
        result[1] = 3;
        result[2] = 1;

        Gauss gauss = new Gauss(matrix, solutions);
        gauss.accept();
        double[] res = gauss.getResult();

        assertAll(
                () -> assertEquals(res[0], result[0]),
                () -> assertEquals(res[1], result[1]),
                () -> assertEquals(res[2], result[2])
        );
    }
}
