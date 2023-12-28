package com.example.tiktokvideoplayer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Gift {
    private static final Logger LOGGER= LoggerFactory.getLogger(Gift.class);
    private int id;
    private String name;
    private int diamondCost;
    private String link;
    public Gift(int id,String name,int diamondCost,String link){
        this.id=id;
        this.name=name;
        this.diamondCost=diamondCost;
        this.link=link;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDiamondCost(int diamondCost) {
        this.diamondCost = diamondCost;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getDiamondCost() {
        return diamondCost;
    }
    public String getLink() {
        return link;
    }
    public static List<Gift> giftList(){
            try {
                LOGGER.debug("Gift Listesi Parse Ediliyor");
                File xmlFile = new File("gift.xml"); // XML dosyanızın adını ve yolunu belirtin
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(xmlFile);

                NodeList giftList = doc.getElementsByTagName("GiftXML");
                List<Gift> gifts = new ArrayList<>();

                for (int i = 0; i < giftList.getLength(); i++) {
                    Node node = giftList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        int diamondCost = Integer.parseInt(element.getElementsByTagName("diamondCost").item(0).getTextContent());
                        String link = element.getElementsByTagName("link").item(0).getTextContent();

                        Gift gift = new Gift(id, name, diamondCost, link);
                        gifts.add(gift);
                    }
                }
                return gifts;
            } catch (Exception e) {
                LOGGER.error(e.getMessage(),e);
                throw new RuntimeException("gift dosyası parse edilemedi "+e.getMessage());
            }
    }

    public Image image(){
        String urlString = this.getLink();
        if(urlString.isEmpty())
        {
            LOGGER.error("Resim Linki Boş");
            return null;
        }
        if (urlString.contains(".webp")){
            InputStream inputStream = null;
            try {
                inputStream = new URL(urlString).openStream();
            } catch (IOException e) {
                LOGGER.error("Webp image Açılmadı",e);
            }
            try {
                BufferedImage read = ImageIO.read(inputStream);
            } catch (IOException e) {
                LOGGER.error("WebP image BufferedImage olmadı",e);
            }
            return new Image(urlString);
        } else if (urlString.contains(".png")) {
            if (urlString.contains("http")){
                return new Image(urlString);
            }else {
                File file = new File(urlString);
                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    WritableImage writableImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
                    SwingFXUtils.toFXImage(bufferedImage,writableImage);
                    return writableImage;
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(),e);
                    throw new RuntimeException(e);
                }
            }

        }else{
            LOGGER.error("Resim Boş Geldi");
            return null;
        }
    }
}
