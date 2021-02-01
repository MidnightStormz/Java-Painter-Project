import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.Hashtable;

public class Assignment10 extends JFrame implements MouseListener, MouseMotionListener, ActionListener, ChangeListener, ItemListener
{
  int last_x=-10;
  int last_y=-10;
  int line_beginX=-10;
  int line_beginY=-10;
  int line_endX=-10;
  int line_endY=-10;
  int xpos=-10;
  int ypos=-10;
  Container con = null;
  Color drawColor = (Color.red);

  JPanel buttonPanel = new JPanel(new GridLayout(1,7));
  JPanel fontPanel = new JPanel(new GridLayout(3,0));
  JPanel brushPanel = new JPanel(new GridLayout(2,0));
  JPanel drawPanel = new JPanel(); //default flowLayout
  JPanel colorTextPanel = new JPanel(new GridLayout(3,2));

  ImageIcon eraser = new ImageIcon("eraser.png");
  JButton eraserButton = new JButton(eraser);

  ImageIcon pencil = new ImageIcon("pencil.png");
  JButton pencilButton = new JButton(pencil);

  ImageIcon brush = new ImageIcon("brush.png");
  JButton brushButton = new JButton(brush);

  ImageIcon text = new ImageIcon("text.png");
  JButton textButton = new JButton(text);

  ImageIcon line = new ImageIcon("line.png");
  JButton lineButton = new JButton(line);

  ButtonGroup fontGroup = new ButtonGroup();

  JRadioButton arial = new JRadioButton("Arial");

  JRadioButton tahoma = new JRadioButton("Tahoma");

  JRadioButton courier = new JRadioButton("Courier New");

  JLabel brushLabel = new JLabel("Brush:");
  

  ImageIcon red = new ImageIcon("red.gif");
  JButton redButton = new JButton(red);

  ImageIcon yellow = new ImageIcon("yellow.gif");
  JButton yellowButton = new JButton(yellow);

  ImageIcon green = new ImageIcon("green.gif");
  JButton greenButton = new JButton(green);

  ImageIcon blue = new ImageIcon("blue.gif");
  JButton blueButton = new JButton(blue);

  JLabel label = new JLabel("Enter the text, then click the location:");
  JTextField textField = new JTextField(20);


  boolean eraserOn = false;
  boolean pencilOn = true;
  boolean brushOn = false;
  boolean textOn = false;
  boolean lineOn = false;

  boolean redOn = true;
  boolean yellowOn = false;
  boolean greenOn = false;
  boolean blueOn = false;

  float thickness = 20.0f;

  Font myFont = new Font("Arial", Font.BOLD, 20);



