package com.gb.agile.craft_master.tgbot.fsm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gb.agile.craft_master.tgbot.entities.MessageDto;
import com.gb.agile.craft_master.tgbot.entities.OfferDto;
import com.gb.agile.craft_master.tgbot.entities.UserDto;
import com.gb.agile.craft_master.tgbot.utils.BotCharacters;
import com.gb.agile.craft_master.tgbot.utils.RestRequests;
import com.gb.agile.craft_master.tgbot.utils.TelegramUtils;
import org.apache.camel.component.telegram.model.*;


import java.math.BigDecimal;
import java.util.List;

public class UserDialogTransitions {

    private final RestRequests restRequests = new RestRequests();
    private OfferDto offer;

    public boolean login(MessageDto msg, OutgoingTextMessage response) {
        boolean result = false;
        if (msg.getText().equals("/start")) {
            String userName = "Error";
            String login = msg.getChatId();
            String password = msg.getChatId(); //TODO: unsecured
            try {
                userName = msg.getUserName();
                if (!restRequests.loginUser(login, password)) {
                    restRequests.createUser(login, userName, password);
                    restRequests.loginUser(login, password);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setText(String.format("Вы вошли в систему как %s\nВаша роль?", userName));
            response.setReplyMarkup(TelegramUtils.getInlineKeyboardMarkup(List.of("Заказчик", "Исполнитель")));
            result = true;
        }
        return result;
    }

    public boolean getRole(MessageDto msg, OutgoingTextMessage response) {
        String text = "Error";
        boolean result = msg.getText().equals("Исполнитель");
        try {
            if (result) text = "Размещенные предложения:\n" + restRequests.getOffers();
            else {
                response.setReplyMarkup(
                        TelegramUtils.getInlineKeyboardMarkup(List.of("Создать заявку", "Акцептовать заявку")));
                text = "Ваше действие?";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setText(text);
        return result;
    }

    public boolean chooseOrderItem(MessageDto msg, OutgoingTextMessage response) {
        response.setText("Вы откликнулись на предложение: " + msg.getText() + '\n' + "Введите любой текст для перезапуска");
        restRequests.createBid(Integer.parseInt(msg.getText()),msg.getChatId());
        return true;
    }

    public boolean transparent(MessageDto msg, OutgoingTextMessage response) {
        response.setText("Выберите роль: ");
        response.setReplyMarkup(TelegramUtils.getInlineKeyboardMarkup(List.of("Заказчик", "Исполнитель")));
        return true;
    }

    public boolean getOfferCategory(MessageDto msg, OutgoingTextMessage response) {
        boolean result = true;
        String text = "Error";
        try {
            text = restRequests.getOccupations(Integer.parseInt(msg.getText()));
            result = (!text.contains(BotCharacters.OCCUPATION_LIST_SIGN.value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setText(text);
        return result;
    }

    public boolean chooseOfferItem(MessageDto msg, OutgoingTextMessage response) {
        response.setText("Вы выбрали категорию: " + msg.getText() + '\n' + "Введите описание заказа: ");
        offer = new OfferDto();
        UserDto creator = new UserDto();
        creator.setLogin(msg.getChatId());
        offer.setCreator(creator);
        offer.setOccupationId(Long.parseLong(msg.getText()));
        return true;
    }

    public boolean placeOfferDetails(MessageDto msg, OutgoingTextMessage response) {
        offer.setTitle("Заказ, категория " + offer.getOccupationId());
        offer.setDescription(msg.getText());
        response.setText("Введите стоимость заказа: ");
        return true;
    }

    public boolean placeOfferPrice(MessageDto msg, OutgoingTextMessage response) {
        offer.setPrice(new BigDecimal(msg.getText()));
        //TODO:offer.setCreator();
        response.setText("Вы оформили заказ: " + offer.toString() + '\n' + "Введите любой текст для перезапуска");
        restRequests.createOffer(offer);
        return true;
    }

    public boolean getExecutorAction(MessageDto msg, OutgoingTextMessage response) {
        boolean result = msg.getText().equals("Создать заявку");
        String text = "Error";
        try {
            text = result
                    ?
                    "Выберите категорию :\n" + restRequests.getOccupations(null)
                    :
                    "Отклики на ваши заявки:\n" + restRequests.getBids() + "\n";
        } catch (Exception e) {

        }
        response.setText(text);
        return result;
    }

    public boolean chooseBid(MessageDto msg, OutgoingTextMessage response) {
        restRequests.acceptBid(Integer.parseInt(msg.getText()));
        response.setText("Акцептована заявка : " + msg.getText());
        return true;
    }
}
