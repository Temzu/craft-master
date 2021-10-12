package com.gb.agile.craft_master.tgbot.routers;

import com.gb.agile.craft_master.tgbot.processors.MessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TelegramRouter extends RouteBuilder {

    private final MessageProcessor messageProcessor;

    public TelegramRouter(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void configure() {
        onException(RuntimeException.class)
                .log("Exception!!! ${exception.message}");

        from("telegram:bots?authorizationToken={{telegram.key}}:{{telegram.token}}")
                .log("Incoming message ${body}")
                .process(messageProcessor)
                .to("telegram:bots?authorizationToken={{telegram.key}}:{{telegram.token}}&chatId=${header.CamelTelegramChatId}");
    }
}
