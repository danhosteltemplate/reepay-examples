package com.reepay.examples.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by mikkel on 11/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardList {

    private List<Card> cards;

    public CardList() {
    }

    public CardList(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }
}
