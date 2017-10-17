package com.mygdx.game

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.{Gdx, Screen}

/**
  * Created by felipecosta on 10/1/17.
  *
  */
class MainScreen(screenGame: MyGdxGame, newGameEngine: GameEngine, newEventHandler: EventHandler) extends Screen {

    val util = new Util()

    var gameEngine = newGameEngine

    val eventHandler = newEventHandler

    private val game = screenGame
    private val camera = new OrthographicCamera()
    camera.setToOrtho(false, util.SCREEN_WIDTH, util.SCREEN_HEIGHT)

    override def hide(): Unit = {}

    override def resize(width: Int, height: Int): Unit = {}

    override def dispose(): Unit = {}

    override def pause(): Unit = {}

    override def render(delta: Float): Unit = {

        eventHandler.listen()


        Gdx.gl.glClearColor(0.6f, 0.8f, 1, 1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        game.shape.setProjectionMatrix(camera.combined)

        if(gameEngine.auto) {
            gameEngine.count += 1
            if(gameEngine.count >= gameEngine.changeGenAuto) {
                gameEngine.nextGeneration()
                gameEngine.count = 0
            }
        }

        game.shape.begin(ShapeType.Line)
        game.shape.setColor(0, 0, 0, 1)
        for(i <- 0 until gameEngine.height) {
            for(j <- 0 until gameEngine.width) {
                if(!gameEngine.isCellAlive(i, j)) {
                    game.shape.rect(i * util.squareSizeW, j * util.squareSizeH, util.squareSizeW, util.squareSizeH)
                }
            }
        }
        game.shape.end()

        game.shape.begin(ShapeType.Filled)
        game.shape.setColor(0, 0, 0, 1)
        for(i <- 0 until gameEngine.height) {
            for(j <- 0 until gameEngine.width) {
                if(gameEngine.isCellAlive(i, j)) {
                    game.shape.rect(i * util.squareSizeW, j * util.squareSizeH, util.squareSizeW, util.squareSizeH)
                }
            }
        }
        game.shape.end()
    }

    override def show(): Unit = {}

    override def resume(): Unit = {}
}
