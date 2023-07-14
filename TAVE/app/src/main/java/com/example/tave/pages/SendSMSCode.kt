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
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tave.R
import com.example.tave.common.util.state.SendSMSCodeState
import com.example.tave.items.sms.SMSLogo
import com.example.tave.items.sms.SMSPhoneNumberBtn
import com.example.tave.ui.theme.Shape
import com.example.tave.viewmodel.SendSMSViewModel

@Composable
fun SendSMSCodePage(
    modifier: Modifier,
    onNavigateOTP: (String) -> Unit,
    sendSMSViewModel: SendSMSViewModel = hiltViewModel()
) {
    val localContext: Context = LocalContext.current
    val isSendSMSCodeState by sendSMSViewModel.isSendSMSCode.collectAsState()

    var phoneNumber by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        content = {
            Column(
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SMSLogo(modifier = modifier)
                Spacer(modifier = modifier.size(10.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Column(
                            modifier = modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            content = {
                                OutlinedTextField(
                                    value = phoneNumber,
                                    onValueChange = { phoneNumber = it },
                                    label = { Text(text = stringResource(id = R.string.SMS_Phone_Number)) },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Outlined.Phone,
                                            contentDescription = stringResource(id = R.string.SMS_Phone_Number)
                                        )
                                    },
                                    modifier = modifier.width(300.dp),
                                    shape = Shape.medium,
                                    singleLine = true,
                                )
                                Spacer(modifier = modifier.size(10.dp))
                                when (isSendSMSCodeState) {
                                    is SendSMSCodeState.Idle -> {
                                        SMSPhoneNumberBtn(
                                            modifier = modifier,
                                            sendSMSCode = { sendSMSViewModel.sendSMSCode(phoneNumber) }
                                        )
                                    }
                                    is SendSMSCodeState.IsLoading -> CircularProgressIndicator()
                                    is SendSMSCodeState.IsComplete -> LaunchedEffect(Unit) {
                                        onNavigateOTP(phoneNumber)
                                    }
                                    is SendSMSCodeState.IsFailed -> {
                                        SMSPhoneNumberBtn(
                                            modifier = modifier,
                                            sendSMSCode = { sendSMSViewModel.sendSMSCode(phoneNumber) }
                                        )
                                        Toast.makeText(
                                            localContext,
                                            "발송에 실패했습니다! 다시 번호를 확인해주세요!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        )
                    }
                )
            }
        }
    )
}