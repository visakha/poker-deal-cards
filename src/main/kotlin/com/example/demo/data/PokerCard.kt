package com.example.demo.data

class PokerCard {
    val imageLoc: String?
    val rank: String
    val rankNum: Int
    val symbol: String
    val symbolPic: String
    val symbolNum: Int
    val natPos: Int
    val natPosCode: Int
    var shuffPos = 0
    private val shuffPosCode = 0
    var inDeck = true

    constructor(imageLoc: String?, rank: String, symbol: String, natPos: Int) {
        this.imageLoc = imageLoc
        this.rank = rank
        this.symbol = symbol
        this.natPos = natPos
        natPosCode = natPos - 1

        rankNum = when (rank.toUpperCase()) {
            "2" -> 2
            "3" -> 3
            "4" -> 4
            "5" -> 5
            "6" -> 6
            "7" -> 7
            "8" -> 8
            "9" -> 9
            "T" -> 10
            "J" -> 11
            "Q" -> 12
            "K" -> 13
            else -> 14
        }

        symbolNum = when (symbol.toUpperCase()) {
            "C" -> 2
            "D" -> 3
            "H" -> 4
            else -> 5 // default Spade
        }
        symbolPic = when (symbol.toUpperCase()) {
            "C" -> Suit.CLUBS.icon
            "D" -> Suit.DIAMONDS.icon
            "H" -> Suit.HEARTS.icon
            else -> Suit.SPADES.icon  // defaul to Spade
        }

    }

    fun _ShuffPosCode(): Int = shuffPos - 1

    constructor() : super() {
        imageLoc = null
        rank = ""
        rankNum = -1
        symbol = ""
        symbolNum = -1
        natPos = -1
        natPosCode = -1
        symbolPic = ""
    }

    override fun toString(): String = " $rank$symbolPic "

    fun toStringDtl(): String {
        return "PokerCard{" +
                "imageLoc='" + imageLoc + '\'' +
                ", rank='" + rank + '\'' +
                ", rankNum=" + rankNum +
                ", symbol='" + symbol + '\'' +
                ", symbolNum=" + symbolNum +
                ", natPos=" + natPos +
                ", natPosCode=" + natPosCode +
                ", shuffPos=" + shuffPos +
                ", shuffPosCode=" + shuffPosCode

    }

    fun isLow() = rankNum <= 7
    fun isMedium() = rankNum > 7 && rankNum <= 10
    fun isHigh() = rankNum > 10


