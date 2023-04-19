import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.sound.sampled.*; /////////////��������� �߰��ϱ� ���� Ŭ���� ����Ʈ

class HalliGalli_Board extends JPanel
{
	PrintWriter writer;
	ImageIcon	ic;
	ImageIcon	iiCard[];    
	ImageIcon	iiCardBack;
	ImageIcon	iiBell;
	ImageIcon	iiPlayerCard[];
	ImageIcon	iiPlayerLife[];
	ImageIcon	iiPlayerDead[], iiPlayerDL[];
	JLabel		laPlayer[];
	JLabel		laCardNum[];
	String		userName[];


	public HalliGalli_Board()
	{
		setLayout(null);
		iiPlayerCard = new ImageIcon[4]; // ī��޸� �迭 -> ī�� �޸� ���
		iiPlayerLife = new ImageIcon[4]; 
		iiPlayerLife[0] = new ImageIcon("image/p1.gif");
		iiPlayerLife[1] = new ImageIcon("image/p2.gif");
		iiPlayerLife[2] = new ImageIcon("image/p3.gif");
		iiPlayerLife[3] = new ImageIcon("image/p4.gif");
		iiPlayerDead = new ImageIcon[4]; 
		iiPlayerDead[0] = new ImageIcon("image/p11.gif");
		iiPlayerDead[1] = new ImageIcon("image/p22.gif");
		iiPlayerDead[2] = new ImageIcon("image/p33.gif");
		iiPlayerDead[3] = new ImageIcon("image/p44.gif");
		iiPlayerDL = new ImageIcon[4]; 
		iiPlayerDL[0] = new ImageIcon("image/p1.gif");
		iiPlayerDL[1] = new ImageIcon("image/p2.gif");
		iiPlayerDL[2] = new ImageIcon("image/p3.gif");
		iiPlayerDL[3] = new ImageIcon("image/p4.gif");
		iiCard = new ImageIcon[56];      //
		laPlayer = new JLabel[4];
		laCardNum = new JLabel[4];
		userName = new String[4];

		for (int i=0; i<5; i++)		// 1��¥�� ī�� �̹�������
		{
			iiCard[14*0 + i] = new ImageIcon("image/c1_1.jpg");
			iiCard[14*1 + i] = new ImageIcon("image/c2_1.jpg"); 
			iiCard[14*2 + i] = new ImageIcon("image/c3_1.jpg");
			iiCard[14*3 + i] = new ImageIcon("image/c4_1.jpg");
		}
		for (int i=0; i<3; i++)		// 2,3��¥�� ī�� �̹�������
		{
			iiCard[14*0 + i+5] = new ImageIcon("image/c1_2.jpg");
			iiCard[14*1 + i+5] = new ImageIcon("image/c2_2.jpg");
			iiCard[14*2 + i+5] = new ImageIcon("image/c3_2.jpg");
			iiCard[14*3 + i+5] = new ImageIcon("image/c4_2.jpg");
			iiCard[14*0 + i+8] = new ImageIcon("image/c1_3.jpg");
			iiCard[14*1 + i+8] = new ImageIcon("image/c2_3.jpg");
			iiCard[14*2 + i+8] = new ImageIcon("image/c3_3.jpg");
			iiCard[14*3 + i+8] = new ImageIcon("image/c4_3.jpg");
		}
		for (int i=0; i<2; i++)		// 4��¥�� ī�� �̹�������
		{
			iiCard[14*0 + i+11] = new ImageIcon("image/c1_4.jpg");
			iiCard[14*1 + i+11] = new ImageIcon("image/c2_4.jpg");
			iiCard[14*2 + i+11] = new ImageIcon("image/c3_4.jpg");
			iiCard[14*3 + i+11] = new ImageIcon("image/c4_4.jpg");
		}
		// 5��¥�� ī�� �̹�������
		iiCard[14*0 + 13] = new ImageIcon("image/c1_5.jpg");
		iiCard[14*1 + 13] = new ImageIcon("image/c2_5.jpg");
		iiCard[14*2 + 13] = new ImageIcon("image/c3_5.jpg"); 
		iiCard[14*3 + 13] = new ImageIcon("image/c4_5.jpg");

		iiCardBack = new ImageIcon("image/back2.jpg");		// ī�� �޸�
		for (int i=0; i<4; i++)
		{
			iiPlayerCard[i] = iiCardBack;
		}

		laPlayer[0] = new JLabel("Player1"); //+++++++++++// ������ġ ����
		laPlayer[0].setBounds(20, 180, 100, 15);
		add(laPlayer[0]);
		laCardNum[0] = new JLabel("0��");
		laCardNum[0].setBounds(100, 180, 100, 15); 
		add(laCardNum[0]);

		laPlayer[1] = new JLabel("Player2");
		laPlayer[1].setBounds(375, 180, 100, 15); 
		add(laPlayer[1]);
		laCardNum[1] = new JLabel("0��");
		laCardNum[1].setBounds(455, 180, 100, 15);
		add(laCardNum[1]);

		laPlayer[2] = new JLabel("Player3");
		laPlayer[2].setBounds(20, 305, 100, 15);
		add(laPlayer[2]);
		laCardNum[2] = new JLabel("0��");
		laCardNum[2].setBounds(100, 305, 100, 15); 
		add(laCardNum[2]);

		laPlayer[3]= new JLabel("Player4");
		laPlayer[3].setBounds(375, 305, 100, 15); 
		add(laPlayer[3]);
		laCardNum[3] = new JLabel("0��");
		laCardNum[3].setBounds(455, 305, 100, 15);
		add(laCardNum[3]);
	}

