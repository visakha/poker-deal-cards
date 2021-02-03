package com.example.demo.cntrl;

import com.example.demo.data.Cycle
import com.example.demo.data.Players
import com.example.demo.data.PokerCard
import com.example.demo.data.PokerCards
import org.junit.Test
import org.junit.jupiter.api.Assertions


public class CalcOddsTest {

    var cntrl = MainCntrl()

    @Test
    fun isSuited(): Unit {
        // see clearTable in the MainView : do the same here
        val numPlayers = 9;
        val players = Players().ready(numPlayers )!!;
        val plyr_1 = players[1]!!
        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._2D)
        Assertions.assertFalse(PokerCards.handIsSuited(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._5C)
        Assertions.assertTrue(PokerCards.handIsSuited(plyr_1.getHands()))


    }

    @Test
    fun isPocketPair(): Unit {
        // see clearTable in the MainView : do the same here
        val numPlayers = 9;
        val players = Players().ready(numPlayers )!!;
        val plyr_1 = players[1]!!
        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._2D)
        Assertions.assertTrue(PokerCards.handIsPoketPair(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._3D)
        Assertions.assertFalse(PokerCards.handIsPoketPair(plyr_1.getHands()))
    }

    @Test
    fun isSuitedConnected(): Unit {
        // see clearTable in the MainView : do the same here
        val numPlayers = 9;
        val players = Players().ready(numPlayers )!!;
        val plyr_1 = players[1]!!
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._7C)
        Assertions.assertTrue(PokerCards.handIsConnected(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._3D)
        Assertions.assertTrue(PokerCards.handIsConnected(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._AC)
        plyr_1.setSecondHand(PokerCard._2D)
        Assertions.assertTrue(PokerCards.handIsConnected(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._AD)
        Assertions.assertTrue(PokerCards.handIsConnected(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._9D)
        plyr_1.setSecondHand(PokerCard._TD)
        Assertions.assertTrue(PokerCards.handIsConnected(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._KD)
        plyr_1.setSecondHand(PokerCard._QC)
        Assertions.assertTrue(PokerCards.handIsConnected(plyr_1.getHands()))

        // FALSY
        plyr_1.setFirstHand(PokerCard._9D)
        plyr_1.setSecondHand(PokerCard._JD)
        Assertions.assertFalse(PokerCards.handIsConnected(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._AD)
        plyr_1.setSecondHand(PokerCard._3C)
        Assertions.assertFalse(PokerCards.handIsConnected(plyr_1.getHands()))

        plyr_1.setFirstHand(PokerCard._KD)
        plyr_1.setSecondHand(PokerCard._JC)
        Assertions.assertFalse(PokerCards.handIsConnected(plyr_1.getHands()))

    }

    @Test
    fun isQuad(): Unit {
        // see clearTable in the MainView : do the same here
        val numPlayers = 9;
        val players = Players().ready(numPlayers )!!;
        val plyr_1 = players[1]!!
        // Truty
        // quads on flop
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._7C)
        var flopCards = arrayListOf<PokerCard>(PokerCard._8C, PokerCard._8S, PokerCard._8H)
        Assertions.assertTrue(PokerCards.quad(Cycle.FLOP,plyr_1.getHands(),flopCards, null, null))

        // quads on flop and no turn
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._7C)
        flopCards = arrayListOf<PokerCard>(PokerCard._8C, PokerCard._8S, PokerCard._8H)
        Assertions.assertTrue(PokerCards.quad(Cycle.FLOP,plyr_1.getHands(),flopCards, PokerCard._2C, null))

        // quads on turn
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._7C)
        flopCards = arrayListOf<PokerCard>(PokerCard._8C, PokerCard._KS, PokerCard._8H)
        Assertions.assertTrue(PokerCards.quad(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._8S, null))

        // quads on river
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._7C)
        flopCards = arrayListOf<PokerCard>(PokerCard._QC, PokerCard._KS, PokerCard._8H)
        Assertions.assertTrue(PokerCards.quad(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8S, PokerCard._8C))

        // pair and quads on flop
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._8C)
        flopCards = arrayListOf<PokerCard>(PokerCard._QC, PokerCard._8S, PokerCard._8H)
        Assertions.assertTrue(PokerCards.quad(Cycle.FLOP,plyr_1.getHands(),flopCards))


        // FALSY ---------------------
        // pair and NO quads on flop
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._8C)
        flopCards = arrayListOf<PokerCard>(PokerCard._QC, PokerCard._3S, PokerCard._8H)
        Assertions.assertFalse(PokerCards.quad(Cycle.FLOP,plyr_1.getHands(),flopCards))

