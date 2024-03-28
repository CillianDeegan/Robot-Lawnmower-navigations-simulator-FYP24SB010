import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LawnmowerTest {

    @org.junit.jupiter.api.Test
    void isAtBoundary() {
        Lawn lawn = new Lawn();
        Lawnmower lawnmower = new Lawnmower(lawn);

        lawnmower.setX(1);
        lawnmower.setY(1);
        assertTrue(lawnmower.isAtBoundary());

        lawnmower.setX(10);
        lawnmower.setY(5);
        assertFalse(lawnmower.isAtBoundary());
    }

    @org.junit.jupiter.api.Test
    void outOfBounds() {
        Lawn lawn = new Lawn();
        Lawnmower lawnmower = new Lawnmower(lawn);

        lawnmower.setX(-1);
        lawnmower.setY(-1);
        assertTrue(lawnmower.outOfBounds());

        lawnmower.setX(5);
        lawnmower.setY(5);
        assertFalse(lawnmower.outOfBounds());
    }

    @Test
    void moveForward() {
        Lawn lawn = new Lawn();
        Lawnmower lawnmower = new Lawnmower(lawn);
         lawnmower.setX(3);
         lawnmower.setY(3);
         lawnmower.setAngle(Math.PI / 4);
         lawnmower.setSpeed(2.0);


         double deltaTime = 1.0;

        double expectedX = 3+ 2.0 * Math.cos(Math.PI / 4); // 45-degree angle
        double expectedY = 3+ 2.0 * Math.sin(Math.PI / 4); // 45-degree angle


        lawnmower.moveForward(deltaTime);

        assertEquals(expectedX, lawnmower.getX(), 0.001); // Using delta for floating point comparison
        assertEquals(expectedY, lawnmower.getY(), 0.001);


    }

    @Test
    void moveBackwards() {
        Lawn lawn = new Lawn();
        Lawnmower lawnmower = new Lawnmower(lawn);
        lawnmower.setX(3);
        lawnmower.setY(3);
        lawnmower.setAngle(Math.PI / 4);
        lawnmower.setSpeed(2.0);


        double deltaTime = 1.0;

        double expectedX =3-  2.0 * Math.cos(Math.PI / 4); // 45-degree angle
        double expectedY =3-  2.0 * Math.sin(Math.PI / 4); // 45-degree angle


        lawnmower.moveBackwards(deltaTime);

        assertEquals(expectedX, lawnmower.getX(), 0.001); // Using delta for floating point comparison
        assertEquals(expectedY, lawnmower.getY(), 0.001);
    }

    @Test
    void turn() {
        Lawn lawn = new Lawn();
        Lawnmower lawnmower = new Lawnmower(lawn);
        lawnmower.setAngle(Math.PI / 4);
        double degrees = 30.0;

        double expectedAngle = Math.PI / 4 + Math.toRadians(degrees);

        lawnmower.turn(degrees);

        assertEquals(expectedAngle, lawnmower.getAngle(), 0.001);

    }
}