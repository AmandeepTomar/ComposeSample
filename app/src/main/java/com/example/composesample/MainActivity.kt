package com.example.composesample

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.composesample.basicslayouts.AlignYourBodyUI
import com.example.composesample.basicslayouts.SearchBarUI
import com.example.composesample.bottomnav.BottomNavItem
import com.example.composesample.bottomnav.BottomNavigationBar
import com.example.composesample.bottomnav.Navigation
import com.example.composesample.datasource.Message
import com.example.composesample.datasource.SampleDataSource
import com.example.composesample.multipleselectorrlazyColumn.MultiSelectLazyColumn
import com.example.composesample.ui.theme.ComposeSampleTheme
import com.example.composesample.viewmodel.MainViewModel
import java.util.*
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.updateName("First Value of Name")
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                // AppSample()

                MultiSelectLazyColumn()

//                val navController = rememberNavController()
//                Scaffold(
//
//                    bottomBar = {
//                        BottomNavigationBar(
//                            items = BottomNavItem.getItems(),
//                            navController = navController,
//                            modifier = Modifier,
//                            onBottomItemClick = {
//                                navController.navigate(it.route)
//                            }
//                        )
//                    }
//                ) {
//                    Navigation(navController = navController)
//                }
            }
        }
    }
}


@Composable
fun withSurfaceExample(@DrawableRes drawable: Int, modifier: Modifier, @StringRes string: Int) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                stringResource(id = string),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }
    }
}

@Composable
fun alignYourBodyRow() {
    Row(modifier = Modifier.background(MaterialTheme.colors.background)) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(SampleDataSource.getAlignYourBodyRowData()) { item ->
                val alignYourBody = AlignYourBodyUI()
                alignYourBody.alignYouBodyUI(
                    modifier = Modifier.padding(vertical = 8.dp),
                    item.text,
                    item.drawable
                )
            }
        }
    }
}

@Composable
fun favoriteColumnCompose(modifier: Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        items(SampleDataSource.favoriteCollectionsData) { item ->
            withSurfaceExample(drawable = item.drawable, modifier = modifier, string = item.text)
        }
    }
}


@Composable
fun homeSection(modifier: Modifier, @StringRes title: Int, content: @Composable () -> Unit) {
    Column {
        Text(
            stringResource(id = title).toUpperCase(Locale.getDefault()),
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }

}

@Composable
fun homeScreen() {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .safeContentPadding()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        val searchBarUI = SearchBarUI()
        searchBarUI.searchBarUI(modifier = Modifier.padding(horizontal = 16.dp))

        homeSection(modifier = Modifier, title = R.string.align_your_body) {
            alignYourBodyRow()
        }
        homeSection(modifier = Modifier, title = R.string.favorite_collections) {
            favoriteColumnCompose(modifier = Modifier)
        }
    }

}


@Composable
fun homeSectionView() {

    Column {
        // alignYourBodyRow(modifier = Modifier)
        homeSection(modifier = Modifier, title = R.string.align_your_body) {
            alignYourBodyRow()
        }
        Spacer(modifier = Modifier.size(10.dp))
        favoriteColumnCompose(modifier = Modifier)
    }
}

@Composable
fun designBottomNavigation(modifier: Modifier) {
    BottomNavigation(modifier) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "")
            },
            label = {
                Text(text = "Home")
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.VerifiedUser, contentDescription = "")
            },
            label = {
                Text(text = "Home")
            }
        )
    }
}

@Composable
fun AppSample() {
//    val searchBarUI = SearchBarUI()
//    searchBarUI.searchBarUI(modifier = Modifier.padding(all = 8.dp))

//    val alignYourBody = AlignYourBodyUI()
//    val name = "Inversions"
//
//    alignYourBody.alignYouBodyUI(
//        modifier = Modifier.padding(all = 8.dp),
//        name = name,
//        R.drawable.ab1_inversions
//    )
    //withSurfaceExample(drawable = R.drawable.fc2_nature_meditations, modifier =Modifier.padding(all=8.dp),R.string.app_name )
    Scaffold(bottomBar = {
        designBottomNavigation(modifier = Modifier)
    }) {
        homeScreen()
    }
}

@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
        //  Conversations(messages = SampleDataSource.getConversations())
        AppSample()
    }
}