//电话簿信息管理

package Text_6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class PhoneJFrame extends JFrame implements ActionListener{
	JPanel footer;
	JTable table;
	DefaultTableModel tablemodel;
//	MyTableModel tablemodel;
	
	
	///底部面板
	JTextField text_name, text_phone, text_relationship;
	JRadioButton[] radios;
	ButtonGroup bgroup;
	JComboBox<String> combox_phone, combox_relationship;
	
	String[] str = {"男","女"};
	String[] bStr = {"添加","删除","删除选中项"};
	String[][] comStr = {{"姓名","电话号码","关系"},{"电话号码","关系"}};
	
	
	
	public void init() {
		footer = new JPanel();
		footer.setBorder(new TitledBorder("Friend"));
		footer.setLayout(new GridLayout(2, 1));
		
		//上半部面板
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.add(this.text_name = new JTextField("姓名",13));
		
		this.bgroup = new ButtonGroup();
		this.radios = new JRadioButton[str.length];
		for(int i = 0; i < str.length; i ++) {
			this.radios[i] = new JRadioButton(str[i]);
			this.bgroup.add(radios[i]);
			radios[i].addActionListener(this);
			top.add(radios[i]);
		}
		this.radios[0].setSelected(true);
		top.add(this.text_phone = new JTextField(13));
		top.add(this.text_relationship = new JTextField(13));
		
		//下半部面板
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		for(int i = 0; i < this.bStr.length; i ++) {
			JButton button = new JButton(bStr[i]);
			bottom.add(button);
			button.addActionListener(this);
		}
		bottom.add( new JLabel("查找关键字"));
		bottom.add(this.combox_phone = new JComboBox<String>(this.comStr[0]));
		bottom.add( new JLabel("排序关键字"));
		bottom.add(this.combox_relationship = new JComboBox<String>(this.comStr[1]));
		
		footer.add(top);
		footer.add(bottom);
	}
	
	public PhoneJFrame(Person[] per) {
		super("电话簿");
		this.setBounds(450, 200, 550, 300);	
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setDividerLocation(150);
		
		//顶部表格设置
		JPanel header = new JPanel();

		String[] titles = {"姓名","性别","电话号码","关系"};
		this.tablemodel = new DefaultTableModel(titles, 3);
		
//		this.tablemodel = new MyTableModel();
		for(int i = 0; i < per.length; i ++) {
			tablemodel.setValueAt(per[i].name, i, 0);
			tablemodel.setValueAt(per[i].gender, i, 1);
			tablemodel.setValueAt(per[i].phone, i, 2);
			tablemodel.setValueAt(per[i].relationship, i, 3);
		}		
		this.table = new JTable(tablemodel);
		header.add(new JScrollPane(table));
		split.add(header);
		
		//底部用户界面添加
		init();
		split.add(footer);		
		this.getContentPane().add(split);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JButton) {
			
			switch(arg0.getActionCommand()) {
			case "添加":
				Person p = get();
				if(p != null) {
					tablemodel.addRow(new Object[] {p.name, p.gender, p.phone, p.relationship});
				}
				break;
			case "删除":
				tablemodel.removeRow(this.table.getSelectedRow());
				break;
			case "删除选中":
				tablemodel.removeRow(this.table.getSelectedRow());
				break;
			}
		}
	}
	
	public Person get() {
		String name = this.text_name.getText();
		String gender;
		if(radios[0].isSelected())
			gender = "男";
		else 
			gender = "女";
		
		String phone = this.text_phone.getText();
		String relationship = this.text_relationship.getText();
		
		if( phone.length() != 11) {
			JOptionPane.showMessageDialog(this, "电话不正确"); 
			return null;
		}
		Person per = new Person(name, gender, phone, relationship);
		return per;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Person[] per = new Person[3];
		per[0] = new Person("张三","男","22222222222","同事");
		per[1] = new Person("李四","女","11111111111","老师");
		per[2] = new Person("楚五","男","66666666666","儿子");

		new PhoneJFrame(per);
	}

}

class Person{
	String name;
	String gender;
	String phone;
	String relationship;
	
	public Person(String name, String sex, String phone, String relationship) {
		this.name = name;

		this.gender = sex;
		this.phone = phone;
		this.relationship = relationship;
	}
	
	public String toString() {
		return name + " "+ " " + gender + " " + phone + " " + relationship + "";
	}

}