package com.gb.agile.craft_master.tgbot.fsm;

import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TgCamelDemoFsmTests {

//    @Test
//    void transitions() {
//        OutgoingTextMessage response = new OutgoingTextMessage();
//        Object request = new Object();
//        UserDialogState state = UserDialogState.Start;
//        state = state.nextState((Exchange) request, response);
//        assertEquals(UserDialogState.GetRole, state);
//        state = state.nextState((Exchange) request, response);
//        assertEquals(UserDialogState.ChooseItem, state);
//        state = state.nextState((Exchange) request, response);
//        assertEquals(UserDialogState.Final, state);
//    }
//
//    @Test
//    void notTransitions() {
//        OutgoingTextMessage response = new OutgoingTextMessage();
//        Object request = new Object();
//        UserDialogState state = UserDialogState.Start;
//        state = state.nextState((Exchange) request, response);
//        assertEquals(UserDialogState.Start, state);
//        state = UserDialogState.Start;
//        state = state.nextState((Exchange) request, response);
//        assertEquals(UserDialogState.LoggedIn, state);
//        state = UserDialogState.GetRole;
//        state = state.nextState((Exchange) request, response);
//        assertEquals(UserDialogState.GetRole, state);
//    }

}
