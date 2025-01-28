package br.org.scsi.lenet5.layers.impl;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractPoolingLayer;

public class LePoolingLayer extends AbstractPoolingLayer<FloatMatrix> {

	public LePoolingLayer(int windowSize, int stride) {
		super(windowSize, stride);
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
