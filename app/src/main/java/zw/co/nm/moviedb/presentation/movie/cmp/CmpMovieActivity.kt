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
import zw.co.nm.moviedb.data.remote.model.response.NN
import zw.co.nm.moviedb.presentation.movie.cmp.ui.theme.MovieDBTheme
import zw.co.nm.moviedb.presentation.movie.newViewModel
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil.gradientBackground


class CmpMovieActivity : ComponentActivity() {
    private lateinit var viewModel: newViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this@CmpMovieActivity)[newViewModel::class.java]

        setContent {
            MovieDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    viewModel.getMovieDetail(11, "en-US")
                    movieDetail(viewModel.movieDetail)

                }
            }
        }

    }


    @Composable
    fun movieDetail(movieDetailResponse: NN) {

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
                        .data(Constants.IMAGE_BASE_URL + movieDetailResponse.posterPath)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.sample_cover_large_exp),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.height(550.dp)
                )
                Column(
                    Modifier.background(
                        gradientBackground(
                            isverticalGrad = true,
                            colors = gradientColorList
                        )
                    )
                ) {
                    Row {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = movieDetailResponse.title.toString(), modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally),
                                fontWeight = FontWeight.ExtraBold, fontSize = 25.sp,
                                color = Color.White

                            )
                            Text(
                                text = movieDetailResponse.releaseDate.toString(),
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally),
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
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
                            shape = RoundedCornerShape(10),
                        ) {
                            Text(text = "Reviews")
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        FilledTonalButton(
                            onClick = { /*TODO*/ },
                            Modifier.weight(1f), shape = RoundedCornerShape(10)
                        ) {
                            Text(text = "Previews")
                        }
                    }

                    Text(
                        text = movieDetailResponse.tagline.toString(),
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
                        .padding(8.dp, 4.dp),
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
                        text = movieDetailResponse.overview.toString(),
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 4.dp),
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

                        Text(text = movieDetailResponse.productionCompanies.toString())
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
    private fun ListShower(movieDetailResponse: List<NN.ProductionCompany>) {
        if (movieDetailResponse != null) {
            var productionCompanies: ArrayList<String>? = arrayListOf()
            movieDetailResponse.forEach { company ->
                productionCompanies!!.add(company.name)
            }
            Column {
                Text(text = "zd")
            }
        } else {
            Text(text = "sdkskds")
        }

    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MovieDBTheme {
            movieDetail(movieDetailResponse = NN())
        }
    }
}
