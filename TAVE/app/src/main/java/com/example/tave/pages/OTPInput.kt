package com.example.tave.pages

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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tave.R
import com.example.tave.items.otp.OTPCodeInput
import com.example.tave.items.otp.OtpLogo
import com.example.tave.ui.theme.Shape
import com.example.tave.viewmodel.InputOTPViewModel

@Composable
fun OTPCodePage(
    modifier: Modifier,
    navController: NavController,
    inputOTPViewModel: InputOTPViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val localContext = LocalContext.current
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
                                label = { Text(text = stringResource(id = R.string.otp_Number)) },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Lock,
                                        contentDescription = stringResource(id = R.string.otp_Number)
                                    )
                                },
                                modifier = modifier.width(300.dp),
                                shape = Shape.medium,
                                singleLine = true
                            )
                            Spacer(modifier = modifier.size(10.dp))
                            OTPCodeInput(
                                modifier = modifier,
                                checkOTPCode = {
                                    inputOTPViewModel.checkOTPCode(otpCode)

                                    inputOTPViewModel.isOTPSuccess.observe(lifecycleOwner) {
                                        if (it.isSuccess) {
                                            navController.navigate("InitPasswordPage")
                                        } else {
                                            Toast.makeText(
                                                localContext,
                                                "코드가 일치하지 않습니다.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            )
                        }
                    }
                )
            }
        }
    )
}