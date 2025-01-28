package br.org.scsi.lenet5.layers.impl;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractConvolutionLayer;

public class LeC5ConvolutionLayer extends AbstractConvolutionLayer<FloatMatrix> {

	public LeC5ConvolutionLayer() {
		super(120, 1, 1, 1);
	}

	@Override
	public FloatMatrix createMatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}

	@Override
	protected void initializeKernelsAndBias() {
		// TODO Auto-generated method stub

	}

	@Override
	public FloatMatrix[] createMatrixArray(int size) {
		return new FloatMatrix[size];
	}
}
