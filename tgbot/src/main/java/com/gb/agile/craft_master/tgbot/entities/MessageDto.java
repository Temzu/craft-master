package com.gb.agile.craft_master.tgbot.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.IncomingCallbackQuery;
import org.apache.camel.component.telegram.model.IncomingMessage;

@Setter
@Getter
public class MessageDto {
    private String text;
    private String chatId;

    public MessageDto(Exchange exchange) {
        if (exchange.getIn().getBody() instanceof IncomingMessage) {
            IncomingMessage msg = exchange.getIn().getBody(IncomingMessage.class);
            this.text = msg.getText();
            this.chatId = msg.getChat().getId();
        } else if (exchange.getIn().getBody() instanceof IncomingCallbackQuery) {
            IncomingCallbackQuery msg = exchange.getIn().getBody(IncomingCallbackQuery.class);
            this.text = msg.getData();
            this.chatId = msg.getMessage().getChat().getId();
        }
    }
}
