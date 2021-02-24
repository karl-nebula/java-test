//进制转换
package 进制转换;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
//import com.rgy.Tools.Tools;
 
public class SystemSwitch {
	private JPanel panel_left;
	private JPanel panel_right;
	private Label label[];
	private JTextField text_field[];
	public void init(){
		JFrame f=new JFrame("int整数的进制转化");
		f.setVisible(true);
		f.setLocation(500, 250);
		f.setBackground(Color.lightGray);
		f.setLayout(new FlowLayout());
		
		panel_left=new JPanel();
		panel_right=new JPanel();
		panel_left.setLayout(new GridLayout(5,1,10,10));
		panel_right.setLayout(new GridLayout(5,2,10,10));
		
		String str[]={"十进制","二进制","四进制","八进制","十六进制"};
		label=new Label[5];//重要
		for(int i=0;i<label.length;i++){
			label[i]=new Label(str[i],Label.RIGHT);
			panel_left.add(label[i]);
		}
		
		text_field=new JTextField[10];//重要
		for(int i=0;i<text_field.length;i++){
			text_field[i]=new JTextField(20);
			panel_right.add(text_field[i]);
			if(i%2==1){
				text_field[i].setEditable(false);
			}
		}
		
		f.add(panel_left);
		f.add(panel_right);
		f.pack();
		
		text_field[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str,str1,str2="",str3="",str4="";//辅助变量
				int len;
				int temp;
				str=text_field[0].getText();
				temp=Integer.parseInt(str);
				text_field[1].setText(""+(-temp));//十进制负数
				//正数二进制
				if(Integer.toBinaryString(temp).length()<=32){
					len=32-Integer.toBinaryString(temp).length();
					for(int i=0;i<len-1;i++){
						str4=str4+"0";
					}
					text_field[2].setText(str4+Integer.toBinaryString(temp));
					str4="";
				}
				else{
					return;
				}
				//相反数二进制
				text_field[3].setText(Integer.toBinaryString(-temp));
				//相反数四进制（利用负数的二进制分别进行两位合并得到该负数的四进制）
				str1=text_field[3].getText();
				for(int i=0;i<str1.length();i=i+2){
					str2=str1.substring(i,i+2);
					str3=str3+(Integer.parseInt(str2.substring(0,1))*2+
							Integer.parseInt(str2.substring(1,2)));
				}
				text_field[5].setText(str3);
				
				
				//正数八进制
				if(Integer.toOctalString(temp).length()<=12){
					len=12-Integer.toOctalString(temp).length();
					for(int i=0;i<len-1;i++){
						str4=str4+"0";
					}
					text_field[6].setText(str4+Integer.toOctalString(temp));
					str4="";
				}
				else{
					return;
				}
				//相反数八进制
				text_field[7].setText(Integer.toOctalString(-temp));
				//正数十六进制
				if(Integer.toHexString(temp).length()<=8){
					len=8-Integer.toHexString(temp).length();
					for(int i=0;i<len-1;i++){
						str4=str4+"0";
					}
					text_field[8].setText(str4+Integer.toHexString(temp));
					str4="";
				}
				else{
					return;
				}
				//相反数十六进制
				text_field[9].setText(Integer.toHexString(-temp));
			}
		});
	}
	
	public static void main(String args[]){
		new SystemSwitch().init();
	}