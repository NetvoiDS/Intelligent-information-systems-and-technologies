package perceptron;



public class Main {

    static double[][] Pattern = {
            {0, 0},
            {1, 1},
            {2, 2},
            {3, 3},
            {4, 4},
            {5, 5},
            {6, 6},
            {7, 7},
            {8, 8},
            {9, 9},
    };

    public static void main(String[] args) {

        new Perceptron(Pattern).test();

    }
}