package com.mygdx.game

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.{Gdx, Input, InputProcessor}

/**
  * Created by felipecosta on 10/14/17.
  */
class EventHandler(newGameEngine: GameEngine) {

    val gameEngine = newGameEngine
    val util = new Util()
    val careTaker = new CareTaker()
    var buttonClicked = false

    def listen(): Unit = {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            careTaker.append(gameEngine.save())
            gameEngine.nextGeneration()
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            gameEngine.auto = !gameEngine.auto
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(gameEngine.changeGenAuto < 1000) gameEngine.changeGenAuto += 30
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(gameEngine.changeGenAuto > 50) gameEngine.changeGenAuto -= 30
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            val newMemento = careTaker.prev()

            if(newMemento != null) { gameEngine.setState(newMemento) }
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            val i = Gdx.input.getX()/util.squareSizeW
            var j =  (util.SCREEN_HEIGHT-Gdx.input.getY())/util.squareSizeH
            gameEngine.makeCellAlive(i, j)
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            val i = Gdx.input.getX()/util.squareSizeW
            var j =  (util.SCREEN_HEIGHT-Gdx.input.getY())/util.squareSizeH
            gameEngine.killCell(i, j)
        }
    }

    def printRules(): Unit = {
        println("Instructions:")
        println("a - Automatico")
        println("Space - Proxima Geracao")
        println("Setas cima/baixo - Aumenta/Diminui velocidade automatico")
        println("Seta esquerda - Voltar para ger. anterior")
        println("Mouse Esquerdo - Revive Celula")
        println("Mouse Direito - Mata Celula")
    }
}
