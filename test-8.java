//车牌号添加与查找

package Text_3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CarList extends JFrame implements ActionListener {
	
	//前端显示图形组件
	JButton text_add, text_search;
	JTextField text;
	JPanel p1, p2;
	JScrollPane jsp;
	
	//表单以及后台数据存储
	JList<String> jlist;
	DefaultListModel<String> listmodel;
	
	//初始化String 数组
	String[] cars = {"苏AX5701","京AR689","沪AW012","苏A12345"};
	
	
	public CarList() {
		super("车牌号的添加与查询");
		this.setBounds(200, 300,300, 250);
		this.setLayout(new GridLayout(2, 1));
		
		addPanel();
		this.getContentPane().add(p1);		
		this.getContentPane().add(p2);
		
		//设置按钮监听器
		this.text_add.addActionListener(this);
		this.text_search.addActionListener(this);
		
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	void addPanel() {
		//初始化组件 -> p1
		this.text = new JTextField(20);
		this.text_search = new JButton("查找");
		this.text_add = new JButton("添加");
		this.jlist = new JList<String>();
		
		//设置组件属性 -> p1
		this.text.setSize(50, 20);
		
		//建立面板
		p1 = new JPanel();
		p2 = new JPanel();
		
		//初始化构造面板 p1
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("车牌号"));
		p1.add(text);
		p1.add(text_add);
		p1.add(text_search);
		
		//构造列表框模板 -> p2
		this.listmodel = new DefaultListModel<String>();
		for(int i = 0; i < cars.length; i ++) {
			this.listmodel.addElement(cars[i]);
		}
		
		//构造列表框 -> p2
		this.jlist = new JList<String>(this.listmodel);
		this.jlist.setSize(400, 20);
		this.jlist.setSelectedIndex(0);
		
		this.jsp = new JScrollPane(jlist);
		
		//初始化构造面板 p2
		p2.setLayout(new BorderLayout());
		p2.add(jsp);
		
	}
	
	
	public static void main(String[] args) {
		new CarList();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		//查找列表相应信息
		if(arg0.getSource() == this.text_search) {
			String str = this.text.getText();
			for(int i = 0; i < this.listmodel.getSize(); i ++) {
				String car = this.listmodel.getElementAt(i);
				if( str.equals(car )) {
					this.jlist.setSelectedIndex(i);
					break;
				}
			}
		}
		
		//添加文本框内容进入列表
		if(arg0.getSource() == this.text_add) {
			String str = this.text.getText();
			if(!str.equals(""))
				this.listmodel.addElement(str);
			this.text.setText("");
		}
	}