    companion object {
        val imagesDir = "static/images/cards/"


        fun genPokerCard(cardNum: Int): PokerCard { //        System.out.println("genPokerCard - for cardNum=" + cardNum);
            val imagesDir = "static/images/cards/"
            if (cardNum == 1) return _2C
            if (cardNum == 2) return _2D
            if (cardNum == 3) return _2H
            if (cardNum == 4) return _2S
            if (cardNum == 5) return _3C
            if (cardNum == 6) return _3D
            if (cardNum == 7) return _3H
            if (cardNum == 8) return _3S
            if (cardNum == 9) return _4C
            if (cardNum == 10) return _4D
            if (cardNum == 11) return _4H
            if (cardNum == 12) return _4S
            if (cardNum == 13) return _5C
            if (cardNum == 14) return _5D
            if (cardNum == 15) return _5H
            if (cardNum == 16) return _5S
            if (cardNum == 17) return _6C
            if (cardNum == 18) return _6D
            if (cardNum == 19) return _6H
            if (cardNum == 20) return _6S
            if (cardNum == 21) return _7C
            if (cardNum == 22) return _7D
            if (cardNum == 23) return _7H
            if (cardNum == 24) return _7S
            if (cardNum == 25) return _8C
            if (cardNum == 26) return _8D
            if (cardNum == 27) return _8H
            if (cardNum == 28) return _8S
            if (cardNum == 29) return _9C
            if (cardNum == 30) return _9D
            if (cardNum == 31) return _9H
            if (cardNum == 32) return _9S
            if (cardNum == 33) return _TC
            if (cardNum == 34) return _TD
            if (cardNum == 35) return _TH
            if (cardNum == 36) return _TS
            if (cardNum == 37) return _JC
            if (cardNum == 38) return _JD
            if (cardNum == 39) return _JH
            if (cardNum == 40) return _JS
            if (cardNum == 41) return _QC
            if (cardNum == 42) return _QD
            if (cardNum == 43) return _QH
            if (cardNum == 44) return _QS
            if (cardNum == 45) return _KC
            if (cardNum == 46) return _KD
            if (cardNum == 47) return _KH
            if (cardNum == 48) return _KS
            if (cardNum == 49) return _AC
            if (cardNum == 50) return _AD
            if (cardNum == 51) return _AH
            if (cardNum == 52) return _AS

            return _XX
        }

        val _XX = PokerCard(imagesDir + "XX.png", "X", "X", 59)

        val _AS = PokerCard(imagesDir + "AS.png", "A", "S", 52)

        val _AH = PokerCard(imagesDir + "AH.png", "A", "H", 51)

        val _AD = PokerCard(imagesDir + "AD.png", "A", "D", 50)

        val _AC = PokerCard(imagesDir + "AC.png", "A", "C", 49)

        val _KS = PokerCard(imagesDir + "KS.png", "K", "S", 48)

        val _KH = PokerCard(imagesDir + "KH.png", "K", "H", 47)

        val _KD = PokerCard(imagesDir + "KD.png", "K", "D", 46)

        val _KC = PokerCard(imagesDir + "KC.png", "K", "C", 45)

        val _QS = PokerCard(imagesDir + "QS.png", "Q", "S", 44)

        val _QH = PokerCard(imagesDir + "QH.png", "Q", "H", 43)

        val _QD = PokerCard(imagesDir + "QD.png", "Q", "D", 42)

        val _QC = PokerCard(imagesDir + "QC.png", "Q", "C", 41)

        val _JS = PokerCard(imagesDir + "JS.png", "J", "S", 40)

        val _JH = PokerCard(imagesDir + "JH.png", "J", "H", 39)

        val _JD = PokerCard(imagesDir + "JD.png", "J", "D", 38)

        val _JC = PokerCard(imagesDir + "JC.png", "J", "C", 37)

        val _TS = PokerCard(imagesDir + "TS.png", "T", "S", 36)

        val _TH = PokerCard(imagesDir + "TH.png", "T", "H", 35)

        val _TD = PokerCard(imagesDir + "TD.png", "T", "D", 34)

        val _TC = PokerCard(imagesDir + "TC.png", "T", "C", 33)

        val _9S = PokerCard(imagesDir + "9S.png", "9", "S", 32)

        val _9H = PokerCard(imagesDir + "9H.png", "9", "H", 31)

        val _9D = PokerCard(imagesDir + "9D.png", "9", "D", 30)

        val _9C = PokerCard(imagesDir + "9C.png", "9", "C", 29)

        val _8S = PokerCard(imagesDir + "8S.png", "8", "S", 28)

        val _8H = PokerCard(imagesDir + "8H.png", "8", "H", 27)

        val _8D = PokerCard(imagesDir + "8D.png", "8", "D", 26)

        val _8C = PokerCard(imagesDir + "8C.png", "8", "C", 25)

        val _7S = PokerCard(imagesDir + "7S.png", "7", "S", 24)

        val _7H = PokerCard(imagesDir + "7H.png", "7", "H", 23)

        val _7D = PokerCard(imagesDir + "7D.png", "7", "D", 22)

        val _7C = PokerCard(imagesDir + "7C.png", "7", "C", 21)

        val _6S = PokerCard(imagesDir + "6S.png", "6", "S", 20)

        val _6H = PokerCard(imagesDir + "6H.png", "6", "H", 19)

        val _6D = PokerCard(imagesDir + "6D.png", "6", "D", 18)

        val _6C = PokerCard(imagesDir + "6C.png", "6", "C", 17)

        val _5S = PokerCard(imagesDir + "5S.png", "5", "S", 16)

        val _5H = PokerCard(imagesDir + "5H.png", "5", "H", 15)

        val _5D = PokerCard(imagesDir + "5D.png", "5", "D", 14)

        val _5C = PokerCard(imagesDir + "5C.png", "5", "C", 13)

        val _4S = PokerCard(imagesDir + "4S.png", "4", "S", 12)

        val _4H = PokerCard(imagesDir + "4H.png", "4", "H", 11)

        val _4D = PokerCard(imagesDir + "4D.png", "4", "D", 10)

        val _4C = PokerCard(imagesDir + "4C.png", "4", "C", 9)

        val _3S = PokerCard(imagesDir + "3S.png", "3", "S", 8)

        val _3H = PokerCard(imagesDir + "3H.png", "3", "H", 7)

        val _3D = PokerCard(imagesDir + "3D.png", "3", "D", 6)

        val _3C = PokerCard(imagesDir + "3C.png", "3", "C", 5)

        val _2H = PokerCard(imagesDir + "2H.png", "2", "H", 3)

        val _2D = PokerCard(imagesDir + "2D.png", "2", "D", 2)

        val _2C = PokerCard(imagesDir + "2C.png", "2", "C", 1)

        val _2S = PokerCard(imagesDir + "2S.png", "2", "S", 4)
    }


}