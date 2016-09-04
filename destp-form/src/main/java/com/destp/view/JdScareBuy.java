package com.destp.view;

import com.destp.scarebuy.JdCartScareBuy;
import com.dk.Daka;
import com.dk.Task;
import com.dk.TaskTableModel;
import org.apache.http.client.CookieStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuli10 on 2016/7/6.
 */
public class JdScareBuy extends JFrame {
    private JPanel panel;
    private JPanel logPanel;
    private JSplitPane mainPanl;
    private JPanel taskListPanel;
    private JPanel controlPanel;
    private JTextField scare_url;
    private JTextField cart_url;
    private JTextField scartTime;
    private JButton logButton;
    private JTable taskTable;
    private JTextArea controlArea;
    private JMenuBar bar;



    private String title = "DK-";
    private CookieStore cookieStore;
    private Vector<Task> tasks = new Vector<Task>();

    private ExecutorService exe = Executors.newFixedThreadPool(5);

    private static JdScareBuy dk = new JdScareBuy();

    public static JdScareBuy getInstance(){
        return dk;
    }


    private void initUI(){
        panel = new JPanel();
        logPanel = new JPanel();
        taskListPanel = new JPanel();
        controlPanel = new JPanel();
        scare_url = new JTextField(15);
        cart_url = new JTextField(15);
        scartTime = new JTextField(10);
        logButton = new JButton("抢购");
        taskTable = new JTable();
        controlArea = new JTextArea();
        controlArea.setEnabled(true);
        controlArea.setEditable(true);
        controlArea.setLineWrap(true);

        JMenu menu = new JMenu("菜单");
        JMenuItem menuItem = new JMenuItem("添加任务");
        menu.add(menuItem);

        bar = new JMenuBar();
        bar.add(menu);



        logPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        logPanel.add(new JLabel("抢购地址"));
        logPanel.add(scare_url);
        logPanel.add(new JLabel("购物车地址"));
        logPanel.add(cart_url);
        logPanel.add(new JLabel("抢购时间"));
        logPanel.add(scartTime);
        logPanel.add(logButton);


        taskTable.setModel(new TaskTableModel(tasks));
        JScrollPane JSP= new JScrollPane(taskTable);
        taskListPanel.setLayout(new BorderLayout());
        taskListPanel.add("Center",JSP);

        controlPanel.setLayout(new BorderLayout());
        controlPanel.add("Center",new JScrollPane(controlArea));

        mainPanl = new JSplitPane();
        mainPanl.setOneTouchExpandable(true);//让分割线显示出箭头
        mainPanl.setContinuousLayout(true);//操作箭头，重绘图形
        mainPanl.setOrientation(JSplitPane.VERTICAL_SPLIT);//设置分割线方向
        mainPanl.setDividerSize(10);
        mainPanl.setDividerLocation(190);//设置分割线位于中央
        mainPanl.setTopComponent(taskListPanel);
        mainPanl.setBottomComponent(controlPanel);

        panel.setLayout(new BorderLayout());
        panel.add("North",logPanel);
        panel.add("Center",mainPanl);


        this.setJMenuBar(bar);
        this.getContentPane().add(panel);
        this.setSize(750,600);
    }

    public void initData(){
        if(null==cookieStore){
            this.setTitle(title+"未登录");
        }else {
            this.setTitle(title+"已登录");
        }
    }

    public void registerListener(){

        logButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                exe.execute(new Runnable() {
                    public void run() {
                        try {
                            String scareurl = scare_url.getText().trim();
                            String carturl = cart_url.getText().trim();
                            String time = scartTime.getText().trim();
                            JdCartScareBuy buy = new JdCartScareBuy();
                            buy.setTimeUrl(carturl);
                            buy.setScareBuyTime(time);
                            buy.scareBuy(scareurl);
                        } catch (Exception e1) {
                            controlArea.append("失败\n"+e1+"\n");
                        }
                    }
                });
            }
        });

        bar.getMenu(0).getItem(0).addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    controlArea.append("点击了菜单-添加任务\n");
                    String time = (String)JOptionPane.showInputDialog(dk,"请输入你的执行时间：\n","任务设置",JOptionPane.PLAIN_MESSAGE,null,null,"yyyy-mm-dd hh:mm:ss");
                    controlArea.append(time+"\n");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse(time);
                    Task task = new Task("renwu1",1,time,new Date().toString(),null);
                    task.setCookieStore(cookieStore);
                    tasks.add(task);
                    taskTable.setModel(new TaskTableModel(tasks));
                    Timer timer = new Timer();
                    timer.schedule(task, date);
                } catch (Exception e1) {
                    controlArea.append("任务添加失败\n"+e1.getMessage()+"\n");
                }
            }
        });


    }

    public JTextArea getControlArea() {
        return controlArea;
    }

    private JdScareBuy(){
        initUI();
        initData();
        registerListener();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args){
        JdScareBuy dk = JdScareBuy.getInstance();

    }

}
