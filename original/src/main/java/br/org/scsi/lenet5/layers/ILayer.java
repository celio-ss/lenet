package br.org.scsi.lenet5.layers;

import br.org.scsi.lenet5.data.IMatrix;

public interface ILayer<T extends IMatrix<T>> {

	T[] forward(T[] in);

	default T forward(T in) {
		T[] tmp = createMatrixArray(1);
		tmp[0] = in;
		return forward(tmp)[0];
	}

	T createMatrix(int width, int height);

	T[] createMatrixArray(int size);

}
