package co.edu.unab.jorgebalaguera.ecomerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.unab.jorgebalaguera.ecomerceapp.ui.theme.EcomerceAppTheme
import coil3.compose.rememberAsyncImagePainter
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "bienvenido",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },


        ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                "promociones", modifier = Modifier.padding(
                    top = 16.dp,
                    start = 16.dp,
                    bottom = 8.dp
                )
            )
            val liturl = listOf(
                "https://static.vecteezy.com/system/resources/previews/002/506/587/non_2x/flash-sale-discount-banner-promotion-background-vector.jpg",
                "https://img.freepik.com/vector-gratis/banner-promocion-2x1_52683-50845.jpg",
                "https://previews.123rf.com/images/stickerside/stickerside2207/stickerside220700124/189853956-plantilla-de-banner-de-gran-venta-hasta-un-50-por-ciento-de-oferta-especial-gran-promoci%C3%B3n-de.jpg",
                "https://img.freepik.com/vector-gratis/plantilla-banner-horizontal-degradado-ventas-buen-fin_23-2150873583.jpg",
                "https://static.vecteezy.com/system/resources/previews/004/672/772/non_2x/flash-sale-banner-design-template-offer-shopping-on-dark-blue-background-free-vector.jpg",
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                for (url in liturl){
                    item { cardPromo(url) }
                }
            }
        }
    }
}


@Composable
fun cardPromo(url: String) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(300.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "promocion",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    EcomerceAppTheme {
        HomeScreen()
    }
}