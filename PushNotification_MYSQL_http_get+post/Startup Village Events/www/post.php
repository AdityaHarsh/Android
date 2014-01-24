<?php 
       $name   = urldecode($_POST['name']);
       $user   = urldecode($_POST['user']);
       $email  = urldecode($_POST['email']);
       $pass   = urldecode($_POST['pass']);
        


$con = mysql_connect('localhost', 'root', '');

if (!$con)
	{
	die("could not connect: " + mysql_error());
	}
	mysql_select_db("emp", $con);
$sql = "INSERT INTO employee".
       "(name, user, email, pass) ".
       "VALUES ( '$name', '$user', '$email', '$pass' )";
      
$retval = mysql_query( $sql, $con );
if(! $retval )
{
  die('Could not enter data: ' . mysql_error());
}
echo "Entered data successfully\n";
   mysql_close($con);       

       print " ==== POST DATA =====
       Name  : $name 
       Email : $email 
       User  : $user 
       Pass  : $pass";  
       
       $filename="androidmessages.html";
// write (append) the data to the file
file_put_contents($filename, " <br />",FILE_APPEND);
file_put_contents($filename, "name: ",FILE_APPEND);
file_put_contents($filename, $name." <br />",FILE_APPEND);

file_put_contents($filename, "email: ",FILE_APPEND);
file_put_contents($filename, $email."<br />",FILE_APPEND);

file_put_contents($filename, "user: ",FILE_APPEND);
file_put_contents($filename, $user."<br />",FILE_APPEND);

file_put_contents($filename, "passwd: ",FILE_APPEND);
file_put_contents($filename, $pass."<br />",FILE_APPEND);
file_put_contents($filename, " <br />",FILE_APPEND);
// load the contents of the file to a variable
$androidmessages=file_get_contents($filename);
echo $androidmessages;
 ?>