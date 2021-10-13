package com.gb.agile.craft_master.tgbot.fsm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gb.agile.craft_master.tgbot.entities.MessageDto;
import com.gb.agile.craft_master.tgbot.utils.RestRequests;
import com.gb.agile.craft_master.tgbot.utils.TelegramUtils;
import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.*;


import java.util.List;

public class UserDialogTransitions {

    private final RestRequests restRequests = new RestRequests();

    public boolean login(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);
        boolean result = false;
        if (msg.getText().equals("/start")) {
            String userName = "Error";
            String login = msg.getChatId();
            String password = msg.getChatId(); //TODO: unsecured
            try {
                userName = request.getIn().getBody(IncomingMessage.class).getFrom().getUsername();
                if (!restRequests.loginUser(login, password)) {
                    restRequests.createUser(login, userName, password);
                    restRequests.loginUser(login, password);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setText(String.format("Вы вошли в систему как %s\nВаша роль?", userName));
            response.setChatId(msg.getChatId());
            response.setReplyMarkup(TelegramUtils.getInlineKeyboardMarkup(List.of("Заказчик", "Исполнитель")));
            result = true;
        }
        return result;
    }

    public boolean getRole(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);
        String text = "Error";
        boolean result = msg.getText().equals("Исполнитель");
        try {
            text = result ?
                    "Размещенные предложения:\n" + restRequests.getOffers()
                    :
                    "Выберете категорию :\n" + restRequests.getOccupations(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setText(text);
        response.setChatId(msg.getChatId());
        return result;
    }

    public boolean chooseOrderItem(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);
        response.setText("Вы откликнулись на предложение: " + msg.getText() + '\n' + "Введите любой текст для перезапуска");
        response.setChatId(msg.getChatId());
        return true;
    }

    public boolean transparent(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);
        response.setChatId(msg.getChatId());
        response.setText("Выберите роль: " + msg.getText());
        response.setReplyMarkup(TelegramUtils.getInlineKeyboardMarkup(List.of("Заказчик", "Исполнитель")));
        return true;
    }

    public boolean getOfferCategory(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);
        String text = "Error";
        try {
            text = restRequests.getOccupations(Integer.parseInt(msg.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setText(text);
        response.setChatId(msg.getChatId());
        return true;
    }

    public boolean chooseOfferItem(Exchange request, OutgoingTextMessage response) {
        MessageDto msg = new MessageDto(request);
        response.setText("Вы выбрали категорию: " + msg.getText() + '\n' + "Введите любой текст для перезапуска");
        response.setChatId(msg.getChatId());
        return true;
    }
}
