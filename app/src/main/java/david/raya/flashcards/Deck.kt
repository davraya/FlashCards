package david.raya.flashcards

data class Deck(var subject: String="Lorem Ipsum", val card: MutableList<Card>) {
    fun addCard() {
        val aCard = Card()
        card.add(aCard)
    }

    fun edit(card: Card) {
        val promptFront = "New front text"
        val promptBack = "New back text"

        card.front = promptFront
        card.back = promptBack
    }

}