/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import image.processing.ImageProcessing;
import image.processing.UndoImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author malaka
 */
public class ImageViewer extends javax.swing.JInternalFrame {

    /**
     * Creates new form ImageViewer
     */
    BufferedImage image;
    
    public ImageViewer() {
        initComponents();
        refreshImage();
    }

    public void refreshImage(){
        try {
            BufferedImage image = UndoImage.getCurrent().getImg();
            this.image = image;
            this.lblImage.setIcon(new ImageIcon(image));
            this.setSize(image.getWidth() + 20, image.getHeight() + 30);
        } catch (Exception e) {
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuX = new javax.swing.JMenu();
        mnuY = new javax.swing.JMenu();
        mnuColor = new javax.swing.JMenu();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        lblImage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblImageMouseMoved(evt);
            }
        });
        getContentPane().add(lblImage);

        mnuX.setText("X : ");
        jMenuBar1.add(mnuX);

        mnuY.setText("Y :");
        jMenuBar1.add(mnuY);
        jMenuBar1.add(mnuColor);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseMoved
        // TODO add your handling code here:
        mnuX.setText("X: " + evt.getX());
        mnuY.setText("Y: " + evt.getY());
        Color color = new Color(image.getRGB(evt.getX(), evt.getY()));
        mnuColor.setText("( R :" + color.getRed() + ", G :" + color.getGreen() + ", B :" + color.getBlue() + " )");
    }//GEN-LAST:event_lblImageMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JMenu mnuColor;
    private javax.swing.JMenu mnuX;
    private javax.swing.JMenu mnuY;
    // End of variables declaration//GEN-END:variables
}
