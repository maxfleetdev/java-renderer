import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Window extends Canvas {

    private final JFrame            frame;
    private final RenderContext     frameBuffer;
    private final BufferedImage     displayImage;
    private final byte[]            displayComponents;
    private final BufferStrategy    bufferStrategy;
    private final Graphics          graphics;

    public Window(int width, int height, String windowName) {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);       // Stops Resizing
        setMaximumSize(size);       // Stops Resizing

        // Bitmap
        frameBuffer = new RenderContext(width, height);
        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        displayComponents =
                ((DataBufferByte)displayImage.getRaster().getDataBuffer()).getData();

        // Creates JFrame Window
        frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle(windowName);
        frame.setVisible(true);

        // Buffer
        createBufferStrategy(1);
        bufferStrategy = getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void swapBuffers() {
        frameBuffer.copyToByteArray(displayComponents);
        graphics.drawImage(displayImage, 0, 0,
                frameBuffer.getWidth(), frameBuffer.getHeight(), null);
        bufferStrategy.show();
    }

    public RenderContext getFrameBuffer() {
        return frameBuffer;
    }
}