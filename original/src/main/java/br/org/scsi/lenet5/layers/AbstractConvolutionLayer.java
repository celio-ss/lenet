package br.org.scsi.lenet5.layers;

import java.util.ArrayList;
import java.util.List;

import br.org.scsi.lenet5.data.FloatMatrix;

//Camada de Convolução (C1, C3, C5)
public abstract class AbstractConvolutionLayer<T extends FloatMatrix> {

	protected final List<List<T>> filters; // Pode ser uma lista de filtros (C1) ou uma lista de listas de
											// filtros (C3)
	protected final int filterSize;
	protected final int stride;
	protected final int deep;
	protected final int cOutSize;

	protected AbstractConvolutionLayer(int filterSize, int stride, int deep, int cOutSize) {
		this.filterSize = filterSize;
		this.stride = stride;
		this.cOutSize = cOutSize;
		this.deep = deep;
		// Inicialize os filtros (pode ser aleatoriamente ou carregados de um arquivo)
		this.filters = new ArrayList<>();

	}

	public List<T> compute(List<T> input) {
		List<T> output = new ArrayList<>();
		for (int i = 0; i < filters.size(); i++) {
			List<T> filtros = filters.get(i);
			T resultado = convolveWithMultipleFilters(input, filtros, filterSize, stride);
			output.add(resultado);
		}
		return output;
	}

	protected abstract T createMatrix(int width, int height);

	public T convolveWithMultipleFilters(List<T> inputs, List<T> filters, int filterSize, int stride) {
		T output = createMatrix(inputs.get(0).getHeight(), inputs.get(0).getWidth());

		for (int i = 0; i < filters.size(); i++) {
			T input = inputs.get(i);
			T filter = filters.get(i);
			T convolved = convolve(input, filter, filterSize, stride);
			output.add(convolved);
		}
		return output;
	}

	public T convolve(T input, T filter, int filterSize, int stride) {
		int outputSize = (input.getHeight() - filterSize) / stride + 1;
		T output = createMatrix(outputSize, outputSize);

		for (int y = 0; y < outputSize; y++) {
			for (int x = 0; x < outputSize; x++) {
				float sum = 0;
				for (int fy = 0; fy < filterSize; fy++) {
					for (int fx = 0; fx < filterSize; fx++) {
						sum += input.get(x * stride + fx, y * stride + fy) * filter.get(fx, fy);
					}
				}
				output.set(x, y, sum);
			}
		}
		return output;
	}

}
