package com.gb.agile.craft_master.tgbot.fsm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gb.agile.craft_master.tgbot.utils.MessageDto;
import com.gb.agile.craft_master.tgbot.utils.RestRequests;
import com.gb.agile.craft_master.tgbot.utils.TelegramUtils;
import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.*;


import java.util.List;

public class UserDialogTransitions {

    private final RestRequests restRequests = new RestRequests();

    public boolean Login(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);

        if (msg.getText().equals("/start")) {
            String userName = "Error";
            String login = msg.getChatId();
            String password = msg.getChatId(); //TODO: unsecured
            try{
                userName = request.getIn().getBody(IncomingMessage.class).getFrom().getUsername();
                restRequests.createUser(login, userName, password);
                restRequests.loginUser(login, password);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setText(String.format("%s logged in", userName));
            response.setChatId(msg.getChatId());
            response.setReplyMarkup(TelegramUtils.getInlineKeyboardMarkup(List.of("Заказчик", "Исполнитель")));
            return true;
        }
        return false;
    }

    public boolean GetRole(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);
        response.setText(msg.getText());
        response.setChatId(msg.getChatId());
        return true;
    }

    public boolean Restart(Exchange request, OutgoingTextMessage response) {
        response.setText("Restart");
        return true;
    }
}
