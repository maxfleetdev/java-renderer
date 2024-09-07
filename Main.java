public class Main {
    public static void main(String[] args) {

        Window window = new Window(600, 600, "Software Renderer");
        RenderContext target = window.getFrameBuffer();

        Stars3D stars = new Stars3D(1000, 1, 0.5f);
        long previousTime = System.nanoTime();

        while(true) {
            // Time Calculation
            long currentTime = System.nanoTime();
            float delta = (float) ((currentTime - previousTime) / 1_000_000_000d);

            // Render Stars field
            stars.updateAndRender(target, delta);

            // Final Rendering
            previousTime = currentTime;
            window.swapBuffers();
        }
    }
}