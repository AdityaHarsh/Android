<html>
<head>
<title>Add New Event To The Database</title>
</head>
<body>
<?php
if(isset($_POST['add']))
{

$conn = mysql_connect('localhost', 'root','');
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

if(! get_magic_quotes_gpc() )
{
	$no = addslashes ($_POST['no']);
	$name = addslashes ($_POST['name']);
	$date = addslashes ($_POST['date']);
	$time = addslashes ($_POST['time']);
  	$description = addslashes ($_POST['description']);
	$o_r = addslashes ( $_POST['o_r']);
}
else
{
   	$no = $_POST['no'];
	$name = $_POST['name'];	
	$date = $_POST['date'];
	$time = $_POST['time'];
 	$description = $_POST['description'];
	$o_r = $_POST['o_r'];
}


$sql = "INSERT INTO add_events ".
       "(No, Event_Name, Date, Time, Description, O_R) ".
       "VALUES('$no','$name', '$date', '$time','$description','$o_r')";
mysql_select_db('sv_events');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not enter data: ' . mysql_error());
}
echo "Entered data successfully\n";
mysql_close($conn);
}
else
{
?>
<form method="post" action="<?php $_PHP_SELF ?>" name="f1">
<table width="400" border="0" cellspacing="1" cellpadding="2">
<tr>
<td width="100">No</td>
<td><input name="no" type="text" id="no"></td>
</tr>

<tr>
<td width="100">Event Name</td>
<td><input name="name" type="text" id="name"></td>
</tr>

<tr>
<td width="100">Date (dd/mm/yy)</td>
<td><input name="date" type="text" id="date"></td>
</tr>

<tr>
<td width="100">Time</td>
<td><input name="time" type="text" id="time"></td>
</tr>

<tr>
<td width="100">Description</td>
<td><input name="description" type="text" id="description"></td>
</tr>

<tr>
<td width="100">Open/Register</td>
<td><input name="o_r" type="text" id="o_r"></td>
</tr>
<tr>
<td width="100"> </td>
<td> </td>
</tr>
<tr>
<td width="100"> </td>
<td>
<input name="add" type="submit" id="add" value="Add Event">
</td>
</tr>
</table>
</form>
<?php
}
?>
</body>
</html>