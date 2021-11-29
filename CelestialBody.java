/**
 * @author Leonardo Framba
 */
public class CelestialBody {
    private final String name;
    private final double scale;
    private final double mass;
    private double xCord;
    private double yCord;
    private double xDirection;
    private double yDirection;
    private final int size;
    private float[] color; //Contains 3 float values that dictate the color of the cb

    /**
     * @param data  A special string containing "name, mass, xCord, yCord, xDir, yDir, size"
     * @param scale The value by which the cb is scaled by
     */
    CelestialBody(String data, double scale) {
        String[] cbData = data.split(","); //split data using ","
        name = cbData[0];
        this.scale = scale;
        mass = Double.parseDouble(cbData[1]);
        xCord = Double.parseDouble(cbData[2]) * scale;
        yCord = Double.parseDouble(cbData[3]) * scale;
        xDirection = Double.parseDouble(cbData[4]) * scale;
        yDirection = Double.parseDouble(cbData[5]) * scale;
        size = Integer.parseInt(cbData[6]);
    }


    //method to calc the distance between this.cb and the parameter cb
    public double calcDistance(CelestialBody cb) {
        double deltaX = this.xCord - cb.xCord;
        double deltaY = this.yCord - cb.yCord;
        return Math.hypot(deltaX, deltaY);
    }

    //method to calc the pairwise force exerted between two cbs
    public double calcForce(CelestialBody cb) {
        //F = G * (m1 * m2) / r^2
        double G = 6.67408 * Math.pow(10.0, -11.0);
        double m1 = this.mass;
        double m2 = cb.mass;
        double r = this.calcDistance(cb);
        return G * m1 * m2 / (r * r);
    }

    //method to calc the force exerted on this.cb by parameter cb in the x direction
    public double calcForceX(CelestialBody cb) {
        //Fx = F * dx/r
        double F = this.calcForce(cb);
        double deltaX = this.xCord - cb.xCord;
        double r = this.calcDistance(cb);
        return F * deltaX / r;
    }

    //method to calc the force exerted on this.cb by parameter cb in the y direction
    public double calcForceY(CelestialBody cb) {
        //Fx = F * dy/r
        double F = this.calcForce(cb);
        double deltaY = this.yCord - cb.yCord;
        double r = this.calcDistance(cb);
        return F * deltaY / r;
    }

    //method to calc the total force exerted on this.cb by all other cbs in the x direction
    public double calcTotalForceX(List<CelestialBody> cbList) {
        double totalForceX = 0;
        for (int i = 0; i < cbList.size(); i++) {
            if (!this.name.equals(cbList.get(i).name)) {
                totalForceX += this.calcForceX(cbList.get(i));
            }
        }
        return totalForceX;
    }

    //method to calc the total force exerted on this.cb by all other cbs in the y direction
    public double calcTotalForceY(List<CelestialBody> cbList) {
        double totalForceY = 0;
        for (int i = 0; i < cbList.size(); i++) {
            if (!this.name.equals(cbList.get(i).name)) {
                totalForceY += this.calcForceY(cbList.get(i));
            }
        }
        return totalForceY;
    }

    //method to update the position of a cb given the force exerted
    public void update(double forceX, double forceY) {
        //TODO find out why you need to multiply by the scale twice????
        //Find acceleration in both directions
        double aX = (forceX / this.mass) * scale * scale;
        double aY = (forceY / this.mass) * scale * scale;

        //Update Direction based on acceleration
        this.xDirection += aX;
        this.yDirection += aY;

        //Update position based on direction/velocity
        this.xCord += xDirection;
        this.yCord += yDirection;

        if (this.yCord / scale < 0) {
            this.yCord = 699 * scale;
            this.yDirection = this.yDirection * .5;
            this.xDirection = this.xDirection * .5;
        }
        if (this.yCord / scale > 700) {
            this.yCord = 1;
            this.yDirection = this.yDirection * .5;
            this.xDirection = this.xDirection * .5;
        }
        if (this.xCord / scale < 0) {
            this.xCord = 699 * scale;
            this.xDirection = this.xDirection * .5;
            this.yDirection = this.yDirection * .5;
        }
        if (this.xCord / scale > 700) {
            this.xCord = 1;
            this.xDirection = this.xDirection * .5;
            this.yDirection = this.yDirection * .5;
        }
    }

    public double getxCord() {
        return xCord / scale;
    }

    public double getyCord() {
        return yCord / scale;
    }

    public int getSize() {
        return size;
    }

    public float[] getColor() {
        return color;
    }

    public void setColor(float[] color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CelestialBody{" +
                "name='" + name + '\'' +
                ", mass=" + mass +
                ", xCord=" + xCord +
                ", yCord=" + yCord +
                ", xDirection=" + xDirection +
                ", yDirection=" + yDirection +
                ", size=" + size +
                '}';
    }
}
