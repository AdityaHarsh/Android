<?php

class DB_Functions {

    private $db;

    //put your code here
    // constructor
    function __construct() {
        include_once './db_connect2.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($no, $name, $date, $time, $description, $o_r) {
        // insert user into database
        $result = mysql_query("INSERT INTO add_events ".
       "(No, Event_Name, Date, Time, Description, O_R) ".
       "VALUES('$no','$name', '$date', '$time','$description','$o_r')";
        // check for successful store
        if ($result) {
            // get user details
            $no = mysql_insert_id(); // last inserted id
            $result = mysql_query("SELECT * FROM add_events WHERE No = $no") or die(mysql_error());
            // return user details
            if (mysql_num_rows($result) > 0) {
                return mysql_fetch_array($result);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Get user by Date and password
     */
    public function getUserByDate($date) {
        $result = mysql_query("SELECT * FROM add_events WHERE Date = '$date' LIMIT 1");
        return $result;
    }

    /**
     * Getting all users
     */
    public function getAllUsers() {
        $result = mysql_query("select * FROM add_events");
        return $result;
    }

    /**
     * Check user is existed or not
     */
    public function isUserExisted($date) {
        $result = mysql_query("SELECT Date from add_events WHERE Date = '$date'");
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // user existed
            return true;
        } else {
            // user not existed
            return false;
        }
    }

}

?>