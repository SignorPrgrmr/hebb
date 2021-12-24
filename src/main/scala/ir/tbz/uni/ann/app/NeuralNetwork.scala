package ir.tbz.uni.ann.app

import ir.tbz.uni.ann.core.{Bias, InputNeuron, OutputNeuron}
import ir.tbz.uni.ann.sample.data.Data

import scala.annotation.tailrec
import scala.collection.mutable

object NeuralNetwork extends App {

	val outputNeuron = OutputNeuron(3)
	val inputNeuron1 = InputNeuron(mutable.Map(outputNeuron -> 0))
	val inputNeuron2 = InputNeuron(mutable.Map(outputNeuron -> 0))
	val bias = Bias(mutable.Map(outputNeuron -> 1))

	val data = Data.orSampleData
	for sampleData <- data do
		inputNeuron1.neuron.train(Map(outputNeuron -> (sampleData.x1, sampleData.y)))
		inputNeuron2.neuron.train(Map(outputNeuron -> (sampleData.x2, sampleData.y)))
		bias.train(Map(outputNeuron -> sampleData.y))

	println("Neural network is trained.")
	println("Put some data and enjoy!!")

	@tailrec
	def readInput(text: String): String =
		import scala.io.StdIn.readLine
		print(text)
		val input = readLine
		if input.equals("1") || input.equals("-1") then input
		else
			println("Invalid input!")
			readInput(text)

	@tailrec
	def interactWithUser(): Unit =
		inputNeuron1 put readInput("x1: ").toInt
		inputNeuron2 put readInput("x2: ").toInt
		bias.sendSignal()
		println("output: " + (OutputNeuron getOutput outputNeuron.neuron))
		interactWithUser()

	interactWithUser()
}
