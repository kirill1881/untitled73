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

    private static int a = 0;
    @Override
    public void onUpdateReceived(Update update){
        try {
            String message = update.getMessage().getText();
            SendMessage sendMessage = new SendMessage();
            if (message.equals("/start")||message.equals("Главная страница")){
                sendMessage = new MainCommand().execute(update);
            }else if (message.equals("Просмотр курса")){
                sendMessage = new ChooseCurensyCommand().execute(update);
            }else if (message.equals("Доллар США")||
            message.equals("Евро")||message.equals("Российский рубль")){
                sendMessage = new CheckCourse().execute(update);
            }else if (message.equals("Расчет курса")){
                sendMessage = new CountCourse().execute(update);
            }else if (message.equals("BYN -> USD")
            /*message.equals("BYN -> EUR")||
            message.equals("BYN -> RUB")*/){
                a = 1;
                sendMessage.setText("Введите сумму");
                sendMessage.setChatId(update.getMessage().getChatId());
            }else if(message.equals("BYN -> EUR")){
                a = 2;
                sendMessage.setText("Введите сумму");
                sendMessage.setChatId(update.getMessage().getChatId());
            }else if ( message.equals("BYN -> RUB")){
                a = 3;
                sendMessage.setText("Введите сумму");
                sendMessage.setChatId(update.getMessage().getChatId());
            }
            else if (ifNumber(message)){
                sendMessage = new CountCommand().execute(update, a);
            }
            System.out.println(a);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean ifNumber(String str){
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
