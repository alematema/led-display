
package com.undra.view.bombacombustivel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Uma classe que simula um display de leds
 * Suporta algarismos de 0 a 9
 * @author alexandre
 */
public class LedDisplay extends JPanel {

    // os tamanhos do led display
    public static byte JUMBO = 4;
    public static byte BIG = 3;
    public static byte MEDIUM = 2;
    public static byte SMALL = 1;
    public static byte MICRO = 0;
    //configuracoes default
    private final Border ledBorder;
    private final Border displayBorder=BorderFactory.createLineBorder(Color.BLACK, 2, true);
    private Dimension ledVDimension = new Dimension(3, 25);
    private Dimension ledHDimension = new Dimension(30, 3);
    private Color displayBackGroundColor = Color.black;
    private Color ledColor = Color.GREEN;
    private Dimension displayDimension = new Dimension(60, 98);
    private double insetsRatio = 1.0;

    //os leds do display
    private final List<JPanel> leds = new ArrayList();

    //os leds do display
    JPanel led0 ;
    JPanel led1;
    JPanel led2;
    JPanel led3;
    JPanel led4;
    JPanel led5;
    JPanel led6;
    JPanel led7;

    //models value
    byte value = 0;

    //cria um display com configuracoes default
    public LedDisplay(byte size) {

        if (size != LedDisplay.JUMBO && size != LedDisplay.BIG && size != LedDisplay.MEDIUM && size != LedDisplay.SMALL && size != LedDisplay.MICRO) {
            throw new IllegalArgumentException("O tamanho deve ser LedDisplay.JUMBO, LedDisplay.BIG, LedDisplay.MEDIUM, LedDisplay.SMALL ou LedDisplay.MICRO");
        }
        
        this.ledBorder = BorderFactory.createLineBorder(Color.WHITE, 0, true);
        
        //se size valido, entao configura o display
        configure(size);
    }

    //cria um display customizado
    public LedDisplay(byte size, Color displayBackgroundColor, Color ledColor) {

        this(size);

        if (displayBackgroundColor != null && ledColor != null) {

            if (displayBackgroundColor.equals(ledColor)) {
                throw new IllegalArgumentException("As cores do fundo do display e dos leds devems ser diferentes!!!");
            }

            this.displayBackGroundColor = displayBackgroundColor;
            this.ledColor = ledColor;

            //se size valido e cores validas, entao configura o display
            configure(size);
        }

    }

    private byte oldValue=8;//little cache
    public void setValue(byte newValue) {
        if (newValue < 0 || newValue > 9) {
            throw new IllegalArgumentException("O valor não pode ser menor do que zero ou maior do que 9");
        }
        if(oldValue==newValue)return;
        this.value = newValue;
        updateView(newValue);
        oldValue=newValue;
    }

    public byte getValue() {
        return value;
    }

    private void updateView(byte newValue) {

        leds.forEach((led) -> {//acende todos leds
            led.setBackground(ledColor);
        });

        //apaga leds para fazer corresponder ao algarismo newValue
        switch (newValue) {

            case 0:
                led4.setBackground(displayBackGroundColor);
                break;
            case 1:
                led0.setBackground(displayBackGroundColor);
                led1.setBackground(displayBackGroundColor);
                led4.setBackground(displayBackGroundColor);
                led5.setBackground(displayBackGroundColor);
                led6.setBackground(displayBackGroundColor);
                break;
            case 2:
                led0.setBackground(displayBackGroundColor);
                led3.setBackground(displayBackGroundColor);
                break;

            case 3:
                led0.setBackground(displayBackGroundColor);
                led6.setBackground(displayBackGroundColor);
                break;
            case 4:
                led1.setBackground(displayBackGroundColor);
                led5.setBackground(displayBackGroundColor);
                led6.setBackground(displayBackGroundColor);
                break;
            case 5:
                led2.setBackground(displayBackGroundColor);
                led6.setBackground(displayBackGroundColor);
                break;
            case 6:
                led2.setBackground(displayBackGroundColor);
                break;
            case 7:
                led0.setBackground(displayBackGroundColor);
                led4.setBackground(displayBackGroundColor);
                led5.setBackground(displayBackGroundColor);
                led6.setBackground(displayBackGroundColor);
                break;
            case 8:
                //do nothing
                break;
            case 9:
                led6.setBackground(displayBackGroundColor);
                break;
        }
    }

