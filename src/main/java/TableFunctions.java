public class TableFunctions {
    // все нормы - кубические, то есть с индексом бесконечность
    // f = A * x^* (матрица умножить на точное решение)

    private double matrixNorm;
    private double inverseMatrixNorm;
    private double V_NevyazkaOfGeneration;
    private double normOfError_z;
    private double J_OtnosNormOfError;
    private double normOfNevyazka_r;
    private double p_OtnosNormOfNevyazka;
    private double[] z_Error;
    private double[] r_nevyazka;

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
        matrixNorm = calculateCubicNormOfMatrix(matrix); // кубическая норма матрицы
        inverseMatrixNorm = calculateCubicNormOfMatrix(calculateInverseMatrix(matrix)); // кубическая норма обратной матрицы
        V_NevyazkaOfGeneration = calculateV_nevyazka_of_generation(matrix); // невязка генерации (норма матрицы * норма обратной матрицы)
        r_nevyazka = calculateR_Nevyazka(matrix,approximateX,f); // невязка
        normOfNevyazka_r = calculateCubicNormOfVector(r_nevyazka); // норма невязки
        p_OtnosNormOfNevyazka = calculateP_otnositelnaya_norm_nevyazki(r_nevyazka, f); // относительная норма невязки
        z_Error = calculateErrorZ(approximateX, exactX); // ошибка
        normOfError_z = calculateCubicNormOfVector(z_Error); // норма ошибки
        J_OtnosNormOfError = calculateJ_otnositelnaya_norm_of_error(z_Error,exactX); // относительная норма ошибки
    }

    // кубическая норма матрицы - максимум из сумм модулей элементов каждой строки
    // || A ||, || A ^ -1 || будет считаться тоже здесь
    private double calculateCubicNormOfMatrix(double[][] matrix) {
        return 0;
    }

    // поиск обратной матрицы A^-1
    private double[][] calculateInverseMatrix(double[][] matrix) {
        return new double[1][1];
    }

    // невязка генерации V(A) (греческая ню с индексом бесконечность от матрицы А)
    // норма матрицы умножить на норму обратной матрицы
    private double calculateV_nevyazka_of_generation(double[][] matrix) {
        return 0;
    }

    // z - ошибка = приближ решение (x с волной) минус точное решение (x со звездочкой) (вектор минус вектор = вектор)
    // возвращает массив формата double[n]
    private double[] calculateErrorZ(double[] approximateX, double[] exactX) {
        return new double[5];
    }

    // норма вектора - максимум из модулей элементов вектора
    // здесь будет считаться норма невязки || r ||, норма ошибки || z ||, и промежуточные вычисления
    // для поиска относительной нормы ошибки J(красивое) и относительной нормы невязки p(красивое)
    private double calculateCubicNormOfVector(double[] v) {
        return 0;
    }

    // невязка (греческая тау либо английская r) r = A*x (x с волной) - f
    private double[] calculateR_Nevyazka(double[][] matrix, double[] approximateX, double[] f) {
        return new double[5];
    }

    // относительная норма невязки p = норма невязки разделить на норму вектора f (значения за знаком равенства, или за чертой)
    private double calculateP_otnositelnaya_norm_nevyazki(double[] r_nevyazka, double[] f) {
        return 0;
    }

    // относительная норма ошибки J (греческая йота) = норма ошибки поделить на норму точного решения
    private double calculateJ_otnositelnaya_norm_of_error(double[] z_error, double[] exactX) {
        return 0;
    }


}
