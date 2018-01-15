import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class MapCreator {

	BufferedImage srcImage;
	ArrayList<Tile> tiles;
	String filePath;
	String imagePath;
	
	
	MapCreator(String textFilePath,String imagePath) throws Exception{
		this.filePath=textFilePath;
		this.imagePath=imagePath;
		loadImage();
		tiles=new ArrayList<Tile>();
		getCoordinates();
		
	}
	
	
	public void loadImage() throws IOException {
		URL url = this.getClass().getResource(imagePath);
		srcImage = ImageIO.read(url);
	//	if(srcImage==null){System.out.println("NULL");}
	}
	
	
	
	public void paint(Graphics g){
		
		
	}
	
	public ArrayList<Integer> getCoordinates() throws Exception{
	/*	Scanner scanner = new Scanner(getClass().getResourceAsStream("newtest.txt"));
		Pattern p=Pattern.compile("[[\\s|][A-Za-z][A-Za-z0-9]*]");
		p.matcher(input)*/
		ArrayList<Integer> crd = new ArrayList<Integer>();
		 
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		  String l;
		  File file = new File(filePath);
		  @SuppressWarnings("resource")
		Scanner i=new Scanner(file);
		    while ( i.hasNextLine()) {
		       // process the line.
		    	l=i.nextLine();
		    	 System.out.println(l);
		    	 String[] in=l.split("[\\s+]");
		    	 System.out.println(Arrays.toString(in));
		    	 //if(srcImage==null){System.out.println("NULL_IMAGE_SRC");}
		    	 BufferedImage im=srcImage.getSubimage(Integer.parseInt(in[1]),Integer.parseInt(in[2]),Integer.parseInt(in[3]), Integer.parseInt(in[4]));
		    	 ArrayList<Integer> coords=new ArrayList<Integer>();
		    	 //if(i==null){System.out.println("null mini image ");}
		    	 for(int j=5;j<in.length;j++){
		    		 coords.add(Integer.parseInt(in[j]));
		    	 }
		    	 System.out.println(coords);
		    	tiles.add(new Tile(im,coords));
		    	 
		    	 
		    }
		}
	
		return crd;
	}

}
