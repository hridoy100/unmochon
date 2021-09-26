package DB;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BuffImg {
    public static BufferedImage toBufferedImage(String imgName) throws IOException {
        ImageIO.read(new File(imgName));
        final float FACTOR  = 4f;
        BufferedImage img = ImageIO.read(new File(imgName));
//
//        int scaleX = (int) (img.getWidth() * FACTOR);
//        int scaleY = (int) (img.getHeight() * FACTOR);
//        Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
//        BufferedImage buffered = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_ARGB);
//        buffered.getGraphics().drawImage(image, 0, 0 , null);
        return img;
    }
}
