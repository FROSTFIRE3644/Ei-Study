package Exercise3;
import java.util.HashSet;
import java.util.Set;

// Interface for Command Pattern
interface Command {
    void execute();
}

// Rover Class
class Rover {
    private int x, y;
    private Direction direction;
    private Grid grid;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
    }

    public void moveForward() {
        int newX = x + direction.getXStep();
        int newY = y + direction.getYStep();
        if (!grid.hasObstacle(newX, newY) && grid.isWithinBounds(newX, newY)) {
            this.x = newX;
            this.y = newY;
        } else {
            System.out.println("Obstacle detected or out of bounds!");
        }
    }

    public void turnLeft() {
        this.direction = direction.left();
    }

    public void turnRight() {
        this.direction = direction.right();
    }

    public String getStatus() {
        return "Rover is at (" + x + ", " + y + ") facing " + direction;
    }
}

// Enum for Directions
enum Direction {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    private int xStep, yStep;

    Direction(int xStep, int yStep) {
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public int getXStep() {
        return xStep;
    }

    public int getYStep() {
        return yStep;
    }

    public Direction left() {
        switch (this) {
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            default: return this;
        }
    }

    public Direction right() {
        switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            default: return this;
        }
    }
}

// Command Implementations
class MoveCommand implements Command {
    private Rover rover;

    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.moveForward();
    }
}

class TurnLeftCommand implements Command {
    private Rover rover;

    public TurnLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnLeft();
    }
}

class TurnRightCommand implements Command {
    private Rover rover;

    public TurnRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnRight();
    }
}

// Composite Pattern for Grid
class Grid {
    private int width, height;
    private Set<Point> obstacles;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.obstacles = new HashSet<>();
    }

    public void addObstacle(int x, int y) {
        obstacles.add(new Point(x, y));
    }

    public boolean hasObstacle(int x, int y) {
        return obstacles.contains(new Point(x, y));
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}

// Point Class to Represent Obstacles
class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}

// Main Class
public class MarsRoverSimulation {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        grid.addObstacle(2, 2);  // Adding obstacles
        grid.addObstacle(3, 5);

        Rover rover = new Rover(0, 0, Direction.NORTH, grid);

        // Defining commands
        Command move = new MoveCommand(rover);
        Command turnLeft = new TurnLeftCommand(rover);
        Command turnRight = new TurnRightCommand(rover);

        // Executing commands
        move.execute();
        move.execute();
        turnRight.execute();
        move.execute();
        turnLeft.execute();
        move.execute();

        System.out.println(rover.getStatus());  // Output: Rover is at (1, 3) facing EAST
    }
}
