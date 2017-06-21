/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
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
        BufferedImage newPic = new BufferedImage(b.getHeight(),b.getWidth(), BufferedImage.TYPE_INT_RGB);
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
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                newPic.setRGB(i, j, b.getRGB(i, b.getHeight() - 1 -j));
            }
        }
      
        return newPic;
    }
    
    public static BufferedImage hFlip (BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                newPic.setRGB(i, j, b.getRGB(b.getWidth() - 1 - i, j));
            }
        }
      
        return newPic;
    }
    
    public static BufferedImage digitalNegetive(BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                Color point = new Color(b.getRGB(i, j));
                newPic.setRGB(i, j, new Color(255 - point.getRed(), 255 - point.getGreen(), 255 - point.getBlue()).getRGB());
            }
        }
        return newPic;
    }
    
    public static BufferedImage squreRoot(BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
        int[] mappingArray = new int[256];
        for (int i = 0; i < 256; i++) {
            int x = (int) Math.round(Math.sqrt(i)) * 16;
            if (x == 256) x -= 1;
            mappingArray[i] = x;
        }
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                Color point = new Color(b.getRGB(i, j));
                newPic.setRGB(i, j, new Color(
                        mappingArray[point.getRed()], 
                        mappingArray[point.getGreen()],
                        mappingArray[point.getBlue()]
                ).getRGB());
            }
        }
        return newPic;
    }
    
    public static BufferedImage grayScale(BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                Color color = new Color(b.getRGB(i, j));
                int x = color.getRed() + color.getGreen() + color.getBlue();
                
                newPic.setRGB(i, j, new Color(x/3, x/3, x/3).getRGB());
            }
        }
        return newPic;
    }
}
