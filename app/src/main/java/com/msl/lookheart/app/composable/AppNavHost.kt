package com.msl.lookheart.app.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msl.lookheart.app.viewModel.SharedViewModel

import com.msl.lookheart.enums.ui.SlideType
import com.msl.lookheart.screen.start.mvvm.StartAppView
import com.msl.lookheart.ui.anim.nav.navEnterTransition
import com.msl.lookheart.ui.anim.nav.navPopEnterTransition
import com.msl.lookheart.ui.anim.nav.navPopExitTransition
import com.msl.lookheart.ui.anim.nav.naviExitTransition

@Composable
fun AppNavHost(
    sharedViewModel: SharedViewModel,
    innerPadding: PaddingValues
) {
    /** Nav **/
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = "start",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                "start",
                enterTransition = { navEnterTransition(SlideType.VERTICALLY, 1500) },
                exitTransition = { naviExitTransition(SlideType.VERTICALLY, 1500) },
                popEnterTransition = { navPopEnterTransition(SlideType.VERTICALLY, 1500) },
                popExitTransition = { navPopExitTransition(SlideType.VERTICALLY, 1500) },
            ) {
                StartAppView(sharedViewModel)
            }

//            composable(
//                "login",
//                enterTransition = { navEnterTransition(SlideType.VERTICALLY, 1500) },
//                exitTransition = { naviExitTransition(SlideType.VERTICALLY, 1500) },
//                popEnterTransition = { navPopEnterTransition(SlideType.VERTICALLY, 1500) },
//                popExitTransition = { navPopExitTransition(SlideType.VERTICALLY, 1500) },
//            ) {
//                LoginView()
//            }
//
//
//            composable(
//                "find",
//                enterTransition = { navEnterTransition(SlideType.VERTICALLY, 1500) },
//                exitTransition = { naviExitTransition(SlideType.VERTICALLY, 1500) },
//                popEnterTransition = { navPopEnterTransition(SlideType.VERTICALLY, 1500) },
//                popExitTransition = { navPopExitTransition(SlideType.VERTICALLY, 1500) },
//            ) {
//                FindAccountView()
//            }
//
//            composable(
//                "signup",
//                enterTransition = { navEnterTransition(SlideType.VERTICALLY, 1500) },
//                exitTransition = { naviExitTransition(SlideType.VERTICALLY, 1500) },
//                popEnterTransition = { navPopEnterTransition(SlideType.VERTICALLY, 1500) },
//                popExitTransition = { navPopExitTransition(SlideType.VERTICALLY, 1500) },
//            ) {
//                SignUpView()
//            }
//
//            composable(
//                "main",
//                enterTransition = { navEnterTransition(SlideType.VERTICALLY, 1500) },
//                exitTransition = { naviExitTransition(SlideType.VERTICALLY, 1500) },
//                popEnterTransition = { navPopEnterTransition(SlideType.VERTICALLY, 1500) },
//                popExitTransition = { navPopExitTransition(SlideType.VERTICALLY, 1500) },
//            ) {
//                MainView()
//            }
        }
    }
}

