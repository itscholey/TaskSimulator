import java.util.Arrays;
import java.util.Random;
import org.jblas.*;

public class NeuralNetwork {
	private int	inputLayerSize;
	private int	outputLayerSize;
	private int hiddenLayerSize;

	private DoubleMatrix inputData;
	private DoubleMatrix outputData;
	private double[] expectedOutputData;

	private DoubleMatrix weights1;
	private DoubleMatrix weights2;

	private Random random;
	private final int roundLength = 5;
	private double fitness;
	
	public NeuralNetwork(double[][] input, double[] expectedOutput) {
		inputData = new DoubleMatrix(input);
		expectedOutputData = expectedOutput;
		random = new Random();
		inputLayerSize = inputData.columns; // 2
		outputLayerSize = 1;
		hiddenLayerSize = 3;
		
		// initialise weights and assign random weights
		weights1 = DoubleMatrix.rand(inputLayerSize, hiddenLayerSize); 	// 2 / 3 
		weights2 = DoubleMatrix.rand(hiddenLayerSize, outputLayerSize);	// 3 / 1 
		
		//System.out.println("Inputs: " + inputData.toString());
		//System.out.println("Weights1: " + weights1.toString());
		//System.out.println("Weights2: " + weights2.toString() + "\n");
		
		outputData = forward();
		fitness = evaluate();
		
		//System.out.println("Fitness: " + fitness + "\n");
	}
	
	public NeuralNetwork(double[][] input, double[] expectedOutput, double[][] w1, double[][] w2) {
		inputData = new DoubleMatrix(input);
		expectedOutputData = expectedOutput;
		inputLayerSize = inputData.columns; // 2
		outputLayerSize = 1;
		hiddenLayerSize = 3;
		weights1 = new DoubleMatrix(w1);
		weights2 = new DoubleMatrix(w2);
		random = new Random();
		
		outputData = forward();
		fitness = evaluate();
	}
	
	public double[][] getWeights1() {
		return weights1.toArray2();
	}
	
	public double[][] getWeights2() {
		return weights2.toArray2();
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public DoubleMatrix getOutput() {
		return outputData;
	}
	
	private DoubleMatrix forward() {
		DoubleMatrix yHat = null;
		
		// get first activation value
		DoubleMatrix z2 = inputData.mmul(weights1);
		// apply activation function
		DoubleMatrix a2 = sigmoid(z2);
		// get second activation value
		DoubleMatrix z3 = a2.mmul(weights2);
		// apply final activation function to get predicted output
		yHat = sigmoid(z3); 
		
		//System.out.println("Z2: " + z2.toString());
		//System.out.println("A2: " + a2.toString());
		//System.out.println("Z3: " + z3.toString());
		//System.out.println("yHat: " + yHat.toString() + "\n\n-----------------------------\n\n");
		
		return yHat;
	}
	
	public double evaluate() {
		double result = 0.0;
		
		for (int i = 0; i < outputData.rows; i++) {
			for (int j = 0; j < outputData.columns; j++) {
				result += Math.abs(expectedOutputData[i] - outputData.get(i, j));
			}
		}
		
		return result;
	}
	
	
	public void mutate() {
		boolean w1 = random.nextBoolean();
		boolean negate = random.nextBoolean();
		
		int numMutations = random.nextInt(4) + 1;
		
		for (int m = 0; m < numMutations; m++) {
			if (w1) {
				//do weights1
				
				int i = random.nextInt(weights1.getRows());
				int j = random.nextInt(weights1.getColumns());
				
				double newValue = random.nextDouble();
				
				if (negate) {
					newValue *= -1;                        
				}
				
				newValue += weights1.get(i, j);
				weights1.put(i, j, newValue);
				
			}
			else { //weights2
				int i = random.nextInt(weights2.getRows());
				int j = random.nextInt(weights2.getColumns());
				
				double newValue = random.nextDouble();
				
				if (negate) {
					newValue *= -1;
				}
				
				newValue += weights2.get(i, j);
				weights2.put(i, j, newValue);
			}
			
			fitness = evaluate();
		}
	}
	
	private DoubleMatrix sigmoid(DoubleMatrix matrix)
	{
		DoubleMatrix result = new DoubleMatrix(matrix.rows, matrix.columns);
		
		for (int i = 0; i < matrix.rows; i++) {
			for (int j = 0; j < matrix.columns; j++) {
				result.put(i, j, (1 / (1 + Math.exp(-matrix.get(i, j)))));
			}
		}
	    return result;
	}
}