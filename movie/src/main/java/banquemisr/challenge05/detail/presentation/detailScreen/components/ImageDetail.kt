package banquemisr.challenge05.detail.presentation.detailScreen.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import banquemisr.challenge05.core.components.CoilImageComponent
import banquemisr.challenge05.core.utils.Constants.IMAGE_URL
import banquemisr.challenge05.home.R

@Composable
fun ImageDetail(imageUrl: String?,backClick:()->Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val imageHeight = screenHeight * 0.4f
    ConstraintLayout(modifier =Modifier.fillMaxWidth()) {
        val (image, backIcon) = createRefs()
        CoilImageComponent(
            imageUrl = IMAGE_URL + imageUrl,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .height(imageHeight),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        // Back icon positioned at the bottom of the image
        IconButton(
            onClick = { backClick()},
            modifier = Modifier
                .size(48.dp)
                .constrainAs(backIcon) {
                    top.linkTo(image.top, margin = 16.dp)
                    start.linkTo(image.start, margin = 16.dp)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
    }
