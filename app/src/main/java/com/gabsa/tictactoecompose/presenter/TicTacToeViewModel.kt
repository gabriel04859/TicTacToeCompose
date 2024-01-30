package com.gabsa.tictactoecompose.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gabsa.tictactoecompose.data.TicTacToeItem
import com.gabsa.tictactoecompose.data.TicTacToeState
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

    private val _state = mutableStateOf(TicTacToeState())
    val state: State<TicTacToeState> get() = _state

    private val _victorious = mutableStateOf(EMPTY)
    val victorious get() = _victorious

    fun markPlayerAction(positionButton: Int) {
        ticTacToeItems[positionButton].isXPlayer = isXTurn
    }

    fun getCurrentPlayer(): String {
        return if (_state.value.isXTurn) X_PLAYER else O_PLAYER
    }

    fun checkWinner(): String {
        val winningCombinations = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )

        for (combination in winningCombinations) {
            val symbols = combination.map { mapPlayerActionsToString()[it] }
            if (symbols.all { it == X_PLAYER }) {
                return X_PLAYER
            } else if (symbols.all { it == O_PLAYER }) {
                return O_PLAYER
            }
        }
        return EMPTY
    }

    private fun mapPlayerActionsToString(): List<String> {
        return  ticTacToeItems.map { if (it.isXPlayer == null) EMPTY else if (isXTurn) X_PLAYER else O_PLAYER }
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