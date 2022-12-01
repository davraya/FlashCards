package david.raya.flashcards

// Data class that will contain a collection of Cards.
data class Deck(var subject: String="Lorem Ipsum", val card: MutableList<Card>) {
    fun addCard() {
        val aCard = Card()
        card.add(aCard)
    }
    
    // Edits a selected card by prompting for the content.
    fun edit(card: Card) {
        val promptFront = "New front text"
        val promptBack = "New back text"

        card.front = promptFront
        card.back = promptBack
    }

}
