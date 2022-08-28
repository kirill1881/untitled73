import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;

public class CheckCourse {

    public SendMessage execute(Update update) throws Exception{


        URL url = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");
        String text = "";
        switch (update.getMessage().getText()){
            case "Доллар сша":
                url = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");
                text = "Курс доллара США - ";
                break;
            case "Российский рубль":
                url = new URL("https://www.nbrb.by/api/exrates/rates/rub?parammode=2");
                text = "Курс Рубля - ";
                break;
            case "Евро":
                url = new URL("https://www.nbrb.by/api/exrates/rates/eur?parammode=2");
                text = "Курс Евро - ";
                break;
        }

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(url.openStream()));
        String str = bufferedReader.readLine();

        JSONObject jsonObject = new JSONObject(str);
        String cur = jsonObject.get("Cur_OfficialRate").toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(text+cur);
        return sendMessage;
    }
}
