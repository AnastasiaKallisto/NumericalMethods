public class TableFunctions {
    // все нормы - кубические, то есть с индексом бесконечность

    // кубическая норма матрицы - максимум из сумм модулей элементов каждой строки
    // || A ||, || A ^ -1 || будет считаться тоже здесь
    public double getCubicNormOfMatrix(double[][] matrix){
        return 0;
    }

    // поиск обратной матрицы A^-1
    public double[][] getInverseMatrix(double[][] matrix){
        return new double[1][1];
    }

    // невязка генерации V(A) (греческая ню с индексом бесконечность от матрицы А)
    // норма матрицы умножить на норму обратной матрицы
    public double getV_nevyazka_of_generation(double[][] matrix){
        return 0;
    }

    // z - ошибка = приближ решение (x с волной) минус точное решение (x со звездочкой) (вектор минус вектор = вектор)
    // возвращает массив формата double[n]
    public double[] getErrorZ(double[] approximateX, double[] exactX){
        return new double[5];
    }

    // норма вектора - максимум из модулей элементов вектора
    // здесь будет считаться норма невязки || r ||, норма ошибки || z ||, и промежуточные вычисления
    // для поиска относительной нормы ошибки J(красивое) и относительной нормы невязки p(красивое)
    public double getCubicNormOfVector(double[] v){
        return 0;
    }

    // невязка (греческая тау либо английская r) r = A*x (x с волной) - f
    public double[] getR_Nevyazka(double[][] matrix, double[] approximateX, double[] f){
        return new double[5];
    }

    // относительная норма невязки p = норма невязки разделить на норму вектора f (значения за знаком равенства, или за чертой)
    public double getP_otnositelnaya_norm_nevyazki(double[] r_nevyazka, double f){
        return 0;
    }

    // относительная норма ошибки J (греческая йота) = норма ошибки поделить на норму точного решения
    public double getJ_otnositelnaya_norm_of_error(double[] z_error, double[] exactX){
        return 0;
    }


}
