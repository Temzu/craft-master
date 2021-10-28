package com.gb.agile.craft_master.tgbot.processors;

import com.gb.agile.craft_master.tgbot.entities.MessageDto;
import com.gb.agile.craft_master.tgbot.fsm.UserDialogState;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.telegram.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageProcessor implements Processor {

    private Map<String, UserDialogState> state;

    MessageProcessor() {
        this.state = new HashMap<>();
    }

    @Override
    public void process(Exchange exchange) throws RuntimeException {
        OutgoingTextMessage msg = new OutgoingTextMessage();
        MessageDto msgIn = new MessageDto(exchange);
        msg.setChatId(msgIn.getChatId());

        state.putIfAbsent(msgIn.getChatId(), UserDialogState.Start);

        // DEBUG String transition = state.name();
        try {
            if (!(exchange.getIn().getBody() instanceof IncomingCallbackQuery)) {
                if (msgIn.isBot()) throw new RuntimeException("Bots are not allowed");
                if (msgIn.getText().equals("/exit")) {
                    state.put(msgIn.getChatId(),UserDialogState.Start);
                    msg.setText("Введите /start");
                }
            }
            state.put(msgIn.getChatId(),state.get(msgIn.getChatId()).nextState(msgIn, msg));
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
