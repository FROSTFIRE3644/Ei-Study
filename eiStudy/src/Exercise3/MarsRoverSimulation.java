package Exercise3;
import java.util.*;


// Rover Class
class Rover {
    private int x;
    private int y;
    String direction;

    public Rover(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void moveForward() {
        switch (direction) {
            case "N":
                y++;
                break;
            case "E":
                x++;
                break;
            case "S":
                y--;
                break;
            case "W":
                x--;
                break;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case "N":
                direction = "W";
                break;
            case "E":
                direction = "N";
                break;
            case "S":
                direction = "E";
                break;
            case "W":
                direction = "S";
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case "N":
                direction = "E";
                break;
            case "E":
                direction = "S";
                break;
            case "S":
                direction = "W";
                break;
            case "W":
                direction = "N";
                break;
        }
    }

    public String getStatus() {
        return "Rover is at (" + x + ", " + y + ") facing " + direction + ".";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

// Obstacle Class
class Obstacle {
    private int x;
    private int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

// Main Class to run the simulation
public class MarsRoverSimulation {
    private static final int GRID_SIZE = 10; // Grid size set to 10x10
    private static List<Obstacle> obstacles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get starting position and direction
        System.out.print("Enter grid width and height (e.g., 10 10): ");
        int gridWidth = scanner.nextInt();
        int gridHeight = scanner.nextInt();

        System.out.print("Enter Rover's starting x, y coordinates and direction (N, E, S, W): ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        String startDirection = scanner.next();

        // Get obstacles
        System.out.print("Enter number of obstacles: ");
        int obstacleCount = scanner.nextInt();
        for (int i = 0; i < obstacleCount; i++) {
            System.out.print("Enter obstacle position (x y): ");
            int obstacleX = scanner.nextInt();
            int obstacleY = scanner.nextInt();
            obstacles.add(new Obstacle(obstacleX, obstacleY));
        }

        Rover rover = new Rover(startX, startY, startDirection);

        // Get commands
        System.out.print("Enter commands (M for move, L for turn left, R for turn right): ");
        String commands = scanner.next();

        // Execute commands
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'M':
                    if (canMove(rover, gridWidth, gridHeight)) {
                        rover.moveForward();
                    } else {
                        System.out.println("Obstacle detected! Cannot move.");
                    }
                    break;
                case 'L':
                    rover.turnLeft();
                    break;
                case 'R':
                    rover.turnRight();
                    break;
            }
        }

        System.out.println(rover.getStatus());
        scanner.close();
    }

    private static boolean canMove(Rover rover, int gridWidth, int gridHeight) {
        int nextX = rover.getX();
        int nextY = rover.getY();

        // Check next position based on direction
        switch (rover.direction) {
            case "N":
                nextY++;
                break;
            case "E":
                nextX++;
                break;
            case "S":
                nextY--;
                break;
            case "W":
                nextX--;
                break;
        }

        // Check if the next position is within grid boundaries
        if (nextX < 0 || nextX >= gridWidth || nextY < 0 || nextY >= gridHeight) {
            return false; // Out of bounds
        }

        // Check for obstacles
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == nextX && obstacle.getY() == nextY) {
                return false; // Obstacle detected
            }
        }

        return true;
    }
}
