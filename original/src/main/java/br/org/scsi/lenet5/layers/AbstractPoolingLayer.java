package br.org.scsi.lenet5.layers;

import java.util.List;

import br.org.scsi.lenet5.data.FloatMatrix;

public abstract class AbstractPoolingLayer {
	private final int windowSize;
	private final int stride;
	// ... outros atributos ...

	protected AbstractPoolingLayer(int windowSize, int stride) {
		this.windowSize = windowSize;
		this.stride = stride;
	}

	public abstract List<FloatMatrix> compute(List<FloatMatrix> c1Output);

}
