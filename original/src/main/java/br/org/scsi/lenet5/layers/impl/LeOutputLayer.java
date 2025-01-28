package br.org.scsi.lenet5.layers.impl;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractFullyConnectedLayer;

public class LeOutputLayer extends AbstractFullyConnectedLayer<FloatMatrix> {

	public LeOutputLayer(int numNeurons, int inputSize) {
		super(numNeurons, inputSize);
	}

	@Override
	protected FloatMatrix applyActivation(FloatMatrix matrix) {
		// Implementação da função softmax
		FloatMatrix expMatrix = matrix.copy();
		float sum = 0;
		for (int i = 0; i < expMatrix.getHeight(); i++) {
			float expValue = (float) Math.exp(expMatrix.get(i, 0));
			expMatrix.set(i, 0, expValue);
			sum += expValue;
		}

		FloatMatrix result = new FloatMatrix(expMatrix.getHeight(), 1);
		for (int i = 0; i < expMatrix.getHeight(); i++) {
			result.set(i, 0, expMatrix.get(i, 0) / sum);
		}
		return result;
	}

	@Override
	public FloatMatrix createMatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}

	@Override
	public FloatMatrix[] createMatrixArray(int size) {
		return new FloatMatrix[size];
	}

	@Override
	protected void initializeWeightsAndBias() {
		// TODO Auto-generated method stub

	}
}