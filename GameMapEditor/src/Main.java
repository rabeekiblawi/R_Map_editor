import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable, KeyListener {
	//BufferedImage sprite;
	BufferedImage bg;
	int keypressed = 0;
	public static int WIDTH = 480;
	public static int  HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE =2 ;
	public static final String NAME = "Game";
	private JFrame frame;
	public int tickCount = 0;
	Tile tileTest;
	MapCreator map;
	//SpriteSheet myCharacter;
	
	

	public Main() throws Exception {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		 map =new MapCreator("e:\\testRabee.txt", "chipset01.jpg");
	}

	public boolean running = false;

	public synchronized void start() {
		running = true;
		new Thread(this).start();

	}

	public synchronized void stop() {
		running = false;

	}
//**********************************************************************************************
	public void init() throws IOException {
		
		/*URL url = this.getClass().getResource("bg_Dusk.png");
		 bg = ImageIO.read(url);*/
	//	myCharacter=new SpriteSheet("SpriteSheet.png", 80,80);
	//myCharacter.loadImage();
		addKeyListener(this);
	}

	public void tick() throws IOException  {
	tickCount++;
	if(tickCount>60){
		tickCount=0;
	}
//	System.out.println(tickCount);
	}//end tick
	
	
	public void render() throws IOException  {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics circle=bs.getDrawGraphics();
		Graphics2D g2d=(Graphics2D)g;
	
		g.setColor(Color.BLUE);
	//	tileTest.paint(g);
		g.fillRect(0, 0, getWidth(), getHeight());
	//	tileTest.paint(g);
		//myCharacter.render(g, 80, 8,this);
		for(Tile t: map.tiles){
			t.paint(g);
		}
		//map.tiles.get(0).paint(g);
		
		g.dispose(); 
		bs.show();
		
		
	}

	
	
/**************************************************************************************/
/**************************************************************************************/
	public void keyPressed(KeyEvent e) {
		
		
		}


	public void keyReleased(KeyEvent e) {

		
	
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	

	
/*************************************************************************************/
/*********************DontTouch!!*****************************************************/	
	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000 / 60D;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		try {
			init();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;

			while (delta >= 1) {
				ticks++;
				
					try {
						tick();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				

				delta -= 1;
				shouldRender = true;
			}// end while
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				
					try {
						render();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}// end if
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				// System.out.println("ticks:  "+ticks+" Frames: "+frames);
				frames = 0;
				ticks = 0;

			}// end if

		}// end game loop

	}
	public static void main(String[] args) throws Exception {
		new Main().start();

	}
/*************************************************************************************/



	public  static int Height() {
		return HEIGHT*SCALE;
	}
	public  static int Width() {
		return WIDTH*SCALE;
	}

}
