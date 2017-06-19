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
public class EdgeDetector {

    public static BufferedImage sobelVOperation(BufferedImage b, int level, boolean color) {
        int[][] kernel = {{-1, 0, 1}, {-2, 0, -2}, {-1, 0, -1}};
        return operation(kernel, b, level, color);
    }

    public static BufferedImage sobelHOperation(BufferedImage b, int level, boolean color) {
        int[][] kernel = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        return operation(kernel, b, level, color);
    }

    public static BufferedImage prewittHOperation(BufferedImage b, int level, boolean color) {
        int[][] kernel = {{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
        return operation(kernel, b, level, color);
    }

    public static BufferedImage prewittVOperation(BufferedImage b, int level, boolean color) {
        int[][] kernel = {{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}};
        return operation(kernel, b, level, color);
    }

    public static BufferedImage robertsOperation1(BufferedImage b, int level, boolean  color){
        int[][] kernel = {{0, 1}, {-1, 0}};
        return operation(kernel, b, level, color);
    }
    public static BufferedImage robertsOperation2(BufferedImage b, int level, boolean  color){
        int[][] kernel = {{1, 0}, {0, -1}};
        return operation(kernel, b, level, color);
    }
    
    public static BufferedImage lap(BufferedImage b, int level, boolean  color){
        int[][] kernel = {{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
        return operation(kernel, b, level, color);
    }
    
    public static BufferedImage operation(int[][] kernel, BufferedImage b, int level, boolean color) {
        BufferedImage newPic = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);

        int margin = kernel.length / 2;
        for (int i = margin; i < b.getWidth() - margin; i++) {
            for (int j = margin; j < b.getHeight() - margin; j++) {

                int sumR = 0, sumG = 0, sumB = 0;
                for (int k = 0; k < kernel.length; k++) {
                    for (int l = 0; l < kernel[k].length; l++) {
                        Color c = new Color(b.getRGB(i + (k - margin), j + (l - margin)));
                        sumR += kernel[k][l] * (c.getRed());
                        sumG += kernel[k][l] * (c.getGreen());
                        sumB += kernel[k][l] * (c.getBlue());
                    }
                }
                
                boolean red = sumR > -level && sumR < level;
                boolean green = sumG > -level && sumG < level; 
                boolean blue = sumB > -level && sumB < level;
                
                if(color){
                    newPic.setRGB(i, j, new Color(red ? 255 : 0, green ? 255 : 0, blue ? 255 : 0).getRGB());
                }else{
                    if(red || green || blue){                    
                        newPic.setRGB(i, j, new Color(0, 0, 0).getRGB());
                    }
                }
            }
        }
        return newPic;
    }
}
