public class Main {
    public static void main(String[] args) {

        Window window = new Window(600, 600, "Software Renderer");
        RenderContext target = window.getFrameBuffer();

        Vertex minYVert = new Vertex(100, 500);
        Vertex midYVert = new Vertex(-600, 600);
        Vertex maxYVert = new Vertex(500, 500);
        long previousTime = System.nanoTime();

        while(true) {
            long currentTime = System.nanoTime();
            float delta = (float) ((currentTime - previousTime) / 1_000_000_000d);
            previousTime = currentTime;

            target.fillTriangle(maxYVert, minYVert, midYVert);
            window.swapBuffers();
        }
    }
}