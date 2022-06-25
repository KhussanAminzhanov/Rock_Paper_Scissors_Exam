package com.example.rockpaperscissors

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    enum class GameState {
        PC_WON, USER_WON, DRAW, SELECT_OBJECT, OBJECT_SELECTED
    }

    var userObjectName: String = ""
    var pcObjectName: String = ""
    val gameState = MutableLiveData(GameState.SELECT_OBJECT)
    val userObject = MutableLiveData<Drawable>()
    val pcObject = MutableLiveData<Drawable>()

    fun fight(context: Context) {
        val randomObject = getRandomObject(context) ?: return
        pcObject.value = randomObject
        setGameState()
    }

    private fun getRandomObject(context: Context): Drawable? {
        val drawableId = when ((0..2).shuffled().random()) {
            0 -> {
                pcObjectName = "Rock"
                R.drawable.im_rock
            }
            1 -> {
                pcObjectName = "Scissor"
                R.drawable.im_scissor
            }
            2 -> {
                pcObjectName = "Paper"
                R.drawable.im_paper
            }
            else -> {
                pcObjectName = "Question"
                R.drawable.im_question
            }
        }
        return context.getDrawable(drawableId)
    }

    private fun setGameState() {
        when {
            pcObjectName == "Rock" && userObjectName == "Scissor" -> {
                gameState.value = GameState.PC_WON
            }
            pcObjectName == "Paper" && userObjectName == "Rock" -> {
                gameState.value = GameState.PC_WON
            }
            pcObjectName == "Scissor" && userObjectName == "Paper" -> {
                gameState.value = GameState.PC_WON
            }
            pcObjectName == userObjectName -> {
                gameState.value = GameState.DRAW
            }
            else -> gameState.value = GameState.USER_WON
        }
//        if (pcObjectName == "Rock" && userObjectName == "Scissor") gameState.value = GameState.PC_WON
//        if (pcObjectName == "Paper" && userObjectName == "Rock") gameState.value = GameState.PC_WON
//        if (pcObjectName == "Scissor" && userObjectName == "Paper") gameState.value = GameState.PC_WON
//        if (pcObjectName == userObjectName) gameState.value = GameState.DRAW
    }
}