	protected void paintComponent(Graphics g)		// �׸���
	{
		super.paintComponent(g);
		iiPlayerCard[0].paintIcon(this, g, 130, 25);  //player1
		iiPlayerLife[0].paintIcon(this, g, 20, 25);

		iiPlayerCard[1].paintIcon(this, g, 265, 25); //player2
		iiPlayerLife[1].paintIcon(this, g, 375, 25);

		iiPlayerCard[2].paintIcon(this, g, 130, 325); //player3
		iiPlayerLife[2].paintIcon(this, g, 20, 325);

		iiPlayerCard[3].paintIcon(this, g, 265, 325); //player4
		iiPlayerLife[3].paintIcon(this, g, 375, 325);
	}

	public void setWriter(PrintWriter writer)
	{
		this.writer = writer;
	}

	public void setLabel(DefaultListModel model)
	{
		for (int i=0; i<4; i++)
		{
			userName[i] = (String)model.get(i);
			laPlayer[i].setText(userName[i]);
		}
	}

	public void UpdateDraw(String name, int CardNum)	// �׸���
	{
		for (int i=0; i<4; i++)
		{
			if (name.equals(userName[i]))
			{
				iiPlayerCard[i] = iiCard[CardNum];
				repaint();
			}
		}
	}

	public void UpdateCardNum(String name, int Count)	// ī�带 ������� ������Ʈ
	{
		for (int i=0; i<4; i++)
		{
			if (name.equals(userName[i]))
			{
				laCardNum[i].setText(Count + "��");
			}
		}
	}

	public void UpdateDead(String name)					// ���� �÷��̾����� ������Ʈ
	{
		for (int i=0; i<4; i++)
		{
			if (name.equals(userName[i]))
			{
				iiPlayerCard[i] = iiCardBack;
				laCardNum[i].setText("LOSE");
				iiPlayerLife[i] = iiPlayerDead[i];
				repaint();
			}
		}
	}

	public void CardInit()								// ī�带 �ʱ�ȭ
	{
		for (int i=0; i<4; i++)
		{
			iiPlayerCard[i] = iiCardBack;
		}
		repaint();
	}
}

