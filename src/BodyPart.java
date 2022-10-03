import java.util.Objects;

public final class BodyPart //data structure
{
    public int x;
    public int y;
    public BodyPart(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodyPart bodyPart = (BodyPart) o;
        return x == bodyPart.x && y == bodyPart.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}