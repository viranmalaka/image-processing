/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import java.awt.image.BufferedImage;

/**
 *
 * @author malaka
 */
public class UndoImage {
    private static UndoImage current;
    private BufferedImage img;
    private UndoImage backImage;
    private UndoImage nextImage;
    
    private static ImageProcessing notifyTo;

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
        return backImage;
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
    
    public static void addNext(BufferedImage img){
        UndoImage n = new UndoImage();
        n.setImg(img);
        if(current != null){
            current.setNextImage(n);
            n.setBackImage(current);
        }
        current = n;
        notifyTo.setImage(img);
    }
    
    public static void undo(){
        if(canUndo())
            current = current.backImage;
    }
    
    public static boolean canUndo(){
        return current.backImage != null;
    }
    
    public static void redo(){
        if(canRedo())
            current = current.nextImage;
    }
    
    public static boolean canRedo(){
        return current.nextImage != null;
    }

    public static UndoImage getCurrent() {
        return current;
    }
    
    
    
}
