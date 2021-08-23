package com.bignerdranch.android.shareddata.commands.cards;

public class TrainCarCard implements ITrainCarCard, java.io.Serializable {
    CardType cardType;

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
