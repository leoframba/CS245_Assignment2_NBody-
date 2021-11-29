# CS245_Assignment2_NBody-
NBody Sim

I was unable to get the physics sim to work with the data set provided on the instructions. I had to make some minor modifications to the data file to get my celestial bodies to orbit the larger central cb.
The modfied data set has both CB's orbiting the larger P0 perfectly. In the case that a CB is shot off the window I continue it's movment coming from the opposite side of the window. I found this method was the most fun when I was creating random crazy data sets with multiple particals shooting around.


Orginal data-
ArrayList
1000000.0
P0,1000000000000000000.,384,384,0.,0.,20
P1,150000.,350,350,-0.001,0.001,12
P2,100000.,480,480,0.015,-0.015,10

Modified Data
LinkedList
10000000
P0,1000000000000000000.,384.,384.,0.,0.,20
P1,15000.,350.,350.,-0.28,0.28,12
P2,10000.,480.,480.,0.15,-0.15,10
I increased the scale and intial directions of the particals by x10 + added more initial direction to P1 
