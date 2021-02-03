package com.example.demo.data

import java.lang.Integer.max
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

object PokerCards {
    fun genNewShuffledCards(): MutableList<PokerCard> {
        val pokerCards: MutableList<PokerCard> = ArrayList()
        for (i in 1..52) pokerCards.add(PokerCard.genPokerCard(i))
        val randoGen = Random()
        val rando = randoGen.nextInt(52)
        Collections.shuffle(pokerCards, Random(rando.toLong()))
        var seq = 0
        for (pokerCard in pokerCards) {
            seq++
            pokerCard!!.shuffPos = seq
        }
        return pokerCards
    }

    fun printCards(cards: List<PokerCard>) {
        println("******** Printing cards **********")
        cards.forEach(Consumer { card: PokerCard -> println("\t " + card + " --natPos--" + card.natPos) })
    }

    fun handIsSuited(hands: Pair<PokerCard, PokerCard>): Boolean {
        return hands.first.symbol === hands.second.symbol
    }

    fun handIsPoketPair(hands: Pair<PokerCard, PokerCard>): Boolean {
        return hands.first.rank === hands.second.rank
    }

    fun handIsSuitedConnected(hands: Pair<PokerCard, PokerCard>): Boolean {
        return handIsConnected(hands) && handIsSuited(hands)

    }

    fun handIsConnected(hands: Pair<PokerCard, PokerCard>): Boolean {
        if (hands.first.rankNum == 14 && hands.second.rankNum == 2) return true
        if (hands.second.rankNum == 14 && hands.first.rankNum == 2) return true
        val abs = kotlin.math.abs(hands.first.rankNum - hands.second.rankNum);
        return abs == 1
    }

    // POCKETS PreFlop
    fun pfPocketHigh(hands: Pair<PokerCard, PokerCard>): Boolean {
        return handIsPoketPair(hands) && hands.first.isHigh() && hands.second.isHigh()
    }

    fun pfPocketMedium(hands: Pair<PokerCard, PokerCard>): Boolean {
        return handIsPoketPair(hands) && (hands.first.isMedium() || hands.second.isMedium())
    }

    fun pfPocketLow(hands: Pair<PokerCard, PokerCard>): Boolean {
        return handIsPoketPair(hands) && (hands.first.isLow() || hands.second.isLow())
    }

    // SUITED PreFlop
    fun pfSuitedHigh(hands: Pair<PokerCard, PokerCard>): Boolean {
        return handIsSuited(hands) && hands.first.isHigh() && hands.second.isHigh()
    }

    fun psSuitedMedium(hands: Pair<PokerCard, PokerCard>): Boolean {
        return handIsSuited(hands) && (hands.first.isMedium() || hands.second.isMedium())
    }

    fun pfSuitedLow(hands: Pair<PokerCard, PokerCard>): Boolean {
        return handIsSuited(hands) && (hands.first.isLow() || hands.second.isLow())
    }

    // no-connect, no-suite : preFlop
    fun pfHighHigh(hands: Pair<PokerCard, PokerCard>): Boolean {
        return hands.first.isHigh() && hands.second.isHigh()
    }

    fun pfHighMedium(hands: Pair<PokerCard, PokerCard>): Boolean {
        return (hands.first.isHigh() && hands.second.isMedium()) || (hands.second.isHigh() && hands.first.isMedium())
    }

    fun pfHighLow(hands: Pair<PokerCard, PokerCard>): Boolean {
        return (hands.first.isHigh() && hands.second.isLow()) || (hands.second.isHigh() && hands.first.isLow())
    }

    fun pfMediumMedium(hands: Pair<PokerCard, PokerCard>): Boolean {
        return hands.first.isMedium() && hands.second.isMedium()
    }

    fun pfMediumLow(hands: Pair<PokerCard, PokerCard>): Boolean {
        return (hands.first.isMedium() && hands.second.isLow()) || (hands.second.isMedium() && hands.first.isLow())
    }

    // --------------- FLOP, TURN , RIVER ------------------
    fun quad(cycle: Cycle, hands: Pair<PokerCard, PokerCard>, flopCards: List<PokerCard>, turnCard: PokerCard? = null, riverCard: PokerCard? = null): Boolean {
        val combinedCards = combineCards(cycle, hands, flopCards, turnCard, riverCard)
        return isQuad(combinedCards)
    }

