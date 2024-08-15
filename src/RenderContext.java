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

    public void fillTriangle(Vertex v1, Vertex v2, Vertex v3) {
        Vertex minYVert = v1;
        Vertex midYVert = v2;
        Vertex maxYVert = v3;

        if (maxYVert.getY() < midYVert.getY()) {
            Vertex temp = maxYVert;
            maxYVert = midYVert;
            midYVert = temp;
        }

        if (midYVert.getY() < minYVert.getY()) {
            Vertex temp = midYVert;
            midYVert = minYVert;
            minYVert = temp;
        }

        if (maxYVert.getY() < midYVert.getY()) {
            Vertex temp = maxYVert;
            maxYVert = midYVert;
            midYVert = temp;
        }

        float area = minYVert.triangleArea(maxYVert, midYVert);
        int side = area >= 0 ? 1 : 0;

        scanConvertTriangle(minYVert, midYVert, maxYVert, side);
        fillShape((int)minYVert.getY(), (int)maxYVert.getY());
    }

    public void scanConvertTriangle(Vertex minYVert, Vertex midYVert, Vertex maxYVert, int side) {
        scanConvertLine(minYVert, maxYVert, 0 + side);
        scanConvertLine(minYVert, midYVert, 1 - side);
        scanConvertLine(midYVert, maxYVert, 1 - side);
    }

    private void scanConvertLine(Vertex minYVert, Vertex maxYVert, int side) {
        int yStart = (int)minYVert.getY();
        int yEnd   = (int)maxYVert.getY();
        int xStart = (int)minYVert.getX();
        int xEnd   = (int)maxYVert.getX();

        int yDist = yEnd - yStart;
        int xDist = xEnd - xStart;

        if (yDist <= 0) {
            return;
        }

        float xStep = (float) xDist/ (float) yDist;
        float currentX = (float) xStart;

        for (int i = yStart; i < yEnd; i++) {
            scanBuffer[i * 2 + side] = (int) currentX;
            currentX += xStep;
        }
    }
}
