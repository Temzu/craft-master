package com.gb.agile.craft_master.tgbot.processors;

import com.gb.agile.craft_master.tgbot.fsm.UserDialogState;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.telegram.model.*;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor implements Processor {

    private UserDialogState state;

    MessageProcessor() {
        state = UserDialogState.Start;
    }

    @Override
    public void process(Exchange exchange) throws RuntimeException {
        OutgoingTextMessage msg = new OutgoingTextMessage();
        // DEBUG String transition = state.name();
        try {
            if (!(exchange.getIn().getBody() instanceof IncomingCallbackQuery)) {
                IncomingMessage msgIn = exchange.getIn().getBody(IncomingMessage.class);
                User from = msgIn.getFrom();
                if (from.isBot()) throw new RuntimeException("Bots are not allowed");
                if (msgIn.getText().equals("/exit")) {
                    state = UserDialogState.Start;
                    msg.setText("Введите /start");
                }
            }

            state = state.nextState(exchange, msg);

        } catch (Exception e) {
            msg.setText(e.getMessage());
        } finally {
            // DEBUG transition = '\n' + transition + "->" + state.name();
            if (msg.getText() == null) msg.setText("Error");
            // DEBUG msg.setText(msg.getText() + transition);
            msg.setText(msg.getText());
            exchange.getIn().setBody(msg);
        }
    }
}
