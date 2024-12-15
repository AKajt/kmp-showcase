package com.aljazkajtna.kmpshowcase.ui.usersstats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.aljazkajtna.kmpshowcase.ComposableLifecycle
import io.github.dautovicharis.charts.PieChartView
import io.github.dautovicharis.charts.common.model.ChartDataSet
import io.github.dautovicharis.charts.style.PieChartDefaults
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_user_details_loading
import kmp_showcase.composeapp.generated.resources.screen_users_stats
import kmp_showcase.composeapp.generated.resources.screen_users_stats_average_age
import kmp_showcase.composeapp.generated.resources.screen_users_stats_chart_description
import kmp_showcase.composeapp.generated.resources.screen_users_stats_chart_slice_description
import kmp_showcase.composeapp.generated.resources.screen_users_stats_female
import kmp_showcase.composeapp.generated.resources.screen_users_stats_male
import kmp_showcase.composeapp.generated.resources.screen_users_stats_other
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersStatsScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<UsersStatsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.loadStats()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.screen_users_stats)
                    )
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                UsersStatsScreenState.Loading -> {
                    RenderLoadingState()
                }

                is UsersStatsScreenState.Ready -> {
                    RenderReadyState(uiState as UsersStatsScreenState.Ready)
                }
            }
        }
    }
}

@Composable
private fun RenderLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = stringResource(Res.string.screen_user_details_loading)
        )
    }
}

@Composable
private fun RenderReadyState(
    state: UsersStatsScreenState.Ready
) {
    Text(
        text = stringResource(Res.string.screen_users_stats_average_age, state.averageAge)
    )

    val total = state.maleCount + state.femaleCount + state.otherCount
    val distribution = remember(state) {
        val malePercentage = (state.maleCount.toFloat() / total * 100).roundTo(1)
        val femalePercentage = (state.femaleCount.toFloat() / total * 100).roundTo(1)

        // Calculate other percentage based on remaining value
        val otherPercentage = 100.0f - malePercentage - femalePercentage

        // Round other percentage to one decimal place
        val roundedOtherPercentage = otherPercentage.roundTo(1)

        listOf(
            malePercentage,
            femalePercentage,
            roundedOtherPercentage
        )
    }

    val pieChartData = ChartDataSet(
        items = distribution,
        title = stringResource(
            Res.string.screen_users_stats_chart_description,
            total
        ),
        labels = listOf(
            stringResource(
                Res.string.screen_users_stats_chart_slice_description,
                total,
                stringResource(Res.string.screen_users_stats_male),
                state.maleCount,
                distribution[0]
            ),
            stringResource(
                Res.string.screen_users_stats_chart_slice_description,
                total,
                stringResource(Res.string.screen_users_stats_female),
                state.femaleCount,
                distribution[1]
            ),
            stringResource(
                Res.string.screen_users_stats_chart_slice_description,
                total,
                stringResource(Res.string.screen_users_stats_other),
                state.otherCount,
                distribution[2]
            ),
        ),
    )
    PieChartView(
        dataSet = pieChartData,
        style = PieChartDefaults.style(
            pieColors = listOf(
                Color.Red,
                Color.Green,
                Color.Blue,
            ),
        )
    )
}

// Helper function for rounding to a specific number of decimal places
fun Float.roundTo(decimals: Int): Float {
    var multiplier = 1.0f
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
