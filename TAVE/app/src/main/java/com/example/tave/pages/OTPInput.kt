package com.example.tave.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tave.InitPasswordPage
import com.example.tave.R
import com.example.tave.common.util.state.CheckOTPCodeState
import com.example.tave.common.util.state.CheckOTPCodeState.*
import com.example.tave.items.otp.OTPCodeInput
import com.example.tave.items.otp.OtpLogo
import com.example.tave.ui.theme.Shape

@Composable
fun OTPCodePage(
    phoneNumber: String?,
    navController: NavController,
    isOTPChecked: CheckOTPCodeState,
    checkOTPCode: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val localContext: Context = LocalContext.current
    var otpCode by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        content = {
            Column(
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OtpLogo(modifier = modifier)
                Spacer(modifier = modifier.size(10.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Column(
                            modifier = modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = otpCode,
                                onValueChange = { otpCode = it },
                                label = { Text(text = stringResource(id = R.string.OTP_Number)) },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Lock,
                                        contentDescription = stringResource(id = R.string.OTP_Number)
                                    )
                                },
                                modifier = modifier.width(300.dp),
                                shape = Shape.medium,
                                singleLine = true
                            )
                            Spacer(modifier = modifier.size(10.dp))

                            when (isOTPChecked) {
                                is Idle -> {
                                    OTPCodeInput(
                                        modifier = modifier,
                                        checkOTPCode = { checkOTPCode("$phoneNumber", otpCode) }
                                    )
                                }
                                is IsLoading -> CircularProgressIndicator()
                                is IsComplete -> LaunchedEffect(Unit) {
                                    navController.navigate(InitPasswordPage.route)
                                }
                                is IsFailed -> {
                                    LaunchedEffect(Unit) {
                                        Toast.makeText(
                                            localContext,
                                            "코드가 일치하지 않습니다.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    OTPCodeInput(
                                        modifier = modifier,
                                        checkOTPCode = { checkOTPCode("$phoneNumber", otpCode) }
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}