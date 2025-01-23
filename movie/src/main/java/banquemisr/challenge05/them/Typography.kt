package banquemisr.challenge05.them

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val MovieTypography = Typography(
  titleLarge = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
    color = Color.DarkGray,
    fontSize = 28.sp
  ),
  titleMedium = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    color = Color.DarkGray,
    fontSize = 21.sp
  ),
  titleSmall = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    color = Color.DarkGray,
    fontSize = 16.sp
  ),
  bodyMedium = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Normal,
    color = Color.Gray,
    fontSize = 16.sp
  ),

  bodySmall = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Normal,
    color = Color.Gray,
    fontSize = 14.sp
  ),
)