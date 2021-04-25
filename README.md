# PatternRecognition

Application that given a set of N feature points in the plane, determine every line that contains N or more of the
points, and return all points involved.

This application exposes four API tha allows you to generate a cartesian plane and determine every line that contains at
least N collinear points.

The first API gives you the possibility to add a point in the space, use postman creating a POST request with 
the following body: 
{
    "x" : integer value of x,
    "y" : integer value of y 
} 
via the url http://localhost:9002/point

The second API retrieves all the points registered inside the plane, use postman creating a GET request via the
url:
http://localhost:9002/space

The third API, given N collinear point in input, determine every line that contains N or more of the points, use 
postman creating a GET request via the url:

http://localhost:9002/lines/{collinearPoints}
To generate a segment, at least 2 points in the plane and 2 collinear points in input are required

The fourth API deletes all the points inside the plane, use postman creating a DELETE request via the url:
http://localhost:9002/space
