public class Gauss {
    private double[][] matrix;
    private double[][] matrix_copy;
    private double[] solutionsVector;
    private double[] result;
    private boolean[] notNullableLines;
    private int line;
    private int column;
    private final double EPS = 1E-4;
    private double maximum = Double.MIN_VALUE;


    public Gauss(double[][] matrix, double[] solutionsVector) {
        this.matrix = MatrixFunctions.getCopyOfMatrix(matrix);
        this.matrix_copy = MatrixFunctions.getCopyOfMatrix(matrix);
        this.solutionsVector = VectorFunctions.getCopyOfVector(solutionsVector);
        this.result = new double[matrix.length];
        // зануляются по мере работы с копией матрицы
        this.notNullableLines = new boolean[solutionsVector.length];
        for (int i = 0; i < notNullableLines.length; i++) {
            notNullableLines[i] = true;
        }
        solveEquation();
    }

    public double[] getResult() {
        return result;
    }

    private void solveEquation() {
        for (int k = 0; k < matrix.length; k++) {
            findMaxElem(matrix_copy);

            double koefic;
            for (int i = 0; i < matrix.length; i++) {
                if (i == line) {
                    continue;
                }
                koefic = matrix[i][column] / maximum;
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = matrix[i][j] - koefic * matrix[line][j];
                    if (notNullableLines[i]) {
                        matrix_copy[i][j] = matrix_copy[i][j] - koefic * matrix_copy[line][j];
                    }
                    if (Double.compare(Math.abs(matrix[i][j]), EPS) < 0) {
                        matrix[i][j] = 0;
                    }
                }
                solutionsVector[i] = solutionsVector[i] - koefic * solutionsVector[line];
            }

            for (int i = 0; i < matrix_copy.length; i++) {
                matrix_copy[line][i] = 0;
            }
            notNullableLines[line] = false;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (Double.compare(matrix[i][j], 0) != 0) {
                    result[j] = solutionsVector[i] / matrix[i][j];
                }
            }
        }
    }

    // line - строка, которую мы трогать не будем в рабочей матрице
    // column - столбец, который мы будем занулять
    private void findMaxElem(double[][] matrix) {
        maximum = Double.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (Double.compare(Math.abs(maximum), Math.abs(matrix[i][j])) < 0) {
                    maximum = matrix[i][j];
                    line = i;
                    column = j;
                }
            }
        }
    }
}
