package kang.mingu.lemonadelab

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kang.mingu.lemonadelab.ui.theme.LemonadeLabTheme
import kang.mingu.lemonadelab.ui.theme.Sky

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    LemonadeLabTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Lemonade()
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(0) }
    var squeezeCount by remember { mutableStateOf(0) }
    val resourceResult = result % 4

    val stringResult = when (resourceResult) {
        0 -> R.string.lemon_tree
        1 -> R.string.lemon
        2 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    val imageResult = when (resourceResult) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = stringResult),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(18.dp))
        Image(
            painter = painterResource(id = imageResult),
            contentDescription = "",
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Sky,
                    shape = RoundedCornerShape(8.dp)
                )
                .wrapContentSize()
                .clickable {
                    when(resourceResult) {
                        0 -> {
                            squeezeCount = (2..4).random()
                            result++
                        }
                        1 -> {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                result++
                            }
                        }
                        else -> {
                            result++
                        }
                    }
                }
        )
    }
}