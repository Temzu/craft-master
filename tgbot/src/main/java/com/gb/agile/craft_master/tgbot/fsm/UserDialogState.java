package com.gb.agile.craft_master.tgbot.fsm;

import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;

public enum UserDialogState {

    Start {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? GetRole : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.login(request, response);
        }
    },

    GetRole {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? ChooseOrderItem : ChooseOfferCategory;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.getRole(request, response);
        }
    },

    ChooseOrderItem {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? Final : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.chooseOrderItem(request, response);
        }
    },

    Final {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? GetRole : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.transparent(request, response);
        }
    },

    ChooseOfferCategory {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? ChooseOffers : ChooseOfferCategory;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.getOfferCategory(request, response);
        }
    },

    ChooseOffers {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? PlaceOffer : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.chooseOfferItem(request, response);
        }
    },

    PlaceOffer {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request,response) ? PlaceOfferDetails : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.placeOfferDetails(request, response);
        }
    },
    PlaceOfferDetails {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request,response) ? Final : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.placeOfferPrice(request, response);
        }
    };

    public final static UserDialogTransitions userDialogTransitions = new UserDialogTransitions();

    public abstract UserDialogState nextState(Exchange request, OutgoingTextMessage response);

    public abstract boolean transitAction(Exchange request, OutgoingTextMessage response);
}

