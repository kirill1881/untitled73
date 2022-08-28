import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

public class CountCourse {

    public SendMessage execute(Update update){
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("BYN -> USD"));
        keyboardRow.add(new KeyboardButton("BYN -> EUR"));
        keyboardRow.add(new KeyboardButton("BYN -> RUB"));
        keyboardRow.add(new KeyboardButton("Главная страница"));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Выберите интересующую конверсию");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