public class HalliGalli_Client extends JFrame implements Runnable, ActionListener
{
	JTextArea			ta_MsgView;									// �޽����� �����ִ� �ؽ�Ʈ����
	JScrollPane			scPane;										// ��ũ��
	JTextField			tf_Send = new JTextField("");				// ���� �޽����� �����ʵ�
	JTextField			tf_Name = new JTextField();					// ����� �̸� ����
	JTextField			tf_ipaddress = new JTextField();			// ������ ������ ip�ּ��Է��ʵ�
	DefaultListModel	model = new DefaultListModel();				// ����Ʈ�� (������ ��Ȳ�� ��Ÿ���� ����)
	JList				li_Player = new JList(model);				// ����� ����Ʈ
	JButton				connectButton = new JButton(new String("�� ��"), new ImageIcon("image/button.jpg"));	    	// ���� ��ư
	JButton				readyButton = new JButton(new String("�� ��"), new ImageIcon("image/button.jpg"));			// �����ư
	JButton				turnButton;		    // ī�� ������ ��ư
	JLabel				la_GameInfo = new JLabel("Hallae? Mallae?");		    // ����â
	JLabel				la_PlayerInfo = new JLabel("������Ȳ");	    // �ο�����
	boolean				ready = false;
	JButton				bellButton;                            
	ImageIcon           bi = new ImageIcon("image/bi1.gif");
	ImageIcon           cdb = new ImageIcon("image/cdb.gif");
	Clip                clip; /////////////////////����� Ŭ���� �߰��ϱ� ���� ����
	Clip				clipEffect; ////////////////ȿ������ �߰��ϱ� ���� ����
	
	HalliGalli_Board board = new HalliGalli_Board();
	BufferedReader reader;								// �Է½�Ʈ��
	PrintWriter writer;									// ��½�Ʈ��
	Socket socket;										// ����
	String userName=null;								// ����� �̸�

