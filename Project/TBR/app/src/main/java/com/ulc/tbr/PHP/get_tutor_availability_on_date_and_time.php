<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorID = null; // Initially null.
    $date = null;
    $time = null;
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["tutor_id"]) && isset($_REQUEST["date"]) && isset($_REQUEST["time"])){
        $tutorID = $_REQUEST["tutor_id"];
        $date = $_REQUEST["date"];
        $time = $_REQUEST["time"];
        // Create SQL query.
        $sql = "select* from tutor_availability_table where tutor_id = ('".$tutorID."') and date = ('".$date."') and time = ('".$time."');";
        $result = mysqli_query($con,$sql);
        if($result){
            // Create array for JSON.
            while($row = $result->fetch_assoc()){
                $return['Sessions: '][] =
                    $array = array(
                        "tutor_id" => $row['tutor_id'],
                        "date" => $row['date'],
                        "time" => $row['time'],
                        "booked" => $row['booked'],
                    );
            }
        }else{
            $return = null;
        }
    }else{
        $return = 'Invalid input.';
    }

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>  