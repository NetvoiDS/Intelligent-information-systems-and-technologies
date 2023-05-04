public class MultiLayerPerceptron {

double[] enters; // слой вход массив
double[] hidden; // скрытый слой массив
double [] outer; // слой выход массив
double[][]E; // двухмерный массив веса от слоя входного к слою скрытому
double[][]H; // веса от слоя скрытого к слою тут на так у ВАС иначе вылазит ошибка.
double[][] patterns = {
{0,0}, {1,0}, {0,1}, {1,1}
};
double[] answers = {0,1,1,0};

MultiLayerPerceptron() {
enters = new double[patterns[0].length]; // привязываем к входным нейронам входы равны количеству входных данных. длинна
hidden = new double[2];
outer = new double[1];
E = new double[enters.length][hidden.length];
H = new double[hidden.length][outer.length];

initWeights();
study();
for (int p = 0; p < patterns.length; p++) {
for (int i = 0; i < enters.length; i++)
enters[i] = patterns[p][i];

countOuter();

System.out.println(outer[0]);
}

}

public void initWeights() {
for (int i = 0; i < E.length; i++) {
for (int j = 0; j < E[i].length; j++) {
E[i][j] = Math.random()*0.3+0.1;
}
}

for (int i = 0; i < H.length; i++) {
for (int j = 0; j < H[i].length; j++) {
H[i][j] = Math.random()*0.3+0.1;
}
}

}

public void countOuter() {
for (int i = 0; i < hidden.length; i++) {
hidden[i] = 0;
for (int j = 0; j < enters.length ; j++) {
hidden[i] += enters[j] * E[j][i];
}
if (hidden[i] > 0.5) hidden[i] = 1; else hidden[i] = 0;
}

for (int i = 0; i < outer.length; i++) {
outer[i] = 0;
for (int j = 0; j < hidden.length; j++) {
outer[i] += hidden[j] * H[j][i];
}
if(outer[i]>0.5) outer[i]=1; else outer[i] = 0;
}

}

public void study() { // обучение
double[] err = new double[hidden.length];
double gError = 0;
double lErr = 0;
do {
gError = 0;
for (int p = 0; p < patterns.length; p++) {
for (int i = 0; i < enters.length; i++)
enters[i] = patterns[p][i];

countOuter();

for (int i = 0; i < outer.length; i++)
lErr = answers[p] - outer[i];

gError += Math.abs(lErr);

for(int i = 0; i < hidden.length; i++) {
for (int j = 0; j < outer.length; j++) {
err[i]=lErr * H[i][j];
}
}

for(int i = 0; i < enters.length; i++) {
for(int j = 0; j < hidden.length; j++) {
E[i][j]+=0.1*err[j]*enters[i];
}
}

for (int i = 0; i < hidden.length; i++) {
for (int j = 0; j < outer.length; j++) {
H[i][j] += 0.1 * lErr * hidden[i];
}
}
}
} while(gError!= 0); //условие выхода.
}

public static void main(String[] args) {
MultiLayerPerceptron MultiLayerPerceptron = new MultiLayerPerceptron(); // задаем точку входа
}
}
