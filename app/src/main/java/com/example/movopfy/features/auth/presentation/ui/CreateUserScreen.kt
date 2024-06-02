package com.example.movopfy.features.auth.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movopfy.R

@Composable
fun CreateUserScreen(
    email: String,
    password: String,
    name: String,
    onEmailChange: (text: String) -> Unit,
    onPasswordChange: (text: String) -> Unit,
    onNameChange: (text: String) -> Unit,
    changeAuthOptionClick: () -> Unit,
    createUserButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isAnimationVisible: Boolean = false,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            WelcomeText(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

            AuthTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = email,
                onValueChange = onEmailChange,
                placeHolder = { Text(text = stringResource(id = R.string.auth_email_placeholder)) })

            AuthTextField(
                modifier = Modifier.padding(top = 10.dp),
                value = password,
                onValueChange = onPasswordChange,
                placeHolder = { Text(text = stringResource(id = R.string.auth_password_placeholder)) })

            AnimatedVisibility(
                visible = isAnimationVisible,
                enter = scaleIn(animationSpec = tween(durationMillis = ANIMATION_DURATION)),
                exit = scaleOut(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            ) {
                AuthTextField(
                    modifier = Modifier.padding(top = 10.dp),
                    value = name,
                    onValueChange = onNameChange,
                    placeHolder = { Text(text = stringResource(id = R.string.auth_name_placeholder)) }
                )
            }

            ChangeAuthOptionText(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                text = stringResource(id = R.string.sign_in_user_text),
                onClick = changeAuthOptionClick
            )
        }

        SignInButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter),
            onClick = createUserButtonClick,
            textButton = stringResource(id = R.string.create_user_button)
        )
    }
}