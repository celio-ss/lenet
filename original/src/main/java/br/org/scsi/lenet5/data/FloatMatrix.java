package br.org.scsi.lenet5.data;

/**
 * 
 */
public class FloatMatrix {
	private final String value;
	final float[] data;
	private final int width;
	private final int height;

	public FloatMatrix(float[] data, int width, int height) {
		this.data = data;
		this.height = height;
		this.width = width;
		this.value = "";
	}

	public FloatMatrix(int width, int height) {
		this.data = new float[width * height];
		this.height = height;
		this.width = width;
		this.value = "";
	}

	public FloatMatrix(String csvLine) {
		final String[] csvArr = csvLine.split(",");
		this.value = csvArr[0];
		final int imageArrLength = csvArr.length - 1;
		this.width = (int) Math.sqrt(imageArrLength);
		this.height = this.width;
		this.data = new float[imageArrLength];
		for (int i = 0; i < data.length; i++) {
			data[i] = Integer.parseInt(csvArr[i + 1]) / 255f;
		}
	}

	public float get(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new IndexOutOfBoundsException("Índices fora dos limites");
		}
		int index = x + y * width;
		return data[index];
	}

	public void set(int x, int y, float value) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new IndexOutOfBoundsException("Índices fora dos limites");
		}
		int index = x + y * width;
		data[index] = value;
	}

	public void add(FloatMatrix other) {
		if (this.width != other.width || this.height != other.height) {
			throw new IllegalArgumentException("Matrix dimensions must match");
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int index = x + y * width;
				this.data[index] += other.get(x, y);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				char c = floatToChar(get(x, y)); // Método para converter float para char
				sb.append(c);
			}
			sb.append("\n"); // Nova linha no final de cada linha da matriz
		}
		return sb.toString();
	}

	private char floatToChar(float value) {
		String chars = " .:-=+*#%@"; // 10 caracteres
		int index = (int) (value * chars.length());
		// Garantir que o índice esteja dentro dos limites
		index = Math.max(0, Math.min(index, chars.length() - 1));

		return chars.charAt(index);
	}

	public String getValue() {
		return value;
	}

	public float[] getData() {
		return data;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
