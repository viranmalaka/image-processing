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
//        for (int i = 0; i < newPic.getWidth(); i++) {
//            for (int j = 0; j < newPic.getHeight(); j++) {
//                int red = Math.round((1-a)*(1-b)*(new Color(img.getRGB(i, j))).getRed() + 
//                        (1-b)*a*(new Color(img.getRGB(i, j+ 1))).getRed() + 
//                        (1-a)*b*(new Color(img.getRGB(i + 1, j))).getRed() + 
//                        a*b*(new Color(img.getRGB(i + 1, j + 1))).getRed());
//                int green = Math.round((1-a)*(1-b)*(new Color(img.getRGB(i, j))).getGreen()+ 
//                        (1-b)*a*(new Color(img.getRGB(i, j+ 1))).getGreen()+ 
//                        (1-a)*b*(new Color(img.getRGB(i + 1, j))).getGreen()+ 
//                        a*b*(new Color(img.getRGB(i + 1, j + 1))).getGreen());
//                int blue = Math.round((1-a)*(1-b)*(new Color(img.getRGB(i, j))).getBlue()+ 
//                        (1-b)*a*(new Color(img.getRGB(i, j+ 1))).getBlue()+ 
//                        (1-a)*b*(new Color(img.getRGB(i + 1, j))).getBlue()+ 
//                        a*b*(new Color(img.getRGB(i + 1, j + 1))).getBlue());
//                
//                Color point = new Color(red, green, blue);
//                newPic.setRGB(i, j, point.getRGB());
//            }
//        }
        float ia = 1/a;
        float ib = 1/b;
        
        for (int i = 0; i < newPic.getHeight() - 1; i++) {    //b  
            for (int j = 0; j < newPic.getWidth() - 1; j++) { //a
                double x = ia * j;
                double y = ib * i;
                int xint = (int) x;
                int yint = (int) y;
                float xfl = (float) x - xint;
                float yfl = (float) y - yint;
                
                Color color1 = new Color(img.getRGB(xint, yint));
                Color color2 = new Color(img.getRGB(xint + 1, yint));
                Color color3 = new Color(img.getRGB(xint, yint + 1));
                Color color4 = new Color(img.getRGB(xint + 1, yint + 1));
                
                int red = Math.round(color1.getRed() * (1- xfl) * (1 - yfl) + 
                        color2.getRed() * (1 - xfl) * yfl + 
                        color3.getRed() * xfl * (1-yfl) + 
                        color4.getRed() * xfl * yfl);
                
                int green = Math.round(color1.getGreen()* (1- xfl) * (1 - yfl) + 
                        color2.getGreen()* (1 - xfl) * yfl + 
                        color3.getGreen()* xfl * (1-yfl) + 
                        color4.getGreen()* xfl * yfl);
                
                int blue = Math.round(color1.getBlue()* (1- xfl) * (1 - yfl) + 
                        color2.getBlue()* (1 - xfl) * yfl + 
                        color3.getBlue()* xfl * (1-yfl) + 
                        color4.getBlue()* xfl * yfl);
                newPic.setRGB(j, i, new Color(red, green, blue).getRGB());
            }
        }
        return newPic;
    }
}
