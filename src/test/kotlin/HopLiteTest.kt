package com.example.demo.cntrl;

import com.example.demo.data.OddOuts
import com.example.demo.data.Players
import com.example.demo.data.PokerCard
import com.example.demo.data.PokerCards
import com.sksamuel.hoplite.ConfigLoader
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.lang.RuntimeException


public class HopLiteTest {

    var cntrl = MainCntrl()

    @Test
    fun isSuited(): Unit {
        val odds = ConfigLoader().loadConfigOrThrow<OddOuts>("/odds.json")
        Assertions.assertNotNull(odds)
        println(odds)
    }

}