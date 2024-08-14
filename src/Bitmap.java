import java.util.Arrays;

public class Bitmap {
    private final int width;                // Size of bitmaps (Pixel Sizes)
    private final int height;
    private final int argbSize = 4;

    private final byte components[];        // ARGB Values


    public Bitmap(int width, int height) {
        this.width  = width;
        this.height = height;
        components  = new byte[(width * height) * argbSize];
    }

    public void clear(byte shade) {
        // Sets every component to the shade (ARGB)
        Arrays.fill(components, shade);
    }

    public void drawPixel(int x, int y, byte a, byte b, byte g, byte r) {
        int index = (x + y * width) * argbSize;

        // Fills pixels with the ARGB value from component array
        components[index    ] = a;
        components[index + 1] = r;
        components[index + 2] = g;
        components[index + 3] = r;
    }

    public void copyToByteArray(byte[] dest) {
        for (int i = 0; i < width * height; i++) {
            dest[i * 3] = components[i * 4 + 1];
            dest[i * 3 + 1] = components[i * 4 + 2];
            dest[i * 3 + 2] = components[i * 4 + 3];
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
