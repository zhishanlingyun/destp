package com.destp.view;

import com.destp.base.IocContent;
import com.destp.service.PrintService;
import com.model.CommonModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class MainForm extends JFrame {

    private JTextArea area;

    public void init(){
        this.getContentPane().setLayout(new BorderLayout());
        area = new JTextArea();
        this.getContentPane().add("Center", area);
    }

    public MainForm(){
        PrintService printService = (PrintService) IocContent.getBean("printService");
        init();
        area.append(printService.out("hello assembly!"));
    }

    public static void main(String[] args){
        JFrame jf = new MainForm();
        jf.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        jf.setTitle(CommonModel.getInstance().getName());
        jf.setSize(300, 360);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);

    }

}
