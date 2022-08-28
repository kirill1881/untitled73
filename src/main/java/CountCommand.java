import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CountCommand {
    public SendMessage execute(Update update, int a) throws Exception{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        URL url = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");

        String text = "";
        switch (a){
            case 1:
                url = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");
                text = "USD";
                break;
            case 2:
                url = new URL("https://www.nbrb.by/api/exrates/rates/eur?parammode=2");
                text = "EUR";
                break;
            case 3:
                url = new URL("https://www.nbrb.by/api/exrates/rates/rub?parammode=2");
                text = "RUB";
                break;
        }
        BufferedReader bufferedReader = new
                BufferedReader(new InputStreamReader(url.openStream()));
        JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
        String course = jsonObject.get("Cur_OfficialRate").toString();
        double sum = Double.parseDouble(update.getMessage().getText());
        double sum1 = Double.parseDouble(course);
        sendMessage.setText("За "+sum+" BYN "+" вы получите "+sum/sum1+text);
        return sendMessage;
    }
}
