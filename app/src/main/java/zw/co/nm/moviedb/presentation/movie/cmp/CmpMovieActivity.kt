package zw.co.nm.moviedb.presentation.movie.cmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.presentation.movie.cmp.ui.theme.MovieDBTheme


class CmpMovieActivity : ComponentActivity() {
    private lateinit var moviesViewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        setContent {
            MovieDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    movieDetail()
                }
            }
        }

    }
}

@Composable
fun movieDetail() {

    val gradientColorList = listOf(
        Color(0xFFAF7F6),
        Color(0xFF000000)
    )

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.wrapContentSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sample_cover_large_exp),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(550.dp)
            )
            Column(
                Modifier.background(
                    gbg(
                        isverticalGrad = true,
                        colors = gradientColorList
                    )
                )
            ) {
                Row {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "FAST X", modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally),
                            fontWeight = FontWeight.ExtraBold, fontSize = 18.sp

                        )
                        Text(
                            text = "2023", modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally),
                            fontWeight = FontWeight.ExtraBold
                        )

                    }
                }

                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    FilledTonalButton(
                        onClick = { /*TODO*/ },
                        Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(20),
                    ) {
                        Text(text = "Reviews")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    FilledTonalButton(
                        onClick = { /*TODO*/ },
                        Modifier.weight(1f), shape = RoundedCornerShape(20)
                    ) {
                        Text(text = "Previews")
                    }
                }

                Text(
                    text = "The world forever changes.",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(5.dp), color = Color.White

                )
                Spacer(modifier = Modifier.height(10.dp))

            }
        }


        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp)
                    .height(110.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            )
            {
                Text(
                    text = "About",
                    modifier = Modifier.padding(5.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "The story of J. Robert Oppenheimer’s role in the development of the atomic bomb during World War II.",
                    modifier = Modifier.padding(5.dp)
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp)
                    .height(110.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            )
            {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Information",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Production",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp)
                    .height(110.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            )
            {
                Text(text = "Information", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Production",
                    fontSize = 18.sp
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp)
                    .height(110.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            )
            {
                Text(text = "Information", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Production",
                    fontSize = 18.sp
                )
            }

        }


    }
}

@Composable
private fun gbg(isverticalGrad: Boolean, colors: List<Color>): Brush {
    val endOffset = if (isverticalGrad) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }
    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieDBTheme {
        movieDetail()
    }
}