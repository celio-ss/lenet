package br.org.scsi.lenet5.layers;

import java.util.List;

import br.org.scsi.lenet5.data.IMatrix;

public abstract class AbstractPoolingLayer<T extends IMatrix<T>> {
	private final int windowSize;
	private final int stride;
	// ... outros atributos ...

	protected AbstractPoolingLayer(int windowSize, int stride) {
		this.windowSize = windowSize;
		this.stride = stride;
	}

	public abstract List<T> compute(List<T> c1Output);

}
