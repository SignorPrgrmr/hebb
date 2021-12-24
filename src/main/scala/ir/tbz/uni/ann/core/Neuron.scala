package ir.tbz.uni.ann.core

trait Neuron:

	def sendSignal(netInput: Int): Unit

	def train(data: Map[NeuralNetworkInput, (Int, Int)]): Unit




