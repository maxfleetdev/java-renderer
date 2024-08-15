public class Vertex {
    private Vector4f pos;

    public float getX() {return pos.GetX();}
    public float getY() {return pos.GetY();}

    public Vertex(float x, float y) {
        this.pos = new Vector4f(x, y, 0, 1);
    }

    public Vertex(Vector4f pos) {
        this.pos = pos;
    }

    public Vertex transform(Matrix4f transform) {
        return new Vertex(transform.Transform(pos));
    }

    public Vertex perspectiveDivide() {
        return new Vertex(new Vector4f(
                pos.GetX() / pos.GetW(),
                pos.GetY() / pos.GetW(),
                pos.GetZ() / pos.GetW(),
                pos.GetW() / pos.GetW()
        ));
    }

    public float triangleAreaSquared(Vertex b, Vertex c) {
        float x1 = b.getX() - pos.GetX();
        float y1 = b.getY() - pos.GetY();

        float x2 = c.getX() - pos.GetX();
        float y2 = c.getY() - pos.GetY();

        return ((x1 * y2) - (x2 * y1));
    }
}