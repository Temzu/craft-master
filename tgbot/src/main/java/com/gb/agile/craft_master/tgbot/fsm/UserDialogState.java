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
            return transitAction(request, response) ? ChooseItem : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.getRole(request, response);
        }
    },

    ChooseItem {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? Final : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.chooseItem(request, response);
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
    };

    public final static UserDialogTransitions userDialogTransitions = new UserDialogTransitions();
    public abstract UserDialogState nextState(Exchange request, OutgoingTextMessage response);

    public abstract boolean transitAction(Exchange request, OutgoingTextMessage response);
}

