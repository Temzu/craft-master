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
        String transition = state.name();
        try {
            if (!(exchange.getIn().getBody() instanceof IncomingCallbackQuery)) {
                User from = exchange.getIn().getBody(IncomingMessage.class).getFrom();
                if (from.isBot()) throw new RuntimeException("Bots are not allowed");
            }
//            exchange.getIn().getBody(IncomingMessage.class)
            state = state.nextState(exchange, msg);

        } catch (Exception e) {
            msg.setText(e.getMessage());
        } finally {
            transition = '\n' + transition + "->" + state.name();
            if (msg.getText() == null) msg.setText("Error");
            msg.setText(msg.getText() + transition);
            exchange.getIn().setBody(msg);
        }
    }
}
