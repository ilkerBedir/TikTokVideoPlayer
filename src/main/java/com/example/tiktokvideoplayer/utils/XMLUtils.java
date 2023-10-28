package com.example.tiktokvideoplayer.utils;

import com.example.tiktokvideoplayer.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLUtils.class);

    public void saveLastSetting(List<Setting> settingList) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement("Settings");
            document.appendChild(rootElement);
            for (Setting setting : settingList) {
                addKeyValuePair(document, rootElement, setting.getKey(), setting.getValue());
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("son_ayar.xml"));
            transformer.transform(source, result);
            LOGGER.debug("XML belgesi oluşturuldu.");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public List<Setting> getLastSettings() {
        try {
            String xmlFilePath = "son_ayar.xml";
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                LOGGER.error("XML dosyası bulunamadı.");
                return new ArrayList<>();
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element root = document.getDocumentElement();
            NodeList settingList = root.getElementsByTagName("Setting");
            List<Setting> settings = new ArrayList<>();
            for (int i = 0; i < settingList.getLength(); i++) {
                Element settingElement = (Element) settingList.item(i);
                String key = settingElement.getElementsByTagName("Key").item(0).getTextContent();
                String value = settingElement.getElementsByTagName("Value").item(0).getTextContent();
                Setting setting = new Setting(key, value);
                settings.add(setting);
            }
            return settings;

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private void addKeyValuePair(Document document, Element parentElement, String key, String value) {
        Element settingElement = document.createElement("Setting");
        Element keyElement = document.createElement("Key");
        keyElement.appendChild(document.createTextNode(key));
        Element valueElement = document.createElement("Value");
        valueElement.appendChild(document.createTextNode(value));
        settingElement.appendChild(keyElement);
        settingElement.appendChild(valueElement);
        parentElement.appendChild(settingElement);
    }
}
