//students信息管理

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class StudentJFrame extends JFrame implements ActionListener, ListSelectionListener {
	
	StudentPanel leftPanel;
	JPanel rightPanel;
	
	DefaultListModel<Student> listmodel;
	JList<Student> jlist;
	
	JComboBox<String>[] comboxs;
	
	
	public StudentJFrame(Student[] std){
		super("Student对象管理");
		this.setBounds(200, 200, 650, 250);
		
		//设置分割面板
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.getContentPane().add(split);
		
		//添加左面板
		this.leftPanel = new StudentPanel();
		split.add(leftPanel);
		split.setDividerLocation(110);
		split.setOneTouchExpandable(true);
		
		//添加右面板
		this.rightPanel = new JPanel();
		this.rightPanel.setLayout(new BorderLayout());
		split.add(rightPanel);
		
		//中部列表框
		this.listmodel = new DefaultListModel<Student>();
		if( std != null) {
			for(int i = 0; i < std.length; i ++)
				this.listmodel.addElement(std[i]);
		}
		
		this.jlist = new JList<Student>(this.listmodel);
		this.jlist.setSelectedIndex(0);
		this.jlist.addListSelectionListener(this);
		
		//底部命令组件
		JPanel cmdpanel = new JPanel();
		String[][] str = {{"添加","删除","删除选中"},{"查找关键字","排序关键字"},{"姓名","出生日期"}};
		
		//左半部命令按钮
		for(int i = 0; i < str[0].length; i ++) {
			JButton button = new JButton(str[0][i]);
			button.addActionListener(this);
			cmdpanel.add(button);
		}
		
		//右半部查找框
		this.comboxs = new JComboBox[str[1].length];
		for(int i = 0; i < str[1].length; i ++) {
			cmdpanel.add(new JLabel(str[1][i]));
			this.comboxs[i] = new JComboBox<String>(str[2]);
			cmdpanel.add(comboxs[i]);
			comboxs[i].addActionListener(this);
		}
		
		//增加组件
		this.rightPanel.add(new JScrollPane(this.jlist));
		this.rightPanel.add(cmdpanel,"South");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
		
	}

	//逻辑事件方法
	public <T> void remove(DefaultListModel<T> listmodel, T obj) {
		if( obj != null && JOptionPane.showConfirmDialog(this, "删除" + obj.toString() + "?",
					"确认", JOptionPane.YES_NO_OPTION) == 0)
		{
			boolean remove = this.listmodel.removeElement(obj);
			JOptionPane.showMessageDialog(this, remove?"删除成功":"查找失败，删除失败");
		}
			
	}
	
	public <T> void removeSelected(JList jlist, DefaultListModel<T> listmodel) {
		if(this.listmodel.getSize() == 0) {
			JOptionPane.showMessageDialog(this, "列表框空");
		}
		else {
			int i = this.jlist.getSelectedIndex();
			if( i == -1){
				JOptionPane.showMessageDialog(this, "请选中列表框数据");
			}
			else {
				String str = this.jlist.getSelectedValue().toString();
				if(JOptionPane.showConfirmDialog(this, "删除第" + i + "项(" + str + ")?"
							,"确认", JOptionPane.YES_NO_OPTION) == 0)
						this.listmodel.removeElement(i);
			}
		}
	}
	

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		this.leftPanel.set(this.jlist.getSelectedValue());
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JButton) {
			Student stu = this.leftPanel.get();
			switch(arg0.getActionCommand()) {
				case "添加":
					if(stu != null)
						this.listmodel.addElement(stu);
					break;
				case "删除":
System.out.println(stu.toString()+"");
					int i = this.jlist.getSelectedIndex();
					this.listmodel.removeElement(this.jlist.getSelectedValue());
					
					this.jlist.setSelectedIndex(i);
					break;
				case "删除选中":
//					this.removeSelected(this.jlist, this.listmodel);
					while(this.jlist.getSelectedValue() != null)
						this.listmodel.removeElement(this.jlist.getSelectedValue());
					break;
			}
		}
		
		if(arg0.getSource() instanceof JComboBox) {
			jlist.clearSelection();
			if(arg0.getSource() instanceof JComboBox) {
				if(arg0.getSource() == this.comboxs[0]){
					int i = this.comboxs[0].getSelectedIndex();
					int n = this.listmodel.getSize();
					boolean isFind = false;
					
					switch(i) {
					case 0:
						for(int j = 0; j < n; j ++) {
							Student stu = this.listmodel.getElementAt(j);
							String str1 = stu.name;
							String str2 = this.leftPanel.get().name;
							if(str1.equals(str2)) {
								this.jlist.setSelectedIndex(j);
								isFind = true;
							}
						}
						if( !isFind) {
							JOptionPane.showMessageDialog(this, "未查找到该学生");
						}
						break;
					case 1:
						for(int j = 0; j < n; j ++) {
							Student stu = this.listmodel.getElementAt(j);
							String str1 = stu.date;
							String str2 = this.leftPanel.get().date;
							if(str1.equals(str2)) {
								this.jlist.setSelectedIndex(j);
								isFind = true;
							}
						}
						if( !isFind) {
							JOptionPane.showMessageDialog(this, "未查找到该学生");
						}
					}
					
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student[] std = new Student[3];
		std[0] = new Student("小李","1999年9月9日","男","浙江","宁波");
		std[1] = new Student("小王","6666年6月6日","女","江苏","南京");
		std[2] = new Student("小新","0000年0月0日","男","江苏","南京");
		
		new StudentJFrame(std );
	}
	
}


class StudentPanel extends JPanel implements ActionListener {
	JTextField text_name,text_date;
	JRadioButton[] radios;
	JComboBox<String> combox_provinces, combox_city;
	
	static String[] provinces = {"江苏","浙江"};
	static String [][] cities = {{"南京","苏州","无锡"}, {"杭州","宁波","温州"} };
	String[] str = {"男","女"};
	
	public StudentPanel() {
		this.setBorder(new TitledBorder("Student"));
		this.setLayout(new GridLayout(5, 1));
		
		
		this.text_name = new JTextField("姓名");
		this.text_date = new JTextField("出生日期");
		
		JPanel rbpanel = new JPanel(new GridLayout(1, 2));
		ButtonGroup bgroup = new ButtonGroup();
		this.radios = new JRadioButton[str.length];
		
		for(int i = 0; i < this.radios.length; i ++) {
			this.radios[i] = new JRadioButton(str[i]);
			bgroup.add(this.radios[i]);
			rbpanel.add(radios[i]);
		}
		this.radios[0].setSelected(true);
		
	
		this.combox_provinces = new JComboBox<String>(this.provinces);
		this.combox_city = new JComboBox<String>(this.cities[0]);
		
		//增加面板组件
		this.add(text_name);
		this.add(text_date);
		this.add(rbpanel);
		this.add(combox_provinces);
		this.add(combox_city);
		
		
		this.combox_provinces.addActionListener(this);
		
	}

	public void set(Student std) {
		if( std == null)
			return;
		
		this.text_name.setText(std.name);
		this.text_date.setText(std.date);
		
		if(std.sex.equals("男")) {
			this.radios[0].setSelected(true);
		}
		else {
			this.radios[1].setSelected(true);
		}
		
		this.combox_provinces.setSelectedItem(std.city);
		this.combox_city.setSelectedItem(std.city);
	}
	
	public Student get() {
		try {
			String name = this.text_name.getText();
			String date = this.text_date.getText();
			String sex = this.radios[0].isSelected()? "男":"女";
			String province = this.combox_provinces.getSelectedItem().toString();
			String city = this.combox_city.getSelectedItem().toString();
			
			Student std = new Student(name, date, sex, province, city);
			
			return std; 
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage()+"出现异常");
		}
		finally {
			
		}
		
		return null;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int i = this.combox_provinces.getSelectedIndex();
		if( cities != null && i != -1) {
			this.combox_city.removeAllItems();
			for(int j = 0; j < this.cities[i].length; j ++) {
				this.combox_city.addItem(this.cities[i][j]);
			}
		}
		
	}
	
}


class Student {
	String name;
	String date;
	String sex;
	String province;
	String city;
	
	public Student(String name, String date, String sex, String province, String city){
		this.name = new String(name);
		this.date = new String(date);
		this.sex = new String (sex);
		this.province = new String(province);
		this.city = new String(city);
	}
	
	public String toString() {
		return name + " " + date+ " " + sex+ " " + province+ " " + city + "";
	}
}