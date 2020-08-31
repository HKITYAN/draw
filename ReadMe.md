# Drawing Programme

## Start the application
* first have all the maven dependencies installed
* then run the maim method in App.java class `draw.App`
* you will see enter command in the console and you can start using it.

## Some Reminders
* if you use L, R B command before C command (creating a canvas), you will be
told to create a canvas first
* If input arguments format are not correct for command, you will be warned, eg `C a b`
* If using non-exist command, you will be warned, eg `X 1 2 3`
* If you try to use command (L, R, B) to draw outside the canvas, you will be warned

## Test cases
* most test cases will be loaded from csv, under `test/resources`
* escaping is used in different input, such as `\n' and '\\,'
* specific test case format for different test files (.csv) are listed in below sections
* to run test, use `mvn test`

### validateArgumentParam.csv
  * total 4 csv files for validating arguments. Including validation for L command, C command, and B command
  * under command/create, command/line, command/rectangle and command/fill folders
  * format => argument_separated_by_space,true_or_false
  * explanation:
    * argument_separated_by_space => arguments for command separated by space, eg `2 3 2 3`
    * true_or_false => expected output after validation, either true or false

### command/create/createCanvasParam.csv
  * format => width height,expected_string_output
  * explanation:
    * width is first argument for C command
    * height is the second argument for C command
    * expected_string_output is the expected string output after running C command
  * assumption/reminders:
    * width height input has proper format as format testing is handle by `command/create/validateArgumentParam.csv`
    * this test focus on testing output string

### command/line/createLineParam.csv
  * format => argument_for_C_command,argument_for_L_command,expected_string_output
  * explanation:
    * argument_for_C_command => arguments for C command separated by space, eg `2 3`
    * argument_for_L_command => arguments for L command separated by space, eg `1 2 1 2`
    * expected_string_output => expected string output after running C and L command with those arguments
    or exception message `Line cannot fit into canvas\, please draw another line` if line is outside
    the canvas
  * assumption/reminders:
    * if argument_for_C_command is missing, like 2nd entry in the file, it will use the previous Canvas created by C command
    * argument_for_L_command should have correct format because format checking is check on `command/line/validateArgumentParam.csv`
    * this test focus on testing output string
    
### command/rectangle/createRectangleParam.csv
  * format => argument_for_C_command,argument_for_R_command,expected_string_output
    * explanation:
      * argument_for_C_command => arguments for C command separated by space, eg `2 3`
      * argument_for_R_command => arguments for R command separated by space, eg `1 2 1 2`
      * expected_string_output => expected string output after running C and L command with those arguments
      or exception message `Rectangle cannot fit into canvas\, please draw another rectangle` if rectangle is outside
      the canvas
    * assumption/reminders:
      * if argument_for_C_command is missing, like 2nd entry in the file, it will use the previous Canvas created by C command
      * argument_for_R_command should have correct format because format checking is check on `command/rectangle/validateArgumentParam.csv`
      * this test focus on testing output string
      
### command/fill/bucketFillParam.csv
  * format => argument_for_C_command,L_or_R_Command,argument_for_L_or_R_command,argument_for_B_command,expected_string_output
    * explanation:
      * argument_for_C_command => arguments for C command separated by space, eg `2 3`
      * L_or_R_Command => L or R command we want to apply to the created canvas before using B command
      * argument_for_L_or_R_command => arguments for L or R command separated by space, eg `1 2 1 2`
      * argument_for_B_command => the argument for B command we want to apply
      * expected_string_output => expected string output after running C and L command with those arguments
      or exception message `Cannot fill outside the canvas\, please fill in another position` if bucket fill is outside
      the canvas
    * assumption/reminders:
      * if you do not want to draw anything on the canvas, skip second and third param, eg `3 3,,,4 5 @`
      * argument_for_B_command should have correct format because format checking is check on `command/fill/validateArgumentParam.csv`
      * this test focus on testing output string

### tool/createLineParam.csv
  * using same format as `command/line/createLineParam.csv`
  
### tool/createRectangleParam.csv
  * using same format as `command/rectangle/createLineParam.csv`
  
### tool/bucketFillParam.csv
  * using same format as `command/fill/bucketFillParam.csv`

### core/createCanvasParam.csv
  * using same format as `command/create/createCanvasParam.csv`
  
### core/getCommandParam.csv
  * format => command_string,command_argument,expected_string
  * explanation:
    * command_string is the command issue to console, only C, L, R, B, Q are correct command
    * command_argument, argument for the command you issue
    * for non existing command, expected string is `No such command\, available commands: [B\, C\, L\, Q\, R]`
    