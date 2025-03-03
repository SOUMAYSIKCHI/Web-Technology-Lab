<?php  
$host = 'localhost';  
$user = 'root';  
$pass = '';  
$dbname='sampledb';
$uname=$_POST["uname"];
$pwd=$_POST["pwd"];
$conn = mysqli_connect($host, $user, $pass,$dbname);  
$sql = "SELECT * FROM students where UserId='$uname' and Password='$pwd'";  
$retval=mysqli_query($conn, $sql);  
if(mysqli_num_rows($retval) > 0)
{
 echo "Hello...".$uname."<br/>";
 echo " Successfully Login..";
}
else
{  
echo "Invalid Login...";  
}  
mysqli_close($conn);  
?>
