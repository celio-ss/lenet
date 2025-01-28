package br.org.scsi.lenet5.data;

/**
 * 
 */
public class FloatMatrix implements IMatrix<FloatMatrix> {
	private final String value;
	private final float[] data;
	private final int width;
	private final int height;

	private FloatMatrix(float[] data, int width, int height) {
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

	@Override
	public String toString() {
		return MatrixViewUtils.toString(this);
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

	@Override
	public FloatMatrix newInstance(int width, int height) {
		return new FloatMatrix(width, height);
	}

	@Override
	public float get(int i) {
		return this.data[i];
	}

	@Override
	public void set(int i, float value) {
		this.data[i] = value;

	}

	@Override
	public FloatMatrix copy() {
		final float[] data2 = new float[data.length];
		for (int i = 0; i < data2.length; i++) {
			data2[i] = data[i];
		}
		return new FloatMatrix(data2, getWidth(), getHeight());
	}

}
