package com.example.demo.data

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.Masked

data class OddOuts(val name: String,
                   val preFlop: PreFlop,
                   val flop: Flop,
                   val turn: Turn,
                   val river: River)


data class PreFlop(
        val pocketHigh: Int,
        val pocketMedium: Int,
        val pocketLow: Int,

        val suitedHigh: Int,
        val suitedMedium: Int,
        val suitedLow: Int,

        val highHigh: Int,
        val highMedium: Int,
        val highLow: Int,


        val mediumMedium: Int,
        val mediumLow: Int,

        val lowLow: Int
)

data class Flop(
        val quad: Int,

        val flushHigh: Int,
        val flushMedium: Int,
        val flushLow: Int,

        val straightHigh: Int,
        val straightMedium: Int,
        val straightLow: Int,

        val setHigh: Int,
        val setMedium: Int,
        val setLow: Int,

        val twoPairHigh: Int,
        val twoPairMedium: Int,
        val twoPairLow: Int,


        val suitedHigh: Int,
        val suitedMedium: Int,
        val suitedLow: Int,

        val highPair: Int
)

data class Turn(
        val quad: Int,

        val flushHigh: Int,
        val flushMedium: Int,
        val flushLow: Int,

        val straightHigh: Int,
        val straightMedium: Int,
        val straightLow: Int,

        val setHigh: Int,
        val setMedium: Int,
        val setLow: Int,

        val twoPairHigh: Int,
        val twoPairMedium: Int,
        val twoPairLow: Int,


        val suitedHigh: Int,
        val suitedMedium: Int,
        val suitedLow: Int,

        val highPair: Int
)

data class River(
        val quad: Int,

        val flushHigh: Int,
        val flushMedium: Int,
        val flushLow: Int,

        val straightHigh: Int,
        val straightMedium: Int,
        val straightLow: Int,

        val setHigh: Int,
        val setMedium: Int,
        val setLow: Int,

        val twoPairHigh: Int,
        val twoPairMedium: Int,
        val twoPairLow: Int,

        val highPair: Int
)




