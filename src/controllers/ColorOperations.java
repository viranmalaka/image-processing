/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author malaka
 */
public class ColorOperations {
    public static BufferedImage increaseBrightenss(BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                Color color = new Color(b.getRGB(i, j));
                newPic.setRGB(i, j, new Color(
                        color.getRed() < 254 ? color.getRed() + 2 : 255, 
                        color.getGreen() < 254 ? color.getGreen() + 2 : 255, 
                        color.getBlue() < 254 ? color.getBlue()+ 2 : 255)
                        .getRGB());
            }
        }
        return newPic;
    }
    
    public static BufferedImage decreaseBrightenss(BufferedImage b){
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                Color color = new Color(b.getRGB(i, j));
                newPic.setRGB(i, j, new Color(
                        color.getRed() > 1 ? color.getRed() - 2 : 0, 
                        color.getGreen() > 1 ? color.getGreen() - 2 : 0, 
                        color.getBlue() > 1 ? color.getBlue() - 2 : 0)
                        .getRGB());
            }
        }
        return newPic;
    }
    
    public static BufferedImage streachHistogram(BufferedImage pic){
        BufferedImage newPic = new BufferedImage(pic.getWidth(), pic.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        int[] r = new int[256];
        int[] g = new int[256];
        int[] b = new int[256];
        
        int min = 255, max = 0;
        for (int i = 0; i < pic.getWidth(); i++) {
            for (int j = 0; j < pic.getHeight(); j++) {
                Color color = new Color(pic.getRGB(i, j));
                min = min > color.getRed() ? color.getRed() : min;
                min = min > color.getGreen()? color.getGreen(): min;
                min = min > color.getBlue()? color.getBlue(): min;
                max = max < color.getRed() ? color.getRed() : max;
                max = max < color.getGreen()? color.getGreen(): max;
                max = max < color.getBlue()? color.getBlue(): max;
//                r[color.getRed()] ++;
//                r[color.getGreen()] ++;
//                r[color.getBlue()] ++;
            }
        }
//        System.out.println(max + " " + min);
//        System.out.println(256.0 / (max - min));
        int[] map = new int[max - min + 1];
        
        for (int i = 0; i <= max - min; i++) {
            map[i] = (int)Math.round(256.0 * i / (max - min + 1));
        }
//        System.out.println(Arrays.toString(map));
        for (int i = 0; i < newPic.getWidth(); i++) {
            for (int j = 0; j < newPic.getHeight(); j++) {
                Color color = new Color(pic.getRGB(i, j));
                newPic.setRGB(i, j, new Color(
                        map[color.getRed() - min], 
                        map[color.getGreen() - min], 
                        map[color.getBlue() - min])
                        .getRGB());
            }
        }
        
        
        
        return newPic;
    }
}
