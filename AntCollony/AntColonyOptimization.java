import java.util.Arrays;
import java.util.Random;

public class AntColonyOptimization {
    // Количество городов
    private int numOfCities;
    // Расстояния между городами
    private double[][] distances;
    // Количество муравьев
    private int numOfAnts;
    // Коэффициенты алгоритма
    private double alpha;
    private double beta;
    private double rho;
    private double Q;
    // Матрица феромонов
    private double[][] pheromones;
    // Лучший маршрут и его длина
    private int[] bestRoute;
    private double bestDistance;
    // Рандомайзер
    private Random random;

    public AntColonyOptimization(int numOfCities, double[][] distances, int numOfAnts, double alpha, double beta, double rho, double Q) {
        this.numOfCities = numOfCities;
        this.distances = distances;
        this.numOfAnts = numOfAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
        this.Q = Q;
        this.pheromones = new double[numOfCities][numOfCities];
        this.random = new Random();
    }

    // Инициализация матрицы феромонов
    private void initPheromones() {
        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                pheromones[i][j] = 0.01;
            }
        }
    }

    // Выполнение алгоритма
    public void run(int numOfIterations) {
        // Инициализация матрицы феромонов
        initPheromones();

        for (int i = 0; i < numOfIterations; i++) {
            // Выбор маршрутов муравьев и обновление матрицы феромонов
            for (int j = 0; j < numOfAnts; j++) {
                int[] route = chooseRoute();
                double distance = calculateDistance(route);
                updatePheromones(route, distance);
                if (bestRoute == null || distance < bestDistance) {
                    bestRoute = route;
                    bestDistance = distance;
                }
            }

            // Обновление матрицы феромонов по формуле rho
            for (int j = 0; j < numOfCities; j++) {
                for (int k = 0; k < numOfCities; k++) {
                    pheromones[j][k] *= (1 - rho);
                }
            }
        }
    }

    // Выбор маршрута муравья
    private int[] chooseRoute() {
        int[] route = new int[numOfCities];
        boolean[] visited = new boolean[numOfCities];
        int currCity = random.nextInt(numOfCities);
        route[0] = currCity;
        visited[currCity] = true;

        for (int i = 1; i < numOfCities; i++) {
            int nextCity = chooseNextCity(currCity, visited);
            route[i] = nextCity;
            visited[nextCity] = true;
            currCity = nextCity;
        }

        return route;
    }

    // Выбор следующего города
    private int chooseNextCity(int currCity, boolean[] visited) {
        double[] probabilities = new double[numOfCities];
        double probabilitiesSum = 0;

        for (int i = 0; i < numOfCities; i++) {
            if (visited[i]) {
                probabilities[i] = 0;
            } else {
                probabilities[i] = Math.pow(pheromones[currCity][i], alpha) * Math.pow(1 / distances[currCity][i], beta);
                probabilitiesSum += probabilities[i];
            }
        }

        double rand = random.nextDouble() * probabilitiesSum;
        double cumProb = 0;

        for (int i = 0; i < numOfCities; i++) {
            if (!visited[i]) {
                cumProb += probabilities[i];
                if (cumProb >= rand) {
                    return i;
                }
            }
        }

        // Если все города уже посещены, выбираем случайный непосещенный город
        for (int i = 0; i < numOfCities; i++) {
            if (!visited[i]) {
                return i;
            }
        }

        return -1;
    }

    // Вычисление длины маршрута
    private double calculateDistance(int[] route) {
        double distance = 0;

        for (int i = 0; i < numOfCities; i++) {
            int city1 = route[i];
            int city2 = i == numOfCities - 1 ? route[0] : route[i + 1];
            distance += distances[city1][city2];
        }

        return distance;
    }

    // Обновление матрицы феромонов на основе выбранного маршрута и его длины
    private void updatePheromones(int[] route, double distance) {
        for (int i = 0; i < numOfCities; i++) {
            int city1 = route[i];
            int city2 = i == numOfCities - 1 ? route[0] : route[i + 1];
            pheromones[city1][city2] += Q / distance;
            pheromones[city2][city1] += Q / distance;
        }
    }

    // Геттер для лучшего маршрута
    public int[] getBestRoute() {
        return bestRoute;
    }

    // Геттер для длины лучшего маршрута
    public double getBestDistance() {
        return bestDistance;
    }


    public static void main(String[] args) { // Создание массива расстояний между городами
        int numOfCities = 5;
        double[][] distances = {{0, 10, 20, 30, 40}, {10, 0, 15, 25, 35}, {20, 15, 0, 14, 29}, {30, 25, 14, 0, 11}, {40, 35, 29, 11, 0}};

// Создание объекта для алгоритма муравья
        int numOfAnts = 10;
        double alpha = 1;
        double beta = 2;
        double rho = 0.5;
        double Q = 100;
        AntColonyOptimization aco = new AntColonyOptimization(numOfCities, distances, numOfAnts, alpha, beta, rho, Q);

// Запуск алгоритма на 100 итераций
        int numOfIterations = 100;
        aco.run(numOfIterations);

// Вывод результата
        System.out.println("Best route: " + Arrays.toString(aco.getBestRoute()));
        System.out.println("Best distance: " + aco.getBestDistance());
    }
}
