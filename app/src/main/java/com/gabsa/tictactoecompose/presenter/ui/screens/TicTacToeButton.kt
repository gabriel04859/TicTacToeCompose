package com.gabsa.tictactoecompose.presenter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabsa.tictactoecompose.presenter.TicTacToeViewModel
import com.gabsa.tictactoecompose.presenter.theme.Purple40
import com.gabsa.tictactoecompose.presenter.utils.Constants.EMPTY

@Composable
fun TicTacToeButton(
    position: Int,
    viewModel: TicTacToeViewModel = TicTacToeViewModel()
) {
    var text by remember {
        mutableStateOf(EMPTY)
    }

    var enable by remember {
        mutableStateOf(true)
    }

    var bgColor by remember {
        mutableStateOf(Purple40)
    }
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 16.dp)
    ) {
        Button(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 4.dp, end = 4.dp),
            onClick = {
                viewModel.markPlayerAction(position)
                text = viewModel.getCurrentTurnText()
                bgColor = viewModel.getColorButton()
                enable = false
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                contentColor = Color.White,
                disabledContainerColor = bgColor,
                disabledContentColor = Color.Black
            ),
            enabled = enable
        ) {
            Text(text = text, fontSize = 50.sp)
        }
    }
}

@Composable
@Preview
fun TicTacToeButtonPreview() {
    TicTacToeButton(0)
}