/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;

/**
 *
 * @author malaka
 */
public class PointOperation {
    public static enum Rotate{
        Deg_90, Deg_180, Deg_270
    }
    
    private static BufferedImage rotate(BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getHeight(),b.getWidth(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < b.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                newPic.setRGB(newPic.getWidth()-1-i, j, b.getRGB(j, i));
            }
        }
        return newPic;
    }
    
    public static BufferedImage transpose(BufferedImage b, Rotate r){
        switch (r) {
            case Deg_180:
                return rotate(rotate(b));
            case Deg_270 : 
                return rotate(rotate(rotate(b)));
            case Deg_90:
                return rotate(b);
            default:
                return null;
        }
    }
    
    public static BufferedImage vFlip (BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                newPic.setRGB(i, j, b.getRGB(i, b.getHeight() - 1 -j));
            }
        }
      
        return newPic;
    }
    public static BufferedImage hFlip (BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                newPic.setRGB(i, j, b.getRGB(b.getWidth() - 1 - i, j));
            }
        }
      
        return newPic;
    }
}
