package files.app.notation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import files.app.notation.ui.theme.customBlackOne
import files.app.notation.ui.theme.customBlackTwo
import files.app.notation.ui.theme.customBrown
import files.app.notation.ui.theme.customTextColor
import files.app.notation.ui.theme.customYellow
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
private fun PreviewMAinScreen() {
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(drawerContent = { DrawerMenu() }, drawerState = drawerState) {
        Scaffold(
            topBar = { CustomAppBar(drawerState) },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.size(60.dp),
                    onClick = { /*TODO*/ },
                    contentColor = customBlackOne,
                    containerColor = customYellow,
                    shape = CircleShape
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
                        ShortNoteBox()
                        ShortNoteBox()
                    }
                }
                item {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp))
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
        ShortNoteBox()
        ShortNoteBox()
    }
}

@Composable
fun ShortNoteBox() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(customBlackTwo)
                .clickable {

                },
            colors = CardDefaults.cardColors(
                containerColor = customBlackTwo
            )
        ) {
            Text(
                text = "Content",
                color = customTextColor,
                modifier = Modifier.padding(10.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = "Title",
            fontWeight = FontWeight.Bold,
            color = customTextColor,
            fontSize = 18.sp,
            modifier = Modifier
                .width(160.dp),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = customYellow,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    tint = customYellow,
                    modifier = Modifier.size(30.dp)
                )
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(customBlackOne),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = "", onValueChange = {}, colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent
            ), shape = CircleShape
        )
    }
}