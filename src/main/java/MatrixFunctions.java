public class MatrixFunctions {
    // поиск обратной матрицы A^-1
    public static double[][] calculateInverseMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] a = getTransposedMatrix(matrix);
        double coef = 1.0 / Math.abs(calculateDeterminant(matrix));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = a[i][j] * coef;
            }
        }
        return a;
    }

    // поиск транспонированной матрицы
    public static double[][] getTransposedMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = matrix[j][i];
                a[j][i] = matrix[i][j];
            }
        }
        return a;
    }

    // поиск определителя матрицы
    public static double calculateDeterminant(double[][] matrix) {
        double det = 0;
        if (matrix.length == 1)
            return matrix[0][0];
        else
            for (int j = 0; j < matrix.length; j++)
                det = det + Math.pow(-1, j) * matrix[0][j] * calculateDeterminant(getMinorOfMatrix(0, j, matrix));
        return det;
    }

    // получение минора матрицы
    public static double[][] getMinorOfMatrix(int a, int b, double[][] matrix) {
        int i, j, p, q;
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];
        for (j = 0, q = 0; q < n - 1; j++, q++)
            for (i = 0, p = 0; p < n - 1; i++, p++) {
                if (i == a) i++;
                if (j == b) j++;
                minor[p][q] = matrix[i][j];
            }
        return minor;
    }

    // умножение матрицы на вектор
    public static double[] multiplyMatrixOnVector(double[][] matrix, double[] v) {
        int n = matrix.length;
        double[] answer = new double[n];
        double elem;
        for (int i = 0; i < n; i++) {
            elem = 0;
            for (int j = 0; j < n; j++) {
                elem += matrix[i][j] * v[j];
            }
            answer[i] = elem;
        }
        return answer;
    }

    // умножение квадратной матрицы на кв. матрицу
    public static double[][] multiplyMatrixOnMatrix(double[][] matrix1, double[][] matrix2) {
        int n = matrix1.length;
        double[][] answer = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    answer[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return answer;
    }

    // копирование матрицы
    public static double[][] getCopyOfMatrix(double[][] matrix){
        int n = matrix.length;
        double[][] answer = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = matrix[i][j];
            }
        }
        return answer;
    }

}