    private void configure(byte size) {

        configureDisplayAndLedsDimensions(size);
        
        //leds
        led0 = getLed();
        led1 = getLed();
        led2 = getLed();
        led3 = getLed();
        led4 = getLed();
        led5 = getLed();
        led6 = getLed();

        removeAll();
        leds.clear();

        setLayout(new GridBagLayout());

        led0.setName("led0");
        led1.setName("led1");
        led2.setName("led2");
        led3.setName("led3");
        led4.setName("led4");
        led5.setName("led5");
        led6.setName("led6");

        setBackground(displayBackGroundColor);
        setPreferredSize(displayDimension);
        setBorder(displayBorder);

        GridBagConstraints gridConstraintsForLed = new GridBagConstraints();
        
        gridConstraintsForLed.gridx = 0;
        gridConstraintsForLed.gridy = 0;
        gridConstraintsForLed.insets = new Insets(0, 0, 0, 0);

        add(led0, gridConstraintsForLed);

        led1.setPreferredSize(ledHDimension);
        led1.setBackground(ledColor);
        led1.setBorder(ledBorder);

        gridConstraintsForLed.gridx = 1;
        gridConstraintsForLed.gridy = 0;
        gridConstraintsForLed.insets = new Insets((int) (5 / insetsRatio), (int) (0 / insetsRatio), (int) (35 / insetsRatio), (int) (0 / insetsRatio));

        add(led1, gridConstraintsForLed);


        gridConstraintsForLed.gridx = 2;
        gridConstraintsForLed.gridy = 0;
        gridConstraintsForLed.insets = new Insets((int) (10 / insetsRatio), (int) (5 / insetsRatio), (int) (5 / insetsRatio), (int) (5 / insetsRatio));

        add(led2, gridConstraintsForLed);

        gridConstraintsForLed.gridx = 2;
        gridConstraintsForLed.gridy = 1;
//        gridConstraintsForLed.insets = new Insets(0, 0, 30, 0);
        add(led3, gridConstraintsForLed);

        led4.setPreferredSize(ledHDimension);
        led4.setBackground(ledColor);
        led4.setBorder(ledBorder);

        gridConstraintsForLed.gridx = 1;
        gridConstraintsForLed.gridy = 1;
        gridConstraintsForLed.insets = new Insets((int) (0 / insetsRatio), (int) (0 / insetsRatio), (int) (40 / insetsRatio), (int) (0 / insetsRatio));

        add(led4, gridConstraintsForLed);

        led5.setPreferredSize(ledHDimension);
        led5.setBackground(ledColor);
        led5.setBorder(ledBorder);

        gridConstraintsForLed.gridx = 1;
        gridConstraintsForLed.gridy = 2;
        gridConstraintsForLed.insets = new Insets(0, 0, (int) (5 / insetsRatio), 0);

        add(led5, gridConstraintsForLed);

        gridConstraintsForLed.gridx = 0;
        gridConstraintsForLed.gridy = 1;
        gridConstraintsForLed.insets = new Insets((int) (10 / insetsRatio), (int) (5 / insetsRatio), 0, (int) (5 / insetsRatio));

        add(led6, gridConstraintsForLed);

        leds.addAll(Arrays.asList(led0, led1, led2, led3, led4, led5, led6));

    }

    private JPanel getLed() {

        JPanel led = new JPanel();

        led.setPreferredSize(ledVDimension);
        led.setBackground(ledColor);
        led.setBorder(ledBorder);

        return led;
    }

    private void configureDisplayAndLedsDimensions(byte size) {
       
        if (size == LedDisplay.JUMBO) {

            ledVDimension = new Dimension(6, 50);
            ledHDimension = new Dimension(60, 6);
            displayDimension = new Dimension(120, 200);

            insetsRatio = 0.5;

        } else if (size == LedDisplay.BIG) {

            ledVDimension = new Dimension(3, 25);
            ledHDimension = new Dimension(30, 3);
            displayDimension = new Dimension(60, 98);
            insetsRatio = 1.0;

        } else if (size == LedDisplay.MEDIUM) {

            ledVDimension = new Dimension(2, 15);
            ledHDimension = new Dimension(15, 2);
            displayDimension = new Dimension(37, 54);
            insetsRatio = 2.0;

        } else if (size == LedDisplay.SMALL) {

            ledVDimension = new Dimension(2, 5);
            ledHDimension = new Dimension(10, 2);
            displayDimension = new Dimension(25, 30);
            insetsRatio = 5.0;

        } else {//size==LedDisplay.MICRO

            ledVDimension = new Dimension(1, 4);
            ledHDimension = new Dimension(7, 1);
            displayDimension = new Dimension(17, 25);
            insetsRatio = 5.0;

        }
    }

    public Color getDisplayBackGroundColor() {
        return displayBackGroundColor;
    }

    public void setDisplayBackGroundColor(Color displayBackGroundColor) {
        this.displayBackGroundColor = displayBackGroundColor;
        setBackground(displayBackGroundColor);
        updateView(value);
    }

    public Color getLedColor() {
        return ledColor;
    }

    public void setLedColor(Color ledColor) {
        this.ledColor = ledColor;
        led0.setBackground(ledColor);
        led1.setBackground(ledColor);
        led2.setBackground(ledColor);
        led3.setBackground(ledColor);
        led4.setBackground(ledColor);
        led5.setBackground(ledColor);
        led6.setBackground(ledColor);
        led6.setBackground(ledColor);
        updateView(value);
    }
    
    
    

}
