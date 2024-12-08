package com.calberto_barbosa_jr.maximizingrevenues

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calberto_barbosa_jr.maximizingrevenues.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.exp
import kotlin.math.sqrt
import kotlin.math.cos
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val N = 10000
    private val d = 9
    private val conversionRates = listOf(0.05, 0.13, 0.09, 0.16, 0.11, 0.04, 0.20, 0.08, 0.01)
    private val X = Array(N) { IntArray(d) }

    private val strategiesSelectedRS = mutableListOf<Int>()
    private val strategiesSelectedTS = mutableListOf<Int>()
    private var totalRewardRS = 0
    private var totalRewardTS = 0
    private val numbersOfRewards1 = IntArray(d)
    private val numbersOfRewards0 = IntArray(d)
    private val regret = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        simulate()
        displayResults()
    }

    private fun simulate() {
        // Create simulation
        for (i in 0 until N) {
            for (j in 0 until d) {
                if (Random.nextDouble() <= conversionRates[j]) {
                    X[i][j] = 1
                }
            }
        }

        // Implement random strategy and Thompson Sampling
        for (n in 0 until N) {
            // Random strategy
            val strategyRS = Random.nextInt(d)
            strategiesSelectedRS.add(strategyRS)
            val rewardRS = X[n][strategyRS]
            totalRewardRS += rewardRS

            // Thompson Sampling
            var strategyTS = 0
            var maxRandom = 0.0
            for (i in 0 until d) {
                val randomBeta = betaSample(numbersOfRewards1[i] + 1, numbersOfRewards0[i] + 1)
                if (randomBeta > maxRandom) {
                    maxRandom = randomBeta
                    strategyTS = i
                }
            }
            val rewardTS = X[n][strategyTS]
            if (rewardTS == 1) {
                numbersOfRewards1[strategyTS]++
            } else {
                numbersOfRewards0[strategyTS]++
            }
            strategiesSelectedTS.add(strategyTS)
            totalRewardTS += rewardTS

            // Regret
            val bestStrategyReward = (0 until d).maxOf { numbersOfRewards1[it] }
            regret.add(bestStrategyReward - totalRewardTS)
        }
    }

    private fun betaSample(alpha: Int, beta: Int): Double {
        val x = gammaSample(alpha.toDouble(), 1.0)
        val y = gammaSample(beta.toDouble(), 1.0)
        return x / (x + y)
    }

    private fun gammaSample(shape: Double, scale: Double): Double {
        // Use the Marsaglia and Tsang method for shape >= 1
        if (shape < 1.0) {
            return gammaSample(shape + 1, scale) * exp(ln(Random.nextDouble()) / shape)
        }
        val d = shape - 1.0 / 3.0
        val c = 1.0 / sqrt(3.0 * d)
        while (true) {
            val x = boxMullerTransform()
            var v = 1 + c * x
            if (v > 0) {
                v = v.pow(3)
                val u = Random.nextDouble()
                if (u < 1 - 0.0331 * x.pow(4)) return d * v * scale
                if (ln(u) < 0.5 * x.pow(2) + d * (1 - v + ln(v))) return d * v * scale
            }
        }
    }

    private fun boxMullerTransform(): Double {
        val u1 = Random.nextDouble()
        val u2 = Random.nextDouble()
        return sqrt(-2.0 * ln(u1)) * cos(2.0 * PI * u2)
    }

    private fun displayResults() {
        val absoluteReturn = (totalRewardTS - totalRewardRS) * 19.99
        val relativeReturn = (totalRewardTS - totalRewardRS) / totalRewardRS.toDouble() * 100
        binding.absoluteReturnText.text = String.format("Absolute Return: %.0f R$", absoluteReturn)
        binding.relativeReturnText.text = String.format("Relative Return: %.0f %%", relativeReturn)

        // Display histogram
        val barEntries = strategiesSelectedTS.groupingBy { it }.eachCount().map { BarEntry(it.key.toFloat(), it.value.toFloat()) }
        val barDataSet = BarDataSet(barEntries, "Strategy")
        val barData = BarData(barDataSet)
        binding.barChart.data = barData
        binding.barChart.invalidate()

        // Display regret curve
        val lineEntries = regret.mapIndexed { index, value -> Entry(index.toFloat(), value.toFloat()) }
        val lineDataSet = LineDataSet(lineEntries, "Regret")
        val lineData = LineData(lineDataSet)
        binding.lineChart.data = lineData
        binding.lineChart.invalidate()
    }

}