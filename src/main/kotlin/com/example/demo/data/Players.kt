package com.example.demo.data

import javafx.collections.FXCollections
import javafx.collections.ObservableMap

/**
 * @author Vamsi Vegi
 * @date 1/7/2020
 */
class Players {

    fun ready(numPlayers: Int) : ObservableMap<Int, Player>? {
        val hashMapOfPlayers = hashMapOf<Int, Player>(
                1 to Player("Spyder",100, 1, "R1-C4"),
                2 to Player("Ant", 100, 2, "R2-C5"),
                3 to Player("Crab", 100, 3, "R3-C5"),
                4 to Player("Fish", 100, 4, "R4-C4"),
                5 to Player("Turkey", 100, 5, "R4-C3"),
                6 to Player("Unicorn", 100, 6, "R4-C2"),
                7 to Player("Monkey", 100, 7, "R3-C1"),
                8 to Player("Tiger", 100, 8,"R2-C1"),
                9 to Player("Chimp", 100, 9, "R1-C2")
        )

        val players = FXCollections.observableHashMap<Int, Player>()

        players.putAll(hashMapOfPlayers)
        return players

    }
}