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
			set(i, get(i) + other.get(i));

		}
		return fm;
	}

	default T subtract(T other) {
		if (getWidth() != other.getWidth() || getHeight() != other.getHeight()) {
			throw new IllegalArgumentException("Matrix dimensions must match");
		}
		final T fm = newInstance(getWidth(), getHeight());
		for (int i = 0; i < getLength(); i++) {
			set(i, get(i) - other.get(i));

		}
		return fm;
	}

	T multiply(T other);

	T multiply(float scalar);

	T transpose();

	int getRows();

	int getCols();

	int getWidth();

	int getLength();

	int getHeight();

}
