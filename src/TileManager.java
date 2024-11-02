import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TileManager {
    ArrayList<Tile> tile_List;

    public TileManager(){
        tile_List = new ArrayList<>();
    }

    public void loadTiles(){
        try{
            Tile t1 = new Tile(0,0);
            t1.img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_Tile.png"));
            Tile t2 = new Tile(48,0);
            t2.img = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_Tile.png"));
            tile_List.add(t1);
            tile_List.add(t2);
        }

        catch (IOException e){
            e.printStackTrace();
        }

    }
}
