import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Ratings extends Frame implements ActionListener {
	Reviewer viewer;
	Paper paper;

	Set<Map.Entry<String, Reviewer>> reviewer;
	Set<Map.Entry<String, Paper>> paper_set;
	Conference c;

	int tech_merit;
	int readability;
	int originality;
	int relavance;
	boolean recommendation;
	String comment_author;
	String comment_review_committee;

	Label pap, title, rev, tch, red, org, rel, rec, com1, com2, report;

	Checkbox tech[] = new Checkbox[10], read[] = new Checkbox[10], origin[] = new Checkbox[10],
			relav[] = new Checkbox[10], recom[] = new Checkbox[2];

	TextField auth, committee;

	Choice email, paper_no;

	Button submit, cancel, next;

	CheckboxGroup g1, g2, g3, g4, g5;

	Ratings(Conference c, HashMap<String, Reviewer> rr, HashMap<String, Paper> pp) {
		super("Rate Papers");

		this.c = c;

		reviewer = rr.entrySet();
		paper_set = pp.entrySet();

		title = new Label("Ratings");
		pap = new Label("Paper");
		rev = new Label("Reviewer :");
		tch = new Label("Tech Merit :");
		red = new Label("Readability :");
		org = new Label("Originality :");
		rel = new Label("Relevance :");
		rec = new Label("Recommend : ");
		com1 = new Label("Comment on Author :");
		com2 = new Label("Comment for Reviewer :");
		report = new Label("Press Submit.");

		email = new Choice();
		for (Map.Entry<String, Reviewer> me : reviewer) {
			email.add(me.getKey());
		}
		email.setBounds(260, 120, 200, 20);
		add(email);

		paper_no = new Choice();
		for (Map.Entry<String, Paper> me : paper_set) {
			paper_no.add(me.getKey());
		}
		paper_no.setBounds(260, 80, 200, 20);
		add(paper_no);

		auth = new TextField();
		committee = new TextField();

		Font f1 = new Font("form1", Font.ITALIC, 15);

		setLayout(null);

		title.setBounds(350, 40, 150, 20);
		title.setFont(new Font("Head", Font.BOLD, 15));
		add(title);

		pap.setBounds(30, 80, 150, 20);
		pap.setFont(f1);
		add(pap);

		rev.setBounds(30, 120, 150, 20);
		rev.setFont(f1);
		add(rev);

		tch.setBounds(30, 160, 150, 20);
		tch.setFont(f1);
		add(tch);

		red.setBounds(30, 200, 150, 20);
		red.setFont(f1);
		add(red);

		org.setBounds(30, 240, 150, 20);
		org.setFont(f1);
		add(org);

		rel.setBounds(30, 280, 150, 20);
		rel.setFont(f1);
		add(rel);

		rec.setBounds(30, 320, 150, 20);
		rec.setFont(f1);
		add(rec);

		com1.setBounds(30, 360, 180, 20);
		com1.setFont(f1);
		add(com1);

		com2.setBounds(30, 400, 190, 20);
		com2.setFont(f1);
		add(com2);

		report.setBounds(350, 440, 180, 20);
		report.setFont(f1);
		add(report);

		auth.setBounds(260, 360, 200, 20);
		add(auth);

		committee.setBounds(260, 400, 200, 20);
		add(committee);

		g1 = new CheckboxGroup();
		int aa = 260;
		for (int i = 0; i < 10; i++) {
			tech[i] = new Checkbox(String.valueOf(i + 1), g1, false);
			tech[i].setBounds(aa, 160, 35, 20);
			aa = aa + 45;
			add(tech[i]);
		}
		tech[0].setState(true);

		g2 = new CheckboxGroup();
		aa = 260;
		for (int i = 0; i < 10; i++) {
			read[i] = new Checkbox(String.valueOf(i + 1), g2, false);
			read[i].setBounds(aa, 200, 35, 20);
			aa = aa + 45;
			add(read[i]);
		}
		read[0].setState(true);

		g3 = new CheckboxGroup();
		aa = 260;
		for (int i = 0; i < 10; i++) {
			origin[i] = new Checkbox(String.valueOf(i + 1), g3, false);
			origin[i].setBounds(aa, 240, 35, 20);
			aa = aa + 45;
			add(origin[i]);
		}
		origin[0].setState(true);

		g4 = new CheckboxGroup();
		aa = 260;
		for (int i = 0; i < 10; i++) {
			relav[i] = new Checkbox(String.valueOf(i + 1), g4, false);
			relav[i].setBounds(aa, 280, 35, 20);
			aa = aa + 45;
			add(relav[i]);
		}
		relav[0].setState(true);

		g5 = new CheckboxGroup();

		recom[0] = new Checkbox("Yes", g5, false);
		recom[1] = new Checkbox("No", g5, true);
		recom[0].setBounds(260, 320, 50, 20);
		recom[1].setBounds(320, 320, 50, 20);
		add(recom[0]);
		add(recom[1]);

		submit = new Button("Submit");
		submit.setBounds(250, 480, 55, 25);
		add(submit);

		cancel = new Button("Cancel");
		cancel.setBounds(400, 480, 55, 25);
		add(cancel);

		next = new Button(">>");
		next.setBounds(500, 480, 55, 25);
		add(next);

		submit.addActionListener(this);
		cancel.addActionListener(this);
		next.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent w) {
				
				setVisible(false);
				committee.setVisible(true);
				email.setVisible(true);
				submit.setEnabled(true);
				next.setEnabled(true);

			}
		});

		setSize(750, 550);
		setBackground(Color.LIGHT_GRAY);

	}

	public void actionPerformed(ActionEvent a) {
		String event = a.getActionCommand();

		if (event.equals("Submit")) {

			if (auth.getText().isEmpty()) {
				report.setText("Invalid Comment ");
				return;
			}

			if (committee.getText().isEmpty()) {
				report.setText("Invalid Comment");
				return;
			}

			for (Map.Entry<String, Reviewer> me : reviewer) {
				if (me.getKey().equals(email.getSelectedItem())) {
					viewer = me.getValue();
				}
			}

			for (Map.Entry<String, Paper> me : paper_set) {
				if (me.getKey().equals(paper_no.getSelectedItem())) {
					paper = me.getValue();
				}
			}

			for (int i = 0; i < 10; i++) {
				if (tech[i].getState()) {
					tech_merit = i + 1;
				}
			}

			for (int i = 0; i < 10; i++) {
				if (read[i].getState()) {
					readability = i + 1;
				}
			}

			for (int i = 0; i < 10; i++) {
				if (origin[i].getState()) {
					originality = i + 1;
				}
			}

			for (int i = 0; i < 10; i++) {
				if (relav[i].getState()) {
					relavance = i + 1;
				}
			}

			if (recom[0].getState()) {
				recommendation = true;

			} else {
				recommendation = false;
			}

			comment_author = auth.getText();
			comment_review_committee = committee.getText();

			report.setText("Successfully Registered");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			c.submitRatings();
			setVisible(false);

		}

		if (event.equals("Cancel")) {

			auth.setText("");
			committee.setText("");
			tech[0].setState(true);
			read[0].setState(true);
			origin[0].setState(true);
			relav[0].setState(true);
			recom[1].setState(true);

		}

		if (event.equals(">>")) {
			setVisible(false);
			c.hasNextRatings();
		}
	}

	void display() {
		auth.setText(comment_author);
		committee.setText(comment_review_committee);

		tech[tech_merit - 1].setState(true);
		read[readability - 1].setState(true);
		origin[originality - 1].setState(true);
		relav[relavance - 1].setState(true);

		if (recommendation) {
			recom[0].setState(true);
		} else {
			recom[1].setState(true);
		}

		email.select(viewer.getEmail());
		paper_no.select(paper.getNumber());

		setVisible(true);
	}

	/*
	 * 
	 * public static void main(String args[]) { Ratings r = new Ratings();
	 * 
	 * }
	 * 
	 */
}