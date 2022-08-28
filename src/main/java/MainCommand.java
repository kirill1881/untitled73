import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

public class MainCommand {
    public SendMessage execute(Update update){
        SendMessage sendMessage = new SendMessage();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Просмотр курса");
        keyboardRow.add("Расчет курса");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));

        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Выберите команду");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
