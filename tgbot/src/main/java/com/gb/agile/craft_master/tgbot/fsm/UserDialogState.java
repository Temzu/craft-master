package com.gb.agile.craft_master.tgbot.fsm;

import com.gb.agile.craft_master.tgbot.entities.MessageDto;
import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;

public enum UserDialogState {

    Start {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? GetRole : Start;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.login(msg, response);
        }
    },

    GetRole {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? ChooseOrderItem : GetExecutorAction;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.getRole(msg, response);
        }
    },

    GetExecutorAction {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? ChooseOfferCategory : ChooseBid;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.getExecutorAction(msg, response);
        }
    },

    ChooseBid {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? Start : Final;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.chooseBid(msg, response);
        }
    },

    ChooseOrderItem {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? Final : Start;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.chooseOrderItem(msg, response);
        }
    },

    Final {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? GetRole : Start;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.transparent(msg, response);
        }
    },

    ChooseOfferCategory {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? ChooseOffers : ChooseOfferCategory;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.getOfferCategory(msg, response);
        }
    },

    ChooseOffers {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg, response) ? PlaceOffer : Start;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.chooseOfferItem(msg, response);
        }
    },

    PlaceOffer {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg,response) ? PlaceOfferDetails : Start;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.placeOfferDetails(msg, response);
        }
    },
    PlaceOfferDetails {
        @Override
        public UserDialogState nextState(MessageDto msg, OutgoingTextMessage response) {
            return transitAction(msg,response) ? Final : Start;
        }

        @Override
        public boolean transitAction(MessageDto msg, OutgoingTextMessage response) {
            return userDialogTransitions.placeOfferPrice(msg, response);
        }
    };

    public final static UserDialogTransitions userDialogTransitions = new UserDialogTransitions();

    public abstract UserDialogState nextState(MessageDto request, OutgoingTextMessage response);

    public abstract boolean transitAction(MessageDto request, OutgoingTextMessage response);
}

