package files.app.notation.layouts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import files.app.notation.R
import files.app.notation.navigation.Screens
import files.app.notation.ui.theme.customBlackOne
import files.app.notation.ui.theme.customBlackTwo
import files.app.notation.ui.theme.customBrown
import files.app.notation.ui.theme.customTextColor
import files.app.notation.ui.theme.customYellow
import kotlinx.coroutines.launch


@Preview(showSystemUi = true)
@Composable
private fun PreviewMainScreen() {
    MainScreen(rememberNavController(), listOf(""))
}

@Composable
fun MainScreen(navController: NavController, settings: List<String>) {
    val showAddNoteDialog = remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    if (showAddNoteDialog.value) {
        AddNoteDialog(showState = showAddNoteDialog)
    }

    ModalNavigationDrawer(drawerContent = { DrawerMenu() }, drawerState = drawerState) {
        Scaffold(
            topBar = { CustomAppBar(drawerState, navController, settings) },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(bottom = 60.dp)
                        .size(60.dp),
                    onClick = { showAddNoteDialog.value = true },
                    contentColor = customBlackOne,
                    containerColor = customYellow,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(50.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(60.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add icon",
                    )
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = customBlackOne)
                    .padding(top = padding.calculateTopPadding() + 5.dp)
            ) {
                items(20) {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(customBlackOne)
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ShortNoteBox(navController)
                        ShortNoteBox(navController)
                    }
                }
                item {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(customBlackOne)
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ShortNoteBox(navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShortNoteBoxPreview() {
    Row(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(customBlackOne),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShortNoteBox(rememberNavController())
        ShortNoteBox(rememberNavController())
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(drawerState: DrawerState, navController: NavController, settings: List<String>) {
    val coroutineScope = rememberCoroutineScope()
    val dialogState = remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = customBlackTwo),
        title = { Text("Notes", color = Color.White, fontSize = 20.sp) },
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                    tint = customYellow,
                    modifier = Modifier.size(40.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(Screens.Search.route) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search button",
                    tint = customYellow,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = { dialogState.value = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Settings",
                    tint = customYellow,
                    modifier = Modifier.size(30.dp)
                )
            }
            CustomDropDownMenu(dialogState, settings)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DrawerMenu() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f)
            .background(customBlackTwo),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .fillMaxWidth()
                .background(customBrown),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Folder manager",
                fontSize = 35.sp,
                color = customTextColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 25.dp)
            )
        }
        LazyColumn(modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(), content = {
            items(50) {
                Row(Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
                    Text(text = it.toString(), color = customTextColor)
                }
            }
        })
        Canvas(modifier = Modifier.fillMaxWidth(), onDraw = {
            val startY = size.height / 2f
            val startX = 0f
            val endX = size.width
            drawLine(
                color = customBrown,
                start = Offset(startX, startY),
                end = Offset(endX, startY),
                strokeWidth = 4f
            )
        })
        Row(
            Modifier
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .clickable {

                }, verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add folder",
                tint = Color.White
            )
            Text(text = "Add new folder", color = customTextColor)
        }
    }
}


@Preview
@Composable
fun PreviewAddNoteDialog() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AddNoteDialog(remember { mutableStateOf(true) })
    }
}

@Composable
fun AddNoteDialog(showState: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = { showState.value = false },
        confirmButton = {
            Button(
                onClick = { showState.value = false }, colors = ButtonDefaults.buttonColors(
                    containerColor = customYellow
                )
            ) {
                Text(text = "Create")
            }
        },
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(1f),
        titleContentColor = customTextColor,
        containerColor = customBlackTwo,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.create_pen),
                contentDescription = "Create icon",
                tint = customBlackOne,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = customYellow)
                    .size(50.dp)
                    .wrapContentSize(Alignment.Center, unbounded = true)
            )
        },
        title = {
            Text("Create a note", fontSize = 18.sp)
        },
        text = {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(15.dp)) {
                CustomDialogTextField()
                FolderDropDownMenu()
            }
        }
    )
}

@Composable
fun CustomDialogTextField() {
    val textState = remember { mutableStateOf("") }

    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        placeholder = {
            Text("Enter title")
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = customBlackTwo,
            unfocusedContainerColor = customBlackTwo,
            focusedIndicatorColor = customBrown,
            focusedTextColor = customTextColor,
            cursorColor = customYellow
        ),
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { textState.value = "" }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_clear_24),
                    contentDescription = "cross delete",
                    tint = customYellow
                )
            }
        }
    )
}

@Composable
fun FolderDropDownMenu() {
    val showDropdownMenu = remember { mutableStateOf(false) }
    val folderState = remember { mutableStateOf("General folder") }
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                showDropdownMenu.value = !showDropdownMenu.value
            }) {
        Icon(
            painter = painterResource(if (showDropdownMenu.value) R.drawable.arrow_down else R.drawable.arrow_right),
            contentDescription = "arrow icon",
            tint = customYellow
        )
        Text(text = folderState.value, color = customTextColor)
        DropdownMenu(
            modifier = Modifier
                .background(customBlackTwo)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, customBrown, RoundedCornerShape(10.dp))
                .height(250.dp),
            expanded = showDropdownMenu.value,
            onDismissRequest = { showDropdownMenu.value = false }) {
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach {
                CustomDropdownMenuItem(title = it.toString()) {
                    folderState.value = it.toString()
                    showDropdownMenu.value = false
                }
            }
        }
    }
}