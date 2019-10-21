package WorldOfZuul2D;


import javafx.scene.image.Image;

// Controls animation of images
public class AnimatedImage {
    // Array of 
    public Image[] frames;
    public double duration;
     
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    } 
}
