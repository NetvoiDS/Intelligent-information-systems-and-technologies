package perceptron;

public class Perceptron {
    double[] enters;
    double[] weights;
    double outer;

    public double[][] patterns;

    public Perceptron() {
        patterns  = new double[][] {
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

        enters = new double[1];
        weights = new double[enters.length];
        for (int i = 0; i < enters.length; i++) {
            weights[i] = Math.random() * 0.3 + 0.1;
        }
    }

    public Perceptron(double[][] p) {
        patterns = p;
        enters = new double[1];
        weights = new double[enters.length];
        for (int i = 0; i < enters.length; i++) {
            weights[i] = Math.random() * 0.001 + 0.1;
        }
    }

    public void countOuter() {
        outer = 0;
        for (int i = 0; i < enters.length; i++) {
            outer += enters[i] * weights[i];
        }

        if (outer < 0.5) {
            outer = 0;
        } else if(outer < 1.5) {
            outer = 1;
        } else if (outer < 2.5) {
            outer = 2;
        } else if (outer < 3.5) {
            outer = 3;
        } else if (outer < 4.5) {
            outer = 4;
        } else if (outer < 5.5 & outer > 4.5) {
            outer = 5;
        } else if (outer < 6.5 & outer > 5.5) {
            outer = 6;
        } else if (outer < 7.5 & outer > 6.5) {
            outer = 7;
        } else if (outer < 8.5 & outer > 7.5) {
            outer = 8;
        } else if (outer > 8.5) {
            outer = 9;
        }
    }

    public int study() {
        double gError = 0;
        int iteration = 0;
        do {
            iteration++;
            gError = 0;
            for(int p = 0; p < patterns.length; p++) {
                enters = java.util.Arrays.copyOf(patterns[p], patterns[p].length - 1);
                countOuter();
                double error = patterns[p][1] - outer;
                gError += Math.abs(error);
                for (int i = 0; i < enters.length; i++) {
                    weights[i] += 0.01 * error * enters[i];
                }
            }
        } while (gError != 0);
        return iteration;
    }

    public void test() {
        System.out.println(study());
        for (int p = 0; p < patterns.length; p++) {
            enters = java.util.Arrays.copyOf(patterns[p], patterns[p].length - 1);
            countOuter();
            System.out.println(outer);
        }
    }

}
