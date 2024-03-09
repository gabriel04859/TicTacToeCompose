package com.gabsa.tictactoecompose.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabsa.tictactoecompose.data.TicTacToeItem
import com.gabsa.tictactoecompose.presenter.theme.OTurnColor
import com.gabsa.tictactoecompose.presenter.theme.XTurnColor
import com.gabsa.tictactoecompose.presenter.utils.Constants.O_PLAYER
import com.gabsa.tictactoecompose.presenter.utils.Constants.X_PLAYER
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicTacToeViewModel : ViewModel() {

    private val _playerWinner = MutableStateFlow<String?>(null)

    val playerWinner: StateFlow<String?> get() = _playerWinner

    val ticItems = MutableStateFlow(
        mutableListOf(
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
    )

    val shouldResetGame = MutableStateFlow(false)

    private val isXTurn = MutableStateFlow(true)

    fun markPlayerAction(positionButton: Int) {
        viewModelScope.launch {
            ticItems.value[positionButton].isXPlayer = isXTurn.value
            checkWinner()
            shouldResetGame.value = false
        }
    }

    fun getCurrentTurnText(): String {
        val currentTurnString = if (isXTurn.value) "X" else "O"
        isXTurn.value = !isXTurn.value
        return currentTurnString
    }

    fun getCurrentPlayerTurnText() = isXTurn

    private fun checkWinner() {
        val gameBoard = ticItems.value.map { it.isXPlayer }
        Log.d("checkWinner", "checkWinner: $gameBoard")
        getWinningCombinations().forEach { combination ->
            val firstItem = gameBoard[combination[0]]
            val secondItem = gameBoard[combination[1]]
            val thirdItem = gameBoard[combination[2]]

            if (firstItem != null && firstItem == secondItem && secondItem == thirdItem) {
                _playerWinner.value = if (firstItem == true) X_PLAYER else O_PLAYER
                return
            }
        }
    }

    fun getColorButton() = if (isXTurn.value) XTurnColor else OTurnColor

    private fun getWinningCombinations() = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    fun resetGame() {
        ticItems.value.map { it.isXPlayer = null }
        isXTurn.value = true
        _playerWinner.value = null
        shouldResetGame.value = true
    }
}
