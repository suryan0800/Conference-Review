import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

public class Paper extends Frame implements ActionListener {
	String paper_no;
	String paper_title;
	String paper_abstract;
	String name_of_file;
	Author author[] = new Author[4];
	int i;

	Set<Map.Entry<String, Author>> authr;
	Conference c;

	Label titl, pap, tit, abs, nam, aut, report;
	TextField title, abst, file;
	
	

	List auther;

	Button submit, cancel, next;

	Paper(Conference c, HashMap<String, Author> authr,int no) {
		super("Paper Details");

		this.c = c;
		this.authr = authr.entrySet();

		i = 0;
		this.paper_no = String.valueOf(no +1);

		titl = new Label("Paper Details");
		pap = new Label("Paper no : " + paper_no);
		tit = new Label("Title of Paper :");
		abs = new Label("Paper Abstract :");
		nam = new Label("Name of File :");
		aut = new Label("Author name :");
		report = new Label("Press Submit.");

		
		title = new TextField();
		abst = new TextField();
		file = new TextField();

		auther = new List(3, true);
		auther.setBounds(200, 240, 200, 50);

		for (Map.Entry<String, Author> me : this.authr) {
			auther.add(me.getKey());
		}
		add(auther);

		Font f1 = new Font("form1", Font.ITALIC, 15);

		setLayout(null);

		titl.setBounds(200, 40, 150, 20);
		titl.setFont(new Font("Head", Font.BOLD, 15));
		add(titl);

		pap.setBounds(30, 80, 150, 20);
		pap.setFont(f1);
		add(pap);

		tit.setBounds(30, 120, 150, 20);
		tit.setFont(f1);
		add(tit);

		abs.setBounds(30, 160, 150, 20);
		abs.setFont(f1);
		add(abs);

		nam.setBounds(30, 200, 150, 20);
		nam.setFont(f1);
		add(nam);

		aut.setBounds(30, 240, 150, 20);
		aut.setFont(f1);
		add(aut);

		report.setBounds(200, 320, 200, 20);
		report.setFont(f1);
		add(report);

		
		title.setBounds(200, 120, 200, 20);
		add(title);

		abst.setBounds(200, 160, 200, 20);
		add(abst);

		file.setBounds(200, 200, 200, 20);
		add(file);

		submit = new Button("Submit");
		submit.setBounds(150, 360, 55, 25);
		add(submit);

		cancel = new Button("Cancel");
		cancel.setBounds(250, 360, 55, 25);
		add(cancel);

		next = new Button(">>");
		next.setBounds(350, 360, 55, 25);
		add(next);

		submit.addActionListener(this);
		cancel.addActionListener(this);
		next.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent a) {
				setVisible(false);
			}
		});

		setSize(500, 500);
		setBackground(Color.LIGHT_GRAY);

	}

	public void actionPerformed(ActionEvent a) {
		String event = a.getActionCommand();

		if (event.equals("Submit")) {
			

			if (title.getText().isEmpty()) {
				report.setText("Invalid Title");
				return;
			}

			if (abst.getText().isEmpty()) {
				report.setText("Invalid Abstract");
				return;
			}

			if (file.getText().isEmpty()) {
				report.setText("Invalid File Name");
				return;
			}

			for (String name : auther.getSelectedItems()) {
				for (Map.Entry<String, Author> me : authr) {
					if (me.getKey().equals(name)) {
						author[i] = me.getValue();
						i++;
					}
				}
			}

			
			paper_title = title.getText();
			paper_abstract = abst.getText();
			name_of_file = file.getText();

			report.setText("Successfully Registered");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			c.submitPaper();
			setVisible(false);

		}

		if (event.equals("Cancel")) {
			
			title.setText("");
			abst.setText("");
			file.setText("");

		}

		if (event.equals(">>")) {
			setVisible(false);
			c.hasNextPaper();

		}
	}

	void display() {
		pap.setText("Paper No : " + paper_no);
		title.setText(paper_title);
		abst.setText(paper_abstract);
		file.setText(name_of_file);

		for (int j = 0; j < author.length; j++) {
			if (author[j] != null) {
				for (int i = 0; i < auther.getItemCount(); i++) {

					if (auther.getItem(i).equals(author[j].getEmail())) {
						auther.select(i);
					}
				}
			}
		}

		setVisible(true);
	}

	String getNumber() {
		return paper_no;
	}

	/*
	 * 
	 * public static void main(String args[]) { Paper p = new Paper();
	 * 
	 * }
	 * 
	 */

}