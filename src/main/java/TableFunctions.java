public class TableFunctions {
    // все нормы - кубические, то есть с индексом бесконечность
    // f = A * x^* (матрица умножить на точное решение)

    private final double matrixNorm;
    private final double inverseMatrixNorm;
    private final double V_NevyazkaOfGeneration;
    private final double normOfError_z;
    private final double J_OtnosNormOfError;
    private final double normOfNevyazka_r;
    private final double p_OtnosNormOfNevyazka;
    private final double[] z_Error;
    private final double[] r_nevyazka;

    public double getMatrixNorm() {
        return matrixNorm;
    }

    public double getInverseMatrixNorm() {
        return inverseMatrixNorm;
    }

    public double getV_NevyazkaOfGeneration() {
        return V_NevyazkaOfGeneration;
    }

    public double getNormOfError_z() {
        return normOfError_z;
    }

    public double getJ_OtnosNormOfError() {
        return J_OtnosNormOfError;
    }

    public double getNormOfNevyazka_r() {
        return normOfNevyazka_r;
    }

    public double getP_OtnosNormOfNevyazka() {
        return p_OtnosNormOfNevyazka;
    }

    public double[] getZ_Error() {
        return z_Error;
    }

    public double[] getR_nevyazka() {
        return r_nevyazka;
    }

    public TableFunctions(double[] f, double[][] matrix, double[] approximateX, double[] exactX) {
        matrixNorm = calculateCubicNormOfMatrix(matrix); // куб норма матрицы
        inverseMatrixNorm = calculateCubicNormOfMatrix(MatrixFunctions.calculateInverseMatrix(matrix)); // куб норма обратной матрицы
        V_NevyazkaOfGeneration = calculateV_nevyazka_of_generation(matrixNorm, inverseMatrixNorm); // невязка генерации
        r_nevyazka = calculateR_Nevyazka(matrix, approximateX, f); // невязка
        normOfNevyazka_r = calculateCubicNormOfVector(r_nevyazka); // норма невязки
        p_OtnosNormOfNevyazka = calculateP_otnositelnaya_norm_nevyazki(normOfNevyazka_r, f); // относительная норма невязки
        z_Error = calculateErrorZ(approximateX, exactX); // ошибка
        normOfError_z = calculateCubicNormOfVector(z_Error); // норма ошибки
        J_OtnosNormOfError = calculateJ_otnositelnaya_norm_of_error(normOfError_z, exactX); // относительная норма ошибки
    }

    // кубическая норма матрицы - максимум из сумм модулей элементов каждой строки
    // || A ||, || A ^ -1 || будет считаться тоже здесь

    public static double calculateCubicNormOfMatrix(double[][] matrix) {
        double max = 0;
        double stringMax;
        for (int i = 0; i < matrix.length; i++) {
            stringMax = 0;
            for (int j = 0; j < matrix.length; j++) {
                stringMax += Math.abs(matrix[i][j]);
            }
            if (max < stringMax) {
                max = stringMax;
            }
        }
        return max;
    }


    // невязка генерации V(A) (греческая ню с индексом бесконечность от матрицы А)
    // норма матрицы умножить на норму обратной матрицы
    private double calculateV_nevyazka_of_generation(double matrixNorm, double inverseMatrixNorm) {
        return matrixNorm * inverseMatrixNorm;
    }

    // z - ошибка = приближ решение (x с волной) минус точное решение (x со звездочкой)
    private double[] calculateErrorZ(double[] approximateX, double[] exactX) {
        int n = approximateX.length;
        double[] z = new double[n];
        for (int i = 0; i < n; i++) {
            z[i] = approximateX[i] - exactX[i];
        }
        return z;
    }

    // норма вектора - максимум из модулей элементов вектора
    // здесь будет считаться норма невязки || r ||, норма ошибки || z ||, и промежуточные вычисления
    // для поиска относительной нормы ошибки J(красивое) и относительной нормы невязки p(красивое)
    public static double calculateCubicNormOfVector(double[] v) {
        double max = 0;
        double curElem;
        for (int i = 0; i < v.length; i++) {
            curElem = Math.abs(v[i]);
            if (max < curElem) {
                max = curElem;
            }
        }
        return max;
    }

    // невязка (греческая тау либо английская r) r = A*x (x с волной) - f
    private double[] calculateR_Nevyazka(double[][] matrix, double[] approximateX, double[] f) {
        int n = approximateX.length;
        double[] answer = MatrixFunctions.multiplyMatrixOnVector(matrix, approximateX);
        for (int i = 0; i < n; i++) {
            answer[i] = answer[i] - f[i];
        }
        return answer;
    }

    // относительная норма невязки p = норма невязки разделить на норму вектора f (значения за знаком равенства, или за чертой)
    private double calculateP_otnositelnaya_norm_nevyazki(double normOfNevyazka_r, double[] f) {
        return normOfNevyazka_r / calculateCubicNormOfVector(f);
    }

    // относительная норма ошибки J (греческая йота) = норма ошибки поделить на норму точного решения
    private double calculateJ_otnositelnaya_norm_of_error(double normOfError_z, double[] exactX) {
        return normOfError_z / calculateCubicNormOfVector(exactX);
    }


}
