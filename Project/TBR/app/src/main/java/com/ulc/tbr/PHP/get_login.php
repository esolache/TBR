<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $netid = null; // Initially null.
    $loginpass = null;
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["net_id"]) && isset($_REQUEST["password"])){
        $netid = $_REQUEST["net_id"];
        $loginpass = $_REQUEST["password"];
        $sql = "select* from users_table where net_id = ('".$netid."') and password = ('".$loginpass."');";
        $result = mysqli_query($con,$sql);
        if($result){
    // Fetch result.
            $row = $result->fetch_assoc();
            // Create array for JSON.
            $return = array(
                "student_id" => $row['student_id'],
                "net_id" => $row['net_id'],
                "name" => $row['name'],
                "email" => $row['email'],
                "tutor" => $row['tutor'],
                "tutee" => $row['tutee'],
            );
        }else{
            $return = null;
        }
    }else{
        $return = "Invalid input";
    }

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   