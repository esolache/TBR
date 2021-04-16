<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $netid = null; // Initially null.
    
    // Check that netid is set.  If not, it'll stay null.
    if (isset($_REQUEST["net_id"])){
        $netid = $_REQUEST["net_id"];
        $sql = "select* from users_table where net_id = ('".$netid."');";
        $result = mysqli_query($con,$sql);
        if($result){
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
        $return = 'Invalid input';
    }

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   