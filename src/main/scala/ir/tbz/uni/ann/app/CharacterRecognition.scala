package ir.tbz.uni.ann.app

import ir.tbz.uni.ann.core.{Bias, InputNeuron, NeuralNetworkInput, OutputNeuron}
import ir.tbz.uni.ann.sample.data.CharacterSampleData
import javafx.beans.property.ReadOnlyDoubleProperty
import scalafx.application.JFXApp3
import scalafx.beans.property.DoubleProperty
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{AnchorPane, Background, BackgroundFill, GridPane}
import scalafx.scene.paint.Color.*
import scalafx.scene.paint.*
import scalafx.scene.shape.{Line, Rectangle}
import scalafx.scene.Node

import scala.collection.mutable

object CharacterRecognition extends JFXApp3 {

	private val data = Array[Array[Boolean]](
		Array[Boolean](false, false, false, false, false),
		Array[Boolean](false, false, false, false, false),
		Array[Boolean](false, false, false, false, false),
		Array[Boolean](false, false, false, false, false),
		Array[Boolean](false, false, false, false, false)
	)

	override def start(): Unit =
		stage = new JFXApp3.PrimaryStage {
			title = "ScalaFX hello World"
			scene = new Scene {
					content = Seq(new AnchorPane {
						val on: Color = rgb(220, 220, 220)
						val off: Color = rgb(160, 160, 160)
						val cells: Seq[Node] =
							for
								i <- 0 until 5
								j <- 0 until 5
							yield
								new Rectangle {
									layoutX = 23 + i * 91
									layoutY = 23 + j * 91
									width = 90
									height = 90
									fill = off
									onMouseClicked = { event =>
										if data(i)(j) then {
											fill = off
											data(i)(j) = false
										} else {
											fill = on
											data(i)(j) = true
										}
									}
								}

						children = cells
						prefWidth = 500
						prefHeight = 500
					},
					new Button {
						text = "Check"
						onMouseClicked = { _ =>
							for
								i <- range
								j <- range
							do
								inputNeurons(i)(j).put(if data(i)(j) then 1 else -1)
							bias.sendSignal()
							println("output: " + (if OutputNeuron.getOutput(outputNeuron.neuron) == 1 then "X" else "O"))
						}
						layoutX = 420
						layoutY = 500
					}
				)
			}
			width = 500
			height = 600
		}

	private val outputNeuron = OutputNeuron(26)
	private val inputNeurons = new Array[Array[NeuralNetworkInput]](5)
	for
		i <- 0 until 5
	do
		val neurons = new Array[NeuralNetworkInput](5)
		for
			j <- 0 until 5
		do
			neurons(j) = InputNeuron(mutable.Map(outputNeuron -> 0))
		inputNeurons(i) = neurons

	private val bias = Bias(mutable.Map(outputNeuron -> 0))

	private val range = 0 until 5

	for
		CharacterSampleData(character, target) <- CharacterSampleData.sampleData
	do
		for
			i <- range
			j <- range
		do
			inputNeurons(i)(j).neuron.train(Map(outputNeuron -> (character(i)(j), target)))
		bias.train(Map(outputNeuron -> target))

	println("It's trained")
}
