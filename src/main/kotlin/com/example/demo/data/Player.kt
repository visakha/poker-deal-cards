package com.example.demo.data

import javafx.beans.property.SimpleStringProperty

/**
 * @author Vamsi Vegi
 * @date 1/7/2020
 */
class Player(val name: String, val park: Int, val pos: Int, val fxId: String) {


    private var cardOne: PokerCard = PokerCard();
    private var cardTwo: PokerCard = PokerCard();
    var chips: Int = 100
    var display = SimpleStringProperty(name + "\n(" + chips + ")");

    fun setFirstHand(card: PokerCard) {
        cardOne = card;
        cardOne.inDeck = false;
    }

    fun setSecondHand(card: PokerCard) {
        cardTwo = card;
        cardTwo.inDeck = false;
    }

    fun getFirstHand() : PokerCard {
        return cardOne
    }

    fun getSecondHand() : PokerCard {
        return cardTwo
    }

    fun showHands() : String = cardOne.toString() + "-" + cardTwo

    fun takeCardsAway() {
        cardOne = PokerCard()
        cardTwo = PokerCard()
        display.set("${name}  ( ${chips } )" )

    }

    fun calcOddsPreFlop(numPlayers: Int): Int {
        val cardsInDeck = 52 - (numPlayers * 2) - 1
        println("cardsInDeck = $cardsInDeck")

        return 57
    }

    fun getHands(): Pair<PokerCard, PokerCard> {
        return Pair( cardOne, cardTwo)
    }


}