  public Assignment10() //constructor
  {
    setTitle("Assignment 10");
    setBackground(Color.white);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    con = this.getContentPane(); 
    con.setBackground(Color.white);
    addMouseListener(this);
    addMouseMotionListener(this);

    eraserButton.addActionListener(this);
    eraserButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    buttonPanel.add(eraserButton);

    pencilButton.addActionListener(this);
    pencilButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
    buttonPanel.add(pencilButton);

    brushButton.addActionListener(this);
    brushButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    buttonPanel.add(brushButton);

 	textButton.addActionListener(this);
    textButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    buttonPanel.add(textButton);

    lineButton.addActionListener(this);
    lineButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    buttonPanel.add(lineButton);


    arial.addItemListener(this);
    tahoma.addItemListener(this);
    courier.addItemListener(this);
    fontGroup.add(arial);
    fontPanel.add(arial);
    fontGroup.add(tahoma);
    fontPanel.add(tahoma);
    fontGroup.add(courier);
    fontPanel.add(courier);
    buttonPanel.add(fontPanel);

    brushPanel.add(brushLabel);

  	JSlider strokeSize = new JSlider(JSlider.HORIZONTAL,1,3,3);
 	strokeSize.setMinorTickSpacing(1);
  	strokeSize.setMajorTickSpacing(1);
  	strokeSize.setPaintTicks(true);
  	strokeSize.setPaintLabels(true);
  	strokeSize.setSnapToTicks(true);

  	Hashtable<Integer, JComponent> labelTable = new Hashtable<>();
  	labelTable.put(1, new JLabel("Thin"));
  	labelTable.put(3, new JLabel("Thick") );
  	strokeSize.setLabelTable( labelTable );

  	strokeSize.addChangeListener(this);


    brushPanel.add(strokeSize);
    buttonPanel.add(brushPanel);

    con.add(buttonPanel, BorderLayout.NORTH);
    con.add(drawPanel, BorderLayout.CENTER);
    drawPanel.setBackground(Color.white);

    redButton.addActionListener(this);
    redButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
    colorTextPanel.add(redButton);

    yellowButton.addActionListener(this);
    yellowButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    colorTextPanel.add(yellowButton);

    greenButton.addActionListener(this);
    greenButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    colorTextPanel.add(greenButton);

    blueButton.addActionListener(this);
    blueButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    colorTextPanel.add(blueButton);

    colorTextPanel.add(label);
    label.setVisible(false);
    colorTextPanel.add(textField);
    textField.setVisible(false);

    con.add(colorTextPanel, BorderLayout.SOUTH);


    JMenu myMenu = new JMenu("File");
    JMenuItem m;

    m = new JMenuItem("New");
    m.addActionListener(this);
    myMenu.add(m);

    m = new JMenuItem("Exit");
    m.addActionListener(this);
    myMenu.add(m);


  	JMenuBar mBar = new JMenuBar();
    mBar.add(myMenu);
    setJMenuBar(mBar);



  }

