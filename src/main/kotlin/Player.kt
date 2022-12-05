
class Player(var deck: Deck) {

    var name: String = ""
    var id: String? = null

    var hand: MutableList<String> = mutableListOf()
    var handValue: Int = 0

    var bust = false
    var stay = false

    var wallet: Int = 100
    var bet: Int = 0

    var aceValue = 11

    fun setName() {

        println("What is your name?\n")
        var newname = readln()
        name = newname

        /* TODO: Implement id system that takes the machine profile
           TODO: to create save games. Cached cookies? Auth? */

    }

    private fun displayHand() {

        for (card in hand) {

            println("$name has $card.")

        }

        getCount()
        println(handValue.toString())
    }

    fun play(dealer: Dealer) {
        val actionText =
            "What would you like to do, $name?: \n" +
                "1: Hit \n" +
                "2: Stay \n" +
                "3: Double Down \n"

        while (!bust || !stay) {

            var selection = 0
            displayHand()

            println(
                actionText
            )

            when(readln()) {
                "1" -> hit()
                "2" -> stay()
                "3" -> doubleDown()
                else -> {
                    println(
                        "Please input one of the following options:\n"
                                + actionText)
                    play(dealer)
                }
            }

            getCount()
            if (handValue == 21) {
                displayHand()
                println("Black NYACK!")
                playAgain(dealer)
                return
            }

            if (handValue > 21) {
                displayHand()
                println("BUST!")
                playAgain(dealer)
            }


        }
    }

    fun reset() {
        hand.clear()
        handValue = 0
        this.bust = false
        this.stay = false
        aceValue = 11
        bet = 0
        deck.apply {
            makeDeck(fullDecks)
            shuffle()
        }
    }

    fun playAgain(dealer: Dealer) {
        println("Play again? (Y \\ N) \n")
        when(readln()) {
            "Y" -> {
                round++
                reset()
                dealer.deal()
                println("This is round: $round\n")
                play(dealer)
            }
            "N" -> return
            else -> playAgain(dealer)
        }

    }

    // Actions
    private fun hit() {
        val card = deck.playableDeck.random()
        hand.add(card)
        deck.playableDeck.remove(card)

        if (deck.playableDeck.size <= 0) {
            deck.makeDeck(deck.fullDecks)
            deck.shuffle()
        }

    }

    private fun stay() {
        hand.sort()
        handValue = 0
        for(card in hand) {
            getCount()
        }
        this.stay = true;
    }

    private fun doubleDown() {
        bet *= 2
        hit()
    }

    // Count the cards, add the values. TODO: FIX.
    private fun getCount() {
        handValue = 0
        var countableMap: MutableMap<String, Int> = mutableMapOf()
        var i = 0

        // Add all the cards
        for (card in hand) {

            var value = 0
            var cardValue = card.split(" ").first()
            when(cardValue) {
                "J" -> value = 10
                "K" -> value = 10
                "Q" -> value = 10
                "A" -> value = aceValue
                else -> value = cardValue.toInt()
            }

            countableMap[card] = value

            i++
        }

        handValue = countableMap.values.sum()

        if (countableMap.values.sum() > 21) {
            if (aceValue == 11) {
                aceValue = 1
                getCount()
                return
            }
            bust = true
            stay = true

        }

    }



}