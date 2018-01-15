import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;


public class Tile {

	BufferedImage image;
	
	String path="";
	ArrayList<Integer> coords;
	
	public Tile(BufferedImage image,
			ArrayList<Integer> coords) throws Exception {
		this.coords=coords;
		this.image = image;
		
	//getCoordinates();

	}
	
	public void paint(Graphics g){
		for(int i=0;i<coords.size();i+=3){
			for(int j=0;j<coords.get(i+2);j++){
		g.drawImage(image, coords.get(i)+(image.getWidth()*j), coords.get(i+1), null);}
		}
	}

}
