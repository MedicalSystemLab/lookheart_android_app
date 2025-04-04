package com.msl.lookheart.screen.start.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msl.lookheart.R
import com.msl.lookheart.ui.custom.button.FixedTextButton
import com.msl.lookheart.ui.custom.text.FixedText
import com.msl.lookheart.ui.theme.DarkGray
import com.msl.lookheart.ui.theme.LightGray
import com.msl.lookheart.ui.theme.MyLightSkyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartAppBranchModalSheet(
    visible: Boolean,
    moveLogin: () -> Unit,
    moveSignup: () -> Unit,
) {
    var isPositionSet by remember { mutableStateOf(false) }

    if (visible) {
        BranchModalSheetBackground(isPositionSet = isPositionSet) {
            /** title **/
            val titleRef = createRef()
            FixedText(
                text = stringResource(R.string.msg_welcome_eng),
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(titleRef) {
                        top.linkTo(parent.top, margin = 40.dp)
                    }
            )

            /** hint **/
            val hintRef = createRef()
            FixedText(
                text = stringResource(R.string.desc_welcome),
                color = LightGray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(hintRef) {
                        top.linkTo(titleRef.bottom, margin = 30.dp)
                    }
            )

            /** start **/
            val (startRef) = createRefs()
            FixedTextButton(
                text = stringResource(id = R.string.msg_start),
                textColor = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                onClick = moveLogin,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .background(
                        color = MyLightSkyBlue,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .fillMaxWidth()
                    .height(40.dp)
                    .constrainAs(startRef) {
                        top.linkTo(hintRef.bottom, margin = 30.dp)
                    }
            )


            /** signup **/
            val (signupRef) = createRefs()
            FixedTextButton(
                text = stringResource(id = R.string.desc_signup),
                textColor = DarkGray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                onClick = moveSignup,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(40.dp)
                    .constrainAs(signupRef) {
                        top.linkTo(startRef.bottom, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    }
            )

            // set background location flag
            isPositionSet = true
        }
    }
}