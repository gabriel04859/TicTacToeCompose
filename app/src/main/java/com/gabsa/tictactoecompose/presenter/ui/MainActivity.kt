package com.gabsa.tictactoecompose.presenter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gabsa.tictactoecompose.presenter.TicTacToeViewModel
import com.gabsa.tictactoecompose.presenter.theme.TicTacToeComposeTheme
import com.gabsa.tictactoecompose.presenter.ui.screens.TicTacToeButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicTacToeComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel = TicTacToeViewModel()

                    LazyVerticalGrid(
                        modifier = Modifier.wrapContentSize(),
                        columns = GridCells.Fixed(3)
                    ) {
                        itemsIndexed(viewModel.provideTicTacToeItems()) { index, _ ->
                            TicTacToeButton(index, viewModel) {
                                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}
