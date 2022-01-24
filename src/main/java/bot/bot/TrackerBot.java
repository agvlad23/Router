package bot.bot;

import bot.commands.CommandParser;
import bot.model.Person;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import bot.roles.Role;
import bot.services.SendUserMessageImpl;
import router.client.Client;
import router.client.RestClient;
import router.client.SoapClient;
import router.client.generated.RoleEnum;
import router.model.Tracking;
import router.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

import static org.glassfish.grizzly.http.util.Header.Date;

public class TrackerBot extends TelegramLongPollingBot {
    protected String botName ;
    protected String botToken;
    public static final String botPrefix="/";

    public TrackerBot()  {
        SendUserMessageImpl.init(this);

        Properties properties = new Properties();
        var rootPath2=
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader())
                        ;
        String rootPath=
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource("local.properties")).getPath();
        try {
            properties.load(new FileInputStream(rootPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        botToken = properties.getProperty("Tracker_Token");
        botName = properties.getProperty("Tracker_Name");
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            String telegramID = update.getMessage().getChat().getUserName();
            var user=Client.getUser(telegramID);
            Person person;
            if (user!=null)
                person=new Person(user,update,Role.toRole(user.getUserRole()));
            else  person=new Person(user,update,Role.USER);

            if (messageText.startsWith(botPrefix)){
                CommandParser.getCommandClass(messageText).req(person);
            }

        } else if (update.hasCallbackQuery()) {

            String call_data = update.getCallbackQuery().getData();
            String  message_id = update.getCallbackQuery().getMessage().getMessageId().toString();
            String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
            System.out.println(update.getCallbackQuery().getMessage().getText());
            System.out.println(update.getCallbackQuery().getMessage().getEntities());

            if (call_data.equals("update_msg_text")) {
                String answer = "Updated message text";

                EditMessageText new_message = new EditMessageText();
                new_message.setChatId(chat_id);
                new_message.setMessageId(Integer.parseInt(message_id));
                new_message.setText(answer);

                SendUserMessageImpl.sendMessage(new_message);

            }else
            if (call_data.equals("update_end_task")) {

                EditMessageText new_message = new EditMessageText();
                new_message.setChatId(chat_id);
                new_message.setMessageId(Integer.parseInt(message_id));
                String answer = "Registered task: "+ update.getCallbackQuery().getMessage().getText();
                new_message.setText(answer);
                System.out.println(new Date(update.getCallbackQuery().getMessage().getDate()*1000L));
                var temp=update.getCallbackQuery().getMessage().getDate()*1000L;
                Date time=new Date(temp);


                var userTracker=new User();
                userTracker.setUserId(update.getCallbackQuery().getFrom().getUserName());
                userTracker.setName(update.getCallbackQuery().getFrom().getFirstName());
                Tracking t= new Tracking();
                t.setDate(new java.sql.Date(temp));
                t.setStartTime(new Time(time.getTime()));
                t.setEndTime(new Time(System.currentTimeMillis()));
                t.setMessage(update.getCallbackQuery().getMessage().getText());
                var tt= new ArrayList<Tracking>();
                tt.add(t);
                userTracker.setTracking(tt);

                RestClient.addUser(userTracker);

                SendUserMessageImpl.sendMessage(new_message);


            }else if(call_data.contains("_update_selected")){
                var s=call_data.substring(0,call_data.indexOf("_update"));
                int line=Integer.parseInt(s.split("_")[0]);
                int row=Integer.parseInt(s.split("_")[1]);
                var markup=update.getCallbackQuery().getMessage().getReplyMarkup();
                var keyboard=markup.getKeyboard();
                var text=keyboard.get(line).get(row).getText();
                var lenght=text.length()-1;
                var text2=text.split("✔");
                if(text.charAt(lenght)=='✔')
                    keyboard.get(line).get(row).setText(text.substring(0,lenght) + " ");
                else
                    keyboard.get(line).get(row).setText(text.substring(0,lenght+1) + "✔");

                var editMessage= new EditMessageReplyMarkup();
                editMessage.setChatId(chat_id);
                editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                editMessage.setReplyMarkup(markup);

                SendMessage gs=new SendMessage();
                gs.setChatId(chat_id);
                gs.setReplyMarkup(markup);
                gs.setText("");
                SendUserMessageImpl.sendMessage(editMessage);
            }
            else if (call_data.contains("_update_move")){

                var s=call_data.substring(0,call_data.indexOf("_update"));
               String group=s.split("_")[0];
                String text2;
                var markup=update.getCallbackQuery().getMessage().getReplyMarkup();
                var keyboard=markup.getKeyboard();
                List<String> telegramId=new ArrayList<>();
                for (int i=0;i<keyboard.size();i++){
                    for (int j=0;j<keyboard.get(i).size();j++){
                        var el=keyboard.get(i).get(j);
                        if(el.getCallbackData().contains("update_selected")
                                &&el.getText().charAt(el.getText().length()-1)=='✔'){
                            var telegramID_s=el.getCallbackData().split("telegramID_");
                            telegramId.add(telegramID_s[telegramID_s.length-1]);
                            text2=el.getText().substring(0,el.getText().lastIndexOf(" "));
                            el.setText(text2+" "+group);

                        }
                    }
                }

                Client.setGroup(telegramId,group);

                var editMessage= new EditMessageReplyMarkup();
                editMessage.setChatId(chat_id);
                editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                editMessage.setReplyMarkup(markup);

                SendMessage gs=new SendMessage();
                gs.setChatId(chat_id);
                gs.setReplyMarkup(markup);
                gs.setText(" asd");
                SendUserMessageImpl.sendMessage(editMessage);

            }else if(call_data.equals("update_delete_selected")){


                var markup=update.getCallbackQuery().getMessage().getReplyMarkup();
                var keyboard=markup.getKeyboard();

                List<String> telegramIdToDelete=new ArrayList<>();
                for (int i=0;i<keyboard.size();i++){
                    for (int j=0;j<keyboard.get(i).size();j++){
                        var el=keyboard.get(i).get(j);
                        if(el.getCallbackData().contains("update_selected")
                                &&el.getText().charAt(el.getText().length()-1)=='✔'){
                            var telegramID_s=el.getCallbackData().split("telegramID_");
                            telegramIdToDelete.add(telegramID_s[telegramID_s.length-1]);
                            keyboard.get(i).remove(j);
                        }
                    }
                }
                Client.deleteUser(telegramIdToDelete);

                var editMessage= new EditMessageReplyMarkup();
                editMessage.setChatId(chat_id);
                editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                editMessage.setReplyMarkup(markup);

                SendMessage gs=new SendMessage();
                gs.setChatId(chat_id);
                gs.setReplyMarkup(markup);
                gs.setText(" asd");
                SendUserMessageImpl.sendMessage(editMessage);
            }else if(call_data.equals("update_deleteGroup_selected")){

                var markup=update.getCallbackQuery().getMessage().getReplyMarkup();
                var keyboard=markup.getKeyboard();

                List<String> groupNameToDelete=new ArrayList<>();
                for (int i=0;i<keyboard.size();i++){
                    for (int j=0;j<keyboard.get(i).size();j++){
                        var el=keyboard.get(i).get(j);
                        if(el.getCallbackData().contains("update_selected")
                                &&el.getText().charAt(el.getText().length()-1)=='✔'){
                            var groupName_s=el.getCallbackData().split("groupName_");
                            groupNameToDelete.add(groupName_s[groupName_s.length-1]);
                            keyboard.get(i).remove(j);
                        }
                    }
                }
                Client.deleteGroup(groupNameToDelete);

                var editMessage= new EditMessageReplyMarkup();
                editMessage.setChatId(chat_id);
                editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                editMessage.setReplyMarkup(markup);

                SendMessage gs=new SendMessage();
                gs.setChatId(chat_id);
                gs.setReplyMarkup(markup);
                gs.setText(" asd");
                SendUserMessageImpl.sendMessage(editMessage);
            }
            else if (call_data.contains("|update_role_selected")){

                var markup=update.getCallbackQuery().getMessage().getReplyMarkup();
                var keyboard=markup.getKeyboard();

                String roleString=call_data.substring(0,call_data.indexOf('|'));
                List<String> telegramIdToSetRole=new ArrayList<>();

                for (int i=0;i<keyboard.size();i++){
                    for (int j=0;j<keyboard.get(i).size();j++){
                        var el=keyboard.get(i).get(j);
                        if(el.getCallbackData().contains("update_selected")
                                &&el.getText().charAt(el.getText().length()-1)=='✔'){
                            var telegramID_s=el.getCallbackData().split("telegramID_");
                            telegramIdToSetRole.add(telegramID_s[telegramID_s.length-1]);
                            el.setText(el.getText().substring(0,el.getText().length()-1));

                        }
                    }
                }

                Client.setRole(telegramIdToSetRole, RoleEnum.valueOf(roleString));

                var editMessage= new EditMessageReplyMarkup();
                editMessage.setChatId(chat_id);
                editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                editMessage.setReplyMarkup(markup);

                SendMessage gs=new SendMessage();
                gs.setChatId(chat_id);
                gs.setReplyMarkup(markup);
                gs.setText(" asd");
                SendUserMessageImpl.sendMessage(editMessage);

            }


        }

    }
}
