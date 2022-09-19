public class VectorFunctions {
    // умножение вектора на число
    public static double[] multiplyVectorOnNumber(double[] v, double number){
        double[] answer = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            answer[i] = v[i]*number;
        }
        return answer;
    }

    // разность двух векторов
    public static double[] V1minusV2(double[] v1, double[] v2){
        double[] answer = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            answer[i] = v1[i]-v2[i];
        }
        return answer;
    }
}
