package ir.tbz.uni.ann.core

class OutputNeuron extends Neuron :

	private var output: Option[Int] = None

	override def sendSignal(netInput: Int): Unit =
		output = Some(activationFunction(netInput))

	private def activationFunction(input: Int): Int =
		if input < 0 then -1 else 1

	override def train(data: Map[NeuralNetworkInput, (Int, Int)]): Unit = ()

object OutputNeuron:
	def getOutput(neuron: Neuron): Int =
		neuron match {
			case neuron: OutputNeuron =>
				neuron.output match {
					case None => throw RuntimeException("There is no output")
					case Some(output) => output
				}

			case _ => throw RuntimeException("Invalid neuron: Neuron must be an OutputNeuron")
		}

	def apply(inputCount: Int): NeuralNetworkInput =
		val neuron = new OutputNeuron
		NeuralNetworkInput(neuron, inputCount)