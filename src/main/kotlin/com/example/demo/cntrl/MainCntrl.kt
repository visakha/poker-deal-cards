package com.example.demo.cntrl

import com.example.demo.data.Player
import com.example.demo.data.PokerCard
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableMap
import tornadofx.*

class MainCntrl : Controller() {
    public lateinit var burnCardOne: PokerCard
    public lateinit var flopCardOne: PokerCard
    public lateinit var flopCardTwo: PokerCard
    public lateinit var flopCardThree: PokerCard
    public lateinit var burnCardTwo: PokerCard
    public lateinit var turnCard: PokerCard
    public lateinit var burnCardThree: PokerCard
    public lateinit var riverCard: PokerCard
    public var cardIndex: Int = 0

    fun writeToDb(inputValue: String) {
        println("Writing $inputValue to database!")
    }

    fun getPlayer(players: ObservableMap<Int, Player>?, row: Int, colmn: Int): Player? {
        println(" in getPlayer size = ${players!!.size}")
        val player = players!!.values. filter { it.fxId.startsWith("R$row-C$colmn") }
                .first()


        if (player != null) {
            println("returning player from cntrl-getPlayer on $row $colmn")
            return player

        } else {
            println("NOT returning player from cntrl-getPlayer on $row $colmn")
            return null
        }

    }

    fun lblForPlayerOnReady(player: Player): SimpleStringProperty {
        return player.display
    }

    fun dealPreFlop(playersMap: ObservableMap<Int, Player>?, cards: MutableList<PokerCard>) {
        val numPlayers = playersMap!!.values.size;
        val players = playersMap!!.values;
        // deal 1st card
        players.zip(0..numPlayers)
                .forEach {
                    it.first.setFirstHand(cards.get(it.second))

                }

        // deal second card
        val doubleNumPlayers = numPlayers * 2
        players.zip(numPlayers..doubleNumPlayers)
                .forEach {
                    it.first.setSecondHand(cards.get(it.second))
                }

        cardIndex = numPlayers * 2 - 1
    }

    fun updatePlayerDisplayCard(players: MutableCollection<Player>) {
        val numPlayers = players.size
        players.forEach {
            it.display.set(it.display.get()
                    + "\n[   " + it.getFirstHand() + " | " + it.getSecondHand() + " ]"
            )
        }
    }

    fun updatePlayerDisplayOdds(players: MutableCollection<Player>, cards: MutableList<PokerCard>) {
        players.forEach {
            it.display.set(it.display.get()
                    + "\n   +" + it.calcOddsPreFlop(players.size) + " +"
            )
        }
    }


    fun getFooterTxt4Cards(cards: List<PokerCard>): String {
            val joined: String = cards.joinToString (" |")
            println("-----------Footer-----------")
            println(joined)
            return joined

    }

    fun dealFlop(players: ObservableMap<Int, Player>, cards: List<PokerCard>)  {

        burnCardOne = cards.get(++cardIndex)
        flopCardOne = cards.get(++cardIndex)
        flopCardTwo = cards.get(++cardIndex)
        flopCardThree = cards.get(++cardIndex)
    }

    fun dealTurn(players: ObservableMap<Int, Player>, cards: List<PokerCard>)  {
        burnCardTwo = cards.get(++cardIndex)
        turnCard = cards.get(++cardIndex )
    }

    fun dealRiver(players: ObservableMap<Int, Player>, cards: List<PokerCard>)  {
        burnCardThree = cards.get(++cardIndex)
        riverCard = cards.get(++cardIndex )
    }

    fun clearPlayerCards(players: ObservableMap<Int, Player>) {
        players.values.forEach{
            it.takeCardsAway();
        }
    }

    fun getBoxName(r: Int, c: Int): String {
        if (r == 1 && c == 1) return "TopLeft" // top left
        if (r == 1 && c == 2) return "Plyr 9"
        if (r == 1 && c == 3) return "Dealer" // Dealer Id: R1-C3
        if (r == 1 && c == 4) return "Plyr 1"
        if (r == 1 && c == 5) return "TopRight" // top right

        if (r == 2 && c == 1) return "Plyr 8"
        if (r == 2 && c == 2) return "Flop 1"
        if (r == 2 && c == 3) return "Flop 2"
        if (r == 2 && c == 4) return "Flop 3"
        if (r == 2 && c == 5) return "Plyr 2"

        if (r == 3 && c == 1) return "Plyr 7"
        if (r == 3 && c == 2) return "Turn"
        if (r == 3 && c == 3) return "River"
        if (r == 3 && c == 4) return "Pot $"
        if (r == 3 && c == 5) return "Plyr 3"

        if (r == 4 && c == 1) return "BotLeft"  // Bottom Left
        if (r == 4 && c == 2) return "Plyr 6"
        if (r == 4 && c == 3) return "Plyr 5"
        if (r == 4 && c == 4) return "Plyr 4"
        if (r == 4 && c == 5) return "BotRight" // Bottom right

        return "un-expected";
    }


}
