import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {

	BufferedImage image;
	BufferedImage currentFrame;
	String path;
	public int x = 0, y = 0;
	public int srcX = 0, srcY = 0;
	int frame_length;
	int frame_width;

	SpriteSheet(String p, int fl, int fw) throws IOException {
		this.frame_length = fl;
		this.frame_width = fw;
		path = p;

	}

	public void loadImage() throws IOException {
		URL url = this.getClass().getResource(path);
		image = ImageIO.read(url);
	}

	public void animate(int FPS, Main m) {
		if (m.tickCount % FPS == 0) {
			srcX = srcX + frame_width;
		}
	}

	public void render(Graphics g, int srcY, int FPS, Main m) {
		if ((m.tickCount % (60 / FPS)) == 0) {
			srcX = srcX + frame_width;
		}
		if (srcX > image.getWidth() - frame_width) {
			srcX = 0;
		}

		this.srcY = srcY;
		currentFrame = image.getSubimage(srcX, srcY, frame_width, frame_length);
		g.drawImage(currentFrame, x, y, null);
	}

}
