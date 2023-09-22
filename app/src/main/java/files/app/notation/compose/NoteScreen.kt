package files.app.notation.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import files.app.notation.ui.theme.customBlackOne
import files.app.notation.ui.theme.customBlackTwo
import files.app.notation.ui.theme.customTextColor
import files.app.notation.ui.theme.customYellow

@Preview
@Composable
fun PreviewTextView() {
    TextView(rememberNavController(), listOf(""))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextView(navController: NavController, settings: List<String>) {

    val showMenuState = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Title",
                        color = customTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = customBlackTwo
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Exit",
                            tint = customYellow,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenuState.value = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Settings",
                            tint = customYellow,
                            modifier = Modifier.size(30.dp)
                        )
                        CustomDropDownMenu(showMenuState, settings)
                    }
                },
            )
        },
        containerColor = customBlackOne
    ) {
        CustomTextField(it)
    }
}

@Composable
fun CustomTextField(paddingValues: PaddingValues) {
    val textFlow = remember {
        mutableStateOf(
            TextFieldValue("Content")
        )
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        TextField(
            value = textFlow.value,
            onValueChange = { newText ->
                textFlow.value = newText
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = customTextColor,
                unfocusedTextColor = customTextColor,
                cursorColor = customYellow
            ),
            textStyle = TextStyle(
                textDecoration = TextDecoration.None,
                fontSize = 15.sp
            ),
        )
        Spacer(modifier = Modifier.height(500.dp))
    }
}