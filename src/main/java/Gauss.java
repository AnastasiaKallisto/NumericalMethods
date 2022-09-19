public class Gauss {
    private final double[][] matrix;
    private final double[][] matrix_copy;
    private final double[] solutionsVector;
    private final double[] result;
    private final boolean[] nulableLines;
    private int line;
    private int cols;
    private final double EPS = 1E-4;
    private double maximum = Double.MIN_VALUE;


    public Gauss(double[][] matrix, double[] solutionsVector) {
        this.matrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        this.matrix_copy = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix_copy.length; i++) {
            for (int j = 0; j < matrix_copy.length; j++) {
                matrix_copy[i][j] = matrix[i][j];
            }
        }

        this.solutionsVector = new double[solutionsVector.length];
        for (int i = 0; i < solutionsVector.length; i++) {
            this.solutionsVector[i] = solutionsVector[i];
        }
        this.result = new double[matrix.length];

        this.nulableLines = new boolean[solutionsVector.length];
        for (int i = 0; i < nulableLines.length; i++) {
            nulableLines[i] = true;
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
                koefic = matrix[i][cols] / maximum;
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = matrix[i][j] - koefic * matrix[line][j];
                    if (nulableLines[i]) {
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
            nulableLines[line] = false;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (Double.compare(matrix[i][j], 0) != 0) {
                    result[j] = solutionsVector[i] / matrix[i][j];
                }
            }
        }
    }


    private void findMaxElem(double[][] matrix) {
        maximum = Double.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (Double.compare(Math.abs(maximum), Math.abs(matrix[i][j])) < 0) {
                    maximum = matrix[i][j];
                    line = i;
                    cols = j;
                }
            }
        }
    }


    private void migrateMainElementToStartPosition(double[][] matrix) {
        migrateLinesToStart(matrix);
        migrateColsToStart(matrix);
    }


    private void migrateLinesToStart(double[][] matrix) {
        if (line == 0) {
            return;
        }
        double buffer;
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < matrix.length - 1; j++) {
                buffer = matrix[i][j];
                matrix[i][j] = matrix[i - 1][j];
                matrix[i - 1][j] = buffer;
            }
        }
    }

    private void migrateColsToStart(double[][] matrix) {
        if (cols == 0) {
            return;
        }

        double buffer;
        for (int i = cols; i > 0; i--) {
            for (int j = 0; j < matrix.length - 1; j++) {
                buffer = matrix[j][i];
                matrix[j][i] = matrix[j][i - 1];
                matrix[j][i - 1] = buffer;
            }

        }
    }
}
