/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import views.MainFrame;

/**
 *
 * @author malaka
 */
public class ImageProcessing {

    private static BufferedImage image;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String path;
        path = "1.png";
//        path = "2.png";
//        path = "3.jpg";
//        path = "4.jpg";
//        path = "5.jpg";
//        path = "6.jpg";
//        path = "7.jpg";
//        path = "8.jpg";
//        path = "/media/malaka/Media/Photoes/Dream Wallpaper/A Curved Path.jpg";
//        path = "/home/malaka/Desktop/Image Processing/2015/WP_20160706_14_29_14_Pro.jpg";
        try {
            image = ImageIO.read(new File(path));
            UndoImage.getCurrent().setImg(image);
            new MainFrame().setVisible(true);
            
//            new frmOptions().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static BufferedImage getImage() {
        return image;
    }

    public static void setImage(BufferedImage image) {
        ImageProcessing.image = image;
    }
    
}
