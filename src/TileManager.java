import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class TileManager {
    ArrayList<BufferedImage> tileImages;
    int [][] tileIndex = new int[10][12];
    public TileManager(){
        tileImages = new ArrayList<>();

    }

    public void readTileMap() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("res/tileMap1.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String regex = " ";
            String[] myArray = line.split(regex);
            int i = 0;
            int j = 0;
            while (line != null) {
                for(String s:myArray){
                    if(j < 12){
                        tileIndex [i][j] = Integer.parseInt(s);
                        System.out.println("i: " + i + "j: " + j);
                        System.out.println("TileIndex: " + tileIndex[i][j]);
                        j++;
                    }
                }

                sb.append(line);
                System.out.println("Line: "+line);
                sb.append(System.lineSeparator());
                //Rows below!
                line = br.readLine();
                i++;
                if(i < 10){
                    myArray = line.split(regex);
                }
                j = 0;
            }
            String everything = sb.toString();
            System.out.println(everything);
            br.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load_TileImages() {
        try {
            tileImages.add(ImageIO.read(getClass().getResourceAsStream("/tiles/grass_Tile.png")));
            tileImages.add(ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_Tile.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

