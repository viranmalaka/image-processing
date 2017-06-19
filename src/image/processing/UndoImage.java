/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import java.awt.image.BufferedImage;
import javafx.scene.image.ImageView;
import views.ImageViewer;

/**
 *
 * @author malaka
 */
public class UndoImage {
    
    private static UndoImage current = new UndoImage();
    
    public static UndoImage getCurrent() {
        return current;
    }
    
    private BufferedImage img;
    private UndoImage backImage;
    private UndoImage nextImage;
    
    private static ImageViewer notifyTo;

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    
    /**
     * @return the backImage
     */
    public UndoImage getBackImage() {
        current = backImage;
        return getCurrent();
    }

    /**
     * @param backImage the backImage to set
     */
    public void setBackImage(UndoImage backImage) {
        this.backImage = backImage;
    }

    /**
     * @return the nextImage
     */
    public UndoImage getNextImage() {
        return nextImage;
    }

    /**
     * @param nextImage the nextImage to set
     */
    public void setNextImage(UndoImage nextImage) {
        this.nextImage = nextImage;
    }
    
    public void addNext(BufferedImage img){
        UndoImage n = new UndoImage();
        n.setImg(img);
        if(current != null){
            current.setNextImage(n);
            n.setBackImage(current);
        }
        current = n;
        if(notifyTo != null) notifyTo.refreshImage();
    }
    
    public static void undo(){
        if(canUndo()){
            current = current.backImage;
            if(notifyTo != null) notifyTo.refreshImage();
        }
    }
    
    public static boolean canUndo(){
        return current.backImage != null;
    }
    
    public static void redo(){
        if(canRedo()){
            current = current.nextImage;
            if(notifyTo != null) notifyTo.refreshImage();
        }
    }
    
    public static boolean canRedo(){
        return current.nextImage != null;
    }

    public static void setNotifyTo(ImageViewer notifyTo) {
        UndoImage.notifyTo = notifyTo;
    }
}
