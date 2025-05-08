package co.edu.unab.jorgebalaguera.ecomerceapp

import android.app.Activity
import android.inputmethodservice.Keyboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.edu.unab.jorgebalaguera.ecomerceapp.ui.theme.EcomerceAppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(navcontroller: NavController) {
    val auth = Firebase.auth
    var textocorreo by remember { mutableStateOf("") }
    var textocontra by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var MessageEmail by remember { mutableStateOf("") }
    var MessagePassword by remember { mutableStateOf("") }
    val activity = LocalView.current.context as Activity
    Scaffold { valuesPadding ->
        Column(
            modifier = Modifier
                .padding(valuesPadding)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Unab",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Iniciar Sesion",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF9900)
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = textocorreo,
                onValueChange = {
                    textocorreo = it
                },
                label = { Text("Correo Electronico") }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email",
                        tint = Color(0xFFFF9900)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                supportingText = {
                    if (MessageEmail.isNotEmpty()) {
                        Text(
                            text = MessageEmail,
                            color = Color.Red
                        )
                    }
                }, keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = textocontra,
                onValueChange = {
                    textocontra = it
                },
                label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Contraseña",
                        tint = Color(0xFFFF9900)
                    )
                },keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),

                shape = RoundedCornerShape(12.dp),
                supportingText = {
                    if (MessagePassword.isNotEmpty()) {
                        Text(
                            text = MessagePassword,
                            color = Color.Red
                        )
                    }
                }

            )
            Spacer(modifier = Modifier.height(24.dp))
            if (error.isNotEmpty()) {
                Text(
                    error,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    var BooleanEmail: Boolean = ValidationEmail(textocorreo).first
                    MessageEmail = ValidationEmail(textocorreo).second
                    var BooleanPassword: Boolean = ValidationPassword(textocontra).first
                    MessagePassword = ValidationPassword(textocontra).second

                    if (BooleanPassword && BooleanEmail) {
                        auth.signInWithEmailAndPassword(textocorreo, textocontra)
                            .addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                    navcontroller.navigate("HomeScreen") {
                                        popUpTo("LoginScreen") {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    error = when (task.exception) {
                                        is FirebaseAuthInvalidCredentialsException -> " Correo o Contraseña inconrrecta"
                                        is FirebaseAuthInvalidUserException -> "No existe una cuenta con este correo"
                                        else -> "Error al iniciar sesion Intente de Nuevo"
                                    }
                                }
                            }
                    } else {

                    }
                },
                colors = ButtonDefaults.buttonColors
                    (containerColor = Color(0xFFFF9900)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Iniciar Sesion",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(onClick = {
                navcontroller.navigate("RegisterScreen")
            }) {
                Text(
                    text = "¿No tienes una cuenta? Registrate",
                    color = Color(0xFFFF9900)
                )
            }

        }
    }
}

@Preview
@Composable
fun LoginScreenPrevie() {
    EcomerceAppTheme {
//        LoginScreen()
    }

}