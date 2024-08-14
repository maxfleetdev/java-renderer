public class Main {
    public static void main(String[] args) {

        Window window = new Window(600, 600, "Software Renderer");
        RenderContext target = window.getFrameBuffer();
        Stars3D stars = new Stars3D(1028, 64.0f, 20.0f);

        long previousTime = System.nanoTime();

        while(true) {
            long currentTime = System.nanoTime();
            float delta = (float) ((currentTime - previousTime) / 1_000_000_000d);
            previousTime = currentTime;

            stars.updateAndRender(target, delta);
            window.swapBuffers();
        }
    }
}