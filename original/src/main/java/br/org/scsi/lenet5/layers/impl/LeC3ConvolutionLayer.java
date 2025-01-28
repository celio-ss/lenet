package br.org.scsi.lenet5.layers.impl;

import java.util.Arrays;
import java.util.List;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractConvolutionLayer;

public class LeC3ConvolutionLayer extends AbstractConvolutionLayer<FloatMatrix> {

	public LeC3ConvolutionLayer() {
		super(5, 1, 16, 10);
	}

	private List<Integer> getIndicesEntradaC3(int mapaSaida) {
		switch (mapaSaida) {
		case 0:
			return Arrays.asList(0, 1, 2);
		case 1:
			return Arrays.asList(1, 2, 3);
		case 2:
			return Arrays.asList(2, 3, 4);
		case 3:
			return Arrays.asList(3, 4, 5);
		case 4:
			return Arrays.asList(4, 5, 0);
		case 5:
			return Arrays.asList(5, 0, 1);
		case 6:
			return Arrays.asList(0, 1, 2, 3);
		case 7:
			return Arrays.asList(1, 2, 3, 4);
		case 8:
			return Arrays.asList(2, 3, 4, 5);
		case 9:
			return Arrays.asList(3, 4, 5, 0);
		case 10:
			return Arrays.asList(4, 5, 0, 1);
		case 11:
			return Arrays.asList(5, 0, 1, 2);
		case 12:
			return Arrays.asList(0, 1, 3, 4);
		case 13:
			return Arrays.asList(1, 2, 4, 5);
		case 14:
			return Arrays.asList(0, 2, 3, 5);
		case 15:
			return Arrays.asList(0, 1, 2, 3, 4, 5);
		default:
			return null;
		}
	}

	@Override
	public FloatMatrix createMatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}

	@Override
	protected void initializeKernelsAndBias() {

//	// Mapa de saída 0: usa mapas de entrada 0, 1, 2
		this.kernels[0] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-0
		this.kernels[0] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-1
		this.kernels[0] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-2
//
//	// Mapa de saída 1: usa mapas de entrada 1, 2, 3
		this.kernels[1] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-1
		this.kernels[1] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-2
		this.kernels[1] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-3
//
		this.kernels[2] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[2] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[2] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[3] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[3] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[3] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[4] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[4] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[4] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[5] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[5] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[5] = createMatrix(kernelWidth, kernelHeight);
//
//	// Mapa de saída 6: usa mapas de entrada 0, 1, 2, 3
		this.kernels[6] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-0
		this.kernels[6] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-1
		this.kernels[6] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-2
		this.kernels[6] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-3
//
		this.kernels[7] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[7] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[7] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[7] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[8] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[8] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[8] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[8] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[9] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[9] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[9] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[9] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[10] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[10] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[10] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[10] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[11] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[11] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[11] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[11] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[12] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[12] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[12] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[12] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[13] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[13] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[13] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[13] = createMatrix(kernelWidth, kernelHeight);
//
		this.kernels[14] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[14] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[14] = createMatrix(kernelWidth, kernelHeight);
		this.kernels[14] = createMatrix(kernelWidth, kernelHeight);
//	// Mapa de saída 15: usa mapas de entrada 0, 1, 2, 3, 4, 5
		this.kernels[15] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-0
		this.kernels[15] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-1
		this.kernels[15] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-2
		this.kernels[15] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-3
		this.kernels[15] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-4
		this.kernels[15] = createMatrix(kernelWidth, kernelHeight); // Filtro para mapa de entrada S2-5
	}

	@Override
	public FloatMatrix[] createMatrixArray(int size) {
		return new FloatMatrix[size];
	}

}
