package br.org.scsi.lenet5.layers.impl;

import java.util.ArrayList;
import java.util.Collections;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.data.IMatrix;
import br.org.scsi.lenet5.layers.AbstractConvolutionLayer;

public class LeC5ConvolutionLayer extends AbstractConvolutionLayer<FloatMatrix> {

	public LeC5ConvolutionLayer() {
		super(120, 1, 1, 1);
		filters.add(new ArrayList<>());
		for (int i = 0; i < filterSize; i++) {
			filters.add(Collections.singletonList(new FloatMatrix(filterSize, filterSize)));
		}
	}

	@Override
	protected FloatMatrix createMatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}
}
