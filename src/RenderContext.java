public class RenderContext extends Bitmap {
    private final int scanBuffer[];

    public RenderContext(int width, int height) {
        super(width, height);
        scanBuffer = new int[height * 2];
    }

    public void drawScanBuffer(int yCoord, int xMin, int xMax) {
        scanBuffer[yCoord * 2] = xMin;
        scanBuffer[yCoord * 2 + 1] = xMax;
    }

    public void fillShape(int yMin, int yMax) {
        for (int i = yMin; i < yMax; i++) {
            int xMin = scanBuffer[i * 2];
            int xMax = scanBuffer[i * 2 + 1];

            for (int j = xMin; j < xMax; j++) {
                drawPixel(j, i, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF);
            }
        }
    }
}
