import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.sound.sampled.*; /////////////배경음악을 추가하기 위한 클래스 임포트

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
		iiPlayerCard = new ImageIcon[4]; // 카드뒷면 배열 -> 카드 뒷면 출력
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

		for (int i=0; i<5; i++)		// 1개짜리 카드 이미지설정
		{
			iiCard[14*0 + i] = new ImageIcon("image/c1_1.jpg");
			iiCard[14*1 + i] = new ImageIcon("image/c2_1.jpg"); 
			iiCard[14*2 + i] = new ImageIcon("image/c3_1.jpg");
			iiCard[14*3 + i] = new ImageIcon("image/c4_1.jpg");
		}
		for (int i=0; i<3; i++)		// 2,3개짜리 카드 이미지설정
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
		for (int i=0; i<2; i++)		// 4개짜리 카드 이미지설정
		{
			iiCard[14*0 + i+11] = new ImageIcon("image/c1_4.jpg");
			iiCard[14*1 + i+11] = new ImageIcon("image/c2_4.jpg");
			iiCard[14*2 + i+11] = new ImageIcon("image/c3_4.jpg");
			iiCard[14*3 + i+11] = new ImageIcon("image/c4_4.jpg");
		}
		// 5개짜리 카드 이미지설정
		iiCard[14*0 + 13] = new ImageIcon("image/c1_5.jpg");
		iiCard[14*1 + 13] = new ImageIcon("image/c2_5.jpg");
		iiCard[14*2 + 13] = new ImageIcon("image/c3_5.jpg"); 
		iiCard[14*3 + 13] = new ImageIcon("image/c4_5.jpg");

		iiCardBack = new ImageIcon("image/back2.jpg");		// 카드 뒷면
		for (int i=0; i<4; i++)
		{
			iiPlayerCard[i] = iiCardBack;
		}

		laPlayer[0] = new JLabel("Player1"); //+++++++++++// 세부위치 수정
		laPlayer[0].setBounds(20, 180, 100, 15);
		add(laPlayer[0]);
		laCardNum[0] = new JLabel("0장");
		laCardNum[0].setBounds(100, 180, 100, 15); 
		add(laCardNum[0]);

		laPlayer[1] = new JLabel("Player2");
		laPlayer[1].setBounds(375, 180, 100, 15); 
		add(laPlayer[1]);
		laCardNum[1] = new JLabel("0장");
		laCardNum[1].setBounds(455, 180, 100, 15);
		add(laCardNum[1]);

		laPlayer[2] = new JLabel("Player3");
		laPlayer[2].setBounds(20, 305, 100, 15);
		add(laPlayer[2]);
		laCardNum[2] = new JLabel("0장");
		laCardNum[2].setBounds(100, 305, 100, 15); 
		add(laCardNum[2]);

		laPlayer[3]= new JLabel("Player4");
		laPlayer[3].setBounds(375, 305, 100, 15); 
		add(laPlayer[3]);
		laCardNum[3] = new JLabel("0장");
		laCardNum[3].setBounds(455, 305, 100, 15);
		add(laCardNum[3]);
	}

	protected void paintComponent(Graphics g)		// 그리기
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

	public void UpdateDraw(String name, int CardNum)	// 그리기
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

	public void UpdateCardNum(String name, int Count)	// 카드를 냈을경우 업데이트
	{
		for (int i=0; i<4; i++)
		{
			if (name.equals(userName[i]))
			{
				laCardNum[i].setText(Count + "장");
			}
		}
	}

	public void UpdateDead(String name)					// 죽은 플레이어정보 업데이트
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

	public void CardInit()								// 카드를 초기화
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
	JTextArea			ta_MsgView;									// 메시지를 보여주는 텍스트영역
	JScrollPane			scPane;										// 스크롤
	JTextField			tf_Send = new JTextField("");				// 보낼 메시지를 적는필드
	JTextField			tf_Name = new JTextField();					// 사용자 이름 상자
	JTextField			tf_ipaddress = new JTextField();			// 서버에 접속할 ip주소입력필드
	DefaultListModel	model = new DefaultListModel();				// 리스트모델 (접속자 현황을 나타내기 위함)
	JList				li_Player = new JList(model);				// 사용자 리스트
	JButton				connectButton = new JButton(new String("접 속"), new ImageIcon("image/button.jpg"));	    	// 시작 버튼
	JButton				readyButton = new JButton(new String("준 비"), new ImageIcon("image/button.jpg"));			// 종료버튼
	JButton				turnButton;		    // 카드 뒤집기 버튼
	JLabel				la_GameInfo = new JLabel("Hallae? Mallae?");		    // 정보창
	JLabel				la_PlayerInfo = new JLabel("접속현황");	    // 인원정보
	boolean				ready = false;
	JButton				bellButton;                            
	ImageIcon           bi = new ImageIcon("image/bi1.gif");
	ImageIcon           cdb = new ImageIcon("image/cdb.gif");
	Clip                clip; /////////////////////오디오 클립을 추가하기 위한 변수
	Clip				clipEffect; ////////////////효과음을 추가하기 위한 변수
	
	HalliGalli_Board board = new HalliGalli_Board();
	BufferedReader reader;								// 입력스트림
	PrintWriter writer;									// 출력스트림
	Socket socket;										// 소켓
	String userName=null;								// 사용자 이름

	public HalliGalli_Client()
	{
		super("Hallae? Mallae?");
		
		setLayout(new BorderLayout());		//
		JLabel ct=new JLabel(new ImageIcon("image\\bg.jpg")); //
		add(ct);							// 
		ct.setLayout(new FlowLayout());	    // 배경화면 https://java-demos.blogspot.com/2012/09/setting-background-image-in-jframe.html
		ct.setLayout(null);
		
		ta_MsgView = new JTextArea(1, 1);
		ta_MsgView.setLineWrap(true);
		scPane = new JScrollPane(ta_MsgView);

		// 각종 컴포넌트를 생성하고 배치한다.
		EtchedBorder eborder = new EtchedBorder(EtchedBorder.LOWERED);
		ta_MsgView.setEditable(false);
		la_GameInfo.setBounds(10, 30, 480, 30);  // 게임정보이라는 JLabel 
		la_GameInfo.setHorizontalAlignment(JLabel.CENTER);

		ct.add(la_GameInfo);                     // 컨테이너에 정보창 JLable 추가
		board.setBounds(10, 65, 495, 495);
		board.setBorder(eborder);
		ct.add(board);

		Font f1 = new Font("메이플스토리", Font.BOLD, 22);
		la_GameInfo.setFont(f1);

		Font f2 = new Font("메이플스토리", Font.PLAIN, 17);
		connectButton.setFont(f2);
		readyButton.setFont(f2);

		Font f3 = new Font("메이플스토리", Font.PLAIN, 15);
		la_PlayerInfo.setFont(f3);
		ta_MsgView.setFont(f3);
		tf_Send.setFont(f3);
		li_Player.setFont(f3);
		tf_ipaddress.setFont(f3);
		tf_Name.setFont(f3);

		////////////////// 종버튼
		bellButton = new JButton(bi);
		bellButton.setLayout(new GridLayout());
		bellButton.setEnabled(false); 
		bellButton.setBorderPainted(false);
		bellButton.setContentAreaFilled(false);
		bellButton.setFocusPainted(false);
		bellButton.setBounds(140, 213, 75, 75);
		board.add(bellButton);

		////////////////// 카드 뒤집기
		turnButton = new JButton(cdb);
		turnButton.setLayout(new GridLayout());
		turnButton.setEnabled(false); 
		turnButton.setBorderPainted(false);
		turnButton.setContentAreaFilled(false);
		turnButton.setFocusPainted(false);
		turnButton.setBounds(280, 205, 65, 90);
		board.add(turnButton);

		/////////////////////////////배경음악을 추가한다.
		playSound("bgm/헤네시스.wav");

        ////////////////////////// 서버주소, 닉네임 입력 패널
		JPanel p1 = new JPanel();			 
		p1.setLayout(new GridLayout(1,2));	 
		p1.add(tf_ipaddress);
		p1.add(tf_Name);
		p1.setBounds(520,30,250,25);		 

		////////////////////////// 접속 및 준비버튼 패널
		JPanel p2 = new JPanel();			 
		p2.setLayout(new GridLayout(1,2));
		p2.add(connectButton);				 
		p2.add(readyButton);
		connectButton.setHorizontalTextPosition(JButton.CENTER);
		readyButton.setHorizontalTextPosition(JButton.CENTER);
		//readyButton.setEnabled(false);		 
		p2.setBounds(520,65,250,35);		 

		////////////////////////// 접속현황 패널
		JPanel p3 = new JPanel();          
		p3.setLayout(new BorderLayout());  
		p3.add(la_PlayerInfo, "North");    
		p3.add(li_Player, "Center");	   
		p3.setBounds(520, 105, 250, 125);  
		p3.setBorder(eborder);

		////////////////////////// 채팅입력 및 출력 패널
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
		bellButton.addActionListener(this); //+++++++// 벨버튼 객체 수정

		tf_ipaddress.setText("서버IP");
		tf_Name.setText("닉네임(2~6글자)");
		ta_MsgView.append("서버IP와 닉네임 입력 후\n");
		ta_MsgView.append("접속버튼을 눌러주세요\n");

		board.setOpaque(false); // -> 배경화면(패널) 투명도 
	}


	///////////////////////////////////////배경음악을 넣는 메소드
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
			if (ae.getSource() == tf_Send)			// 보내기텍스트필드에 글을 입력했을 경우
			{
				String msg = tf_Send.getText();
				if (msg.length() == 0)
				{
					return;
				}
	/*			if (msg.length() >= 30) // 글자수 제한
				{
					msg = msg.substring(0, 30);
				}   */
				writer.println("[MSG]" + msg);
				tf_Send.setText("");
			}
			else if (ae.getSource() == connectButton)	// 접속버튼을 눌렀을 경우
			{
				String name = tf_Name.getText().trim();
				if (name.length()<=1 || name.length()>6)
				{
					la_GameInfo.setText("올바른 닉네임을 입력해주세요");
					tf_Name.requestFocus();
					return;
				}
				connect();
				userName = name;
				tf_Name.setText(userName);
				tf_Name.setEditable(false);
				tf_ipaddress.setEditable(false);
				la_GameInfo.setText("접속성공");
				writer.println("[CONNECT]" + userName);
				connectButton.setEnabled(false);				
				readyButton.setEnabled(true);
			}
			else if (ae.getSource() == readyButton)	// 레디해제버튼을 눌렀을 경우
			{
				
				if (!ready)
				{
					ready = true;
					writer.println("[READY]");
					readyButton.setText("준비해제");
					la_GameInfo.setText("준비완료");
				}
				else
				{
					ready = false;
					writer.println("[NOREADY]");
					readyButton.setText("준 비");
					la_GameInfo.setText("준비해제");
				}				
			}
			else if (ae.getSource() == turnButton)		// 카드뒤집기버튼을 눌렀을 경우
			{
				la_GameInfo.setText("당신이 카드를 뒤집습니다.");
				writer.println("[TURN]" + userName);
			}
			else if (ae.getSource() == bellButton)		// 종치기버튼을 눌렀을 경우
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
				if (msg.startsWith("[FULL]"))		// 서버에 접속인원이 다찼을 경우
				{
					la_GameInfo.setText("방에 인원이 다찼습니다.");
				}
				else if (msg.startsWith("재접속"))	 // 닉네임이 중복되어 재접속 할 경우
				{
					ta_MsgView.append("접속이 끊겼습니다.\n");
					tf_Name.setEditable(true);
					tf_ipaddress.setEditable(true);
					connectButton.setEnabled(true);				
					readyButton.setEnabled(false);
					run();
				}

				else if (msg.startsWith("[PLAYERS]"))	// 플레이어리스트를 받는다.
				{
					nameList(msg.substring(9));
				}

				else if (msg.startsWith("[ENTER]"))		// 상대방 입장할 경우
				{
					model.addElement(msg.substring(7));
					playersInfo();
					ta_MsgView.append("[" + msg.substring(7) + "]님이 입장하였습니다.\n");
					scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
					validate(); 
				}

				else if (msg.startsWith("[DISCONNECT]"))	// 접속이 끊어진경우
				{
					model.removeElement(msg.substring(6));
					playersInfo();
					ta_MsgView.append("[" + msg.substring(12) + "]님이 나갔습니다.\n");
					scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
					validate(); 
				}

				else if (msg.startsWith("게임을 시작합니다."))	// 서버에서 게임이 시작된 경우
				{
					board.setLabel(model);
					turnButton.setEnabled(true);
					bellButton.setEnabled(true);
					readyButton.setEnabled(false);
					stopSound(); //////////////////////////////////////게임이 시작되면 음악이 바뀐다.
					playSound("bgm/lck밴픽.wav"); ////////////////////
				}

				else if (msg.startsWith("[REPAINT]"))		// 서버에서 카드뒤집은 뒤 다시 그리기 요청
				{
					int a = msg.indexOf("|");
					int b = Integer.parseInt(msg.substring(a+1));
					board.UpdateDraw(msg.substring(9, a), b);
				}
				
				else if (msg.startsWith("[CARDNUM]"))		// 현재 카드수를 받는다.
				{
					int a = msg.indexOf("|");
					int b = Integer.parseInt(msg.substring(a+1));
					board.UpdateCardNum(msg.substring(9, a), b);
				}
				else if (msg.startsWith("[DEAD]"))			// 카드가 없어서 죽었을 경우 받는 메세지
				{
					bellSound("bgm/게임오버.wav");
					stopSound();
					playSound("bgm/login.wav");
					la_GameInfo.setText("당신은 죽었습니다.");
					turnButton.setEnabled(false);
					bellButton.setEnabled(false);
				}

				else if (msg.startsWith("[UPDATEDEAD]"))	// 죽은 유저의 라벨과 카드를 수정
				{
					ta_MsgView.append(msg.substring(12) + "님이 죽었습니다.\n");
					scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
					validate();
					board.UpdateDead(msg.substring(12));
				}
				
				else if (msg.startsWith("[SUCCESS]"))		// 종치기 성공했을 경우
				{
					try{
						bellSound("bgm/종소리.wav");
						la_GameInfo.setText(msg.substring(9) + "님이 종치기를 성공했습니다!!");
						Thread.sleep(1000);
						bellButton.setEnabled(true);
						turnButton.setEnabled(true);
						board.CardInit();
					}catch(Exception e){
					}
				} 
				else if (msg.startsWith("[FAIL]"))			// 종치기에 실패했을 경우
				{
					bellSound("bgm/띠용.wav");
					la_GameInfo.setText(msg.substring(6) + "님이 종치기를 실패했습니다.");
					turnButton.setEnabled(true);
					bellButton.setEnabled(true);

					validate();
				}
				else if (msg.startsWith("[GAMEINIT]"))		// 게임이 끝나서 초기화를 요청한 경우
				{
					board.CardInit();
					readyButton.setEnabled(true);
					readyButton.setText("준비");
					ready = false;
					board.iiPlayerLife[0] = board.iiPlayerDL[0];
					board.iiPlayerLife[1] = board.iiPlayerDL[1];
					board.iiPlayerLife[2] = board.iiPlayerDL[2];
					board.iiPlayerLife[3] = board.iiPlayerDL[3];
					stopSound(); ///////////////////////////////////게임이 끝나면 다시 원래의 bgm으로 바뀐다.
					playSound("bgm/헤네시스.wav"); ///////////////////
				}
				else if (msg.startsWith("[WIN]"))
				{
					la_GameInfo.setText("당신이 이겼습니다.");
					turnButton.setEnabled(false);
					bellButton.setEnabled(false);
				}
				else if (msg.startsWith("[BELL]"))
				{
					turnButton.setEnabled(false);
					bellButton.setEnabled(false);
					
				}
				else										// 그냥 메세지만 왔을경우 그냥 출력
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
			ta_MsgView.append("접속이 끊겼습니다.\n");
			scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
			validate();
		}
	//	ta_MsgView.append("접속이 끊겼습니다.\n");
		scPane.getVerticalScrollBar().setValue (scPane.getVerticalScrollBar().getMaximum()); 
		validate();
	}

	private void playersInfo()			// 현재 인원수 출력 메소드
	{
		int count = model.getSize();    //리스트
		la_PlayerInfo.setText("현재 " + count + "명 접속중");
	}

	private void nameList(String msg)	// 서버에서 보낸 플레이어 리스트를 분류해서 리스트에 저장.
	{
		model.removeAllElements();
		StringTokenizer st = new StringTokenizer(msg, "\t");
		while (st.hasMoreElements())
		{
			model.addElement(st.nextToken());
		}
		playersInfo();
	}

	private void connect()				// 서버에 연결
	{
		try
		{
			String ip = tf_ipaddress.getText();
			ta_MsgView.append("서버에 연결을 요청합니다.\n");
			socket = new Socket(ip, 7777);
			ta_MsgView.append("--연결 성공--\n");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			new Thread(this).start();
			board.setWriter(writer);
		}
		catch (Exception e)
		{
			ta_MsgView.append("\n\n연결 실패.. IP주소를 확인해주세요.\n");
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