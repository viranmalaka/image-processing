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
public class Scalling {
    public static BufferedImage scale1(BufferedImage b, int n){
        System.out.println(n);
        int picWidth = b.getWidth();
        int picHeight = b.getHeight();
        BufferedImage newPic = new BufferedImage(
                (int) Math.round(picWidth*Math.pow(2, n)), 
                (int) Math.round(picHeight*Math.pow(2, n)), 
                BufferedImage.TYPE_INT_ARGB);
        System.out.println(newPic.getWidth() + " " + newPic.getHeight());
        if (n < 0){
            n = (int) Math.round(Math.pow(2, -1 * n));
           
            for (int i = 0; i < newPic.getWidth(); i++) {
                for (int j = 0; j < newPic.getHeight(); j++) {
                    newPic.setRGB(i, j, b.getRGB(i * n, j * n));
                }
            }
        }else if(n > 0){
            n = (int) Math.round(Math.pow(2, n));
            for (int i = 0; i < newPic.getWidth(); i++) {
                for (int j = 0; j < newPic.getHeight(); j++) {
                    newPic.setRGB(i, j, b.getRGB(i/n, j/n));
                }
            }
        }
        
        return newPic;
    }
    
    public static BufferedImage scale2(BufferedImage b, int ratioX, int ratioY){
        BufferedImage newPic = new BufferedImage(b.getWidth() * ratioX, b.getHeight() * ratioY, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                for (int k = 0; k < ratioX; k++) {
                    for (int l = 0; l < ratioY; l++) {
                        newPic.setRGB(i*ratioX + k, j*ratioY + l, b.getRGB(i, j));
                    }
                }
                
            }
        }
        return newPic;
    }
    
    public static BufferedImage bipolarInterpolation (BufferedImage img, float a, float b){
//        img = getRedImg(img.getWidth(), img.getHeight());
        BufferedImage newPic = new BufferedImage(Math.round(img.getWidth() * a), 
                Math.round(img.getHeight() * b), BufferedImage.TYPE_INT_ARGB);
        
        for (int i = 0; i < newPic.getWidth(); i++) {
            for (int j = 0; j < newPic.getHeight(); j++) {
                double x = i / a;
                double y = j / b;
                
                int intX = (int) x;
                int intY = (int) y;
                
                x -= intX;
                y -= intY;
                
                Color c1 = new Color(img.getRGB(intX, intY));
                
                
                Color c2 = intX == newPic.getWidth() - 1 && intY != newPic.getHeight() - 1 ? 
                        new Color(img.getRGB(intX, intY)) : 
                        new Color(img.getRGB(intX + 1, intY));
                 
                Color c3 = (intY == newPic.getHeight() - 1 && intX != newPic.getWidth() - 1) ? 
                        new Color(img.getRGB(intX, intY)) : 
                        new Color(img.getRGB(intX, intY + 1));
                
                Color c4 = intX == newPic.getWidth() - 1 && intY == newPic.getHeight() - 1 ?
                        new Color(img.getRGB(intX, intY)) :
                        new Color(img.getRGB(intX + 1, intY + 1)) ;
                
                
                int red = (int)Math.round(c1.getRed()*x*y + 
                        c2.getRed()*(1 - x) * y + 
                        c3.getRed()*x * (1 - y) + 
                        c4.getRed()*(1-x) * (1-y));
                
                int green = (int)Math.round(c1.getGreen()*x*y + 
                        c2.getGreen()*(1 - x) * y + 
                        c3.getGreen()*x * (1 - y) + 
                        c4.getGreen()*(1-x) * (1-y));
                
                int blue = (int)Math.round(c1.getBlue()*x*y + 
                        c2.getBlue()*(1 - x) * y + 
                        c3.getBlue()*x * (1 - y) + 
                        c4.getBlue()*(1-x) * (1-y));
                
                newPic.setRGB(i, j, new Color(red, green, blue).getRGB());
//                double r = ()
            }
        }

        return newPic;
    }
}
