package com.example.tiktokvideoplayer;

import com.example.tiktokvideoplayer.utils.RunWSController;
import com.example.tiktokvideoplayer.utils.XMLUtils;
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
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.tiktokvideoplayer.GiftServer.arrayLists;

public class RaceSettingsController implements Initializable {
  private static final Logger LOGGER = LoggerFactory.getLogger(RaceSettingsController.class);

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
  Button startRaceButton;

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
  HashSet<HBox> giftBox = new HashSet<>();
  private Stage stage;
  private Scene scene;
  private Parent root;
  private ControllerInterface controller;
  private RunWSController runWSController;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    LOGGER.debug("RaceSetting initialize");
    combobox_team_count.getItems().addAll(1, 2, 3, 4, 6, 8);
    combobox_gift_count.getItems().addAll(1, 2, 3, 4);
    editGiftComboBox();
    setLastSettings();
  }

  private void editGiftComboBox() {
    LOGGER.debug("EditGiftComboBox");
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

  public void startRaceAction(ActionEvent actionEvent) {
    LOGGER.debug("Yarışma Başladı-startRaceAction");
    saveLastSettings();
    startRaceButton.setDisable(true);
    arrayLists.clear();
    Integer selectedItem = combobox_team_count.getSelectionModel().getSelectedItem();
    if (selectedItem == 1) {
      LOGGER.debug("Tek Ekranlı seçim yaptınız");
      String takim1Text = text_takim1.getText();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("one_team.fxml"));
      try {
        root = loader.load();
      } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
      }
      OneTeamController oneTeamController = loader.getController();
      oneTeamController.display(takim1Text);
      stage = new Stage();
      stage.setTitle("Winning Screen");
      scene = new Scene(root);
      stage.setScene(scene);
      controller = oneTeamController;
      stage.show();
    } else if (selectedItem == 2) {
      LOGGER.debug("2 takımlı yarışmayı seçtiniz");
      String takim1Text = text_takim1.getText();
      String takim2Text = text_takim2.getText();
      Integer giftCount = combobox_gift_count.getSelectionModel().getSelectedItem();
      String textNeutralMusicText = text_neutral_music.getText();
      String streamNameText = text_stream_name.getText();
      int interval = Integer.parseInt(text_commentary_interval.getText());
      int time = Integer.parseInt(text_timer.getText());
      int extendedTime = Integer.parseInt(text_timer_extend.getText());
      runWSController = new RunWSController(streamNameText);
      runWSController.start();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("two_teams.fxml"));
      try {
        root = loader.load();
      } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
      }
      TwoTeamsRaceController twoTeamsRaceController = loader.getController();
      twoTeamsRaceController.setInterval(interval);
      twoTeamsRaceController.display(takim1Text, takim2Text, textNeutralMusicText, getComboBoxGifts(hbox1), getComboBoxGifts(hbox2), giftCount, time, extendedTime);
      //stage= ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
      stage = new Stage();
      stage.setOnCloseRequest(event -> {
        LOGGER.debug("Yarışma Kapatma isteği geldi");
        twoTeamsRaceController.close();
        runWSController.stop();
        startRaceButton.setDisable(false);
      });
      stage.setTitle("Race2Team");
      scene = new Scene(root);
      stage.setScene(scene);
      controller = twoTeamsRaceController;
      stage.show();
    } else if (selectedItem == 3) {
      LOGGER.debug("3 takımlı yarışma başlatılıyor");
      String takim1Text = text_takim1.getText();
      String takim2Text = text_takim2.getText();
      String takim3Text = text_takim3.getText();
      Integer giftCount = combobox_gift_count.getSelectionModel().getSelectedItem();
      String textNeutralMusicText = text_neutral_music.getText();
      String streamNameText = text_stream_name.getText();
      int time = Integer.parseInt(text_timer.getText());
      int extendedTime = Integer.parseInt(text_timer_extend.getText());
      int interval = Integer.parseInt(text_commentary_interval.getText());
      runWSController = new RunWSController(streamNameText);
      runWSController.start();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("three_teams.fxml"));
      try {
        root = loader.load();
      } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
      }
      ThreeTeamsController threeTeamsController = loader.getController();
      threeTeamsController.setInterval(interval);
      threeTeamsController.display(takim1Text, takim2Text, takim3Text, textNeutralMusicText, giftCount, getComboBoxGifts(hbox1), getComboBoxGifts(hbox2),
          getComboBoxGifts(hbox3), time, extendedTime);
      stage = new Stage();
      stage.setOnCloseRequest(event -> {
        LOGGER.debug("Yarışma Kapatma isteği geldi");
        threeTeamsController.close();
        runWSController.stop();
        startRaceButton.setDisable(false);
      });
      stage.setTitle("Race3Team");
      scene = new Scene(root);
      stage.setScene(scene);
      controller = threeTeamsController;
      stage.show();
    } else if (selectedItem == 4) {
      LOGGER.debug("4 takımlı yarışma başlatılıyor");
      String takim1Text = text_takim1.getText();
      String takim2Text = text_takim2.getText();
      String takim3Text = text_takim3.getText();
      String takim4Text = text_takim4.getText();
      Integer giftCount = combobox_gift_count.getSelectionModel().getSelectedItem();
      String textNeutralMusicText = text_neutral_music.getText();
      String streamNameText = text_stream_name.getText();
      int time = Integer.parseInt(text_timer.getText());
      int extendedTime = Integer.parseInt(text_timer_extend.getText());
      int interval = Integer.parseInt(text_commentary_interval.getText());
      runWSController = new RunWSController(streamNameText);
      runWSController.start();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("four_teams.fxml"));
      try {
        root = loader.load();
      } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
      }
      FourTeamsController fourTeamsController = loader.getController();
      fourTeamsController.setInterval(interval);
      fourTeamsController.display(takim1Text, takim2Text, takim3Text, takim4Text, textNeutralMusicText, giftCount, getComboBoxGifts(hbox1), getComboBoxGifts(hbox2), getComboBoxGifts(hbox3), getComboBoxGifts(hbox4), time, extendedTime);
      stage = new Stage();
      stage.setOnCloseRequest(event -> {
        LOGGER.debug("Yarışma Kapatma İsteği geldi");
        fourTeamsController.close();
        runWSController.stop();
        startRaceButton.setDisable(false);
      });
      stage.setTitle("Race4Team");
      scene = new Scene(root);
      stage.setScene(scene);
      controller = fourTeamsController;
      stage.show();
    } else if (selectedItem == 6) {
      LOGGER.debug("6 takımlı yarışma başlatılıyor");
      String takim1Text = text_takim1.getText();
      String takim2Text = text_takim2.getText();
      String takim3Text = text_takim3.getText();
      String takim4Text = text_takim4.getText();
      String takim5Text = text_takim5.getText();
      String takim6Text = text_takim6.getText();
      Integer giftCount = combobox_gift_count.getSelectionModel().getSelectedItem();
      String textNeutralMusicText = text_neutral_music.getText();
      String streamNameText = text_stream_name.getText();
      int time = Integer.parseInt(text_timer.getText());
      int extendedTime = Integer.parseInt(text_timer_extend.getText());
      int interval = Integer.parseInt(text_commentary_interval.getText());
      runWSController = new RunWSController(streamNameText);
      runWSController.start();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("six_teams.fxml"));
      try {
        root = loader.load();
      } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
      }
      SixTeamsController sixTeamsController = loader.getController();
      sixTeamsController.setInterval(interval);
      sixTeamsController.display(takim1Text, takim2Text, takim3Text, takim4Text, takim5Text, takim6Text, textNeutralMusicText, giftCount, getComboBoxGifts(hbox1), getComboBoxGifts(hbox2), getComboBoxGifts(hbox3), getComboBoxGifts(hbox4), getComboBoxGifts(hbox5), getComboBoxGifts(hbox6), time, extendedTime);
      stage = new Stage();
      stage.setOnCloseRequest(event -> {
        LOGGER.debug("Yarışma Kapatma İsteği geldi");
        sixTeamsController.close();
        runWSController.stop();
        startRaceButton.setDisable(false);
      });
      stage.setTitle("Race6Team");
      scene = new Scene(root);
      stage.setScene(scene);
      controller = sixTeamsController;
      stage.show();
    } else if (selectedItem == 8) {
      LOGGER.debug("8 takımlı yarışma başlatılıyor");
      String takim1Text = text_takim1.getText();
      String takim2Text = text_takim2.getText();
      String takim3Text = text_takim3.getText();
      String takim4Text = text_takim4.getText();
      String takim5Text = text_takim5.getText();
      String takim6Text = text_takim6.getText();
      String takim7Text = text_takim7.getText();
      String takim8Text = text_takim8.getText();
      Integer giftCount = combobox_gift_count.getSelectionModel().getSelectedItem();
      String textNeutralMusicText = text_neutral_music.getText();
      String streamNameText = text_stream_name.getText();
      int time = Integer.parseInt(text_timer.getText());
      int extendedTime = Integer.parseInt(text_timer_extend.getText());
      int interval = Integer.parseInt(text_commentary_interval.getText());
      runWSController = new RunWSController(streamNameText);
      runWSController.start();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("eight_teams.fxml"));
      try {
        root = loader.load();
      } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
      }
      EightTeamsController eightTeamsController = loader.getController();
      eightTeamsController.setInterval(interval);
      eightTeamsController.display(takim1Text, takim2Text, takim3Text, takim4Text, takim5Text, takim6Text, takim7Text, takim8Text, textNeutralMusicText, giftCount, getComboBoxGifts(hbox1), getComboBoxGifts(hbox2), getComboBoxGifts(hbox3), getComboBoxGifts(hbox4), getComboBoxGifts(hbox5), getComboBoxGifts(hbox6), getComboBoxGifts(hbox7), getComboBoxGifts(hbox8), time, extendedTime);
      stage = new Stage();
      stage.setOnCloseRequest(event -> {
        LOGGER.debug("Yarışma Kapatma İsteği geldi");
        eightTeamsController.close();
        runWSController.stop();
        startRaceButton.setDisable(false);
      });
      stage.setTitle("Race8Team");
      scene = new Scene(root);
      stage.setScene(scene);
      controller = eightTeamsController;
      stage.show();
    }
  }

  private void setLastSettings() {
    XMLUtils xmlUtils = new XMLUtils();
    List<Setting> lastSettings = xmlUtils.getLastSettings();
    if (lastSettings.size() > 0) {
      text_takim1.setText(lastSettings.get(0).getValue());
      text_takim2.setText(lastSettings.get(1).getValue());
      text_takim3.setText(lastSettings.get(2).getValue());
      text_takim4.setText(lastSettings.get(3).getValue());
      text_takim5.setText(lastSettings.get(4).getValue());
      text_takim6.setText(lastSettings.get(5).getValue());
      text_takim7.setText(lastSettings.get(6).getValue());
      text_takim8.setText(lastSettings.get(7).getValue());
      text_stream_name.setText(lastSettings.get(8).getValue());
      text_timer.setText(lastSettings.get(9).getValue());
      text_timer_extend.setText(lastSettings.get(10).getValue());
      text_neutral_music.setText(lastSettings.get(11).getValue());
      combobox_team_count.getSelectionModel().select(Integer.parseInt(lastSettings.get(12).getValue()));
      combobox_gift_count.getSelectionModel().select(Integer.parseInt(lastSettings.get(13).getValue()));
      combo_gift1.getSelectionModel().select(lastSettings.get(14).getValue());
      combo_gift2.getSelectionModel().select(lastSettings.get(15).getValue());
      combo_gift3.getSelectionModel().select(lastSettings.get(16).getValue());
      combo_gift4.getSelectionModel().select(lastSettings.get(17).getValue());
      combo_gift5.getSelectionModel().select(lastSettings.get(18).getValue());
      combo_gift6.getSelectionModel().select(lastSettings.get(19).getValue());
      combo_gift7.getSelectionModel().select(lastSettings.get(20).getValue());
      combo_gift8.getSelectionModel().select(lastSettings.get(21).getValue());
      combo_gift9.getSelectionModel().select(lastSettings.get(22).getValue());
      combo_gift10.getSelectionModel().select(lastSettings.get(23).getValue());
      combo_gift11.getSelectionModel().select(lastSettings.get(24).getValue());
      combo_gift12.getSelectionModel().select(lastSettings.get(25).getValue());
      combo_gift13.getSelectionModel().select(lastSettings.get(26).getValue());
      combo_gift14.getSelectionModel().select(lastSettings.get(27).getValue());
      combo_gift15.getSelectionModel().select(lastSettings.get(28).getValue());
      combo_gift16.getSelectionModel().select(lastSettings.get(29).getValue());
      combo_gift17.getSelectionModel().select(lastSettings.get(30).getValue());
      combo_gift18.getSelectionModel().select(lastSettings.get(31).getValue());
      combo_gift19.getSelectionModel().select(lastSettings.get(32).getValue());
      combo_gift20.getSelectionModel().select(lastSettings.get(33).getValue());
      combo_gift21.getSelectionModel().select(lastSettings.get(34).getValue());
      combo_gift22.getSelectionModel().select(lastSettings.get(35).getValue());
      combo_gift23.getSelectionModel().select(lastSettings.get(36).getValue());
      combo_gift24.getSelectionModel().select(lastSettings.get(37).getValue());
      combo_gift25.getSelectionModel().select(lastSettings.get(38).getValue());
      combo_gift26.getSelectionModel().select(lastSettings.get(39).getValue());
      combo_gift27.getSelectionModel().select(lastSettings.get(40).getValue());
      combo_gift28.getSelectionModel().select(lastSettings.get(41).getValue());
      combo_gift29.getSelectionModel().select(lastSettings.get(42).getValue());
      combo_gift30.getSelectionModel().select(lastSettings.get(43).getValue());
      combo_gift31.getSelectionModel().select(lastSettings.get(44).getValue());
      combo_gift32.getSelectionModel().select(lastSettings.get(45).getValue());
      text_commentary_interval.setText(lastSettings.get(46).getValue());
    } else {
      LOGGER.debug("Son kayıt alınamadı");
    }
  }

  private void saveLastSettings() {
    List<Setting> settingList = new ArrayList<>();
    XMLUtils xmlUtils = new XMLUtils();
    settingList.add(new Setting("text_takim1", text_takim1.getText()));
    settingList.add(new Setting("text_takim2", text_takim2.getText()));
    settingList.add(new Setting("text_takim3", text_takim3.getText()));
    settingList.add(new Setting("text_takim4", text_takim4.getText()));
    settingList.add(new Setting("text_takim5", text_takim5.getText()));
    settingList.add(new Setting("text_takim6", text_takim6.getText()));
    settingList.add(new Setting("text_takim7", text_takim7.getText()));
    settingList.add(new Setting("text_takim8", text_takim8.getText()));
    settingList.add(new Setting("stream_name", text_stream_name.getText()));
    settingList.add(new Setting("text_timer", text_timer.getText()));
    settingList.add(new Setting("text_timer_extend", text_timer_extend.getText()));
    settingList.add(new Setting("neutral_song", text_neutral_music.getText()));
    settingList.add(new Setting("team_count", String.valueOf(combobox_team_count.getSelectionModel().getSelectedIndex())));
    settingList.add(new Setting("gift_count", String.valueOf(combobox_gift_count.getSelectionModel().getSelectedIndex())));
    settingList.add(new Setting("gift1", ((String) combo_gift1.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift2", ((String) combo_gift2.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift3", ((String) combo_gift3.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift4", ((String) combo_gift4.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift5", ((String) combo_gift5.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift6", ((String) combo_gift6.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift7", ((String) combo_gift7.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift8", ((String) combo_gift8.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift9", ((String) combo_gift9.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift10", ((String) combo_gift10.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift11", ((String) combo_gift11.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift12", ((String) combo_gift12.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift13", ((String) combo_gift13.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift14", ((String) combo_gift14.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift15", ((String) combo_gift15.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift16", ((String) combo_gift16.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift17", ((String) combo_gift17.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift18", ((String) combo_gift18.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift19", ((String) combo_gift19.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift20", ((String) combo_gift20.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift21", ((String) combo_gift21.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift22", ((String) combo_gift22.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift23", ((String) combo_gift23.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift24", ((String) combo_gift24.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift25", ((String) combo_gift25.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift26", ((String) combo_gift26.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift27", ((String) combo_gift27.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift28", ((String) combo_gift28.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift29", ((String) combo_gift29.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift30", ((String) combo_gift30.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift31", ((String) combo_gift31.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("gift32", ((String) combo_gift32.getSelectionModel().getSelectedItem())));
    settingList.add(new Setting("text_commentary_interval", text_commentary_interval.getText()));
    xmlUtils.saveLastSetting(settingList);
  }

  private List<String> getComboBoxGifts(HBox tmpHbox) {
    LOGGER.debug("Get ComboBox Gifts");
    List<String> giftList1 = new ArrayList<>();
    Integer giftCount = combobox_gift_count.getSelectionModel().getSelectedItem();
    int i=0;
    for (Node child : tmpHbox.getChildren()) {
      if (child instanceof ComboBox<?> && child.isVisible()) {
        String selectedItem1 = ((String) ((ComboBox<?>) child).getSelectionModel().getSelectedItem());
        giftList1.add(selectedItem1);
        i++;
      }
      if (i>=giftCount){
        break;
      }
    }
    return giftList1;
  }

  public void finishRaceAction(ActionEvent actionEvent) {
    LOGGER.debug("Yarışma Bitirme Butonuna Basıldı");
    if (controller != null) {
      //runWSController.stop();
      //controller.close();
      controller.finishRace();
      controller = null;
    }

  }

  public void fileselectorBackgroundImageAction(ActionEvent actionEvent) {
    LOGGER.debug("fileselectorBackgroundımageAction");
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Resim Dosyaları", "*.png", "*.jpg"));
    File selectedFile = fileChooser.showOpenDialog(new Stage());
    if (selectedFile != null) {
      LOGGER.debug("Seçilen dosya: " + selectedFile.getAbsolutePath());
      text_background_image.setText(selectedFile.getAbsolutePath());
    }
  }

  public void fileselectorNeutralMusicAction(ActionEvent actionEvent) {
    LOGGER.debug("fileselectorBackgroundımageAction");
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Müzik Dosyaları", "*.mp3", "*.mp4"));
    File selectedFile = fileChooser.showOpenDialog(new Stage());
    if (selectedFile != null) {
      LOGGER.debug("Seçilen dosya: " + selectedFile.getAbsolutePath());
      text_neutral_music.setText(selectedFile.getAbsolutePath());
    }
  }

  public void comboboxAction(ActionEvent actionEvent) {
    LOGGER.debug("fileselectorBackgroundımageAction");
    Integer selectedItem = combobox_team_count.getSelectionModel().getSelectedItem();
    giftBox = new HashSet<>();
    switch (selectedItem) {
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
    LOGGER.debug("timerExtendAction");
    String timerExtendText = text_timer_extend.getText();
    controller.increaseTime(Integer.parseInt(timerExtendText));
  }

  public void teamDialogAction(ActionEvent actionEvent) {
    LOGGER.debug("teamDialogAction");
    Button button = (Button) actionEvent.getSource();
    String name = button.getText();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Klasör Seç");
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory != null) {
      LOGGER.debug("Seçilen Klasör: " + selectedDirectory.getAbsolutePath());
      switch (name) {
        case "Takım1": {
          text_takim1.setText(selectedDirectory.getAbsolutePath());
          break;
        }
        case "Takım2": {
          text_takim2.setText(selectedDirectory.getAbsolutePath());
          break;
        }
        case "Takım3": {
          text_takim3.setText(selectedDirectory.getAbsolutePath());
          break;
        }
        case "Takım4": {
          text_takim4.setText(selectedDirectory.getAbsolutePath());
          break;
        }
        case "Takım5": {
          text_takim5.setText(selectedDirectory.getAbsolutePath());
          break;
        }
        case "Takım6": {
          text_takim6.setText(selectedDirectory.getAbsolutePath());
          break;
        }
        case "Takım7": {
          text_takim7.setText(selectedDirectory.getAbsolutePath());
          break;
        }
        case "Takım8": {
          text_takim8.setText(selectedDirectory.getAbsolutePath());
          break;
        }
      }
    }

  }

  public void gift_comboboxAction(ActionEvent actionEvent) {
    LOGGER.debug("fileselectorBackgroundımageAction");
    Integer selectedItem = combobox_gift_count.getSelectionModel().getSelectedItem();
    for (HBox box : giftBox) {
      int i = 0;
      for (Node child : box.getChildren()) {
        if (child instanceof ComboBox<?> && i < selectedItem) {
          child.setVisible(true);
          i++;
        } else {
          child.setVisible(false);
        }
      }
    }
  }

  public void forceStopClient(ActionEvent actionEvent) {
      try {
          String uygulamaAdi = "TikTokLiveDeneme.exe";
          Process process = Runtime.getRuntime().exec("taskkill /F /IM " + uygulamaAdi);
          int exitCode = process.waitFor();
          if (exitCode == 0) {
              LOGGER.debug(uygulamaAdi + " tüm örnekleri başarıyla kapatıldı.");
          } else {
              LOGGER.error(uygulamaAdi + " tüm örnekleri kapatılamadı.");
          }
      } catch (Exception e) {

      }
  }

}
