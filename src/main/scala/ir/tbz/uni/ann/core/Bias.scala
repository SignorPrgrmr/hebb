package ir.tbz.uni.ann.core

import scala.collection.mutable

class Bias(private val nextLayer: mutable.Map[NeuralNetworkInput, Int]):

	def sendSignal(): Unit =
		for (nni, b) <- nextLayer do nni put b

	def train(data: Map[NeuralNetworkInput, Int]): Unit =
		for nni <- data.keys do
			val y = data(nni)
			nextLayer get nni match {
				case None => throw RuntimeException("Invalid network input")
				case Some(b) => nextLayer += (nni -> (b + y))
			}

