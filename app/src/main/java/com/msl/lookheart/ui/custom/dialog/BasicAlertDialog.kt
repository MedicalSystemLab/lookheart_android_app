package com.msl.lookheart.ui.custom.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.msl.lookheart.ui.custom.text.FixedText
import com.msl.lookheart.ui.theme.DarkGray
import com.msl.lookheart.ui.theme.LightGray
import com.msl.lookheart.ui.theme.MyBlue
import com.msl.lookheart.ui.theme.MyLightGray
import com.msl.lookheart.ui.theme.MySkyBlue


@Composable
fun BasicAlertDialog(
    visible: Boolean,
    title: String,
    body: String,
    confirmText: String,
    cancelText: String? = null,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    dismissEnable: Boolean = true,
    widthFraction: Float = 0.9f
) {
    if (visible) {
        Dialog(onDismissRequest = { if (dismissEnable) onDismiss() }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(widthFraction),
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 8.dp
            ) {
                ConstraintLayout(modifier = Modifier.background(Color.White)) {
                    val (titleRef, underLineRef, bodyRef, buttonsRef) = createRefs()

                    /** Title **/
                    FixedText(
                        text = title,
                        color = MySkyBlue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .constrainAs(titleRef) {
                                top.linkTo(parent.top, margin = 15.dp)
                                centerHorizontallyTo(parent)
                            }
                    )

                    /** under line **/
                    Box(
                        modifier = Modifier
                            .constrainAs(underLineRef) {
                                top.linkTo(titleRef.bottom, margin = 10.dp)
                                start.linkTo(parent.start, margin = 10.dp)
                                end.linkTo(parent.end, margin = 10.dp)
                                width = Dimension.fillToConstraints
                            }
                            .background(MyBlue)
                            .height(1.dp)
                    )

                    /** body **/
                    FixedText(
                        text = body,
                        color = DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .constrainAs(bodyRef) {
                                top.linkTo(underLineRef.bottom, margin = 30.dp)
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )

                    /** buttons **/
                    Row(
                        modifier = Modifier
                            .constrainAs(buttonsRef) {
                                top.linkTo(bodyRef.bottom, margin = 30.dp)
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        /** cancel **/
                        if (cancelText != null) {
                            OutlinedButton(
                                onClick = onDismiss,
                                border = BorderStroke(1.5.dp, MyLightGray),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(35.dp)
                            ) {
                                FixedText(
                                    text = cancelText,
                                    color = LightGray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        /** confirm **/
                        Button(
                            onClick = onConfirm,
                            colors = ButtonDefaults.buttonColors(containerColor = MySkyBlue),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(35.dp)
                        ) {
                            FixedText(
                                text = confirmText,
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}