package co.edu.unab.jorgebalaguera.ecomerceapp
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun RegisterScreen(navcontroller: NavController) {

    val auth = Firebase.auth
    var textocorreo by remember { mutableStateOf("") }
    var textocontra by remember { mutableStateOf("") }
    var textocontra2 by remember { mutableStateOf("") }
    var textonombre by remember { mutableStateOf("") }
    var MessageEmail by remember { mutableStateOf("") }
    var MessagePassword by remember { mutableStateOf("") }
    var MessageName by remember { mutableStateOf("") }
    var MessagePassword2 by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val activity = LocalView.current.context as Activity


    Scaffold(topBar = {

        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { navcontroller.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            })
    }) { innerPading ->
        Column(
            modifier = Modifier
                .padding(innerPading)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Register",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Registrar Usuario",
                fontSize = 24.sp, fontWeight = FontWeight.Bold,
                color = Color(0xFFFF9900)
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = textonombre,
                onValueChange = {
                    textonombre = it
                },
                label = { Text("Nombre Completo") }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Nombre Completo",
                        tint = Color(0xFFFF9900)
                    )
                }, supportingText = {
                    if (MessageName.isNotEmpty()) {
                        Text(MessageName, color = Color.Red)
                    }
                },
                shape = RoundedCornerShape(12.dp)
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
                        contentDescription = "Correo Electronico",
                        tint = Color(0xFFFF9900)
                    )
                }, supportingText = {
                    if (MessageEmail.isNotEmpty()) {
                        Text(MessageEmail, color = Color.Red)
                    }
                },
                shape = RoundedCornerShape(12.dp), keyboardOptions = KeyboardOptions(
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
                },
                supportingText = {
                    if (MessagePassword.isNotEmpty()) {
                        Text(MessagePassword, color = Color.Red)
                    }
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = textocontra2,
                onValueChange = {
                    textocontra2 = it
                },
                label = { Text("Confirmar Contraseña") }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirmar Contraseña",
                        tint = Color(0xFFFF9900)
                    )
                }, supportingText = {
                    if (MessagePassword2.isNotEmpty()) {
                        Text(MessagePassword2, color = Color.Red)
                    }
                },
                shape = RoundedCornerShape(12.dp), keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
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
                    var BooleanName: Boolean = ValidationName(textonombre).first
                    MessageName = ValidationName(textonombre).second
                    var BooleanPassword2: Boolean =
                        ValidationConfirmation(textocontra, textocontra2).first
                    MessagePassword2 = ValidationConfirmation(textocontra, textocontra2).second

                    if (BooleanEmail && BooleanPassword2 && BooleanPassword && BooleanName) {
                        auth.createUserWithEmailAndPassword(textocorreo, textocontra)
                            .addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                    navcontroller.navigate("HomeScreen") {
                                        popUpTo("LoginScreen"){inclusive=true}
                                    }
                                } else {
                                    error = when (task.exception) {
                                        is FirebaseAuthInvalidCredentialsException -> "Correo Invalido"
                                        is FirebaseAuthUserCollisionException -> "Correo ya registrado"
                                        else -> "Error al Registrarse"
                                    }

                                }

                            }
                    }
                },
                colors = ButtonDefaults.buttonColors
                    (containerColor = Color(0xFFFF9900)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Text(
                    text = "Registrarse",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

            }
            TextButton(onClick = {
                navcontroller.popBackStack()
            }) {
                Text(
                    text = "Si tengo una cuenta",
                    color = Color(0xFFFF9900)
                )
            }


        }
    }

}
