package models

import javafx.application.Application
import javafx.geometry.Insets
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import kotlin.random.Random

class NumeroMagico: Application() {
    /*  private var magicNum:Int
        private var tries:Int
        private var min=0
        private var max=0*/

    override fun start(firtsStage: Stage){
        var tries=5
        var min=0
        var max=100
        var magicNum=Random.nextInt(min,max)
        val txtNumber= TextField()
        val btnComprovation= Button("Check number")
        val lblResult= Label()


        val vbox= VBox(10.0)
        vbox.padding= Insets(20.0)
        vbox.children.addAll(
            Label("Introduce a number between ${min} and ${max}"),
            txtNumber,
            btnComprovation,
            lblResult
        )

        fun endGame(stage:Stage){
            val exitBtn = Button("Exit")
            val newGame = Button("New Game")
            exitBtn.setOnAction {
                stage.close()
            }
            newGame.setOnAction {
                // Reiniciar el juego
                magicNum = Random.nextInt(min, max)
                tries = 5
                lblResult.text = ""
                vbox.children.removeAll(exitBtn, newGame)
            }

            vbox.children.add(exitBtn)
            vbox.children.add(newGame)

        }

        fun compareNumbers(userNum:Int){
            if (userNum==magicNum){
                lblResult.text="YOU WON!\n ${magicNum}"
                endGame(firtsStage)

            }
            else{
                tries--
                if (tries==0){
                    lblResult.text="YOU LOST! \nNumber:${magicNum}\nTries: ${tries}"
                    endGame(firtsStage)
                }else{
                    if (userNum>magicNum)lblResult.text="The number is smaller\n Tries: ${tries}"
                    else lblResult.text="The number is bigger \n Tries: ${tries}"
                }
            }
        }


        btnComprovation.setOnAction {
            try {
                val userNum = txtNumber.text.toInt()
                compareNumbers(userNum)
            }catch (e:NumberFormatException){
                lblResult.text = "Please enter a valid number."
                println(e)
            }

        }

        val scene=Scene(vbox, 350.0, 300.0)
        firtsStage.title="Magic Number"
        firtsStage.scene = scene
        firtsStage.show()

    }
}