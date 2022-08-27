import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "overone152bot";
    }

    @Override
    public String getBotToken() {
        return "5768520678:AAGPUZqpKU2quKdd3PWzXpRBy0KVjvEnTQY";
    }

    @Override
    public void onUpdateReceived(Update update){
        try {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Доллар США"));
            keyboardRow.add(new KeyboardButton("Евро"));
            keyboardRow.add(new KeyboardButton("Российский рубль"));

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));

            URL url = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");
            switch (update.getMessage().getText()){
                case "Доллар сша":
                    url = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");
                    break;
                case "Российский рубль":
                    url = new URL("https://www.nbrb.by/api/exrates/rates/rub?parammode=2");
                    break;
                case "Евро":
                    url = new URL("https://www.nbrb.by/api/exrates/rates/eur?parammode=2");
                    break;
            }

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(url.openStream()));
            String str = bufferedReader.readLine();

            JSONObject jsonObject = new JSONObject(str);
            String cur = jsonObject.get("Cur_OfficialRate").toString();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText(cur);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            execute(sendMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
