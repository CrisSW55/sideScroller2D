import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class TileManager {
    ArrayList<Tile> tiles;
    int total_Cols = 267;
    int total_Rows = 14;
    //Current tileIndex from the levelMap.txt file using columns and rows
    int [][] tileIndex = new int[total_Rows][total_Cols];
    public TileManager(){
        tiles = new ArrayList<>();

    }


    public void read_TileMap() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("res/level1_Map.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String regex = " ";
            String[] myArray = line.split(regex);
            int i = 0;
            int j = 0;
            while (line != null) {
                for(String s:myArray){
                    if(j < total_Cols){
                        tileIndex [i][j] = Integer.parseInt(s);
                        //System.out.println("i: " + i + "j: " + j);
                        //System.out.println("TileIndex: " + tileIndex[i][j]);
                        j++;
                    }
                }

                sb.append(line);
                //System.out.println("Line: "+line);
                sb.append(System.lineSeparator());
                //Rows below!
                line = br.readLine();
                i++;
                if(i < total_Rows){
                    myArray = line.split(regex);
                }
                j = 0;
            }
            String everything = sb.toString();
            //System.out.println(everything);
            br.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load_TileImages() {
        try {
            Tile t1 = new Tile();
            t1.img = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_Tile.png"));
            Tile t2 = new Tile();
            t2.img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_Tile.png"));
            tiles.add(null);
            tiles.add(t1);
            tiles.add(t2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

