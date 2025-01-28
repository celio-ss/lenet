package br.org.scsi.lenet5.layers.impl;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractFullyConnectedLayer;

public class LeF6FullyConnectedLayer extends AbstractFullyConnectedLayer<FloatMatrix> {

	public LeF6FullyConnectedLayer(int numNeurons, int inputSize) {
		super(numNeurons, inputSize);
	}

	@Override
	protected void initializeWeightsAndBias() {
		// Inicialize a matriz de pesos com valores pequenos e aleatórios
		// e o vetor de bias com zeros ou valores pequenos.
		this.weights = new FloatMatrix(numNeurons, inputSize); // ou outra implementação de IMatrix
		// ... inicialização dos pesos ...

		this.bias = new FloatMatrix(numNeurons, 1);
		// ... inicialização do bias ...
	}

	@Override
	public FloatMatrix createMatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}

	@Override
	public FloatMatrix[] createMatrixArray(int size) {
		return new FloatMatrix[size];
	}
}
