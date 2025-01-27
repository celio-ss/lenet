package br.org.scsi.lenet5.layers.impl;

import java.util.Collections;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.data.IMatrix;
import br.org.scsi.lenet5.layers.AbstractConvolutionLayer;

public class LeC1ConvolutionLayer extends AbstractConvolutionLayer<FloatMatrix> {

	public LeC1ConvolutionLayer() {
		super(6, 1, 1, 28);
		for (int i = 0; i < filterSize; i++) {
			filters.add(Collections.singletonList(new FloatMatrix(filterSize, filterSize)));
		}
	}

	@Override
	protected FloatMatrix createMatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}

}
