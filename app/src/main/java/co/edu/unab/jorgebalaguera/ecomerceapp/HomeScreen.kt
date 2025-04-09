package co.edu.unab.jorgebalaguera.ecomerceapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import co.edu.unab.jorgebalaguera.ecomerceapp.ui.theme.EcomerceAppTheme

@Composable
fun HomeScreen(){
    Scaffold { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){
            Text(
                "HOME SCREEN",
                fontSize = 50.sp
            )
        }
    }


}

@Preview
@Composable
fun HomeScreenPreview(){
    EcomerceAppTheme {
        HomeScreen()
    }
}