	public HalliGalli_Client()
	{
		super("Hallae? Mallae?");
		
		setLayout(new BorderLayout());		//
		JLabel ct=new JLabel(new ImageIcon("image\\bg.jpg")); //
		add(ct);							// 
		ct.setLayout(new FlowLayout());	    // ���ȭ�� https://java-demos.blogspot.com/2012/09/setting-background-image-in-jframe.html
		ct.setLayout(null);
		
		ta_MsgView = new JTextArea(1, 1);
		ta_MsgView.setLineWrap(true);
		scPane = new JScrollPane(ta_MsgView);

		// ���� ������Ʈ�� �����ϰ� ��ġ�Ѵ�.
		EtchedBorder eborder = new EtchedBorder(EtchedBorder.LOWERED);
		ta_MsgView.setEditable(false);
		la_GameInfo.setBounds(10, 30, 480, 30);  // ���������̶�� JLabel 
		la_GameInfo.setHorizontalAlignment(JLabel.CENTER);

		ct.add(la_GameInfo);                     // �����̳ʿ� ����â JLable �߰�
		board.setBounds(10, 65, 495, 495);
		board.setBorder(eborder);
		ct.add(board);

		Font f1 = new Font("�����ý��丮", Font.BOLD, 22);
		la_GameInfo.setFont(f1);

		Font f2 = new Font("�����ý��丮", Font.PLAIN, 17);
		connectButton.setFont(f2);
		readyButton.setFont(f2);

		Font f3 = new Font("�����ý��丮", Font.PLAIN, 15);
		la_PlayerInfo.setFont(f3);
		ta_MsgView.setFont(f3);
		tf_Send.setFont(f3);
		li_Player.setFont(f3);
		tf_ipaddress.setFont(f3);
		tf_Name.setFont(f3);

		////////////////// ����ư
		bellButton = new JButton(bi);
		bellButton.setLayout(new GridLayout());
		bellButton.setEnabled(false); 
		bellButton.setBorderPainted(false);
		bellButton.setContentAreaFilled(false);
		bellButton.setFocusPainted(false);
		bellButton.setBounds(140, 213, 75, 75);
		board.add(bellButton);

		////////////////// ī�� ������
		turnButton = new JButton(cdb);
		turnButton.setLayout(new GridLayout());
		turnButton.setEnabled(false); 
		turnButton.setBorderPainted(false);
		turnButton.setContentAreaFilled(false);
		turnButton.setFocusPainted(false);
		turnButton.setBounds(280, 205, 65, 90);
		board.add(turnButton);

		/////////////////////////////��������� �߰��Ѵ�.
		playSound("bgm/��׽ý�.wav");

        ////////////////////////// �����ּ�, �г��� �Է� �г�
		JPanel p1 = new JPanel();			 
		p1.setLayout(new GridLayout(1,2));	 
		p1.add(tf_ipaddress);
		p1.add(tf_Name);
		p1.setBounds(520,30,250,25);		 

		////////////////////////// ���� �� �غ��ư �г�
		JPanel p2 = new JPanel();			 
		p2.setLayout(new GridLayout(1,2));
		p2.add(connectButton);				 
		p2.add(readyButton);
		connectButton.setHorizontalTextPosition(JButton.CENTER);
		readyButton.setHorizontalTextPosition(JButton.CENTER);
		//readyButton.setEnabled(false);		 
		p2.setBounds(520,65,250,35);		 

		////////////////////////// ������Ȳ �г�
		JPanel p3 = new JPanel();          
		p3.setLayout(new BorderLayout());  
		p3.add(la_PlayerInfo, "North");    
		p3.add(li_Player, "Center");	   
		p3.setBounds(520, 105, 250, 125);  
		p3.setBorder(eborder);

		////////////////////////// ä���Է� �� ��� �г�
		JPanel p4 = new JPanel();          
		p4.setLayout(new BorderLayout());  
		p4.add(scPane, "Center");
		p4.add(tf_Send, "South");
		p4.setBounds(520, 235, 250, 325); 
		p4.setBorder(eborder);

		ct.add(p1);
		ct.add(p2);
		ct.add(p3);
		ct.add(p4);

		tf_Send.addActionListener(this);
		readyButton.addActionListener(this);
		connectButton.addActionListener(this);
		turnButton.addActionListener(this);
		bellButton.addActionListener(this); //+++++++// ����ư ��ü ����

		tf_ipaddress.setText("����IP");
		tf_Name.setText("�г���(2~6����)");
		ta_MsgView.append("����IP�� �г��� �Է� ��\n");
		ta_MsgView.append("���ӹ�ư�� �����ּ���\n");

		board.setOpaque(false); // -> ���ȭ��(�г�) ���� 
	}


