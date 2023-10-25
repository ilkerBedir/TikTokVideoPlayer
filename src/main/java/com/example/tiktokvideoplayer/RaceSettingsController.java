package com.example.tiktokvideoplayer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RaceSettingsController implements Initializable {

    @FXML
    TextField text_stream_name;
    @FXML
    Button button_background_image;
    @FXML
    TextField text_background_image;
    @FXML
    TextField text_timer;
    @FXML
    TextField text_commentary_interval;
    @FXML
    Button button_neutral_music;
    @FXML
    TextField text_neutral_music;
    @FXML
    ComboBox<Integer> combobox_team_count;
    @FXML
    ComboBox<Integer> combobox_gift_count;
    @FXML
    ButtonBar buttonbar_teams;
    @FXML
    TextField text_timer_extend;
    @FXML
    Button button_timer_extend;
    @FXML
    Button button_team1_dialog;
    @FXML
    Button button_team2_dialog;
    @FXML
    Button button_team3_dialog;
    @FXML
    Button button_team4_dialog;
    @FXML
    Button button_team5_dialog;
    @FXML
    Button button_team6_dialog;
    @FXML
    Button button_team7_dialog;
    @FXML
    Button button_team8_dialog;

    @FXML
    private ComboBox combo_gift1;

    @FXML
    private ComboBox combo_gift2;

    @FXML
    private ComboBox combo_gift3;

    @FXML
    private ComboBox combo_gift4;

    @FXML
    private ComboBox combo_gift5;

    @FXML
    private ComboBox combo_gift6;

    @FXML
    private ComboBox combo_gift7;

    @FXML
    private ComboBox combo_gift8;

    @FXML
    private ComboBox combo_gift9;

    @FXML
    private ComboBox combo_gift10;

    @FXML
    private ComboBox combo_gift11;

    @FXML
    private ComboBox combo_gift12;

    @FXML
    private ComboBox combo_gift13;

    @FXML
    private ComboBox combo_gift14;

    @FXML
    private ComboBox combo_gift15;

    @FXML
    private ComboBox combo_gift16;

    @FXML
    private ComboBox combo_gift17;

    @FXML
    private ComboBox combo_gift18;

    @FXML
    private ComboBox combo_gift19;

    @FXML
    private ComboBox combo_gift20;

    @FXML
    private ComboBox combo_gift21;

    @FXML
    private ComboBox combo_gift22;

    @FXML
    private ComboBox combo_gift23;

    @FXML
    private ComboBox combo_gift24;

    @FXML
    private ComboBox combo_gift25;

    @FXML
    private ComboBox combo_gift26;

    @FXML
    private ComboBox combo_gift27;

    @FXML
    private ComboBox combo_gift28;

    @FXML
    private ComboBox combo_gift29;

    @FXML
    private ComboBox combo_gift30;

    @FXML
    private ComboBox combo_gift31;

    @FXML
    private ComboBox combo_gift32;

    @FXML
    private TextField text_takim1;

    @FXML
    private TextField text_takim2;

    @FXML
    private TextField text_takim3;

    @FXML
    private TextField text_takim4;

    @FXML
    private TextField text_takim5;

    @FXML
    private TextField text_takim6;

    @FXML
    private TextField text_takim7;

    @FXML
    private TextField text_takim8;
    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    @FXML
    private HBox hbox4;

    @FXML
    private HBox hbox5;

    @FXML
    private HBox hbox6;

    @FXML
    private HBox hbox7;

    @FXML
    private HBox hbox8;
    HashSet<HBox> giftBox=new HashSet<>();
    private Stage yeniEkranStage;
    private Scene yeniEkranScene;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox_team_count.getItems().addAll(1,2,3,4,6,8);
        combobox_gift_count.getItems().addAll(1,2,3,4);
        editGiftComboBox();

    }
    private void editGiftComboBox(){
        List<String> giftnames = Gift.giftList().stream().map(gift -> gift.getName()).distinct().collect(Collectors.toList());
        ObservableList<String> data = FXCollections.observableList(giftnames);
        FilteredList<String> filteredData = new FilteredList<>(data, p -> true);
        combo_gift1.getItems().addAll(filteredData);
        combo_gift2.getItems().addAll(filteredData);
        combo_gift3.getItems().addAll(filteredData);
        combo_gift4.getItems().addAll(filteredData);
        combo_gift5.getItems().addAll(filteredData);
        combo_gift6.getItems().addAll(filteredData);
        combo_gift7.getItems().addAll(filteredData);
        combo_gift8.getItems().addAll(filteredData);
        combo_gift9.getItems().addAll(filteredData);
        combo_gift10.getItems().addAll(filteredData);
        combo_gift11.getItems().addAll(filteredData);
        combo_gift12.getItems().addAll(filteredData);
        combo_gift13.getItems().addAll(filteredData);
        combo_gift14.getItems().addAll(filteredData);
        combo_gift15.getItems().addAll(filteredData);
        combo_gift16.getItems().addAll(filteredData);
        combo_gift17.getItems().addAll(filteredData);
        combo_gift18.getItems().addAll(filteredData);
        combo_gift19.getItems().addAll(filteredData);
        combo_gift20.getItems().addAll(filteredData);
        combo_gift21.getItems().addAll(filteredData);
        combo_gift22.getItems().addAll(filteredData);
        combo_gift23.getItems().addAll(filteredData);
        combo_gift24.getItems().addAll(filteredData);
        combo_gift25.getItems().addAll(filteredData);
        combo_gift26.getItems().addAll(filteredData);
        combo_gift27.getItems().addAll(filteredData);
        combo_gift28.getItems().addAll(filteredData);
        combo_gift29.getItems().addAll(filteredData);
        combo_gift30.getItems().addAll(filteredData);
        combo_gift31.getItems().addAll(filteredData);
        combo_gift32.getItems().addAll(filteredData);
    }

    public void startRaceAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Yarışma Başladı");
        Integer selectedItem = combobox_team_count.getSelectionModel().getSelectedItem();
        if (selectedItem==1){
            String takim1Text=text_takim1.getText();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("one_team.fxml"));
            root=loader.load();
            OneTeamController oneTeamController=loader.getController();
            oneTeamController.display(takim1Text);
            stage= ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        }else if (selectedItem==2){
            String takim1Text=text_takim1.getText();
            String takim2Text=text_takim2.getText();
            Integer giftCount = combobox_gift_count.getSelectionModel().getSelectedItem();
            String textNeutralMusicText = text_neutral_music.getText();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("two_teams.fxml"));
            root=loader.load();
            TwoTeamsRaceController twoTeamsRaceController=loader.getController();
            twoTeamsRaceController.display(takim1Text,takim2Text,textNeutralMusicText,getComboBoxGifts(hbox1),getComboBoxGifts(hbox2),giftCount);
            stage= ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("three_teams.fxml"));
        AnchorPane yeniEkranRoot = null;
        try {
            yeniEkranRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        yeniEkranScene = new Scene(yeniEkranRoot);
        yeniEkranStage = new Stage();
        yeniEkranStage.setTitle("Race");
        yeniEkranStage.setScene(yeniEkranScene);
        yeniEkranStage.show();
    }

    private List<String> getComboBoxGifts(HBox tmpHbox){
        List<String> giftList1 = new ArrayList<>();
        for (Node child : tmpHbox.getChildren()) {
            if (child instanceof ComboBox<?> && child.isVisible()){
                String selectedItem1 = ((String) ((ComboBox<?>) child).getSelectionModel().getSelectedItem());
                giftList1.add(selectedItem1);
            }
        }
        return giftList1;
    }
    public void finishRaceAction(ActionEvent actionEvent) {
        System.out.println("Yarışma Kapatılıyor");
        yeniEkranStage.close();
        Parent root = yeniEkranScene.getRoot();
        for (Node node : root.getChildrenUnmodifiable()) {
            System.out.println(node.getClass());
            if (node instanceof MediaView) {
                MediaPlayer mediaPlayer = ((MediaView) node).getMediaPlayer();
                if (mediaPlayer!=null){
                    mediaPlayer.stop();
                }
            }
        }
    }
    public void fileselectorBackgroundImageAction(ActionEvent actionEvent) {
        System.out.println("fileselectorBackgroundımageAction");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Resim Dosyaları", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            System.out.println("Seçilen dosya: " + selectedFile.getAbsolutePath());
            text_background_image.setText(selectedFile.getAbsolutePath());
        }
    }
    public void fileselectorNeutralMusicAction(ActionEvent actionEvent) {
        System.out.println("fileselectorBackgroundımageAction");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Müzik Dosyaları", "*.mp3", "*.mp4")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            System.out.println("Seçilen dosya: " + selectedFile.getAbsolutePath());
            text_neutral_music.setText(selectedFile.getAbsolutePath());
        }
    }
    public void comboboxAction(ActionEvent actionEvent) {
        System.out.println("fileselectorBackgroundımageAction");
        Integer selectedItem = combobox_team_count.getSelectionModel().getSelectedItem();
        giftBox=new HashSet<>();
        switch (selectedItem){
            case 1: {
                button_team1_dialog.setVisible(true);
                text_takim1.setVisible(true);
                hbox1.setVisible(true);
                giftBox.add(hbox1);
                button_team2_dialog.setVisible(false);
                text_takim2.setVisible(false);
                hbox2.setVisible(false);
                button_team3_dialog.setVisible(false);
                text_takim3.setVisible(false);
                hbox3.setVisible(false);
                button_team4_dialog.setVisible(false);
                text_takim4.setVisible(false);
                hbox4.setVisible(false);
                button_team5_dialog.setVisible(false);
                text_takim5.setVisible(false);
                hbox5.setVisible(false);
                button_team6_dialog.setVisible(false);
                text_takim6.setVisible(false);
                hbox6.setVisible(false);
                button_team7_dialog.setVisible(false);
                text_takim7.setVisible(false);
                hbox7.setVisible(false);
                button_team8_dialog.setVisible(false);
                text_takim8.setVisible(false);
                hbox8.setVisible(false);
                break;
            }
            case 2: {
                button_team1_dialog.setVisible(true);
                text_takim1.setVisible(true);
                hbox1.setVisible(true);
                button_team2_dialog.setVisible(true);
                text_takim2.setVisible(true);
                hbox2.setVisible(true);
                giftBox.add(hbox1);
                giftBox.add(hbox2);
                button_team3_dialog.setVisible(false);
                text_takim3.setVisible(false);
                hbox3.setVisible(false);
                button_team4_dialog.setVisible(false);
                text_takim4.setVisible(false);
                hbox4.setVisible(false);
                button_team5_dialog.setVisible(false);
                text_takim5.setVisible(false);
                hbox5.setVisible(false);
                button_team6_dialog.setVisible(false);
                text_takim6.setVisible(false);
                hbox6.setVisible(false);
                button_team7_dialog.setVisible(false);
                text_takim7.setVisible(false);
                hbox7.setVisible(false);
                button_team8_dialog.setVisible(false);
                text_takim8.setVisible(false);
                hbox8.setVisible(false);
                break;
            }
            case 3: {
                button_team1_dialog.setVisible(true);
                text_takim1.setVisible(true);
                hbox1.setVisible(true);
                button_team2_dialog.setVisible(true);
                text_takim2.setVisible(true);
                hbox2.setVisible(true);
                button_team3_dialog.setVisible(true);
                text_takim3.setVisible(true);
                hbox3.setVisible(true);
                giftBox.add(hbox1);
                giftBox.add(hbox2);
                giftBox.add(hbox3);
                button_team4_dialog.setVisible(false);
                text_takim4.setVisible(false);
                hbox4.setVisible(false);
                button_team5_dialog.setVisible(false);
                text_takim5.setVisible(false);
                hbox5.setVisible(false);
                button_team6_dialog.setVisible(false);
                text_takim6.setVisible(false);
                hbox6.setVisible(false);
                button_team7_dialog.setVisible(false);
                text_takim7.setVisible(false);
                hbox7.setVisible(false);
                button_team8_dialog.setVisible(false);
                text_takim8.setVisible(false);
                hbox8.setVisible(false);
                break;
            }
            case 4: {
                button_team1_dialog.setVisible(true);
                text_takim1.setVisible(true);
                hbox1.setVisible(true);
                button_team2_dialog.setVisible(true);
                text_takim2.setVisible(true);
                hbox2.setVisible(true);
                button_team3_dialog.setVisible(true);
                text_takim3.setVisible(true);
                hbox3.setVisible(true);
                button_team4_dialog.setVisible(true);
                text_takim4.setVisible(true);
                hbox4.setVisible(true);
                giftBox.add(hbox1);
                giftBox.add(hbox2);
                giftBox.add(hbox3);
                giftBox.add(hbox4);
                button_team5_dialog.setVisible(false);
                text_takim5.setVisible(false);
                hbox5.setVisible(false);
                button_team6_dialog.setVisible(false);
                text_takim6.setVisible(false);
                hbox6.setVisible(false);
                button_team7_dialog.setVisible(false);
                text_takim7.setVisible(false);
                hbox7.setVisible(false);
                button_team8_dialog.setVisible(false);
                text_takim8.setVisible(false);
                hbox8.setVisible(false);
                break;
            }
            case 6: {
                button_team1_dialog.setVisible(true);
                text_takim1.setVisible(true);
                hbox1.setVisible(true);
                button_team2_dialog.setVisible(true);
                text_takim2.setVisible(true);
                hbox2.setVisible(true);
                button_team3_dialog.setVisible(true);
                text_takim3.setVisible(true);
                hbox3.setVisible(true);
                button_team4_dialog.setVisible(true);
                text_takim4.setVisible(true);
                hbox4.setVisible(true);
                button_team5_dialog.setVisible(true);
                text_takim5.setVisible(true);
                hbox5.setVisible(true);
                button_team6_dialog.setVisible(true);
                text_takim6.setVisible(true);
                hbox6.setVisible(true);
                giftBox.add(hbox1);
                giftBox.add(hbox2);
                giftBox.add(hbox3);
                giftBox.add(hbox4);
                giftBox.add(hbox5);
                giftBox.add(hbox6);
                button_team7_dialog.setVisible(false);
                text_takim7.setVisible(false);
                hbox7.setVisible(false);
                button_team8_dialog.setVisible(false);
                text_takim8.setVisible(false);
                hbox8.setVisible(false);
                break;
            }
            case 8: {
                button_team1_dialog.setVisible(true);
                text_takim1.setVisible(true);
                hbox1.setVisible(true);
                button_team2_dialog.setVisible(true);
                text_takim2.setVisible(true);
                hbox2.setVisible(true);
                button_team3_dialog.setVisible(true);
                text_takim3.setVisible(true);
                hbox3.setVisible(true);
                button_team4_dialog.setVisible(true);
                text_takim4.setVisible(true);
                hbox4.setVisible(true);
                button_team5_dialog.setVisible(true);
                text_takim5.setVisible(true);
                hbox5.setVisible(true);
                button_team6_dialog.setVisible(true);
                text_takim6.setVisible(true);
                hbox6.setVisible(true);
                button_team7_dialog.setVisible(true);
                text_takim7.setVisible(true);
                hbox7.setVisible(true);
                button_team8_dialog.setVisible(true);
                text_takim8.setVisible(true);
                hbox8.setVisible(true);
                giftBox.add(hbox1);
                giftBox.add(hbox2);
                giftBox.add(hbox3);
                giftBox.add(hbox4);
                giftBox.add(hbox5);
                giftBox.add(hbox6);
                giftBox.add(hbox7);
                giftBox.add(hbox8);
                break;
            }
        }
    }
    public void timerExtendAction(ActionEvent actionEvent) {
        System.out.println("timerExtendAction");
    }
    public void teamDialogAction(ActionEvent actionEvent) {
        System.out.println("teamDialogAction");
        Button button = (Button) actionEvent.getSource();
        String name = button.getText();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Klasör Seç");
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            System.out.println("Seçilen Klasör: " + selectedDirectory.getAbsolutePath());
            switch (name){
                case "Takım1":{
                    text_takim1.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
                case "Takım2":{
                    text_takim2.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
                case "Takım3":{
                    text_takim3.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
                case "Takım4":{
                    text_takim4.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
                case "Takım5":{
                    text_takim5.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
                case "Takım6":{
                    text_takim6.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
                case "Takım7":{
                    text_takim7.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
                case "Takım8":{
                    text_takim8.setText(selectedDirectory.getAbsolutePath());
                    break;
                }
            }
        }

    }
    public void gift_comboboxAction(ActionEvent actionEvent) {
        System.out.println("fileselectorBackgroundımageAction");
        Integer selectedItem = combobox_gift_count.getSelectionModel().getSelectedItem();
        for (HBox box : giftBox) {
            int i=0;
            for (Node child : box.getChildren()) {
                if (child instanceof ComboBox<?> && i<selectedItem){
                    child.setVisible(true);
                    i++;
                }else {
                    child.setVisible(false);
                }
            }
        }
    }

}
