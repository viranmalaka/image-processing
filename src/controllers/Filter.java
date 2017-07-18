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
public class Filter {

    public static BufferedImage meanFilter(BufferedImage b, int i) {

        int[][] kernel = null;
        switch (i) {
            case 1:
                kernel = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
                break;
            case 2:
                kernel = new int[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
                break;
            case 3:
                kernel = new int[][]{{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
                break;
            case 4:
                kernel = new int[][]{{1, 2, 3, 2, 1}, {2, 7, 11, 7, 2}, {3, 11, 17, 11, 3}, {2, 7, 11, 7, 2}, {1, 2, 3, 2, 1}};
                break;
        }
        return mean(b, kernel, 255);
    }

    public static BufferedImage mean(BufferedImage b, int[][] kernel, int t) {
        BufferedImage img = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int kernelSum = getTotalValue(kernel);
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
                Color point = new Color(b.getRGB(i, j));
                int meanR = sumR / kernelSum,
                        meanG = sumG / kernelSum,
                        meanB = sumB / kernelSum;
                img.setRGB(i, j, new Color(
                        point.getRed() - meanR < t ? meanR : point.getRed(),
                        point.getGreen() - meanG < t ? meanG : point.getGreen(),
                        point.getBlue() - meanB < t ? meanB : point.getBlue()
                ).getRGB());
            }
        }
        return img;
    }

    public static BufferedImage thresholdAveraging(BufferedImage b, int i, int t) {

        int[][] kernel = null;
        switch (i) {
            case 1:
                kernel = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
                break;
            case 2:
                kernel = new int[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
                break;
            case 3:
                kernel = new int[][]{{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
                break;
            case 4:
                kernel = new int[][]{{1, 2, 3, 2, 1}, {2, 7, 11, 7, 2}, {3, 11, 17, 11, 3}, {2, 7, 11, 7, 2}, {1, 2, 3, 2, 1}};
                break;
        }
        return mean(b, kernel, t);
    }

    private static int getTotalValue(int[][] k) {
        int s = 0;
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                s += k[i][j];
            }
        }
        return s;
    }

    public static BufferedImage medianFiltering(BufferedImage b) {
        BufferedImage img = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 1; i < b.getHeight() - 1; i++) {
            for (int j = 1; j < b.getWidth() - 1; j++) {
                int[] red = new int[9];
                int[] green = new int[9];
                int[] blue = new int[9];
                int index = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
//                        if(y == b.getWidth()-1) 
//?\                            System.out.println(y);
                        Color color = new Color(b.getRGB(y,x));
                        red[index] = color.getRed();
                        green[index] = color.getGreen();
                        blue[index] = color.getBlue();
                        index++;
                        
                    }
                }
                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);
                img.setRGB(j, i, new Color(red[5], green[5], blue[5]).getRGB());               
            }
        }

        return img;
    }

}
