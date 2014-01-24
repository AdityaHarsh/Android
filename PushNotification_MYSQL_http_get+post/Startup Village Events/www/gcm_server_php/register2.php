<?php

// response json
$json = array();

/**
 * Registering a user device
 * Store reg id in users table
 */
if (isset($_POST["no"]) && isset($_POST["name"]) && isset($_POST["dame"]) && isset($_POST["time"]) && isset($_POST["description"]) && isset($_POST["o_r"])) {
   $no = $_POST['no'];
	$name = $_POST['name'];	
	$date = $_POST['date'];
	$time = $_POST['time'];
 	$description = $_POST['description'];
	$o_r = $_POST['o_r'];
    // Store user details in db
    include_once './db_functions.php';
    include_once './GCM.php';

    $db = new DB_Functions();
    $gcm = new GCM();

    $res = $db->storeUser($name, $date, $time);

    $registatoin_ids = array($gcm_regid);
    $message = array("product" => "shirt");

    $result = $gcm->send_notification($registatoin_ids, $message);

    echo $result;
} else {
    // user details missing
}
?>