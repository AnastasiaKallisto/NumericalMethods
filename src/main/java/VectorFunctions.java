public class VectorFunctions {
    // умножение вектора на число
    public static double[] multiplyVectorOnNumber(double[] v, double number) {
        double[] answer = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            answer[i] = v[i] * number;
        }
        return answer;
    }

    // разность двух векторов
    public static double[] V1minusV2(double[] v1, double[] v2) {
        double[] answer = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            answer[i] = v1[i] - v2[i];
        }
        return answer;
    }

    // копирование вектора
    public static double[] getCopyOfVector(double[] v) {
        double[] vector = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            vector[i] = v[i];
        }
        return v;
    }
    // тензорное произведение векторов, v2 как будто строка, v1 как столбец
    public double[][] calculateTensorMultiplicationOfVectors(double[] v1, double[] v2) {
        int n = v1.length;
        int m = v2.length;
        double[][] answer = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer[i][j] = v1[i] * v2[j];
            }
        }
        return answer;
    }


    public static double getNormOfVector(double[] vector) {
        double result = 0;
        for (int i = 0; i < vector.length; i++) {
            result += (vector[i] * vector[i]);
        }
        return Math.sqrt(result);
    }
}
