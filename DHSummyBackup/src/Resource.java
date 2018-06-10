import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

    public class Resource {

        private static String resourcePath = "Resources/ImageFilePaths";

        static ImageIcon background = new ImageIcon(Resource.class.getResource(getPic("background")));
        static ImageIcon heartFull = new ImageIcon(Resource.class.getResource(getPic("heartFull")));
        static ImageIcon heartEmpty = new ImageIcon(Resource.class.getResource(getPic("heartEmpty")));
        static ImageIcon timerFrame = new ImageIcon (Resource.class.getResource(getPic("timerFrame")));

        static ImageIcon mainRestingRight = new ImageIcon(Resource.class.getResource(getPic("mainRestingRight")));
        static ImageIcon mainRestingLeft = new ImageIcon(Resource.class.getResource(getPic("mainRestingLeft")));
        static ImageIcon mainRunningRight = new ImageIcon(Resource.class.getResource(getPic("mainRunningRight")));
        static ImageIcon mainRunningLeft = new ImageIcon(Resource.class.getResource(getPic("mainRunningLeft")));
        static ImageIcon mCShield = new ImageIcon(Resource.class.getResource(getPic("mCShield")));
        static ImageIcon bulletRight = new ImageIcon(Resource.class.getResource(getPic("bulletRight")));
        static ImageIcon bulletLeft = new ImageIcon(Resource.class.getResource(getPic("bulletLeft")));

        static ImageIcon bossRest = new ImageIcon(Resource.class.getResource(getPic("bossResting"))); //TODO: FIND GIF OF SHAWN
        static ImageIcon creepFace = new ImageIcon(Resource.class.getResource(getPic("BSSA")));
        static ImageIcon bossCreep = new ImageIcon(Resource.class.getResource(getPic("lustCreep")));
        static ImageIcon bossKissing = new ImageIcon(Resource.class.getResource(getPic("bossKissing")));
        static ImageIcon bossProjectile = new ImageIcon(Resource.class.getResource(getPic("bossProjectile")));


        /**
         * Parse strings from XML entries.
         *
         *
         * @param resourceName name of the requested resource
         * @return parsed content (string)
         */
        private static String getPic(String resourceName) {

            try {
                Document XMLTree = DocumentBuilderFactory.newInstance().
                        newDocumentBuilder().parse(Resource.class.getResourceAsStream(resourcePath));
                XMLTree.getDocumentElement().normalize();

                return XMLTree.getElementsByTagName(resourceName).item(0).getTextContent();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) { //Unnecessary
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

