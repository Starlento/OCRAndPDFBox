import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Jframe_2 extends JFrame {
    private JLabel lb1 = new JLabel("此处显示鼠标点击坐标");
    private JLabel lb2 = new JLabel("此处显示鼠标释放坐标");

    public void init() {
        layoutInit();
        add(lb1);
        add(lb2);
        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getButton() == e.BUTTON1) { //left
                    lb1.setText(e.getX() + "," + e.getY());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getButton() == e.BUTTON1) { //left
                    lb2.setText(e.getX() + "," + e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }


        });
    }

    private void layoutInit() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体关闭时的操作 退出程序
        double width = Toolkit.getDefaultToolkit().getScreenSize().width; //得到当前屏幕分辨率的高
        double height = Toolkit.getDefaultToolkit().getScreenSize().height;//得到当前屏幕分辨率的宽
        setSize((int) width, (int) height);//设置大小
        setLocation(0, 0); //设置窗体居中显示
//        jf.setResizable(false);//禁用最大化按钮
        setLayout(new FlowLayout());
        setVisible(true);
    }

}