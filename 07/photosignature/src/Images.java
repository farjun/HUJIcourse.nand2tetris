import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Images {
    public static BufferedImage getImage(String path, int width, int height) {
        BufferedImage image = null;
        File f;

        //read image
        try{
            f = new File(path); //image file path
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            image = ImageIO.read(f);
            System.out.println("Reading complete.");
        }catch(IOException e){
            System.out.println("Error: "+e);
            System.exit(1);
        }

        return image;
    }

    public static void saveImage(BufferedImage image, String path){
        System.out.println(12);
        File f;

        //write image
        try{
            f = new File(path);  //output file path
            ImageIO.write(image, "jpg", f);
            System.out.println("Writing complete.");
        }catch(IOException e){
            System.out.println("1Error: "+e);
            System.exit(1);
        }
    }
}