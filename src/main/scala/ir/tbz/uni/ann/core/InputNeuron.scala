package ir.tbz.uni.ann.core

import scala.collection.mutable

class InputNeuron(private val nextLayer: mutable.Map[NeuralNetworkInput, Int]) extends Neuron :

	override def sendSignal(netInput: Int): Unit =
		for (nni, w) <- nextLayer do nni put netInput * w

	override def train(data: Map[NeuralNetworkInput, (Int, Int)]): Unit =
		for nni <- data.keys do
			val (x, y) = data(nni)
			nextLayer get nni match {
				case None => throw RuntimeException("Invalid nni")
				case Some(weight) =>
					nextLayer += (nni -> (weight + x * y))
			}


object InputNeuron:

	def apply(nextLayer: mutable.Map[NeuralNetworkInput, Int]): NeuralNetworkInput =
		val neuron = new InputNeuron(nextLayer)
		NeuralNetworkInput(neuron, 1)