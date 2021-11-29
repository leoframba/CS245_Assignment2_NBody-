import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * @author Leonardo Framba
 * The NBody class handles the animation using instructions from a data file inputed in args[0]
 */
public class NBody extends JPanel implements ActionListener {

    //Main data structure- Is either array or linked list depending on file input
    public List<CelestialBody> cbList;
    //Timer for action listener animations
    Timer tm = new Timer(5, this);

    /**
     *Animation function. For every tick of the animation we update the position of every cb in
     * the list by calculating the total force in both x and y plane.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < cbList.size(); i++){
            //For the given cb calc the total force in both planes
            double fx = cbList.get(i).calcTotalForceX(cbList);
            double fy = cbList.get(i).calcTotalForceY(cbList);
            //Update the position based on the calculated force.
            cbList.get(i).update(-fx, -fy);
        }
        repaint();
    }

    /**
     * Function that paints the graphics of the simulation. We paint each cb using its color and
     * x and y coordinate
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < cbList.size(); i++){
            //Get the cb's color
            float[] color = cbList.get(i).getColor();
            g.setColor(new Color(color[0], color[1], color[2]));
            //Get the cb's coordinates
            double x = cbList.get(i).getxCord();
            double y = cbList.get(i).getyCord();
            int size = cbList.get(i).getSize();
            //Paint cb
            g.fillOval((int)x, (int)y, size, size);
        }
        tm.start();
    }

    /**
     * Function that generates a random RGB value for all cbs in the list
     * TODO Modify the range of colors so that we only generate those that are easily visible
     */
    public void giveMeColor(){
        Random rand = new Random();
        for (int i = 0; i < cbList.size(); i++) {
            cbList.get(i).setColor(new float[]{rand.nextFloat(), rand.nextFloat(),
                    rand.nextFloat()});
        }
    }

    public static void main(String[] args) throws IOException {
        NBody NBody = new NBody();
        ///Read the data file inputted via the first arg.
        if(args.length < 1) throw new IndexOutOfBoundsException("No data file");
        NBody.readFile(args[0]);
        //Generate colors for the all the cbs
        NBody.giveMeColor();
        //Set background to black for better color contrast + looks like SPACE
        NBody.setBackground(Color.black);

        JFrame jf = new JFrame();
        jf.setTitle("Celestial Bodies Animation");
        jf.setSize(768, 768);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(NBody);
    }

    /**
     * @param filePath A string containing the path to the data file- inputted in args[0]
     * @throws IOException When the file is not found
     */
    public void readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        //First line of input file contains which List to use
        String listType = br.readLine();
        //TODO Use try catch to set default as arraylist when input is invalid
        if(listType.equals("ArrayList"))
            cbList = new ArrayList<>();
        else if(listType.equals("LinkedList"))
            cbList = new LinkedList<>();
        else
            throw new IOException("Invalid List type: \"" + listType + "\". Input must be " +
                    "\"ArrayList\" " + "or \"LinkedList\".");

        //Second line contains cb scale factor
        //TODO handle invalid inputs
        double scale = Double.parseDouble(br.readLine());

        //Remaining lines contain data for cb's. Populate the cbList
        String line = br.readLine();
        while (line != null) {
            cbList.add(new CelestialBody(line, scale));
            line = br.readLine();
        }
    }
}
