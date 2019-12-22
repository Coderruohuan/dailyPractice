package algorithm.util;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import algorithm.model.BaseInfo;

/**
 * 题目搜索工具类
 * 目前是用题号来判断是否处理过
 * @author wwn
 * @date 2019年10月14日
 *
 */
public class SearchUtil {
	// 题号标签
	private JLabel questionIndex;
	// 题号输入框
	private JTextField field;
	// 面板
	private JFrame searchTool;
	// 搜索按钮
	private JButton button;

	public static void main(String[] args) {
		SearchUtil util = new SearchUtil();
		util.init();
		util.search();

	}

	/**
	 * 初始化
	 */
	private void init() {
		searchTool = new JFrame();
		searchTool.setBounds(800, 300, 400, 400); // 前两个参数表示在屏幕的位置，后面两个参数是大小
		searchTool.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置窗口关闭的方式
		searchTool.setLayout(new FlowLayout(FlowLayout.LEFT));// 布局：靠左
		questionIndex = new JLabel();
		questionIndex.setText("题号");
		questionIndex.setVisible(true);
		field = new JTextField(15);
		field.setPreferredSize(new Dimension(20, 20));
		field.setVisible(true);
		searchTool.add(questionIndex);
		searchTool.add(field);
		button = new JButton("搜索");
		searchTool.add(button);
		searchTool.setVisible(true);// 设置面板可见要放在最后，不然初始化后看不到面板上的组件
	}

	/**
	 * 搜索
	 */
	private void search() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String,BaseInfo> map = getAllQuestionIndexs("algorithm");
				if (field.getText() != null && field.getText().length() > 0 && map != null
						&& map.containsKey(field.getText())) {
					BaseInfo baseInfo=map.get(field.getText());
					if(baseInfo.isSolve()) {
						JOptionPane.showMessageDialog(null, "该题已经做过,位置："+baseInfo.getClassName(), "题目搜索结果", JOptionPane.INFORMATION_MESSAGE);	
					}else {
						JOptionPane.showMessageDialog(null, "该题未解决，位置:"+baseInfo.getClassName(), "题目搜索结果", JOptionPane.WARNING_MESSAGE);	
					}		
				} else {
					JOptionPane.showMessageDialog(null, "搜不到该题");
				}
			}
		});
	}

	public HashMap<String,BaseInfo> getAllQuestionIndexs(String packageName) {
		HashMap<String,BaseInfo> map = new HashMap<>();
		URL url = this.getClass().getClassLoader().getResource(packageName);
		if (url == null) {
			return null;
		}
		File file = new File(url.getFile());
		File[] fileList = file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains("Algorithm");
			}
		});

		for (File f : fileList) {
			try {
				// 怎么根据.class 文件获取class对象
				Class<?> className = ClassLoader.getSystemClassLoader()
						.loadClass("algorithm." + f.getName().replace(".class", ""));
				Method[] methods = className.getMethods();
				for (Method m : methods) {
					Solve solve = null;
					Unsolve unsolve = null;
				    BaseInfo baseInfo=null;
					if ((solve = m.getAnnotation(Solve.class)) != null) {
						baseInfo=new BaseInfo(solve.content(),solve.keywords(),solve.date(),true);
						baseInfo.setClassName(className.getName());
						map.put(solve.title(),baseInfo );
					} else if ((unsolve = m.getAnnotation(Unsolve.class)) != null) {
						baseInfo=new BaseInfo(unsolve.content(),null,unsolve.date(),false);
						baseInfo.setClassName(className.getName());
						map.put(unsolve.title(),baseInfo);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;

	}

}