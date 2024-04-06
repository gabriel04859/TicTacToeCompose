package com.gabsa.tictactoecompose.presenter

import androidx.lifecycle.ViewModel
import com.gabsa.tictactoecompose.data.TicTacToeItem
import com.gabsa.tictactoecompose.presenter.theme.OTurnColor
import com.gabsa.tictactoecompose.presenter.theme.XTurnColor
import com.gabsa.tictactoecompose.presenter.utils.Constants.EMPTY
import com.gabsa.tictactoecompose.presenter.utils.Constants.O_PLAYER
import com.gabsa.tictactoecompose.presenter.utils.Constants.X_PLAYER

class TicTacToeViewModel : ViewModel() {

    private val ticTacToeItems = mutableListOf(
        TicTacToeItem(),
        TicTacToeItem(),
        TicTacToeItem(),
        TicTacToeItem(),
        TicTacToeItem(),
        TicTacToeItem(),
        TicTacToeItem(),
        TicTacToeItem(),
        TicTacToeItem()
    )

    private var isXTurn = true

    fun markPlayerAction(positionButton: Int) {
        ticTacToeItems[positionButton].isXPlayer = isXTurn
    }

    fun checkWinner(): String {
        val winningCombinations = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )

        for (combination in winningCombinations) {
            val symbols = combination.map { ticTacToeItems[it] }
            if (symbols.all { it.isXPlayer == true }) {
                return X_PLAYER
            } else if (symbols.all { it.isXPlayer == false }) {
                return O_PLAYER
            }
        }
        return EMPTY
    }

    fun provideTicTacToeItems(): MutableList<TicTacToeItem> {
        return ticTacToeItems
    }

    fun setCurrentTurn() {
        isXTurn = !isXTurn
    }

    fun getCurrentTurnText() = if (isXTurn) X_PLAYER else O_PLAYER

    fun getColorButton() =  if (isXTurn) XTurnColor else OTurnColor
}