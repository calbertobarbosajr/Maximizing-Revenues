package com.calberto_barbosa_jr.maximizingrevenues

import androidx.compose.animation.animateContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun SimulationScreen(viewModel: SimulationViewModel = viewModel()) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .animateContentSize(), // Anima o layout para expandir com os resultados
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            viewModel.startSimulation()
        }) {
            Text("Start Simulation")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibe a barra de progresso enquanto simula
        if (viewModel.isSimulating) {
            LinearProgressIndicator(progress = viewModel.simulationProgress)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Progress: ${(viewModel.simulationProgress * 100).toInt()}%")
        }

        // Usando AnimatedVisibility para animar a exibição dos resultados após a simulação
        AnimatedVisibility(visible = !viewModel.isSimulating, enter = androidx.compose.animation.fadeIn(), exit = androidx.compose.animation.fadeOut()) {
            Column {
                Text(
                    text = "Absolute Return: ${viewModel.totalRewardTS - viewModel.totalRewardRS} R$",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Relative Return: ${
                        ((viewModel.totalRewardTS - viewModel.totalRewardRS) /
                                viewModel.totalRewardRS.toDouble() * 100).format(2)
                    }%",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                BarChartView(context, viewModel.strategiesSelectedTS)
                Spacer(modifier = Modifier.height(16.dp))
                LineChartView(context, viewModel.regret)
            }
        }
    }
}

@Composable
fun BarChartView(context: Context, strategiesSelectedTS: List<Int>) {
    AndroidView(factory = {
        BarChart(context).apply {
            val entries = strategiesSelectedTS.groupingBy { it }.eachCount().map { BarEntry(it.key.toFloat(), it.value.toFloat()) }
            val barDataSet = BarDataSet(entries, "Strategies").apply {
                colors = ColorTemplate.MATERIAL_COLORS.asList()
            }
            val barData = BarData(barDataSet)
            data = barData
            invalidate() // Atualizar o gráfico
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(300.dp))
}

@Composable
fun LineChartView(context: Context, regret: List<Int>) {
    AndroidView(factory = {
        LineChart(context).apply {
            val entries = regret.mapIndexed { index, value -> Entry(index.toFloat(), value.toFloat()) }
            val lineDataSet = LineDataSet(entries, "Regret").apply {
                colors = ColorTemplate.MATERIAL_COLORS.asList()
            }
            val lineData = LineData(lineDataSet)
            data = lineData
            invalidate() // Atualizar o gráfico
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(300.dp))
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
