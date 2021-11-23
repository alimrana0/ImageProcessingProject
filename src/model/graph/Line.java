package model.graph;

import java.util.Objects;

/**
 * Created by blerner on 10/10/16.
 */
public final class Line {
  public final Position2D start;
  public final Position2D end;

  public Line(Position2D start, Position2D end) {
    this.start = start;
    this.end = end;
  }

  public Position2D getStart() {
    return this.start;
  }


  public Position2D getEnd() {
    return this.end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Line)) {
      return false;
    }

    Line line = (Line) o;

    return (this.start.equals(line.start) && this.end.equals(line.end))
            || (this.end.equals(line.start) && this.start.equals(line.end));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }
}