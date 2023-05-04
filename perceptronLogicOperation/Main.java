package perceptron;

public class Main {

    static double[][] orPattern = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
    };

    static double[][] andPattern = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 0, 0},
            {1, 1, 1},
    };

    static double[][] notPattern = {
            {1, 0, 0},
            {1, 1, 1},
    };

    public static void main(String[] args) {

        System.out.println("Or:");
        new Perceptron(orPattern).test();

        System.out.println("And:");
        new Perceptron(andPattern).test();

        System.out.println("Not:");
        new Perceptron(notPattern).test();
    }
}