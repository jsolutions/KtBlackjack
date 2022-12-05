class Deck {
    val cardValues = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val cardSuites = listOf("Hearts", "Diamonds", "Clubs", "Spades")
    var playableDeck: MutableList<String> = mutableListOf()
    var fullDecks: Int = 0

    fun makeDeck(numberOfDecks: Int) {

        var numDecks = 1
        var i = 1

        if (numberOfDecks > 1) {
            numDecks = numberOfDecks
        }

        fullDecks = numDecks


        while (i <= numDecks) {
            for(suite in cardSuites) {
                for (value in cardValues) {
                    playableDeck += "$value of $suite"
                }
            }
            i++
        }

    }

    fun shuffle(){
        playableDeck.shuffle()
    }

    fun printShuffledDeck() {
        for (card in playableDeck) {
            println(card)
        }
    }



}