  public void mouseDragged(MouseEvent evt)
  {
    xpos=evt.getX();
    ypos=evt.getY();
    Graphics g = getGraphics();
    g.setColor(drawColor);
    g.drawLine(last_x,last_y,xpos,ypos);
    last_x = xpos;
    last_y = ypos;
   	brushPanel.repaint();
   	fontPanel.repaint();
   	colorTextPanel.repaint();
   	buttonPanel.repaint();
   	if(brushOn == true)
   	{
   		Graphics2D gr2D =(Graphics2D)g;
   		BasicStroke aStroke = new BasicStroke(thickness,
   			BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
   		gr2D.setStroke(aStroke);
   		gr2D.drawLine(last_x,last_y,xpos,ypos);
   		
   	}
   	if(eraserOn == true)
   	{
   		Graphics2D gr2D =(Graphics2D)g;
   		BasicStroke aStroke = new BasicStroke(thickness,
   			BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
   		gr2D.setStroke(aStroke);
   		gr2D.drawLine(last_x,last_y,xpos,ypos);
   	}
  }

  public void mousePressed(MouseEvent evt)
  {
    last_x = evt.getX();
    last_y = evt.getY();
    line_beginX = evt.getX();
    line_beginY = evt.getY();
    if(textOn == true)
    {
    	Graphics g = getGraphics();
    	String entry = textField.getText();
    	g.setFont(myFont);
    	g.setColor(drawColor);
    	g.drawString(entry,last_x,last_y);


    }
  }

  //following methods need to be included even if not used
  public void mouseMoved(MouseEvent evt) {}
  public void mouseClicked(MouseEvent evt) {}
  public void mouseReleased(MouseEvent evt) 
  {
  	if(lineOn == true)
  	{
  	line_endX = evt.getX();
  	line_endY = evt.getY();

  	Graphics g = getGraphics();
  	g.drawLine(line_beginX,line_beginY,line_endX,line_endY);
  }
  }
  public void mouseEntered(MouseEvent evt) {}
  public void mouseExited(MouseEvent evt) {}


  //action performed Method
  public void actionPerformed(ActionEvent e)
  {
  	String actionCommand = e.getActionCommand();
  	if(actionCommand.equals("New"))
  		drawPanel.repaint();
  	else if(actionCommand.equals("Exit"))
  		System.exit(0);




  	if(e.getSource() == eraserButton)
  	{
  		eraserOn = true;
  		pencilOn = false;
  		brushOn = false;
  		textOn = false;
  		lineOn = false;
  		drawColor = Color.white;
  		label.setVisible(false);
  		textField.setVisible(false);
  		eraserButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		pencilButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		brushButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		textButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		lineButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		//raise other buttons
  	}
  	if(e.getSource() == pencilButton)
  	{
  		eraserOn = false;
  		pencilOn = true;
  		brushOn = false;
  		textOn = false;
  		lineOn = false;
  		drawColor = Color.black;
  		label.setVisible(false);
  		textField.setVisible(false);
  		pencilButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		eraserButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		brushButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		textButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		lineButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		//raise other buttons

  	}
  	if(e.getSource() == brushButton)
  	{
  		eraserOn = false;
  		pencilOn = false;
  		brushOn = true;
  		textOn = false;
  		lineOn = false;
  		drawColor = Color.red;
  		label.setVisible(false);
  		textField.setVisible(false);
  		pencilButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		eraserButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		brushButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		textButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		lineButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		//raise other buttons

  	}
  	if(e.getSource() == textButton)
  	{
  		eraserOn = false;
  		pencilOn = false;
  		brushOn = false;
  		textOn = true;
  		lineOn = false;
  		drawColor = Color.black;
  		label.setVisible(true);
  		textField.setVisible(true);
  		pencilButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		eraserButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		brushButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		textButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		lineButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		//raise other buttons

  	}
  	if(e.getSource() == lineButton)
  	{
  		eraserOn = false;
  		pencilOn = false;
  		brushOn = false;
  		textOn = false;
  		lineOn = true;
  		label.setVisible(false);
  		textField.setVisible(false);
  		pencilButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		eraserButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		brushButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		textButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		lineButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		//raise other buttons

  	}
  	if(e.getSource() == redButton)
  	{
  		redOn = true;
  		yellowOn = false;
  		greenOn = false;
  		blueOn = false;
  		drawColor = Color.red;
  		redButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		yellowButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		greenButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		blueButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  	}
  	if(e.getSource() == yellowButton)
  	{
  		redOn = false;
  		yellowOn = true;
  		greenOn = false;
  		blueOn = false;
  		drawColor = Color.yellow;
  		redButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		yellowButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		greenButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		blueButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  	}
  	if(e.getSource() == greenButton)
  	{
  		redOn = false;
  		yellowOn = false;
  		greenOn = true;
  		blueOn = false;
  		drawColor = Color.green;
  		redButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		yellowButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		greenButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  		blueButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  	}
  	if(e.getSource() == blueButton)
  	{
  		redOn = false;
  		yellowOn = false;
  		greenOn = false;
  		blueOn = true;
  		drawColor = Color.blue;
  		redButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		yellowButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		greenButton.setBorder(new BevelBorder(BevelBorder.RAISED));
  		blueButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
  	}

  }
  //state changed method
  public void stateChanged(ChangeEvent evt)
  {
    JSlider source = (JSlider)evt.getSource();
    int value = source.getValue();

    if(value == 1)
    {
    	thickness = 5.0f;
    }
    if(value == 2)
    {
    	thickness = 10.0f;
    }
    if(value == 3)
    {
    	thickness = 20.0f;
    }
  }
  public void itemStateChanged(ItemEvent check)
  {
  	Object source = check.getItem();
  	if(source == arial)
  	{
  		myFont = new Font("Arial", Font.BOLD,25);
  	}
  	if(source == tahoma)
  	{
  		myFont = new Font("Tahoma", Font.BOLD,25);
  	}
  	if(source == courier)
  	{
  		myFont = new Font("Courier", Font.BOLD,25);
  	}
  }


  public static void main(String[] args)
  {

    Assignment10 myDD = new Assignment10();
    Image image = Toolkit.getDefaultToolkit().getImage("IconImage.gif");
	myDD.setIconImage(image);
    myDD.setSize(750,850);
    myDD.setResizable(false); // do not allow the frame to be resized
    myDD.setVisible(true);
    myDD.setLocationRelativeTo(null);
  }
}