package com.topic3.android.reddit.routing

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf


private val localBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcher?> { null }
@Composable
fun BackButtonHandler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val dispatcher = localBackPressedDispatcher.current ?: return
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()
            }
        }
    }
    DisposableEffect(dispatcher) {
        dispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}