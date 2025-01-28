package br.org.scsi.lenet5.layers;

import br.org.scsi.lenet5.data.IMatrix;

public abstract class AbstractFullyConnectedLayer<T extends IMatrix<T>> implements ILayer<T> {

	protected T weights;
	protected T bias;
	protected int numNeurons;
	protected int inputSize; // Tamanho da entrada (número de neurônios da camada anterior)

	protected AbstractFullyConnectedLayer(int numNeurons, int inputSize) {
		this.numNeurons = numNeurons;
		this.inputSize = inputSize;
		initializeWeightsAndBias();
	}

	protected abstract void initializeWeightsAndBias();

	@Override
	public T[] forward(T[] in) {
		T[] tmp = createMatrixArray(1);
		tmp[0] = forward(tmp[0]);
		return tmp;
	}

	@Override

	public T forward(T input) {
		// 1. Verifique as dimensões da entrada.
		if (input.getHeight() != inputSize || input.getWidth() != 1) {
			throw new IllegalArgumentException("Entrada incompatível com a camada totalmente conectada.");
		}

		// 2. Realize a multiplicação de matrizes: weights * input
		T weightedInput = this.weights.multiply(input);

		// 3. Adicione o bias
		T biasedInput = weightedInput.add(this.bias);

		// 4. Aplique a função de ativação (ex: tanh)
		return applyActivation(biasedInput);
	}

	protected T applyActivation(T matrix) {
		T result = matrix.copy();
		for (int i = 0; i < result.getHeight(); i++) {
			result.set(i, 0, (float) Math.tanh(result.get(i, 0))); // Exemplo com tanh
		}
		return result;
	}
}
