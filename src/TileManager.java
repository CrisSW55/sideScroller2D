import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class TileManager {
    GamePanel gp;
    ArrayList<Tile> tiles;
    //Current tileIndex from the levelMap.txt file using columns and rows
    int [][] tileIndex;
    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new ArrayList<>();
        tileIndex = new int[gp.totalLevelRow][gp.totalLevelCol];
    }


    public void read_TileMap() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("res/level1_Map.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String regex = " ";
            String[] myArray = line.split(regex);
            int row= 0;
            int col = 0;
            while (line != null) {
                for(String s:myArray){
                    if(col < gp.totalLevelCol){
                        tileIndex [row][col] = Integer.parseInt(s);
                        //System.out.println("i: " + row+ "col: " + col);
                        //System.out.println("TileIndex: " + tileIndex[i][col]);
                        col++;
                    }
                }

                sb.append(line);
                //System.out.println("Line: "+line);
                sb.append(System.lineSeparator());
                //Rows below!
                line = br.readLine();
                row++;
                if(row< gp.totalLevelRow){
                    myArray = line.split(regex);
                }
                col = 0;
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
            t1.collision = true;
            Tile t2 = new Tile();
            t2.img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_Tile.png"));
            t2.collision = true;
            tiles.add(null);
            tiles.add(t1);
            tiles.add(t2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void repaint(Graphics2D g2){
        for(int row = 0; row<gp.totalLevelRow;row++){
            for(int col = 0; col<gp.totalLevelCol; col++){
                int levelX = col * gp.tile_Width;
                int levelY = row * gp.tile_Height;
                int screenX = levelX - gp.player.levelX + gp.player.screenX;
                int screenY = levelY - gp.player.levelY + gp.player.screenY;
                //Add render efficiency bounds only rendering the current screen!
                //      Write code here!    //
                if(levelX > gp.player.levelX - gp.player.screenX - gp.player.width && levelX < gp.player.levelX + gp.player.screenX + gp.player.width &&
                        levelY > gp.player.levelY - gp.player.screenY && levelY < gp.player.levelY + gp.player.screenY &&
                        tiles.get(tileIndex[row][col]) != null){
                    tiles.get(tileIndex[row][col]).set_Position(col * gp.tile_Width,row*gp.tile_Height);
                    g2.drawImage(tiles.get(tileIndex[row][col]).img,screenX,screenY,gp.tile_Width,gp.tile_Height,null);
                }

            }
        }
    }
    }

