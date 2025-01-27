package br.org.scsi.lenet5.data;

/**
 * 
 */
public class FloatMatrix implements IMatrix<FloatMatrix> {
	private final String value;
	final float[] data;
	private final int width;
	private final int height;

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

}
