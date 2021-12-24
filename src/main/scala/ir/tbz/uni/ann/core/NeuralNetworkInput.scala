package ir.tbz.uni.ann.core

import scala.collection.mutable
import scala.language.postfixOps

case class NeuralNetworkInput(neuron: Neuron, private val inputCount: Int):

	private val inputList = mutable.ListBuffer[Int]()

	def put(input: Int): Unit =
		inputList += input
		if inputList.size == inputCount then
			neuron sendSignal inputList.sum
			inputList clear
