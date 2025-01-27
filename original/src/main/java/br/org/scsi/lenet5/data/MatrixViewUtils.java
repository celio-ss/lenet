package br.org.scsi.lenet5.data;

public final class MatrixViewUtils {
	private MatrixViewUtils() {
		
	}
	public static String toString(IMatrix<?> matrix) {
		StringBuilder sb = new StringBuilder();
		
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				char c = floatToChar(matrix.get(x, y)); // Método para converter float para char
				sb.append(c);
			}
			sb.append("\n"); // Nova linha no final de cada linha da matriz
		}
		return sb.toString();
	}

	private static char floatToChar(float value) {
		String chars = " .:-=+*#%@"; // 10 caracteres
		int index = (int) (value * chars.length());
		// Garantir que o índice esteja dentro dos limites
		index = Math.max(0, Math.min(index, chars.length() - 1));

		return chars.charAt(index);
	}
}
