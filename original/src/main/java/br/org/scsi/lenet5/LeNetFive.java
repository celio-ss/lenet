package br.org.scsi.lenet5;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import br.org.scsi.lenet5.data.FloatMatrix;
import br.org.scsi.lenet5.data.IMatrix;
import br.org.scsi.lenet5.layers.AbstractPoolingLayer;
import br.org.scsi.lenet5.layers.impl.LeC1ConvolutionLayer;
import br.org.scsi.lenet5.layers.impl.LeC3ConvolutionLayer;
import br.org.scsi.lenet5.layers.impl.LeC5ConvolutionLayer;
import br.org.scsi.lenet5.layers.impl.LePoolingLayer;
import br.org.scsi.lenet5.mnist.MnistLoader;

public class LeNetFive {

	private final List<FloatMatrix> train; // Conjunto de dados de treinamento
	private final int width; // Largura das imagens de entrada
	private final int height; // Altura das imagens de entrada

	private final LeC1ConvolutionLayer c1Layer;
	private final AbstractPoolingLayer<FloatMatrix> s2Layer;
	private final LeC3ConvolutionLayer c3Layer;
	private final AbstractPoolingLayer<FloatMatrix> s4Layer;
	private final LeC5ConvolutionLayer c5Layer;

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

	}

	public void train() {
		train.forEach(this::compute);
	}

	private void compute(FloatMatrix image) {
		System.out.println(image);
		// Camada C1: convolução
		List<FloatMatrix> c1Output = c1Layer.compute(Collections.singletonList(image));
		c1Output.stream().forEach(System.out::println);
		// Camada S2: pooling
		List<FloatMatrix> s2Output = s2Layer.compute(c1Output);
		c1Output.stream().forEach(System.out::println);

		// Camada C3: convolução
		List<FloatMatrix> c3Output = c3Layer.compute(s2Output);

		// Camada S4: pooling
		List<FloatMatrix> s4Output = s4Layer.compute(c3Output);

		// Camada C5: convolução
		List<FloatMatrix> c5Output = c5Layer.compute(s4Output);

		// Camada F6: totalmente conectada
//        List<T> f6Output = f6Layer.compute(c5Output);

		// Camada de Saída
//        List<T> output = outputLayer.compute(f6Output);

		// ... (usar a saída da rede para calcular o erro e treinar) ...
	}

	public static void main(String... args) {
		try {
			List<FloatMatrix> list = new MnistLoader().getImagesTrain();

			long l = System.currentTimeMillis();
			new LeNetFive(list).train();
			System.out.println(System.currentTimeMillis() - l);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
