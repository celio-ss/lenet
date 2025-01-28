package br.org.scsi.lenet5.layers;

import br.org.scsi.lenet5.data.IMatrix;

public abstract class AbstractPoolingLayer<T extends IMatrix<T>> implements ILayer<T> {
	private final int windowSize;
	private final int stride;

	protected AbstractPoolingLayer(int windowSize, int stride) {
		this.windowSize = windowSize;
		this.stride = stride;
	}

	@Override
	public T[] forward(T[] input) {
		T[] output = createMatrixArray(input.length);
		for (int i = 0; i < input.length; i++) {
			output[i] = performPooling(input[i]); // 'performPooling' seria o seu método atual que realiza o pooling
		}
		return output;
	}


	protected T performPooling(T input) {
		int outputWidth = (input.getWidth() - windowSize) / stride + 1;
		int outputHeight = (input.getHeight() - windowSize) / stride + 1;
		final T output = createMatrix(outputWidth, outputHeight);

		for (int y = 0; y < outputHeight; y++) {
			for (int x = 0; x < outputWidth; x++) {
				float sum = 0;
				for (int wy = 0; wy < windowSize; wy++) {
					for (int wx = 0; wx < windowSize; wx++) {
						sum += input.get(x * stride + wx, y * stride + wy);
					}
				}
				float average = sum / (windowSize * windowSize);
				output.set(x, y, average);
			}
		}
		return output;
	}
}
