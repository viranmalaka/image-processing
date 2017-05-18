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
public class Staticstic {
    public static double getMean(BufferedImage img, char c) {
        double sum = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color color = new Color(img.getRGB(i, j));
                switch (c) {
                    case 'R':
                        sum += color.getRed();
                        break;
                    case 'G':
                        sum += color.getGreen();
                        break;
                    case 'B':
                        sum += color.getBlue();
                }
            }
        }
        return sum /(img.getWidth() * img.getHeight());
    }
    
    public static double getVar(double m, BufferedImage img, char c){
        double sum = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color color = new Color(img.getRGB(i, j));
                switch (c) {
                    case 'R':
                        sum += Math.pow(color.getRed() - m, 2);
                        break;
                    case 'G':
                        sum += Math.pow(color.getGreen() -m , 2);
                        break;
                    case 'B':
                        sum += Math.pow(color.getBlue() -m, 2);
                }
            }
        }
        return sum /(img.getWidth() * img.getHeight());
    }
    
    
    public static BufferedImage getHist(BufferedImage img, char c){
        int[] count = new int[256];
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int val = 0;
                Color color = new Color(img.getRGB(i, j));
                switch (c) {
                    case 'R':
                        val = color.getRed();
                        break;
                    case 'G':
                        val = color.getGreen();
                        break;
                    case 'B':
                        val = color.getBlue();
                }
                count[val] ++;
            }
        }
            
        BufferedImage hist = new BufferedImage(256, 231, BufferedImage.TYPE_INT_ARGB);
        int maxVal = count[0];
        Color redColor = new Color(255,0,0);
        Color greenColor = new Color(0,255,0);
        Color blueColor = new Color(0,0,255);
        
        for (int i = 1; i < count.length; i++) {
            if(maxVal < count[i]) maxVal = count[i];
        }
        double m = 200.0 / maxVal;
        
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < m * count[i]; j++) {
                switch (c) {
                    case 'R':
                        hist.setRGB(i, 200 - j, redColor.getRGB());
                        break;
                    case 'G':
                        hist.setRGB(i, 200 - j, greenColor.getRGB());
                        break;
                    case 'B':
                        hist.setRGB(i, 200 - j, blueColor.getRGB());
                        break;
                }
            }
            
            for (int j = 0; j < 20; j++) {          
                switch (c) {
                    case 'R':
                        hist.setRGB(i, 210 + j, new Color(255, 255 - i, 255 -i).getRGB());
                        break;
                    case 'G':
                        hist.setRGB(i, 210 + j, new Color(255 -i, 255, 255 -i).getRGB());
                        break;
                    case 'B':
                        hist.setRGB(i, 210 + j, new Color(255 - i, 255 - i, 255).getRGB());
                        break;
                }
                
            }
        }
        
        return hist;
    }
}
