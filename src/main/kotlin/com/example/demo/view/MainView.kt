package com.example.demo.view

import com.example.demo.cntrl.MainCntrl
import com.example.demo.data.Players
import com.example.demo.data.PokerCard
import com.example.demo.data.PokerCards
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("FX-POKER yey!!!") {

    private val lkpBtnReady: String = "#btnReady"
    private val lkpBtnPreFlop: String = "#btnPreFlop"
    private val lkpBtnFlop: String = "#btnFlop"
    private val lkpBtnTurn: String = "#btnTurn"
    private val lkpBtnRiver: String = "#btnRiver"
    val lkpDealer = "#R1-C3";

    val cntrl: MainCntrl by inject()
    val dealerLblText = SimpleStringProperty("Dealer")
    val flopOneLblText = SimpleStringProperty("Flop 1")
    val flopTwoLblText = SimpleStringProperty("Flop 2")
    val flopThreeLblText = SimpleStringProperty("Flop 3")
    val turnLblText = SimpleStringProperty("Turn")
    val riverLblText = SimpleStringProperty("River")
    val footlerLblText = SimpleStringProperty("------- footer ---------")
    val cards = PokerCards.genNewShuffledCards()
    var players = Players().ready(1)

    init {
        clearTable();
        players = Players().ready(9)
    }

    override val root = borderpane {
        top {
            hbox {
                button("ready") { id = "btnReady" }.action({ onReadyAction() })
                button("pre-flop") { id = "btnPreFlop" }.action({ onPreFlopAction() })
                button("flop") { id = "btnFlop" }.action({ onFlopAction() })
                button("turn") { id = "btnTurn" }.action({ onTurnAction() })
                button("river") { id = "btnRiver" }.action({ onRiverAction() })
            }
        }
        center {
            gridpane {
                for (r in 1..4)
                    row {
                        for (c in 1..5) {
                            add(mkBoxes(r, c))
                        }
                    }
            }
        }
        bottom {
            label("Development mode - VizPly") {
                id = "btmLbl"
                bind(footlerLblText)
            }
        }

    }


    private fun mkBoxes(r: Int, c: Int): Node {
        var boxName = cntrl.getBoxName(r,c)
        var tableColor = Color.WHITESMOKE

        var boxColor = with(boxName) {
            when {
                startsWith("Plyr") -> Color.PEACHPUFF
                isEmpty() -> Color.WHITE
                else -> tableColor
            }
        }

        return stackpane {
            rectangle {
                fill = boxColor
                width = 240.0
                height = 120.0
                arcWidth = 5.0
                arcHeight = 5.0
                stroke = Color.DARKGREEN
                strokeWidth = 0.4
            }

            // lbls for Players
            when {
                boxName.startsWith("Plyr") -> label("-temp-") {
                    id = "R$r-C$c"
                    bind(cntrl.getPlayer(players, r, c)!!.display)
                }
                boxName.startsWith("Dealer") -> label(dealerLblText) { id = "R$r-C$c" }
                boxName.equals("Flop 1") -> label(flopOneLblText) { id = "R$r-C$c" }
                boxName.equals("Flop 2") -> label(flopTwoLblText) { id = "R$r-C$c" }
                boxName.equals("Flop 3") -> label(flopThreeLblText) { id = "R$r-C$c" }
                boxName.equals("Turn") -> label(turnLblText) { id = "R$r-C$c" }
                boxName.equals("River") -> label(riverLblText) { id = "R$r-C$c" }
                else -> label(boxName) { id = "R$r-C$c" }
            }

            children.style {
                fontSize = 18.px
//                fontWeight = FontWeight.BOLD
            }
        } // stackPane
    }


    fun onReadyAction() {
        dealerLblText.set("Are you guys !Ready!")
        disableBtn(lkpBtnReady)
        disableBtn(lkpBtnFlop)
        disableBtn(lkpBtnTurn)
        disableBtn(lkpBtnRiver)
        disableBtn(lkpBtnReady)
        enableBtn(lkpBtnPreFlop)
        cntrl.clearPlayerCards(players!!)
        clearTable()

    }

    private fun clearTable() {
        cards.clear()
        cards.addAll(PokerCards.genNewShuffledCards());
        dealerLblText.set("Dealer")
        flopOneLblText.set("Flop 1")
        flopTwoLblText.set("Flop 2")
        flopThreeLblText.set("Flop 3")
        turnLblText.set("Turn")
        riverLblText.set("River")
        footlerLblText.set("------- footer ---------")

    }

    fun onPreFlopAction() {
        // hide other controls
        disableBtn(lkpBtnPreFlop)
        enableBtn(lkpBtnFlop)


        cntrl.dealPreFlop(players, cards); // non UI

        cntrl.updatePlayerDisplayCard(players!!.values)
        cntrl.updatePlayerDisplayOdds(players!!.values, cards)
        footlerLblText.set(cntrl.getFooterTxt4Cards(cards))
        dealerLblText.set("PreFlop")
        println("Card Index = " + cntrl.cardIndex)
    }

    fun onFlopAction() {
        // hide other controls
        disableBtn(lkpBtnFlop)
        enableBtn(lkpBtnTurn)

        cntrl.dealFlop(players!!, cards);
        dealerLblText.set("On Flop \n" + cntrl.burnCardOne.toString())
        flopOneLblText.set(cntrl.flopCardOne.toString())
        flopTwoLblText.set(cntrl.flopCardTwo.toString())
        flopThreeLblText.set(cntrl.flopCardThree.toString())
        footlerLblText.set(cntrl.getFooterTxt4Cards(cards))

    }

    fun onTurnAction() {
        // hide other controls
        disableBtn(lkpBtnTurn)
        enableBtn(lkpBtnRiver)
        cntrl.dealTurn(players!!, cards);

        dealerLblText.set("On Turn"
                + "\n " + cntrl.burnCardOne.toString()
                + "\n " + cntrl.burnCardTwo.toString()
        )
        turnLblText.set(cntrl.turnCard.toString())

    }

    fun onRiverAction() {
        // hide other controls
        disableBtn(lkpBtnRiver)
        enableBtn(lkpBtnReady)

        cntrl.dealRiver(players!!, cards);

        dealerLblText.set("On River"
                + "\n " + cntrl.burnCardOne.toString()
                + "\n " + cntrl.burnCardTwo.toString()
                + "\n " + cntrl.burnCardThree.toString()
        )
        riverLblText.set(cntrl.riverCard.toString())

    }


    fun lkpBtn(btnName: String): Button {
        return root.scene.lookup(btnName) as Button
    }

    fun disableBtn(btnName: String) {
        with(root.scene.lookup(btnName) as Button) { isDisable = true }
    }

    fun enableBtn(btnName: String) {
        with(root.scene.lookup(btnName) as Button) { isDisable = false }
    }

    fun getLblDealer(): Label {
        return root.scene.lookup(lkpDealer) as Label
    }
}