    fun flushHigh(cycle: Cycle, hands: Pair<PokerCard, PokerCard>, flopCards: List<PokerCard>, turnCard: PokerCard? = null, riverCard: PokerCard? = null): Boolean {
        val combinedCards = combineCards(cycle, hands, flopCards, turnCard, riverCard)
        val flushOK = isFlush(combinedCards);
        return flushOK.first && atleastOneHandIsHigh(hands, flushOK.second)
    }

    fun flushMedium(cycle: Cycle, hands: Pair<PokerCard, PokerCard>, flopCards: List<PokerCard>, turnCard: PokerCard? = null, riverCard: PokerCard? = null): Boolean {
        val combinedCards = combineCards(cycle, hands, flopCards, turnCard, riverCard)
        val flushOK = isFlush(combinedCards);
        return flushOK.first && atleastOneHandIsMedium(hands, flushOK.second)
    }

    fun flushLow(cycle: Cycle, hands: Pair<PokerCard, PokerCard>, flopCards: List<PokerCard>, turnCard: PokerCard? = null, riverCard: PokerCard? = null): Boolean {
        val combinedCards = combineCards(cycle, hands, flopCards, turnCard, riverCard)
        val flushOK = isFlush(combinedCards);
        return flushOK.first && atleastOneHandIsLow(hands, flushOK.second)
    }


    private fun atleastOneHandIsLow(hands: Pair<PokerCard, PokerCard>, symbol: String): Boolean {
        return getMaxRankBySymbol(hands, symbol) <= 7
    }

    private fun getMaxRankBySymbol(hands: Pair<PokerCard, PokerCard>, symbol: String): Int {
        if (hands.first.symbol === symbol && hands.second.symbol === symbol)
            return max(hands.first.rankNum, hands.second.rankNum)
        else if (hands.first.symbol === symbol) return max(2,hands.first.rankNum)
        else if (hands.second.symbol === symbol) return max(2,hands.second.rankNum)
        else throw RuntimeException("getMaxRank called with no matching symbol")

    }

    private fun atleastOneHandIsHigh(hands: Pair<PokerCard, PokerCard>, symbol: String): Boolean {
        return (hands.first.symbol === symbol && hands.first.isHigh()) || (hands.second.symbol === symbol && hands.second.isHigh())
    }

    private fun atleastOneHandIsMedium(hands: Pair<PokerCard, PokerCard>, symbol: String): Boolean {
        return (hands.first.symbol === symbol && hands.first.isMedium()) || (hands.second.symbol === symbol && hands.second.isMedium())
    }

    private fun isQuad(combinedCards: java.util.ArrayList<PokerCard>): Boolean {
        combinedCards.forEach {
            val nCount = combinedCards.filter { e -> e.rankNum == it.rankNum }
                    .count()
            if (nCount == 4) return true
        }
        return false
    }

    private fun isFlush(combinedCards: java.util.ArrayList<PokerCard>): Pair<Boolean, String> {
        combinedCards.forEach {
            val nCount = combinedCards.filter { e -> e.symbol === it.symbol }
                    .count()
            if (nCount == 5) return Pair(true, it.symbol)
        }
        return Pair(false, "X")
    }


    private fun combineCards(cycle: Cycle, hands: Pair<PokerCard, PokerCard>, flopCards: List<PokerCard>, turnCard: PokerCard?, riverCard: PokerCard?): ArrayList<PokerCard> {
        val allCards = ArrayList<PokerCard>();
        if (cycle == Cycle.FLOP) {
            allCards.add(hands.first)
            allCards.add(hands.second)
            allCards.addAll(flopCards);
        }

        if (cycle == Cycle.TURN) {
            allCards.add(hands.first)
            allCards.add(hands.second)
            allCards.addAll(flopCards);

            allCards.add(turnCard!!)
        }

        if (cycle == Cycle.RIVER) {
            allCards.add(hands.first)
            allCards.add(hands.second)
            allCards.addAll(flopCards);
            allCards.add(turnCard!!)

            allCards.add(riverCard!!)
        }

        return allCards
    }


}

