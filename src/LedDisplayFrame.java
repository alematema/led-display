/*
 * Decompiled with CFR 0_123.
 */
package leddisplay;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.border.Border;

public class LedDisplayFrame
        extends JFrame {

    LedDisplay ledDisplayBig = new LedDisplay(LedDisplay.BIG);
    GridBagConstraints gridConstraints;
    Border ledBorder = BorderFactory.createLineBorder(Color.WHITE, 0, true);
    Color ledBackGroud = Color.GREEN;
    Dimension ledVDimension = new Dimension(3, 25);
    Dimension ledHDimension = new Dimension(30, 3);

    public static void main(String[] args) {
        new LedDisplayFrame().configureAndShow();
    }

    public void configureAndShow() {
        this.getContentPane().setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(3);
        this.setTitle("Led Display");
        this.setResizable(false);
        this.setSize(400, 400);
        this.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2, this.getWidth(), this.getHeight());
        this.setUndecorated(false);
        this.setVisible(true);
        this.gridConstraints = new GridBagConstraints();
        this.gridConstraints.insets = new Insets(0, 0, 0, 0);
        final LedDisplay ledDisplayBiggest = new LedDisplay(LedDisplay.JUMBO);
        this.getContentPane().add((Component) ledDisplayBiggest, this.gridConstraints);
        this.getContentPane().add((Component) this.ledDisplayBig, this.gridConstraints);
        final LedDisplay ledDisplayMedium = new LedDisplay(LedDisplay.MEDIUM);
        this.getContentPane().add((Component) ledDisplayMedium, this.gridConstraints);
        final LedDisplay ledDisplaySmall = new LedDisplay(LedDisplay.SMALL);
        this.getContentPane().add((Component) ledDisplaySmall, this.gridConstraints);
        final LedDisplay ledDisplayMicro = new LedDisplay(LedDisplay.MICRO);
        this.getContentPane().add((Component) ledDisplayMicro, this.gridConstraints);
        final LedDisplay ledDisplayMicro2 = new LedDisplay(LedDisplay.MICRO);
        this.getContentPane().add((Component) ledDisplayMicro2, this.gridConstraints);
        Timer t = new Timer(300, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ledDisplayBiggest.setValue((byte) (Math.random() * 10.0));
                LedDisplayFrame.this.ledDisplayBig.setValue((byte) (Math.random() * 10.0));
                ledDisplayMedium.setValue((byte) (Math.random() * 10.0));
                ledDisplaySmall.setValue((byte) (Math.random() * 10.0));
                ledDisplayMicro.setValue((byte) (Math.random() * 10.0));
                ledDisplayMicro2.setValue((byte) (Math.random() * 10.0));
            }
        });
        t.start();
        this.addMouseListener(new MouseAdapter() {
            Point fromPosition;
            Point toPosition;

            {
                this.fromPosition = null;
                this.toPosition = null;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                this.fromPosition = new Point(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                this.toPosition = new Point(e.getXOnScreen(), e.getYOnScreen());
                LedDisplayFrame.this.setBounds(LedDisplayFrame.this.getBounds().x + (-this.fromPosition.x + this.toPosition.x), LedDisplayFrame.this.getBounds().y + (-this.fromPosition.y + this.toPosition.y), LedDisplayFrame.this.getWidth(), LedDisplayFrame.this.getHeight());
            }
        });
    }

}