	///////////////////////////////////////��������� �ִ� �޼ҵ�
	void playSound(String file){
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void stopSound(){
		try{
			clip.stop();
			//clip.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void bellSound(String file){
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clipEffect = AudioSystem.getClip();
			clipEffect.open(ais);
			clipEffect.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			if (ae.getSource() == tf_Send)			// �������ؽ�Ʈ�ʵ忡 ���� �Է����� ���
			{
				String msg = tf_Send.getText();
				if (msg.length() == 0)
				{
					return;
				}
	/*			if (msg.length() >= 30) // ���ڼ� ����
				{
					msg = msg.substring(0, 30);
				}   */
				writer.println("[MSG]" + msg);
				tf_Send.setText("");
			}
			else if (ae.getSource() == connectButton)	// ���ӹ�ư�� ������ ���
			{
				String name = tf_Name.getText().trim();
				if (name.length()<=1 || name.length()>6)
				{
					la_GameInfo.setText("�ùٸ� �г����� �Է����ּ���");
					tf_Name.requestFocus();
					return;
				}
				connect();
				userName = name;
				tf_Name.setText(userName);
				tf_Name.setEditable(false);
				tf_ipaddress.setEditable(false);
				la_GameInfo.setText("���Ӽ���");
				writer.println("[CONNECT]" + userName);
				connectButton.setEnabled(false);				
				readyButton.setEnabled(true);
			}
			else if (ae.getSource() == readyButton)	// ����������ư�� ������ ���
			{
				
				if (!ready)
				{
					ready = true;
					writer.println("[READY]");
					readyButton.setText("�غ�����");
					la_GameInfo.setText("�غ�Ϸ�");
				}
				else
				{
					ready = false;
					writer.println("[NOREADY]");
					readyButton.setText("�� ��");
					la_GameInfo.setText("�غ�����");
				}				
			}
			else if (ae.getSource() == turnButton)		// ī��������ư�� ������ ���
			{
				la_GameInfo.setText("����� ī�带 �������ϴ�.");
				writer.println("[TURN]" + userName);
			}
			else if (ae.getSource() == bellButton)		// ��ġ���ư�� ������ ���
			{
				writer.println("[BELL]" + userName);
			}
		}
		catch (Exception e)
		{
		}
	}

	public void run()
	{
		String msg;
		try
		{
			while((msg = reader.readLine()) != null)
			{
				if (msg.startsWith("[FULL]"))		// ������ �����ο��� ��á�� ���
				{
					la_GameInfo.setText("�濡 �ο��� ��á���ϴ�.");
				}
				else if (msg.startsWith("������"))	 // �г����� �ߺ��Ǿ� ������ �� ���
				{
					ta_MsgView.append("������ ������ϴ�.\n");
					tf_Name.setEditable(true);
					tf_ipaddress.setEditable(true);
					connectButton.setEnabled(true);				
					readyButton.setEnabled(false);
					run();
				}

				else if (msg.startsWith("[PLAYERS]"))	// �÷��̾��Ʈ�� �޴´�.
				{
					nameList(msg.substring(9));
				}

				else if (msg.startsWith("[ENTER]"))		// ���� ������ ���
				{
					model.addElement(msg.substring(7));
					playersInfo();
					ta_MsgView.append("[" + msg.substring(7) + "]���� �����Ͽ����ϴ�.\n");
					scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
					validate(); 
				}

				else if (msg.startsWith("[DISCONNECT]"))	// ������ ���������
				{
					model.removeElement(msg.substring(6));
					playersInfo();
					ta_MsgView.append("[" + msg.substring(12) + "]���� �������ϴ�.\n");
					scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
					validate(); 
				}

				else if (msg.startsWith("������ �����մϴ�."))	// �������� ������ ���۵� ���
				{
					board.setLabel(model);
					turnButton.setEnabled(true);
					bellButton.setEnabled(true);
					readyButton.setEnabled(false);
					stopSound(); //////////////////////////////////////������ ���۵Ǹ� ������ �ٲ��.
					playSound("bgm/lck����.wav"); ////////////////////
				}

				else if (msg.startsWith("[REPAINT]"))		// �������� ī������� �� �ٽ� �׸��� ��û
				{
					int a = msg.indexOf("|");
					int b = Integer.parseInt(msg.substring(a+1));
					board.UpdateDraw(msg.substring(9, a), b);
				}
				
				else if (msg.startsWith("[CARDNUM]"))		// ���� ī����� �޴´�.
				{
					int a = msg.indexOf("|");
					int b = Integer.parseInt(msg.substring(a+1));
					board.UpdateCardNum(msg.substring(9, a), b);
				}
				else if (msg.startsWith("[DEAD]"))			// ī�尡 ��� �׾��� ��� �޴� �޼���
				{
					bellSound("bgm/���ӿ���.wav");
					stopSound();
					playSound("bgm/login.wav");
					la_GameInfo.setText("����� �׾����ϴ�.");
					turnButton.setEnabled(false);
					bellButton.setEnabled(false);
				}

				else if (msg.startsWith("[UPDATEDEAD]"))	// ���� ������ �󺧰� ī�带 ����
				{
					ta_MsgView.append(msg.substring(12) + "���� �׾����ϴ�.\n");
					scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
					validate();
					board.UpdateDead(msg.substring(12));
				}
				
				else if (msg.startsWith("[SUCCESS]"))		// ��ġ�� �������� ���
				{
					try{
						bellSound("bgm/���Ҹ�.wav");
						la_GameInfo.setText(msg.substring(9) + "���� ��ġ�⸦ �����߽��ϴ�!!");
						Thread.sleep(1000);
						bellButton.setEnabled(true);
						turnButton.setEnabled(true);
						board.CardInit();
					}catch(Exception e){
					}
				} 
				else if (msg.startsWith("[FAIL]"))			// ��ġ�⿡ �������� ���
				{
					bellSound("bgm/���.wav");
					la_GameInfo.setText(msg.substring(6) + "���� ��ġ�⸦ �����߽��ϴ�.");
					turnButton.setEnabled(true);
					bellButton.setEnabled(true);

					validate();
				}
				else if (msg.startsWith("[GAMEINIT]"))		// ������ ������ �ʱ�ȭ�� ��û�� ���
				{
					board.CardInit();
					readyButton.setEnabled(true);
					readyButton.setText("�غ�");
					ready = false;
					board.iiPlayerLife[0] = board.iiPlayerDL[0];
					board.iiPlayerLife[1] = board.iiPlayerDL[1];
					board.iiPlayerLife[2] = board.iiPlayerDL[2];
					board.iiPlayerLife[3] = board.iiPlayerDL[3];
					stopSound(); ///////////////////////////////////������ ������ �ٽ� ������ bgm���� �ٲ��.
					playSound("bgm/��׽ý�.wav"); ///////////////////
				}
				else if (msg.startsWith("[WIN]"))
				{
					la_GameInfo.setText("����� �̰���ϴ�.");
					turnButton.setEnabled(false);
					bellButton.setEnabled(false);
				}
				else if (msg.startsWith("[BELL]"))
				{
					turnButton.setEnabled(false);
					bellButton.setEnabled(false);
					
				}
				else										// �׳� �޼����� ������� �׳� ���
				{
					ta_MsgView.append(msg + "\n");
					scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
					validate();
				}
			}
		}
		catch (IOException ie)
		{
	//		ta_MsgView.append(ie + "\n");
			ta_MsgView.append("������ ������ϴ�.\n");
			scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
			validate();
		}
	//	ta_MsgView.append("������ ������ϴ�.\n");
		scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
		validate();
	}

	private void playersInfo()			// ���� �ο��� ��� �޼ҵ�
	{
		int count = model.getSize();    //����Ʈ
		la_PlayerInfo.setText("���� " + count + "�� ������");
	}

	private void nameList(String msg)	// �������� ���� �÷��̾� ����Ʈ�� �з��ؼ� ����Ʈ�� ����.
	{
		model.removeAllElements();
		StringTokenizer st = new StringTokenizer(msg, "\t");
		while (st.hasMoreElements())
		{
			model.addElement(st.nextToken());
		}
		playersInfo();
	}

	private void connect()				// ������ ����
	{
		try
		{
			String ip = tf_ipaddress.getText();
			ta_MsgView.append("������ ������ ��û�մϴ�.\n");
			socket = new Socket(ip, 7777);
			ta_MsgView.append("--���� ����--\n");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			new Thread(this).start();
			board.setWriter(writer);
		}
		catch (Exception e)
		{
			ta_MsgView.append("\n\n���� ����.. IP�ּҸ� Ȯ�����ּ���.\n");
			run();
		}
	}

	public static void main(String[] args)
	{
		HalliGalli_Client client = new HalliGalli_Client();
		client.setSize(800, 600);
		client.setLocationRelativeTo(null);
		client.setVisible(true);
		client.setResizable(false);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}