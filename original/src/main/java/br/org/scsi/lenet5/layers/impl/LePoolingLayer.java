package br.org.scsi.lenet5.layers.impl;

import java.util.List;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractPoolingLayer;

public class LePoolingLayer extends AbstractPoolingLayer<FloatMatrix> {

	public LePoolingLayer(int windowSize, int stride) {
		super(windowSize, stride);
	}

	@Override
	public List<FloatMatrix> compute(List<FloatMatrix> c1Output) {
		// TODO Auto-generated method stub
		return null;
	}

}
