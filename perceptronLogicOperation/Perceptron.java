package perceptron;

public class Perceptron {
    double[] enters;
    double[] weights;
    double outer;

    public double[][] patterns;

    public Perceptron() {
        patterns  = new double[][] {
                {0, 0, 0},
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        enters = new double[2];
        weights = new double[enters.length];
        for (int i = 0; i < enters.length; i++) {
            weights[i] = Math.random() * 0.3 + 0.1;
        }
    }

    public Perceptron(double[][] p) {
        patterns = p;
        enters = new double[2];
        weights = new double[enters.length];
        for (int i = 0; i < enters.length; i++) {
            weights[i] = Math.random() * 0.3 + 0.1;
        }
    }

    public void countOuter() {
        outer = 0;
        for (int i = 0; i < enters.length; i++) {
            outer += enters[i] * weights[i];
        }
        if(outer > 0.5) {
            outer = 1;
        } else {
            outer = 0;
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
                double error = patterns[p][2] - outer;
                gError += Math.abs(error);
                for (int i = 0; i < enters.length; i++) {
                    weights[i] += 0.1 * error * enters[i];
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
