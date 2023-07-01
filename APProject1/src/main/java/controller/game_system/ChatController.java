package controller.game_system;

import client.Client;
import com.google.gson.Gson;
import controller.ControllerUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.DataBase;
import model.User;
import model.chat.Chat;
import model.chat.ChatType;
import model.chat.Message;
import model.chat.Messenger;
import view.ViewUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatController extends ControllerUtils {


    @FXML
    private TextArea messageBox;
    @FXML private Label usernameLabel;
    @FXML private Label onlineCountLabel;
    @FXML private ListView userList;
    @FXML private ImageView userImageView;
    @FXML private Pane chatPanes;

    private ListView chatPane;
    @FXML
    BorderPane borderPane;
    @FXML
    ComboBox statusComboBox;
    
    @FXML
    public void initialize(){
        setCurrentUserHBOX();
        setChatList();
    }

    private void setCurrentUserHBOX() {
        if(currentUser == null){
            return;
        }
        userImageView.setImage(new Image(ChatController.class.getResource(currentUser.getUrl()).toExternalForm()));
    }

    public void setUsernameLabel(String username) {
        this.usernameLabel.setText(username);
    }

    public void sendButtonAction(ActionEvent actionEvent) throws IOException {
        Message message = new Message(ChatController.currentUser,messageBox.getText());
        messageBox.setText("");
        addMessage(message);
    }

    private void setChatList(){
        Platform.runLater(() -> {
                for(Chat chat:DataBase.getUnloadDataBase().getMessenger().getChats()){
                    addChat(chat,chat.getId());
                    for(Message message:chat.getMessages()){
                        try {
                            System.out.println(chat.getId());
                            addMessage(message);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                chatPane =(ListView) chatPanes.getChildren().get(0);
                chatPanes.getChildren().get(0).setManaged(true);
                chatPanes.getChildren().get(0).setVisible(true);

        });
    }

    public void addChat(Chat chat, String id){
        ListView<HBox> listView = new ListView<>();
        listView.setMaxHeight(1.7976931348623157E308); listView.setMaxWidth(1.7976931348623157E308);
        HBox newHBox = new HBox();
        newHBox.getChildren().add(new Text("sssssssssss"+id));
        listView.getItems().add(newHBox);
        listView.prefHeight(200); listView.prefWidth(200);
        listView.setVisible(false); listView.setManaged(false);
        listView.setId(id);
        chatPanes.getChildren().add(listView);
        HBox hBox = new HBox();
        hBox.setId(id);
        hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(Node node: chatPanes.getChildren()){
                    if(node.getId().equals(hBox.getId())) {
                        node.setManaged(true);
                        node.setVisible(true);
                        chatPane = (ListView) node;
                    } else {
                        node.setVisible(false); node.setManaged(false);
                    }
                }
            }
        });
        String chatName,imageUrl;
        if(chat.getChatType().equals(ChatType.PRIVATE)){
            if(chat.getMembers().get(0).getUsername().equals(ControllerUtils.getCurrentUser().getUsername())){
                chatName = chat.getMembers().get(1).getUsername();
                imageUrl = chat.getMembers().get(1).getUrl();
            } else {
                chatName = chat.getMembers().get(0).getUsername();
                imageUrl = chat.getMembers().get(0).getUrl();
            }
        }else{
            chatName = chat.getName();
            imageUrl = chat.getImageUrl();
        }

        Text name = new Text(chatName);

//        ImageView statusImageView = new ImageView();
//        Image statusImage = new Image(getClass().getClassLoader().getResource("images/" + user.getStatus().toString().toLowerCase() + ".png").toString(), 16, 16,true,true);
//        statusImageView.setImage(statusImage);

        ImageView pictureImageView = new ImageView();
        System.out.println(imageUrl);
        Image image = new Image(ChatController.class.getResource(imageUrl).toString(),50,50,true,true);
        pictureImageView.setImage(image);

        hBox.getChildren().addAll( pictureImageView, name);
        hBox.setAlignment(Pos.CENTER_LEFT);
        userList.getItems().add(hBox);
    }

    public void addMessage(Message message) throws IOException {
        HBox hBox = new HBox();
        Text contentText = new Text(message.getContent());
        contentText.setFont(Font.font(18));
        hBox.getChildren().addAll(new Text(message.getSender().getUsername() + "  :  " ),
                contentText,new Text("     send time:"+message.getSendTime()));
        if(chatPane ==null){
            chatPane =(ListView) chatPanes.getChildren().get(0);
        }
        chatPane.getItems().add(hBox);
        Chat chat = Messenger.getChatById(chatPane.getId());
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(message);
        System.out.println(chatPane.getId());
        chat.setMessages(messages);
        DataBase.saveDataBase();
        Client.getClient().updateDataBase("sendMessenger",new Gson().toJson(DataBase.getUnloadDataBase().getMessenger()));
        HashMap<String,String> inputs = new HashMap<>();
        inputs.put("chatId",chat.getId());
        inputs.put("sender",ControllerUtils.getCurrentUser().getUsername());
        inputs.put("content",message.getContent());
        ArrayList<User> users= new ArrayList<>();
        for(User user:chat.getMembers()){
            if(!user.getUsername().equals(ControllerUtils.getCurrentUser().getUsername())){
                users.add(user);
            }
        }
        Client.getClient().ForceUsersToDo("controller.ChatController","addOthersMessage",inputs,users);
    }

    public static void addOthersMessage() throws IOException {
        Message message = new Message(User.getUserByUsername(inputs.get("sender")),inputs.get("content"));
        new ChatController().addMessage(message);
    }

}
