package br.org.scsi.lenet5;

import java.io.IOException;
import java.util.List;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.layers.AbstractConvolutionLayer;
import br.org.scsi.lenet5.layers.AbstractFullyConnectedLayer;
import br.org.scsi.lenet5.layers.AbstractPoolingLayer;
import br.org.scsi.lenet5.layers.impl.LeC1ConvolutionLayer;
import br.org.scsi.lenet5.layers.impl.LeC3ConvolutionLayer;
import br.org.scsi.lenet5.layers.impl.LeC5ConvolutionLayer;
import br.org.scsi.lenet5.layers.impl.LeF6FullyConnectedLayer;
import br.org.scsi.lenet5.layers.impl.LeOutputLayer;
import br.org.scsi.lenet5.layers.impl.LePoolingLayer;
import br.org.scsi.lenet5.mnist.MnistLoader;

public class LeNetFive {

	private final List<FloatMatrix> train; // Conjunto de dados de treinamento
	private final int width; // Largura das imagens de entrada
	private final int height; // Altura das imagens de entrada

	private final AbstractConvolutionLayer<FloatMatrix> c1Layer;
	private final AbstractPoolingLayer<FloatMatrix> s2Layer;
	private final AbstractConvolutionLayer<FloatMatrix> c3Layer;
	private final AbstractPoolingLayer<FloatMatrix> s4Layer;
	private final AbstractConvolutionLayer<FloatMatrix> c5Layer;
	private final AbstractFullyConnectedLayer<FloatMatrix> f6Layer;
	private final AbstractFullyConnectedLayer<FloatMatrix> output;

	public LeNetFive(List<FloatMatrix> train) {
		this.train = train;
		final FloatMatrix f = train.getFirst();
		this.width = f.getWidth();
		this.height = f.getHeight();
		if (train.parallelStream().anyMatch(img -> this.width != img.getWidth() || this.height != img.getHeight())) {
			throw new RuntimeException("The image sizes does not match!");
		}
		// Inicialização das camadas
		c1Layer = new LeC1ConvolutionLayer();

		s2Layer = new LePoolingLayer(2, 2);

		c3Layer = new LeC3ConvolutionLayer();

		s4Layer = new LePoolingLayer(2, 2);

		c5Layer = new LeC5ConvolutionLayer();
		f6Layer = new LeF6FullyConnectedLayer(1, 1);

		output = new LeOutputLayer(1, 1);

	}

	public FloatMatrix forward(FloatMatrix input) {
		FloatMatrix[] inputs = new FloatMatrix[1];
		inputs[0] = input;
		// Camada C1
		FloatMatrix[] c1Output = c1Layer.forward(inputs);

		// Camada S2 (Pooling) - agora recebe um array de IMatrix
		FloatMatrix[] s2Output = s2Layer.forward(c1Output);

		// Camada C3
		FloatMatrix[] c3Output = c3Layer.forward(s2Output);

		// Camada S4 (Pooling) - agora recebe um array de IMatrix
		FloatMatrix[] s4Output = s4Layer.forward(c3Output);

		// Camada C5 (Convolução como uma camada totalmente conectada)
		FloatMatrix c5Output = c5Layer.forward(s4Output)[0];

		// Camada F6 (Totalmente Conectada)

		// Achata (flatten) a matriz c5Output para um vetor
		FloatMatrix flattenedC5Output = new FloatMatrix(c5Output.getHeight() * c5Output.getWidth(), 1);
		int index = 0;
		for (int i = 0; i < c5Output.getHeight(); i++) {
			for (int j = 0; j < c5Output.getWidth(); j++) {
				flattenedC5Output.set(index++, 0, c5Output.get(i, j));
			}
		}

		// Camada F6 (Totalmente Conectada) - agora recebe o vetor flattenedC5Output
		FloatMatrix f6Output = f6Layer.forward(flattenedC5Output);

		// Camada de Saída
		FloatMatrix outputResult = output.forward(f6Output);

		return outputResult;
	}

	public static void main(String... args) {
		try {
			List<FloatMatrix> list = new MnistLoader().getImagesTrain();

			long l = System.currentTimeMillis();
			new LeNetFive(list);
			System.out.println(System.currentTimeMillis() - l);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
