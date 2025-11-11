package com.work.emapp.ui.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.emapp.R.string.email_hint
import com.work.emapp.R.string.email_label
import com.work.emapp.R.string.forgot_password
import com.work.emapp.R.string.login_button
import com.work.emapp.R.string.login_title
import com.work.emapp.R.string.no_account
import com.work.emapp.R.string.password_hint
import com.work.emapp.R.string.password_label
import com.work.emapp.R.string.register_link
import com.work.emapp.R.string.to_main_button
import com.work.emapp.constants.Constants
import com.work.uikit.R.drawable.ic_ok
import com.work.uikit.R.drawable.ic_vk
import com.work.uikit.button.MyButton
import com.work.uikit.common.Blue
import com.work.uikit.common.Divider
import com.work.uikit.common.Orange
import com.work.uikit.common.SelectedColor
import com.work.uikit.inputField.EmailMaskTransformation
import com.work.uikit.inputField.MyInputField
import com.work.uikit.inputField.openUrl

@Composable
fun RegistrationScreen(
    onRegisterSuccess: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    val isEmailValid = email.isNotBlank() && emailRegex.matches(email)
    val isButtonEnabled = isEmailValid && password.isNotBlank()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(login_title),
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(email_label),
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                    )
                    MyInputField(
                        text = email,
                        hint = stringResource(email_hint),
                        keyboardType = KeyboardType.Email,
                        colorBG = Color.DarkGray.copy(alpha = 0.8f),
                        visualTransformation = EmailMaskTransformation(),
                        onValueChange = { input ->
                            val filtered = input.filter { it !in 'А'..'я' && it !in "Ёё" }
                            email = filtered
                        },
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(password_label),
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                    )

                    MyInputField(
                        text = password,
                        hint = stringResource(password_hint),
                        keyboardType = KeyboardType.Password,
                        colorBG = Color.DarkGray.copy(alpha = 0.8f),
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            MyButton(
                text = stringResource(login_button),
                onClick = onRegisterSuccess,
                enabled = isButtonEnabled,
            )
            MyButton(
                text = stringResource(to_main_button),
                onClick = onRegisterSuccess,
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Divider
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.padding(end = 6.dp),
                        text = stringResource(no_account),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        modifier = Modifier
                            .clickable { },
                        text = stringResource(register_link),
                        color = SelectedColor,
                        fontWeight = FontWeight.SemiBold,

                        )
                }
                Text(
                    modifier = Modifier
                        .clickable { },
                    text = stringResource(forgot_password),
                    color = SelectedColor,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MyButton(
                    modifier = Modifier
                        .weight(1F),
                    icon = painterResource(ic_vk),
                    onClick = {
                        openUrl(context = context, url = Constants.VK_URL)
                    },
                    buttonColor = Blue,
                )
                MyButton(
                    modifier = Modifier
                        .weight(1F),
                    icon = painterResource(ic_ok),
                    onClick = {
                        openUrl(context = context, url = Constants.OK_URL)
                    },
                    buttonColor = Orange,
                )
            }
        }
    }
}

@Composable
@Preview
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}