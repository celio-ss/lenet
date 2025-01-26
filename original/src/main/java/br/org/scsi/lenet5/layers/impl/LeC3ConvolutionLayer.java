package br.org.scsi.lenet5.layers.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractConvolutionLayer;

public class LeC3ConvolutionLayer extends AbstractConvolutionLayer<FloatMatrix> {

	public LeC3ConvolutionLayer() {
		super(5, 1, 16, 10);
		for (int i = 0; i < 16; i++) {
			filters.add(new ArrayList<>());
		}
		// Mapa de saída 0: usa mapas de entrada 0, 1, 2
		this.filters.get(0).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-0
		this.filters.get(0).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-1
		this.filters.get(0).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-2

		// Mapa de saída 1: usa mapas de entrada 1, 2, 3
		this.filters.get(1).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-1
		this.filters.get(1).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-2
		this.filters.get(1).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-3

		this.filters.get(2).add(createMatrix(filterSize, filterSize));
		this.filters.get(2).add(createMatrix(filterSize, filterSize));
		this.filters.get(2).add(createMatrix(filterSize, filterSize));

		this.filters.get(3).add(createMatrix(filterSize, filterSize));
		this.filters.get(3).add(createMatrix(filterSize, filterSize));
		this.filters.get(3).add(createMatrix(filterSize, filterSize));

		this.filters.get(4).add(createMatrix(filterSize, filterSize));
		this.filters.get(4).add(createMatrix(filterSize, filterSize));
		this.filters.get(4).add(createMatrix(filterSize, filterSize));

		this.filters.get(5).add(createMatrix(filterSize, filterSize));
		this.filters.get(5).add(createMatrix(filterSize, filterSize));
		this.filters.get(5).add(createMatrix(filterSize, filterSize));

		// Mapa de saída 6: usa mapas de entrada 0, 1, 2, 3
		this.filters.get(6).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-0
		this.filters.get(6).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-1
		this.filters.get(6).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-2
		this.filters.get(6).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-3

		this.filters.get(7).add(createMatrix(filterSize, filterSize));
		this.filters.get(7).add(createMatrix(filterSize, filterSize));
		this.filters.get(7).add(createMatrix(filterSize, filterSize));
		this.filters.get(7).add(createMatrix(filterSize, filterSize));

		this.filters.get(8).add(createMatrix(filterSize, filterSize));
		this.filters.get(8).add(createMatrix(filterSize, filterSize));
		this.filters.get(8).add(createMatrix(filterSize, filterSize));
		this.filters.get(8).add(createMatrix(filterSize, filterSize));

		this.filters.get(9).add(createMatrix(filterSize, filterSize));
		this.filters.get(9).add(createMatrix(filterSize, filterSize));
		this.filters.get(9).add(createMatrix(filterSize, filterSize));
		this.filters.get(9).add(createMatrix(filterSize, filterSize));

		this.filters.get(10).add(createMatrix(filterSize, filterSize));
		this.filters.get(10).add(createMatrix(filterSize, filterSize));
		this.filters.get(10).add(createMatrix(filterSize, filterSize));
		this.filters.get(10).add(createMatrix(filterSize, filterSize));

		this.filters.get(11).add(createMatrix(filterSize, filterSize));
		this.filters.get(11).add(createMatrix(filterSize, filterSize));
		this.filters.get(11).add(createMatrix(filterSize, filterSize));
		this.filters.get(11).add(createMatrix(filterSize, filterSize));

		this.filters.get(12).add(createMatrix(filterSize, filterSize));
		this.filters.get(12).add(createMatrix(filterSize, filterSize));
		this.filters.get(12).add(createMatrix(filterSize, filterSize));
		this.filters.get(12).add(createMatrix(filterSize, filterSize));

		this.filters.get(13).add(createMatrix(filterSize, filterSize));
		this.filters.get(13).add(createMatrix(filterSize, filterSize));
		this.filters.get(13).add(createMatrix(filterSize, filterSize));
		this.filters.get(13).add(createMatrix(filterSize, filterSize));

		this.filters.get(14).add(createMatrix(filterSize, filterSize));
		this.filters.get(14).add(createMatrix(filterSize, filterSize));
		this.filters.get(14).add(createMatrix(filterSize, filterSize));
		this.filters.get(14).add(createMatrix(filterSize, filterSize));

		// ... e assim por diante para os mapas de saída 7 a 14 ...

		// Mapa de saída 15: usa mapas de entrada 0, 1, 2, 3, 4, 5
		this.filters.get(15).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-0
		this.filters.get(15).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-1
		this.filters.get(15).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-2
		this.filters.get(15).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-3
		this.filters.get(15).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-4
		this.filters.get(15).add(createMatrix(filterSize, filterSize)); // Filtro para mapa de entrada S2-5

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
	public List<FloatMatrix> compute(List<FloatMatrix> input) {
		List<FloatMatrix> output = new ArrayList<>();
		if (filters.size() == 16) {
			for (int i = 0; i < 16; i++) {
				List<Integer> indicesEntrada = getIndicesEntradaC3(i);
				List<FloatMatrix> filtros = filters.get(i);
				List<FloatMatrix> entradas = new ArrayList<>();
				for (int index : indicesEntrada) {
					entradas.add(input.get(index));
				}
				FloatMatrix resultado = convolveWithMultipleFilters(entradas, filtros, filterSize, stride);
				output.add(resultado);
			}
		}
		return output;
	}

	@Override
	protected FloatMatrix createMatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}
}
