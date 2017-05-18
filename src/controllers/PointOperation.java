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
        for (int i = 0; i < newPic.getWidth(); i++) {
            for (int j = 0; j < newPic.getHeight(); j++) {
                newPic.setRGB(i, j, b.getRGB(b.getHeight() - j -1, i));
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
        BufferedImage newPic = new BufferedImage(b.getHeight(),b.getWidth(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < newPic.getWidth(); i++) {
            for (int j = 0; j < newPic.getHeight(); j++) {
                newPic.setRGB(i, j, b.getRGB(i, b.getHeight() - j - 1));
            }
        }
        return newPic;
    }
    public static BufferedImage hFlip (BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getHeight(),b.getWidth(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < newPic.getWidth(); i++) {
            for (int j = 0; j < newPic.getHeight(); j++) {
                newPic.setRGB(i, j, b.getRGB(b.getWidth() - i -1, j));
            }
        }
        return newPic;
    }
}