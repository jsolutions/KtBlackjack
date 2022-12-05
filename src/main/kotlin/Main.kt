var round = 1

fun main(args: Array<String>) {

    // Initialize the deck
    val deck = Deck()
    deck.apply{
        // TODO: Deck size should be player configurable.
        makeDeck(1)
        shuffle()
        printShuffledDeck()
    }
    // Count the rounds while game is running


    // for (connection in connections)...add player to list of players. TODO!
    val player = Player(deck)
    player.setName()

    // Of course we need this guy
    val dealer = Dealer(player, deck)
    println("This is round: $round\n")
        dealer.deal()
        player.play(dealer)

}