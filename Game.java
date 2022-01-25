package adventure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {

	JFrame window;
	JPanel title, startP, mainTP, optionsP, statsP, playerP, failP, winP;
	JLabel name, sub, hp, hpV, mana, manaV, weapon, weaponV, failL, winL;
	JButton play, option1, option2, option3, option4;
	JTextArea mainTA;

	Container mainC, winC;

	int pHp, pMana, monkHp, monkkHp;

	String pWeapon, pos;

	ChoiceButtons choiceButtons = new ChoiceButtons();

	Font titleF = new Font("Ink Free", Font.PLAIN, 72);
	Font constF = new Font("Ink Free", Font.PLAIN, 36);
	Font buttonF = new Font("Ink Free", Font.PLAIN, 20);

	public static void main(String[] args) {

		new Game();

	}

	public Game() {

		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1280, 720);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		mainC = window.getContentPane();

		title = new JPanel();
		title.setBounds(200, 150, 900, 150);
		title.setBackground(Color.black);

		name = new JLabel("Attack of the Monkeys");
		name.setForeground(Color.white);
		name.setFont(titleF);
		title.add(name);

		sub = new JLabel("");
		sub.setForeground(Color.white);
		sub.setFont(buttonF);
		title.add(sub);

		startP = new JPanel();
		startP.setBounds(500, 400, 300, 100);
		startP.setBackground(Color.black);

		play = new JButton("PLAY NOW");
		play.setFont(constF);
		play.setBackground(Color.black);
		play.setForeground(Color.white);
		play.setFocusPainted(false);
		play.addActionListener(e -> {
			startGame();
		});

		startP.add(play);
		mainC.add(title);
		mainC.add(startP);
		window.setVisible(true);
	}

	public void startGame() {

		title.setVisible(false);
		startP.setVisible(false);

		mainTP = new JPanel();
		mainTP.setBounds(410, 50, 850, 300);
		mainTP.setBackground(Color.black);
		mainC.add(mainTP);

		mainTA = new JTextArea();
		mainTA.setBounds(410, 50, 850, 300);
		mainTA.setBackground(Color.black);
		mainTA.setForeground(Color.white);
		mainTA.setFont(constF);
		mainTA.setLineWrap(true);
		mainTA.setWrapStyleWord(true);
		mainTA.setEditable(false);
		mainTP.add(mainTA);

		optionsP = new JPanel();
		optionsP.setBounds(400, 400, 400, 200);
		optionsP.setBackground(Color.black);
		optionsP.setLayout(new GridLayout(2, 2));
		mainC.add(optionsP);

		option1 = OptionButton();
		option1.setActionCommand("o1");
		option2 = OptionButton();
		option2.setActionCommand("o2");
		option3 = OptionButton();
		option3.setActionCommand("o3");
		option4 = OptionButton();
		option4.setActionCommand("o4");
		optionsP.add(option1);
		optionsP.add(option2);
		optionsP.add(option3);
		optionsP.add(option4);

		statsP = new JPanel();
		statsP.setBounds(15, 15, 400, 600);
		statsP.setBackground(Color.black);
		statsP.setLayout(new GridLayout(4, 1));
		mainC.add(statsP);

		hp = new JLabel("HP:");
		hp.setFont(buttonF);
		hp.setForeground(Color.white);
		statsP.add(hp);

		hpV = new JLabel();
		hpV.setFont(buttonF);
		hpV.setForeground(Color.white);
		statsP.add(hpV);

		mana = new JLabel("Mana: ");
		mana.setFont(buttonF);
		mana.setForeground(Color.white);
		statsP.add(mana);

		manaV = new JLabel();
		manaV.setFont(buttonF);
		manaV.setForeground(Color.white);
		statsP.add(manaV);

		weapon = new JLabel("Weapon:");
		weapon.setFont(buttonF);
		weapon.setForeground(Color.white);
		statsP.add(weapon);

		weaponV = new JLabel();
		weaponV.setFont(buttonF);
		weaponV.setForeground(Color.white);
		statsP.add(weaponV);

		playerP = new JPanel();
		playerP.setBounds(40, 15, 100, 600);
		playerP.setBackground(Color.black);
		playerP.setLayout(new GridLayout(2, 1));
		mainC.add(playerP);

		playerDefault();

	}

	public JButton OptionButton() {
		JButton button;
		button = new JButton();
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(buttonF);
		button.setFocusPainted(false);
		button.addActionListener(choiceButtons);
		return button;
	}

	public void playerDefault() {

		pHp = 20;
		pMana = 100;
		pWeapon = "Dual Blades";
		monkHp = 30;
		monkkHp = 60;
		weaponV.setText(pWeapon);
		hpV.setText(String.valueOf(pHp));
		manaV.setText(String.valueOf(pMana));
		begining();

	}

	public void begining() {
		pos = "begining";
		if (monkHp > 0 && pHp > 0) {
			weaponV.setText(pWeapon);
			hpV.setText(String.valueOf(pHp));
			manaV.setText(String.valueOf(pMana));
			mainTA.setText(
					"You enter the Ape village after they have destroyed your home town. You come across a Big Ape Guard! He is the guard of the tribe that attacked your village. He has "
							+ monkHp + " HP. What are you going to do?");
			option1.setText("Attack!");
			option2.setText("Recover");
			option3.setText("Heal");
			option4.setText("Talk");
		} else if (pHp <= 0) {
			failure();
		} else if (monkHp <= 0) {
			weaponSwap();
		}

	}

	public void attack1() {
		pos = "attack1";
		if (pMana < 20) {
			mainTA.setText(
					"You don't have enough mana silly! Recover to gain more mana. Big Monk punches you and deals 10 damage!");
			pHp -= 10;
			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		} else {

			mainTA.setText(
					"'You really think you can just attack the Big Monk???' You deal 4 damage and use 20 mana. Big Monk fights back and deals 2 damage to you!");
			monkHp -= 4;
			pHp -= 2;
			pMana -= 20;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}
	}

	public void recover1() {

		pos = "recover1";

		if (pMana >= 60) {
			mainTA.setText(
					"You cannot recover because you will have more than the maximum amount of mana (100). Big Monk attacks and deals 10 damage!");
			pHp -= 10;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		} else {

			mainTA.setText(
					"You drink a Monster Energy Drink and gain 40 mana! `You dare drink without sharing with me???` Big Monk attacks and deals 2 damage.");
			pMana += 40;
			pHp -= 2;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}

	}

	public void heal1() {

		pos = "heal1";

		if (pMana < 10) {
			mainTA.setText(
					"You don't have enough mana (10), silly! Recover to gain more mana. Big Monk punches you and deals 10 damage!");
			pHp -= 10;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		} else if (pHp > 14) {
			mainTA.setText("You can't gain more than the max HP (20), silly! Big monk attacks and deals 10 damage!");
			pHp -= 10;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		} else {
			mainTA.setText("You drink Pickle Juice and gain 8 HP! Big Monk attacks and deals 2 damage");
			pHp += 6;
			pMana -= 10;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}

	}

	public void talk1() {
		pos = "talk1";
		mainTA.setText(
				"'Im a Big Monk! No one is stronger than me!' Big Monk throws a punch that deals 4 damage! What are you going to do?");
		pHp -= 4;
		option1.setText(">");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}

	public void boss() {

		pos = "boss";
		if (monkkHp > 0 && pHp > 0) {
			weaponV.setText(pWeapon);
			hpV.setText(String.valueOf(pHp));
			manaV.setText(String.valueOf(pMana));
			mainTA.setText(
					"After choosing your weapon, you decide to go further in the village. You come across a Huge Ape! He is the leader of the tribe that started the attack on your village. He has "
							+ monkkHp + " HP. What are you going to do?");
			option1.setText("Attack!");
			option2.setText("Recover");
			option3.setText("Heal");
			option4.setText("Talk");
		} else if (pHp <= 0) {
			failure();
		} else if (monkkHp <= 0) {
			win();
		}

	}

	public void attack2() {

		pos = "attack2";

		if (pWeapon.equals("Dual Blades")) {
			if (pMana < 20) {
				mainTA.setText(
						"You don't have enough mana silly! Recover to gain more mana. Huge Monk punches you and deals 15 damage!");
				pHp -= 15;
				option1.setText(">");
				option2.setText("");
				option3.setText("");
				option4.setText("");
			} else {
				mainTA.setText(
						"'You dare attack the leader of the Apes, Huge Monk???' You deal 4 damage and use 20 mana. Huge Monk fights back and deals 4 damage to you!");
				monkkHp -= 4;
				pHp -= 4;
				pMana -= 20;

				option1.setText(">");
				option2.setText("");
				option3.setText("");
				option4.setText("");
			}

		} else if (pWeapon.equals("Scyth")) {

			if (pMana < 25) {
				mainTA.setText(
						"You don't have enough mana silly! Recover to gain more mana. Huge Monk punches you and deals 15 damage!");
				pHp -= 15;
				option1.setText(">");
				option2.setText("");
				option3.setText("");
				option4.setText("");
			} else {
				mainTA.setText(
						"'You dare attack the leader of the Apes, Huge Monk???' You deal 6 damage and use 25 mana. Huge Monk fights back and deals 4 damage to you!");
				monkkHp -= 6;
				pHp -= 4;
				pMana -= 25;

				option1.setText(">");
				option2.setText("");
				option3.setText("");
				option4.setText("");
			}
		} else if (pWeapon.equals("Katana")) {

			if (pMana < 30) {
				mainTA.setText(
						"You don't have enough mana silly! Recover to gain more mana. Huge Monk punches you and deals 15 damage!");
				pHp -= 15;
				option1.setText(">");
				option2.setText("");
				option3.setText("");
				option4.setText("");
			} else {
				mainTA.setText(
						"'You dare attack the leader of the Apes, Huge Monk???' You deal 7 damage and use 30 mana. Huge Monk fights back and deals 4 damage to you!");
				monkkHp -= 7;
				pHp -= 4;
				pMana -= 30;

				option1.setText(">");
				option2.setText("");
				option3.setText("");
				option4.setText("");
			}
		}

	}

	public void recover2() {

		pos = "recover2";

		if (pMana >= 60) {
			mainTA.setText(
					"You cannot recover because you will have more than the maximum amount of mana (100). Huge Monk attacks and deals 15 damage!");
			pHp -= 15;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		} else {

			mainTA.setText(
					"You drink a Monster Energy Drink and gain 40 mana! `You dare drink without sharing with me???` Huge Monk attacks and deals 4 damage.");
			pMana += 40;
			pHp -= 4;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}

	}

	public void heal2() {

		pos = "heal2";

		if (pMana < 10) {
			mainTA.setText(
					"You don't have enough mana (10), silly! Recover to gain more mana. Huge Monk punches you and deals 15 damage!");
			pHp -= 15;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		} else if (pHp > 10) {
			mainTA.setText("You can't gain more than the max HP (20), silly! Huge monk attacks and deals 15 damage!");
			pHp -= 15;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		} else {
			mainTA.setText("You drink Pickle Juice++ and gain 14 HP! Huge Monk attacks and deals 4 damage");
			pHp += 10;
			pMana -= 10;

			option1.setText(">");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}
	}

	public void talk2() {

		pos = "talk2";
		mainTA.setText(
				"'How could you kill all of my guards!? You must now deal with me Huge Monk!' Huge Monk throws a punch that deals 6 damage! What are you going to do?");
		pHp -= 6;
		option1.setText(">");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}

	public void weaponSwap() {
		pos = "weaponSwap";
		mainTA.setText(
				"You killed all the Guards! You see a Scythe, a Katana, and a Polearm on the floor. What would you like to take?");
		option1.setText("Dual Blades");
		option2.setText("Scythe");
		option3.setText("Katana");
		option4.setText("Polearm");

	}

	public void failure() {

		title.setVisible(true);
		startP.setVisible(true);

		name.setText("     YOU DIED!     ");
		sub.setText("You are a failure and should be ashamed.");
		play.setText("PLAY AGAIN");

		mainTP.setVisible(false);
		optionsP.setVisible(false);
		statsP.setVisible(false);
		playerP.setVisible(false);

	}

	public void win() {

		title.setVisible(true);
		startP.setVisible(true);

		name.setFont(constF);
		name.setText("YOU DEFEATED THE APES!");
		sub.setText("You are the pride of your town and have gotten revenge on the apes!");
		play.setText("PLAY AGAIN");
		
		mainTP.setVisible(false);
		optionsP.setVisible(false);
		statsP.setVisible(false);
		playerP.setVisible(false);

	}

	public class ChoiceButtons implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String getOption = e.getActionCommand();

			switch (pos) {

			case "begining":
				if (getOption.equals("o1")) {
					attack1();
				} else if (getOption.equals("o2")) {
					recover1();
				} else if (getOption.equals("o3")) {
					heal1();
				} else if (getOption.equals("o4")) {
					talk1();
				}
				break;

			case "attack1":
				if (getOption.equals("o1")) {
					begining();
				}
				break;
			case "recover1":
				if (getOption.equals("o1")) {
					begining();
				}
				break;
			case "heal1":
				if (getOption.equals("o1")) {
					begining();
				}
				break;
			case "talk1":
				if (getOption.equals("o1")) {
					begining();
				}
				break;
			case "weaponSwap":
				if (getOption.equals("o1")) {
					boss();
				} else if (getOption.equals("o2")) {
					pWeapon = "Scyth";
					boss();
				} else if (getOption.equals("o3")) {
					pWeapon = "Katana";
					boss();
				} else if (getOption.equals("o4")) {
					pWeapon = "Polearm";
					boss();
				}
				break;
			case "boss":
				if (getOption.equals("o1")) {
					attack2();
				} else if (getOption.equals("o2")) {
					recover2();
				} else if (getOption.equals("o3")) {
					heal2();
				} else if (getOption.equals("o4")) {
					talk2();
				}
				break;

			case "attack2":
				if (getOption.equals("o1")) {
					boss();
				}
				break;
			case "recover2":
				if (getOption.equals("o1")) {
					boss();
				}
				break;
			case "heal2":
				if (getOption.equals("o1")) {
					boss();
				}
				break;
			case "talk2":
				if (getOption.equals("o1")) {
					boss();
				}
				break;
			}

		}

	}

}