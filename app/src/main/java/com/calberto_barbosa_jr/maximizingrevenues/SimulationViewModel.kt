package com.calberto_barbosa_jr.maximizingrevenues

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.exp
import kotlin.math.sqrt
import kotlin.math.cos
import kotlin.math.PI
import kotlin.random.Random

class SimulationViewModel : ViewModel() {

    private val N = 10000
    private val d = 9
    private val conversionRates = listOf(0.05, 0.13, 0.09, 0.16, 0.11, 0.04, 0.20, 0.08, 0.01)
    private val X = Array(N) { IntArray(d) }

    val strategiesSelectedTS = mutableListOf<Int>()
    var totalRewardRS = 0
    var totalRewardTS = 0
    private val numbersOfRewards1 = IntArray(d)
    private val numbersOfRewards0 = IntArray(d)
    val regret = mutableListOf<Int>()

    // Estados de carregamento e progresso
    var isSimulating by mutableStateOf(false)
        private set
    var simulationProgress by mutableStateOf(0f)
        private set

    // Função para iniciar a simulação
    fun startSimulation() {
        viewModelScope.launch {
            isSimulating = true
            simulate()
            isSimulating = false
        }
    }

    private suspend fun simulate() {
        delay(500) // Simula um atraso inicial de processamento

        // Inicializa a simulação
        for (i in 0 until N) {
            for (j in 0 until d) {
                if (Random.nextDouble() <= conversionRates[j]) {
                    X[i][j] = 1
                }
            }
        }

        // Executa a simulação, atualizando o progresso
        for (n in 0 until N) {
            // Atualiza o progresso em porcentagem (0.0 a 1.0)
            simulationProgress = (n + 1) / N.toFloat()

            // Estratégia randômica
            val strategyRS = Random.nextInt(d)
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

            // Calcula o "regret"
            val bestStrategyReward = (0 until d).maxOf { numbersOfRewards1[it] }
            regret.add(bestStrategyReward - totalRewardTS)

            delay(1) // Simula o tempo de execução, para visualização do progresso
        }
    }

    private fun betaSample(alpha: Int, beta: Int): Double {
        val x = gammaSample(alpha.toDouble(), 1.0)
        val y = gammaSample(beta.toDouble(), 1.0)
        return x / (x + y)
    }

    private fun gammaSample(shape: Double, scale: Double): Double {
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
}