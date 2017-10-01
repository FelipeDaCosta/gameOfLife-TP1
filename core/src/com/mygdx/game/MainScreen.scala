package com.mygdx.game

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.{Gdx, Input, InputProcessor, Screen}

/**
  * Created by felipecosta on 10/1/17.
  *
  */
class MainScreen(screenGame: MyGdxGame) extends GameEngine with Screen {

    val util = new Util()
    val screenW = util.WIDTH
    val screenH = util.HEIGHT

    var height: Int = 20
    var width: Int = 20

    var cells: Array[Array[Cell]] = Array.ofDim[Cell](height, width)
    for(i <- 0 until height) {
        for(j <- 0 until width) {
            cells(i)(j) = new Cell()
        }
    }

    val squareSizeW: Int = screenW/width
    val squareSizeH: Int = screenH/height
    2

    private val game = screenGame
    private val camera = new OrthographicCamera()
    camera.setToOrtho(false, screenW, screenH)

    override def hide(): Unit = {}

    override def resize(width: Int, height: Int): Unit = {}

    override def dispose(): Unit = {}

    override def pause(): Unit = {}

    override def render(delta: Float): Unit = {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            makeCellAlive(Gdx.input.getX()/squareSizeW, (screenH-Gdx.input.getY())/squareSizeH)
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.nextGeneration()
        }


        Gdx.gl.glClearColor(0.6f, 0.8f, 1, 1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        game.shape.setProjectionMatrix(camera.combined)

        game.shape.begin(ShapeType.Line)
        game.shape.setColor(0, 0, 0, 1)
        for(i <- 0 until height) {
            for(j <- 0 until width) {
                if(!isCellAlive(i, j)) {
                    game.shape.rect(i * squareSizeW, j * squareSizeH, squareSizeW, squareSizeH)
                }
            }
        }
        game.shape.end()

        game.shape.begin(ShapeType.Filled)
        game.shape.setColor(0, 0, 0, 1)
        for(i <- 0 until height) {
            for(j <- 0 until width) {
                if(isCellAlive(i, j)) {
                    game.shape.rect(i * squareSizeW, j * squareSizeH, squareSizeW, squareSizeH)
                }
            }
        }
        game.shape.end()
    }

    override def show(): Unit = {}

    override def resume(): Unit = {}

    override def shouldKeepAlive(i: Int, j: Int): Boolean = {
        cells(i)(j).isAlive &&
            (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
    }

    override def shouldRevive(i: Int, j: Int): Boolean = {
        (!cells(i)(j).isAlive) &&
            (numberOfNeighborhoodAliveCells(i, j) == 3)
    }

}
