import java.awt.*;
import java.awt.event.*;

public class Reviewer extends Frame implements ActionListener {
	String email;
	String first_name;
	String last_name;
	String phone_number;
	String affiliation;
	String topics_of_interest;

	Button submit, cancel, next;

	Label cls, mal, first, last, ph, report, aff, top;
	TextField mail, fname, lname, phone, affl, topics;

	Conference c;

	Reviewer(Conference c) {
		super("Reviewer Details");

		this.c = c;

		setSize(500, 500);
		setBackground(Color.LIGHT_GRAY);

		cls = new Label("Reviewer Details");
		mal = new Label("Email :");
		first = new Label("First Name :");
		last = new Label("Last Name :");
		ph = new Label("Phone Number :");
		aff = new Label("Affiliation :");
		top = new Label("Topics of Interest: ");
		report = new Label("Press Submit.");

		mail = new TextField();
		fname = new TextField();
		lname = new TextField();
		phone = new TextField();
		affl = new TextField();
		topics = new TextField();

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

		aff.setBounds(30, 240, 150, 20);
		aff.setFont(f1);
		add(aff);

		top.setBounds(30, 280, 150, 20);
		top.setFont(f1);
		add(top);

		report.setBounds(200, 320, 180, 20);
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

		affl.setBounds(200, 240, 200, 20);
		add(affl);

		topics.setBounds(200, 280, 200, 20);
		add(topics);

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
				Double d = Double.parseDouble(phone.getText());

			} catch (Exception e) {
				report.setText("Invalid Phone Number");
				return;

			}

			if (affl.getText().isEmpty()) {
				report.setText("Invalid Affiliated Company.");
				return;

			}

			if (topics.getText().isEmpty()) {
				report.setText("Invalid Interest");
				return;

			}
			email = mail.getText();
			first_name = fname.getText();
			last_name = lname.getText();
			phone_number = phone.getText();
			affiliation = affl.getText();
			topics_of_interest = topics.getText();

			report.setText("Successfully Registered");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			c.submitReviewer();
			setVisible(false);

		}

		if (event.equals("Cancel")) {
			mail.setText("");
			fname.setText("");
			lname.setText("");
			phone.setText("");
			affl.setText("");
			topics.setText("");

			report.setText("Press Submit");

		}

		if (event.equals(">>")) {
			setVisible(false);
			c.hasNextReviewer();

		}

	}

	void display() {
		mail.setText(email);
		fname.setText(first_name);
		lname.setText(last_name);
		phone.setText(phone_number);
		affl.setText(affiliation);
		topics.setText(topics_of_interest);

		setVisible(true);
	}

	String getEmail() {
		return email;
	}

	/*
	 * 
	 * public static void main(String args[]) { Reviewer r = new Reviewer(); }
	 * 
	 */

}