package com.gb.agile.craft_master.tgbot.fsm;

import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;

public enum UserDialogState {

    Start {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? LoggedIn : Start;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.Login(request, response);
        }
    },

    LoggedIn {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? GetRole : LoggedIn;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.GetRole(request, response);
        }
    },

    GetRole {
        @Override
        public UserDialogState nextState(Exchange request, OutgoingTextMessage response) {
            return transitAction(request, response) ? Start : GetRole;
        }

        @Override
        public boolean transitAction(Exchange request, OutgoingTextMessage response) {
            return userDialogTransitions.Restart(request, response);
        }
    };

    public final static UserDialogTransitions userDialogTransitions = new UserDialogTransitions();
    public abstract UserDialogState nextState(Exchange request, OutgoingTextMessage response);

    public abstract boolean transitAction(Exchange request, OutgoingTextMessage response);
}

