public class Stars3D {
    private final float spread;
    private final float speed;

    private final float[] starY;
    private final float[] starX;
    private final float[] starZ;

    public Stars3D(int numStars, float spread, float speed) {
        this.spread = spread;
        this.speed = speed;

        starX = new float[numStars];
        starY = new float[numStars];
        starZ = new float[numStars];

        for(int i = 0; i < starX.length; i++) {
            initStars(i);
        }
    }

    private void initStars(int index) {
        starX[index] = 2 * ((float)Math.random() - 0.5f) * spread;
        starY[index] = 2 * ((float)Math.random() - 0.5f) * spread;
        starZ[index] = ((float)Math.random() + 0.00001f) * spread;
    }

    public void updateAndRender(Bitmap target, float delta) {
        target.clear((byte)0x00);

        float halfWidth = target.getWidth()/2.0f;
        float halfHeight = target.getHeight()/2.0f;
        for(int i = 0; i < starX.length; i++) {
            starZ[i] -= delta * speed;

            if (starZ[i] <= 0) {
                initStars(i);
            }

            int x = (int)((starX[i]/starZ[i]) * halfWidth + halfWidth);
            int y = (int)((starY[i]/starZ[i]) * halfHeight + halfHeight);

            if (x < 0 || x >= target.getWidth() ||
                    (y < 0 || y >= target.getHeight())) {
                initStars(i);
            }
            else {
                target.drawPixel(x, y, (byte)0xFF, (byte)0xFF,(byte)0xFF,(byte)0xFF);
            }
        }
    }
}
