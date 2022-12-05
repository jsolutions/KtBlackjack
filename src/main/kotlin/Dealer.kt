class Dealer(val player: Player, val deck: Deck) {


    fun deal() {
        var i = 0

        while (i < 2) {
            val cardDealt = deck.playableDeck.random()
            player.hand.add(cardDealt)
            deck.playableDeck.remove(cardDealt)
            i++
        }

    }

}