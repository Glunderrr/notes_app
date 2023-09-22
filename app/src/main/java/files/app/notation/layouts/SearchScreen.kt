package files.app.notation.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import files.app.notation.R
import files.app.notation.ui.theme.customBlackOne
import files.app.notation.ui.theme.customBlackTwo
import files.app.notation.ui.theme.customTextColor
import files.app.notation.ui.theme.customYellow

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController())
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController) {
    val flowTextValue = remember { mutableStateOf("") }
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        Modifier
            .fillMaxSize()
            .background(customBlackOne)
            .padding(vertical = 20.dp, horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    tint = customYellow,
                    modifier = Modifier.size(25.dp)
                )
            }
            TextField(
                value = flowTextValue.value,
                onValueChange = { flowTextValue.value = it },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = customBlackTwo,
                    unfocusedContainerColor = customBlackTwo,
                    cursorColor = customYellow,
                    focusedTextColor = customTextColor,
                    unfocusedTextColor = customTextColor
                ),
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { flowTextValue.value = "" }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_clear_24),
                            contentDescription = "cross delete",
                            tint = customYellow
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    capitalization = KeyboardCapitalization.Words,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { keyboard?.hide() }
                ),
                placeholder = { Text(text = "Search for a note") },
            )
        }
        Text(
            "Last searches:",
            Modifier
                .padding(vertical = 10.dp, horizontal = 5.dp)
                .fillMaxWidth(),
            color = customTextColor,
            fontSize = 18.sp
        )
        LazyColumn(content = {
            items(20) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            flowTextValue.value = it.toString()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        it.toString(),
                        color = customTextColor,
                        textDecoration = TextDecoration.Underline
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                            tint = customYellow
                        )
                    }
                }
            }
        }, modifier = Modifier.fillMaxSize())
    }
}