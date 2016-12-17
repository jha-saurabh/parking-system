arg1=$1
arg2=$2
java -cp "target/parking-system-1.0.jar:lib/*" com.gojek.parking.controller.ParkingController $arg1 $arg2
