public class Gauss {
    private double[][] matrix;
    private double[] solutionsVector;
    private double[] result;
    private double maximum = Double.MIN_VALUE;

    private class MaxElem {
        private double max;
        private int i;
        private int j;

        public MaxElem(double max, int i, int j) {
            this.max = max;
            this.i = i;
            this.j = j;
        }

        public double getMax() {
            return max;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public void setI(int i) {
            this.i = i;
        }

        public void setJ(int j) {
            this.j = j;
        }
    }


    public Gauss(double[][] matrix, double[] solutionsVector) {
        this.matrix = MatrixFunctions.getCopyOfMatrix(matrix);
        this.solutionsVector = VectorFunctions.getCopyOfVector(solutionsVector);
        this.result = solveEquation(this.matrix, this.solutionsVector);
    }

    public double[] getResult() {
        return result;
    }

    private double[] solveEquation(double[][] A, double[] b) {
        /*if (Math.abs(MatrixFunctions.calculateDeterminant(A)) <= 1E-8)
            throw new IllegalArgumentException("Determinant = 0, there is no decision");*/
        int n = A.length;
        double[] x = new double[n];
        double[][] ACopy = MatrixFunctions.getCopyOfMatrix(A);
        double[] bCopy = VectorFunctions.getCopyOfVector(b);
        int[] transitions = new int[n];
        for (int i = 0; i < n; i++) {
            transitions[i] = i;
        }
        getAlmostTriangleMatrix(ACopy, bCopy, transitions);
        ACopy = getTriangleMatrix(ACopy, transitions);
        double coef;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                coef = ACopy[i][j] / ACopy[j][j];
                MatrixFunctions.strMinusStrWithCoef(ACopy, coef, i, j, n);
                bCopy[i] -= bCopy[j] * coef;
            }
        }
        for (int i = 0; i < n; i++) {
            x[transitions[i]] = bCopy[i] / ACopy[i][i];
        }
        return x;
    }

    private double[][] getTriangleMatrix(double[][] A, int[] transitions) {
        int n = A.length;
        double[][] newA = new double[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                newA[i][j] = A[i][transitions[j]];
            }
        }
        return newA;
    }

    private MaxElem getAlmostTriangleMatrix(double[][] A, double[] b, int[] transitions) {
        int n = A.length;
        MaxElem maxElem = findMaxElem(A);
        if (n == 1)
            return maxElem;
        //MaxElem maxElem = findMaxElem(A);
        if (maxElem.getJ() != 0) {
            swapColumns(A, n, 0, maxElem.getJ());
            swapElemsInVector(transitions, 0, maxElem.getJ());
        }
        if (maxElem.getI() != 0) {
            swapStrings(A, 0, maxElem.getI());
            swapElemsInVector(b, 0, maxElem.getI());
        }
        MatrixFunctions.makeFirstColumnNull(A, b);
        double[][] minor = MatrixFunctions.getMinorOfMatrix(0, 0, A);
        double[] b1 = new double[n - 1];
        int[] transitions1 = new int[n-1];
        for (int i = 0; i < n - 1; i++) {
            b1[i] = b[i + 1];
            transitions1[i] = transitions[i + 1];
        }
        getAlmostTriangleMatrix(minor, b1, transitions1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                A[i][j] = minor[i - 1][j - 1];
            }
            b[i] = b1[i - 1];
            transitions[i] = transitions1[i-1];
        }
        if (maxElem.getJ()!=0)
            swapColumns(A, n, 0, maxElem.getJ());
        /*if (maxElem.getI() != 0) {
            swapStrings(A, 0, maxElem.getI());
            swapElemsInVector(b, 0, maxElem.getI());
        }*/
        return maxElem;
    }

    private void swapColumns(double[][] A, int n, int i, int j) {
        double columnElem;
        for (int k = 0; k < n; k++) {
            columnElem = A[k][j];
            A[k][j] = A[k][i];
            A[k][i] = columnElem;
        }
    }

    private void swapStrings(double[][] A, int i, int j) {
        double[] s = A[i];
        A[i] = A[j];
        A[j] = s;
    }

    private void swapElemsInVector(double[] v, int i, int j) {
        double elem = v[i];
        v[i] = v[j];
        v[j] = elem;
    }

    private void swapElemsInVector(int[] v, int i, int j) {
        int elem = v[i];
        v[i] = v[j];
        v[j] = elem;
    }

    private MaxElem findMaxElem(double[][] matrix) {
        MaxElem maxElem = new MaxElem(Double.MIN_VALUE, 0, 0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (Double.compare(Math.abs(maxElem.getMax()), Math.abs(matrix[i][j])) < 0) {
                    maxElem.setMax(matrix[i][j]);
                    maxElem.setI(i);
                    maxElem.setJ(j);
                }
            }
        }
        return maxElem;
    }
}
