<?php

$con = mysql_connect('localhost', 'root', '');

if (!$con)
	{
	die("could not connect: " + mysql_error());
	}
	mysql_select_db("sv_events", $con);

$result=mysql_query("SELECT * FROM add_events", $con);
while ($row = mysql_fetch_assoc($result))
{
$output[]=$row;
}
print(json_encode($output));


 mysql_close($con);   
?>