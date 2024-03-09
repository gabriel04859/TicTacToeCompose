package com.gabsa.tictactoecompose.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.gabsa.tictactoecompose.presenter.TicTacToeViewModel
import com.gabsa.tictactoecompose.presenter.theme.TicTacToeComposeTheme
import com.gabsa.tictactoecompose.presenter.ui.screens.TicTacToeButton

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: TicTacToeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TicTacToeViewModel::class.java]
        setContent {
            TicTacToeComposeTheme {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val items by viewModel.ticItems.collectAsState()
                        val currentPlayerTurn by viewModel.getCurrentPlayerTurnText()
                            .collectAsState()
                        val winner by viewModel.playerWinner.collectAsState()
                        val title = if (winner == null) {
                            if (currentPlayerTurn) "X turn" else "O turn"
                        } else {
                            "$winner is the winner"
                        }

                        Text(text = title)
                        LazyVerticalGrid(
                            modifier = Modifier.wrapContentSize(),
                            columns = GridCells.Fixed(3)
                        ) {
                            itemsIndexed(items) { index, _ ->
                                TicTacToeButton(index, viewModel)
                            }
                        }

                        Button(
                            modifier = Modifier.padding(24.dp),
                            onClick = {
                                viewModel.resetGame()
                            }) {
                            Text(text = "Reset game")
                        }
                    }
                }
            }
        }
    }
}
