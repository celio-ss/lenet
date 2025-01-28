package br.org.scsi.lenet5.layers;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.data.IMatrix;

//Camada de Convolução (C1, C3, C5)
public abstract class AbstractConvolutionLayer<T extends IMatrix<T>> implements ILayer<T> {
	protected final T[] kernels;
	protected final T bias;
	protected final int numKernels;
	protected final int kernelHeight;
	protected final int kernelWidth;
	protected final int stride;

	protected AbstractConvolutionLayer(int numKernels, int stride, int kernelHeight, int kernelWidth) {
		this.numKernels = numKernels;
		this.kernelHeight = kernelHeight;
		this.kernelWidth = kernelWidth;
		this.kernels = createMatrixArray(numKernels);
		this.stride = stride;
		this.bias = createMatrix(kernelWidth, kernelHeight);
		// Inicialize os kernels e o bias (ver detalhes abaixo)
		initializeKernelsAndBias();
	}

	// Método abstrato para inicializar kernels e bias
	protected abstract void initializeKernelsAndBias();

	@Override
	public T forward(T in) {
		T[] tmp = createMatrixArray(1);
		tmp[0] = in;
		return forward(tmp)[0];
	}

	public T[] forward(T[] inputs) {
		final T input = inputs[0];

		// 1. Verifica as dimensões da entrada
		if (input.getHeight() < kernelHeight || input.getWidth() < kernelWidth) {
			throw new IllegalArgumentException("A matriz de entrada é menor que o tamanho do kernel.");
		}

		// 2. Cria um array para armazenar os feature maps
		T[] featureMaps = createMatrixArray(numKernels);

		// 3. Itera sobre os kernels
		for (int k = 0; k < numKernels; k++) {
			// 4. Realiza a convolução para cada kernel
			T featureMap = convolve(input, kernels[k]);

			// 5. Adiciona o bias ao feature map
			featureMap = featureMap.add(bias); // Assume que o bias é o mesmo para todos os feature maps.

			// 6. Aplica a função de ativação
			featureMap = applyActivation(featureMap);

			// 7. Armazena o feature map resultante
			featureMaps[k] = featureMap;
		}

		// 8. Retorna o array de feature maps
		return featureMaps;
	}

	private T convolve(T input, T kernel) {
		int featureMapHeight = input.getHeight() - kernelHeight + 1;
		int featureMapWidth = input.getWidth() - kernelWidth + 1;
		T featureMap = createMatrix(featureMapHeight, featureMapWidth); // ou outra implementação de IMatrix

		for (int i = 0; i < featureMapHeight; i++) {
			for (int j = 0; j < featureMapWidth; j++) {
				float sum = 0;
				for (int ki = 0; ki < kernelHeight; ki++) {
					for (int kj = 0; kj < kernelWidth; kj++) {
						sum += input.get(i * stride + ki, j * stride + kj) * kernel.get(ki, kj);
					}
				}
				featureMap.set(i, j, sum); // 'set' é um método que você precisará adicionar em IMatrix
			}
		}

		return featureMap;
	}

	private T applyActivation(T matrix) {
		T result = matrix.copy(); // 'copy' é um método que você precisará adicionar em IMatrix para criar uma
									// cópia da matriz
		for (int i = 0; i < result.getHeight(); i++) {
			for (int j = 0; j < result.getWidth(); j++) {
				result.set(i, j, (float) Math.tanh(result.get(i, j)));
			}
		}
		return result;
	}

}
