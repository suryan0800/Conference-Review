import java.awt.*;

import java.awt.event.*;

public class Author extends Frame implements ActionListener {

	String email;
	String first_name;
	String last_name;
	String phone_number;

	Conference c;

	Button submit, cancel, next;

	Label cls, mal, first, last, ph, report;
	TextField mail, fname, lname, phone;

	Author(Conference c) {
		super("Author Details");

		this.c = c;

		setSize(500, 500);
		setBackground(Color.LIGHT_GRAY);

		cls = new Label("Author Details");
		mal = new Label("Email :");
		first = new Label("First Name :");
		last = new Label("Last Name :");
		ph = new Label("Phone Number :");
		report = new Label("Press Submit.");

		mail = new TextField();
		fname = new TextField();
		lname = new TextField();
		phone = new TextField();

		Font f1 = new Font("form1", Font.ITALIC, 15);

		setLayout(null);

		cls.setBounds(200, 40, 150, 20);
		cls.setFont(new Font("Head", Font.BOLD, 15));
		add(cls);

		mal.setBounds(30, 80, 150, 20);
		mal.setFont(f1);
		add(mal);

		first.setBounds(30, 120, 150, 20);
		first.setFont(f1);
		add(first);

		last.setBounds(30, 160, 150, 20);
		last.setFont(f1);
		add(last);

		ph.setBounds(30, 200, 150, 20);
		ph.setFont(f1);
		add(ph);

		report.setBounds(200, 240, 180, 20);
		report.setFont(f1);
		add(report);

		mail.setBounds(200, 80, 200, 20);
		add(mail);

		fname.setBounds(200, 120, 200, 20);
		add(fname);

		lname.setBounds(200, 160, 200, 20);
		add(lname);

		phone.setBounds(200, 200, 200, 20);
		add(phone);

		submit = new Button("Submit");
		submit.setBounds(150, 300, 55, 25);
		add(submit);

		cancel = new Button("Cancel");
		cancel.setBounds(250, 300, 55, 25);
		add(cancel);

		next = new Button(">>");
		next.setBounds(350, 300, 55, 25);
		add(next);

		submit.addActionListener(this);
		cancel.addActionListener(this);
		next.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent a) {
				setVisible(false);

			}
		});

	}

	public void actionPerformed(ActionEvent a) {
		String event = a.getActionCommand();

		if (event.equals("Submit")) {
			if ((mail.getText().indexOf("@")) < 0) {
				report.setText("Invalid Email ID");

				return;
			}

			if (fname.getText().isEmpty()) {
				report.setText("Invalid First Name");
				return;

			}

			if (lname.getText().isEmpty()) {
				report.setText("Invalid Last Name");
				return;

			}

			try {
				Double.parseDouble(phone.getText());

			} catch (Exception e) {
				report.setText("Invalid Phone Number");
				return;

			}
			email = mail.getText();
			first_name = fname.getText();
			last_name = lname.getText();
			phone_number = phone.getText();

			report.setText("Successfully Registered");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			c.submitAuthor();
			setVisible(false);

		}

		if (event.equals("Cancel")) {
			mail.setText("");
			fname.setText("");
			lname.setText("");
			phone.setText("");
			report.setText("Press Submit");

		}

		if (event.equals(">>")) {
			setVisible(false);
			c.hasNextAuthor();

		}

	}

	void display() {
		mail.setText(email);
		fname.setText(first_name);
		lname.setText(last_name);
		phone.setText(phone_number);

		setVisible(true);
	}

	String getEmail() {
		return email;
	}

	public String toString() {
		return ("Email : " + email + "First Name : " + first_name + "Last Name : " + last_name + "Phone Number : "
				+ phone_number);
	}

	/*
	 * 
	 * public static void main(String args[]) { Author a = new Author();
	 * 
	 * }
	 * 
	 */
}