        // pair and NO quads on Turn
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._8C)
        flopCards = arrayListOf<PokerCard>(PokerCard._QC, PokerCard._3S, PokerCard._AH)
        Assertions.assertFalse(PokerCards.quad(Cycle.FLOP,plyr_1.getHands(),flopCards,PokerCard._8H))

        // pair and NO quads on River
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._8C)
        flopCards = arrayListOf<PokerCard>(PokerCard._QC, PokerCard._3S, PokerCard._AH)
        Assertions.assertFalse(PokerCards.quad(Cycle.FLOP,plyr_1.getHands(),flopCards,PokerCard._8H, PokerCard._TH))
    }

    @Test
    fun isFlush(): Unit {
        // see clearTable in the MainView : do the same here
        val numPlayers = 9;
        val players = Players().ready(numPlayers )!!;
        val plyr_1 = players[1]!!
        // Truty
        // flush on flop
        plyr_1.setFirstHand(PokerCard._8D)
        plyr_1.setSecondHand(PokerCard._7D)
        var flopCards = arrayListOf<PokerCard>(PokerCard._TD, PokerCard._KD, PokerCard._AD)
        Assertions.assertTrue(PokerCards.flushMedium(Cycle.FLOP,plyr_1.getHands(),flopCards, null, null))
        Assertions.assertFalse(PokerCards.flushHigh(Cycle.FLOP,plyr_1.getHands(),flopCards, null, null))
        Assertions.assertFalse(PokerCards.flushLow(Cycle.FLOP,plyr_1.getHands(),flopCards, null, null))

        // flush on flop and no turn : Turn
        plyr_1.setFirstHand(PokerCard._2D)
        plyr_1.setSecondHand(PokerCard._5D)
        flopCards = arrayListOf<PokerCard>(PokerCard._TD, PokerCard._KD, PokerCard._AD)
        Assertions.assertFalse(PokerCards.flushMedium(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._2C, null))
        Assertions.assertFalse(PokerCards.flushHigh(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._QD, null))
        Assertions.assertTrue(PokerCards.flushLow(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._7C, null))

        // flush on turn : Medium
        plyr_1.setFirstHand(PokerCard._8C)
        plyr_1.setSecondHand(PokerCard._7C)
        flopCards = arrayListOf<PokerCard>(PokerCard._3C, PokerCard._KC, PokerCard._8H)
        Assertions.assertTrue(PokerCards.flushMedium(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._2C, null))
        Assertions.assertFalse(PokerCards.flushHigh(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._QD, null))
        Assertions.assertFalse(PokerCards.flushLow(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._7C, null))


        // flush on river : High
        plyr_1.setFirstHand(PokerCard._KC)
        plyr_1.setSecondHand(PokerCard._7H)
        flopCards = arrayListOf<PokerCard>(PokerCard._3C, PokerCard._TC, PokerCard._8H)
        Assertions.assertFalse(PokerCards.flushMedium(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._9C, null))
        Assertions.assertFalse(PokerCards.flushLow(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._9C, null))
        Assertions.assertFalse(PokerCards.flushHigh(Cycle.TURN,plyr_1.getHands(),flopCards, PokerCard._9C, null))

        Assertions.assertFalse(PokerCards.flushHigh(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._5H))
        Assertions.assertFalse(PokerCards.flushMedium(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._5H))
        Assertions.assertFalse(PokerCards.flushLow(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._5H))


        Assertions.assertTrue(PokerCards.flushHigh(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertFalse(PokerCards.flushMedium(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertFalse(PokerCards.flushLow(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))

        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._7H)
        Assertions.assertFalse(PokerCards.flushHigh(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertFalse(PokerCards.flushMedium(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertTrue(PokerCards.flushLow(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))

        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._AH)
        Assertions.assertFalse(PokerCards.flushHigh(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertFalse(PokerCards.flushMedium(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertTrue(PokerCards.flushLow(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))

        plyr_1.setFirstHand(PokerCard._KC)
        plyr_1.setSecondHand(PokerCard._2H)
        Assertions.assertTrue(PokerCards.flushHigh(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertFalse(PokerCards.flushMedium(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
        Assertions.assertFalse(PokerCards.flushLow(Cycle.RIVER,plyr_1.getHands(),flopCards, PokerCard._8C, PokerCard._AC))
    }



    @Test
    fun calcOddsManagedCards(): Unit {
        // see clearTable in the MainView : do the same here
        val numPlayers = 9;
        val players = Players().ready(numPlayers )!!;
        val plyr_1 = players[1]!!
        plyr_1.setFirstHand(PokerCard._2C)
        plyr_1.setSecondHand(PokerCard._2D)

    }

    @Test
    fun calcOddsShuffleCards(): Unit {
        // see clearTable in the MainView : do the same here
        val cards = PokerCards.genNewShuffledCards();
        val numPlayers = 9;
        val players = Players().ready(numPlayers )!!;
        cntrl.dealPreFlop(players, cards); // non UI
        val plyr_1 = players[1]!!
        val plyr_2 = players[2]!!
        val plyr_3 = players[3]!!
        println(plyr_1.showHands())
        println(plyr_2.showHands())
        println(plyr_3.showHands())
    }

}