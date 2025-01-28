package br.org.scsi.lenet5.data;

public interface IMatrix<T extends IMatrix<T>> {
	T newInstance(int width, int height);

	float get(int i);

	void set(int i, float value);

	default float get(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IndexOutOfBoundsException("Índices fora dos limites");
		}
		int index = x + y * getWidth();
		return get(index);
	}

	default void set(int x, int y, float value) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IndexOutOfBoundsException("Índices fora dos limites");
		}
		int index = x + y * getWidth();
		set(index, value);
	}

	default T add(T other) {
		if (getWidth() != other.getWidth() || getHeight() != other.getHeight()) {
			throw new IllegalArgumentException("Matrix dimensions must match");
		}
		final T fm = newInstance(getWidth(), getHeight());
		for (int i = 0; i < getLength(); i++) {
			fm.set(i, get(i) + other.get(i));

		}
		return fm;
	}

	default T subtract(T other) {
		if (getWidth() != other.getWidth() || getHeight() != other.getHeight()) {
			throw new IllegalArgumentException("Matrix dimensions must match");
		}
		final T fm = newInstance(getWidth(), getHeight());
		for (int i = 0; i < getLength(); i++) {
			fm.set(i, get(i) - other.get(i));

		}
		return fm;
	}

	default T multiply(T otherMatrix) {

		if (this.getWidth() != otherMatrix.getHeight()) {
			throw new IllegalArgumentException("Dimensões incompatíveis para multiplicação de matrizes.");
		}
		final T result = newInstance(this.getHeight(), otherMatrix.getWidth());

		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < otherMatrix.getWidth(); j++) {
				for (int k = 0; k < this.getWidth(); k++) {
					result.set(i, j, result.get(i, j) + this.get(i, k) * otherMatrix.get(k, j));
				}
			}
		}

		return result;
	}

	default T multiply(float scalar) {
		final T result = newInstance(this.getHeight(), this.getWidth());
		for (int i = 0; i < this.getHeight(); i++) {
			for (int k = 0; k < this.getWidth(); k++) {
				result.set(i, k, result.get(i, k) + this.get(i, k) * scalar);
			}
		}
		return result;
	}

	default T transpose() {
		final T result = newInstance(this.getHeight(), this.getWidth());
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				result.set(j, i, this.get(i, j));
			}
		}
		return result;
	}

	int getWidth();

	int getHeight();

	default int getLength() {
		return getWidth() * getHeight();
	}

	T copy();

}
