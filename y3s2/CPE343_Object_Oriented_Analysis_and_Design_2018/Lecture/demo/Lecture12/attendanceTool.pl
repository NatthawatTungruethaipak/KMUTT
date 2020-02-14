#!/usr/bin/perl
#
# attendanceTool.pl - Sally Goldin, 24 May 2011
# This script allows students to register their attendance in CPE 100
# labs. It provides three screens, one for use by students and two
# for use by the professor.
#  1) attendance recording - student enters ID, section, and lab number 
#      and clicks on 'Record Attendance'. After checking data input, 
#      the script stores the timestamp in the appropriate lab column, which is 
#      determined by the data input.
#  2) count attendees - instructor enters lab and session (1 or 2). 
#     Script responds with the total number of students who recorded
#     attendance for that session, PLUS listing any students who
#     recorded attendance but are in the wrong section based on their
#     reported section.
#  3) excuse a student - instructor enters student id and selects lab,
#     then clicks on Excuse. The system records an excuse for that
#     student and that lab.

####################################################################
#   23 Dec 2016 changed CPE113 to CPE111

use strict;
use CGI::Carp qw(fatalsToBrowser);
use CGI qw(param);
use DBI;
#use GRS::DB_DBI_Access;
#use GRS::DbField;
#use GRS::ktrweb;

################################################################################
# globals
################################################################################
chomp(my $site = `hostname`);
chomp(my $localhost = `hostname -f`);
my $myemail = 'seg@goldin-rudahl.com';
#my $myurl = 'http://windu.cpe.kmutt.ac.th/cgi-bin/attendanceTool.pl';
my $home ='http://windu.cpe.kmutt.ac.th/cpe100/index.html';
my $myurl = 'attendanceTool.pl';
my $dbname = 'cpe100attend';
my $gCstart = " 13:15:00";
my $gCend = " 14:30:00";
my $gDstart = " 15:15:00";
my $gDend = " 16:30:00";
my $gTitleText = "CPE 100 Introduction to Computer Programming";
my $wwwuser = 'apache';
chomp(my $now = `date +"%Y-%m-%d %T"`);
chomp(my $today = `date +"%Y-%m-%d"`);
my $q = CGI->new;
my $gCourse = $q->param('course');
my $gButton =  $q->param('button');
my $gStudentId = $q->param('studentid');
my $gSection = $q->param('section');
my $gLab = $q->param('lab');
my $gDbh;  # global database connection handle
my $gSqlError;    # sql error number
my $gSqlErrorStr;  # sql error string
if ($gCourse eq "CPE111")
   {
   $home ='http://windu.cpe.kmutt.ac.th/cpe111/index.html';
   $dbname = "cpe111attend";
   $gCstart = " 15:20:00";
   $gCend = " 17:30:00";
   $gDstart = " 15:20:00";
   $gDend = " 17:30:00";
   $gTitleText = "CPE 111 Programming with Data Structures";
   }
########################################################################
#  Utility functions adapted from formgen
########################################################################
sub db_connect 
    {
    my ($user,$dbname) = @_;
    my $dbh;
    $dbh = DBI->connect("dbi:Pg:dbname=$dbname",$user,'');
    return $dbh;
    }
##
# execute an SQL command string with no results
# must first call connect
# usage: execSqlCommand cmdstring
sub execSqlCommand
    {
    my ($sqlcmd) = @_; 
    $gDbh->do($sqlcmd);
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    }

#------------------------------------------------------------------------------
# execute an SQL query string, returning an array of lines in result 
# usage: execSqlGetResults querystring -- array 
sub execSqlGetResults
    {
    my ($sqlcmd) = @_; 
    chomp(my $now = `date +"%T"`);
    my @PGresult;
    my $stmt = $gDbh->prepare($sqlcmd);
    my $numrows = $stmt->execute;
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    my @name = @{$stmt->{'NAME'}};
    my $line = join " | ",@name;
    $line .= "\n";
    my $lineno = 0;
    push @PGresult, ($line);
    $lineno++;
    while(my $ary = $stmt->fetch)
        {
	my $line = join " | ",@$ary;
	$line .= "\n";	
	next if ($line =~ /\-{5}\+/);    # skip divider lines
	next if ($line !~ /\w+/); # must have at least one field
	$lineno++;
	push @PGresult, ($line);
        }
    $stmt->finish;
    return @PGresult;
    }


# Check that the lab parameter is legal
# Usage: checkLab(labstring)
# Return true if ok, else false
sub checkLab
{
  my ($labstring) = @_;
  #print("<p>checking lab |$labstring|</p>");
  if ($labstring !~ /^(lab1|lab2|lab3|lab4|lab5|lab6|lab7|lab8|lab9|lab10|lab11|lab12|lab13|lab14|lab15)$/)
      {
      # print("<p>found error</p>");
      return 0;
      }
  return 1;
}

# Check that the user ID parameter is legal
# Usage: checkId(idstring)
# Return true if ok, else false
sub checkId
{
  my ($idstring) = @_;
  # print("<p>checking id |$idstring|</p>");
  if (length($idstring) != 4)
      {
      # print("<p>found error</p>");
      return 0;
      }
  if ($idstring =~ /[^0-9]/)
      {
      return 0;
      }
  return 1;
}

# Check that the user ID parameter is legal
# Usage: checkSection(secstring)
# Return true if ok, else false
sub checkSection
{
  my ($secstring) = @_;
  uc($secstring);
  if ($secstring !~ /C|D/)
      {
      return 0;
      }
  return 1;
}



#################################################################
# show form for student to record attendance
# no arguments
sub showRecordForm
{
print <<EOM;
Content-type: text/html; charset="UTF-8"

<html><head><title>Record Lab Attendance</title>
  </head>
  <body>
  <script language="JavaScript">
  function checkData() 
    {
    if(document.recordform.studentid.value ==0)
       {
       alert("You must enter a Student ID - last four digits");
       return false;
       }
    else if (document.recordform.section.value ==0)
       {
       alert("You must enter a section");
       return false;
       }
    else if (document.recordform.lab.value == "none")
       {
       alert("You must select a lab");
       return false;
       }
    else /* check values */ 
       {
       var idstring = new String(document.recordform.studentid.value);
       var pattern = new RegExp("[^0-9]","g");
       if ((idstring.length != 4) || (pattern.test(idstring)))
           {
           alert("Invalid student ID - enter last four digits");
           return false;
           }
       var sectionstring = new String(document.recordform.section.value);
       sectionstring = sectionstring.toUpperCase();
       if ((sectionstring != "C") && (sectionstring != "D"))
           {       
           alert("Invalid section - enter either 'C' or 'D'");
           return false;
	   }
       }
    return 1;
    }
  </script>
  <h3 align="center">$gTitleText<br>Record Lab Attendance</h3>
  <table align=center>
  <tr><td align=center>
  <form name="recordform" method="post" action="$myurl">
  <input type="hidden" name="course" value=$gCourse>
    <table> 
      <tr><td>Student ID (last four digits)</td>
          <td><input type="text" name="studentid" size="4" 
               maxlength="4" value=""></td></tr>
      <tr><td>Section</td>
          <td><input type="text" name="section" size="2" 
               maxlength="1" value=""></td></tr>
      <tr><td>Choose Lab</td>
          <td><select name="lab">
                <option value="none"></option>
                <option value="lab1">Lab 1</option>
                <option value="lab2">Lab 2</option>
                <option value="lab3">Lab 3</option>
                <option value="lab4">Lab 4</option>
                <option value="lab5">Lab 5</option>
                <option value="lab6">Lab 6</option>
                <option value="lab7">Lab 7</option>
                <option value="lab8">Lab 8</option>
                <option value="lab9">Lab 9</option>
                <option value="lab10">Lab 10</option>
                <option value="lab11">Lab 11</option>
                <option value="lab12">Lab 12</option>
                <option value="lab13">Lab 13</option>
                <option value="lab14">Lab 14</option>
                <option value="lab15">Lab 15</option>
	      </select>
           </td></tr>
     <table>	       
     <input type="submit" name="button" value="Record" 
            align="center" onClick="return checkData()">
  </form>
  </td></tr></table>
  </body></html>
EOM
}

# give them an error if they enter a section that isn't the same
# as their originally entered section.
# Assumes page header has already been output
# @param     id                   Student Id
# @param     section              Section in the db
# @param     othersection         Section they entered on the fomr
sub showSectionError
{
    my ($id,$section,$othersection) = @_;
print <<EOM;
  <h2 align="center">$gTitleText<br>Student $id Section $section</h2>.
  <h3 align="center"><font color="red">Error: You entered section $othersection but<br>you are already registered in the attendance database as section $section</font></h3>
  <h4 align="center">If the database is wrong, please contact Aj. Sally.<br>
        Otherwise, click on the 'Back' button and try again.</h4>
  </body>
</html>
EOM
}

# Add an attendance record to the database
# Assumes that the DB has been successfully opened.
# If this is the first time that this student appears in the
# DB, add a new record. Otherwise update the existing record.
# usage: addAttendanceRecord(studentid,section,lab)
sub addAttendanceRecord
{
    my ($id,$section,$lab) = @_;
    my $command;
    my $otherid;
    my $othersection;
    my $totalattend;
    my $sqlcommand = "SELECT studentid, section, totalattend FROM attend WHERE studentid = '$id';";
    my $stmt = $gDbh->prepare($sqlcommand);
    my $numrows = $stmt->execute;
    $section = uc($section);
# print header
print <<EOM;
Content-type: text/html; charset="UTF-8"

<html><head><title>Record Lab Attendance</title>
  </head>
<body>
EOM
##
    if ((!checkId($id)) || (!checkLab($lab)) || (!checkSection($section)))
       {
       print("<h4>Bad data passed to script!</h4>");
       print("</body></html>");
       return;
       }
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    #print("<p>search for student id $id returned $numrows rows</p>");
    if ($numrows > 0) # already exists so do an update 
        {
	my @row  = $stmt->fetchrow_array;
	($otherid,$othersection,$totalattend) = @row;
        # strip leading, trailing spaced
        $othersection =~ s/^\s+// if (defined $othersection);
        $othersection =~ s/\s+$// if (defined $othersection);
        #give an error if section they entered does not match the one in the DB
	if ($othersection ne $section)
	    {
            showSectionError($id,$section,$othersection);
	    return;
            }
        $command = "UPDATE attend SET $lab='$now', totalattend=($totalattend+1) WHERE studentid = '$id';";
        }
    else # add new record
        {
	$command = "INSERT INTO attend (studentid,section,$lab,totalattend) VALUES ('$id','$section','$now',1);";
        } 
    #print("<p>About to execute command: $command</p>");
    execSqlCommand($command);
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    
print <<EOM;
  <h2 align="center">$gTitleText<br>Student $id Section $section</h2>.
  <h2 align="center"><font color="blue">Thank you for recording your attendance.
  </font></h2>
  <h4 align="center">Enjoy the lab!</h4>
  <h4 align="center"><a href="$home">Return to course home page</a></h4>
  </body>
</html>
EOM
}

#################################################################
# show form for instructor to find out how many students
# have recorded attendance for each session of a lab.
# no arguments
sub showReviewForm
{
print <<EOM;
Content-type: text/html; charset="UTF-8"

<html><head><title>Review Lab Attendance</title>
  </head>
  <body>
  <script language="JavaScript">
  function checkData() 
    {
    if (document.reviewform.lab.value == "none")
       {
       alert("You must select a lab");
       return false;
       }
    return 1;
    }
  </script>
  <h3 align="center">$gTitleText<br>Review Lab Attendance</h3>
  <table align=center>
  <tr><td align=center>
  <form name="reviewform" method="post" action="$myurl">
  <input type="hidden" name="course" value=$gCourse>
    <table> 
      <tr><td>Choose Lab</td>
          <td><select name="lab">
                <option value="none"></option>
                <option value="lab1">Lab 1</option>
                <option value="lab2">Lab 2</option>
                <option value="lab3">Lab 3</option>
                <option value="lab4">Lab 4</option>
                <option value="lab5">Lab 5</option>
                <option value="lab6">Lab 6</option>
                <option value="lab7">Lab 7</option>
                <option value="lab8">Lab 8</option>
                <option value="lab9">Lab 9</option>
                <option value="lab10">Lab 10</option>
                <option value="lab11">Lab 11</option>
                <option value="lab12">Lab 12</option>
                <option value="lab13">Lab 13</option>
                <option value="lab14">Lab 14</option>
                <option value="lab15">Lab 15</option>
	      </select>
           </td></tr>
     <table>	       
     <input type="submit" name="button" value="Review" 
            align="center" onClick="return checkData()">
  </form>
  </td></tr></table>
  </body></html>
EOM
}

# Count and display the number of students who have
# recorded their attendance for the first session (14:30-16:30) and
# the second session (16:30-18:30) of a lab.
# Also display section D people in the first section
# and section C people in the second section.
sub showTotals
{
    my ($lab) = @_;
    my $command;
    my $total;
    my $studentid;
    my $minC = $today . $gCstart;
    my $maxC = $today . $gCend;
    my $minD = $today . $gDstart; 
    my $maxD = $today . $gDend; 
    my $sqlcommand1 = "SELECT COUNT (*) FROM attend WHERE $lab BETWEEN '$minC' AND '$maxC';";
    my $sqlcommand2 = "SELECT COUNT (*) FROM attend WHERE $lab BETWEEN '$minD' AND '$maxD';";
    my $uclab = uc($lab);
# print header
print <<EOM;
Content-type: text/html; charset="UTF-8"

<html><head><title>Review Lab Attendance</title>
  </head>
<body>
<h4 align="center">$now</h4>
<h3 align="center">$gTitleText</h3>
<h3 align="center">Attendance for $uclab</h3>
EOM
##
    if (!checkLab($lab))
       {
       print("<h4>Bad data passed to script!</h4>");
       print("</body></html>");
       return;
       }
    my $stmt = $gDbh->prepare($sqlcommand1);
    my $numrows = $stmt->execute;
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    if ($numrows > 0) 
        {
	my @row  = $stmt->fetchrow_array;
	($total) = @row;
	print("<h3>First session attendance: $total</h3>");
        print("<p></p>");
	}
    else 
        {
	print("<h4>No rows returned from query?: '$sqlcommand1'</h4>");    
        } 

    $stmt = $gDbh->prepare($sqlcommand2);
    $numrows = $stmt->execute;
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    if ($numrows > 0) 
        {
 	my @row  = $stmt->fetchrow_array;
	($total) = @row;
	print("<h3>Second session attendance: $total</h3>");
        print("<p></p>");
	}
    else # add new record
        {
	print("<h4>No rows returned from query?: '$sqlcommand2'</h4>");    
        }
    print("<p></p><p></p>");
     print("<h3>Students from Section D attending session C</h3>");
    print("<ul>");
    $sqlcommand1 = "SELECT studentid FROM attend WHERE section='D' AND $lab BETWEEN '$minC' AND '$maxC';";
    $stmt = $gDbh->prepare($sqlcommand1);
    $numrows = $stmt->execute;
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    while (my ($studentid) = $stmt->fetchrow_array)
        {
	print("<li>$studentid</li>");    
        }
    printf("</ul>");

    print("<h3>Students from Section C attending session D</h3>");
    print("<ul>");
    $sqlcommand1 = "SELECT studentid FROM attend WHERE section='C' AND $lab BETWEEN '$minD' AND '$maxD';";
    $stmt = $gDbh->prepare($sqlcommand1);
    $numrows = $stmt->execute;
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    while (my ($studentid) = $stmt->fetchrow_array)
        {
	print("<li>$studentid</li>");    
        }
    printf("</ul>");

print <<EOM;
  </body>
</html>
EOM
}


#################################################################
# show form for ajarn to record an excused lab
sub showExcusedForm
{
print <<EOM;
Content-type: text/html; charset="UTF-8"

<html><head><title>Record Lab Excuse</title>
  </head>
  <body>
  <script language="JavaScript">
  function checkData() 
    {
    if(document.excuseform.studentid.value ==0)
       {
       alert("You must enter a Student ID");
       return false;
       }
    else if (document.excuseform.lab.value == "none")
       {
       alert("You must select a lab");
       return false;
       }
    else /* check values */ 
       {
       var idstring = new String(document.excuseform.studentid.value);
       var pattern = new RegExp("[^0-9]","g");
       if ((idstring.length != 4) || (pattern.test(idstring)))
           {
           alert("Invalid student ID - use last four digits");
           return false;
           }
       }
    return 1;
    }
  </script>
  <h3 align="center">$gTitleText<br>Record Excused Lab</h3>
  <table align=center>
  <tr><td align=center>
  <form name="excuseform" method="post" action="$myurl">
  <input type="hidden" name="course" value=$gCourse>
    <table> 
      <tr><td>Student ID (last four digits)</td>
          <td><input type="text" name="studentid" size="4" 
               maxlength="4" value=""></td></tr>
      <tr><td>Lab Excused</td>
          <td><select name="lab">
                <option value="none"></option>
                <option value="lab1">Lab 1</option>
                <option value="lab2">Lab 2</option>
                <option value="lab3">Lab 3</option>
                <option value="lab4">Lab 4</option>
                <option value="lab5">Lab 5</option>
                <option value="lab6">Lab 6</option>
                <option value="lab7">Lab 7</option>
                <option value="lab8">Lab 8</option>
                <option value="lab9">Lab 9</option>
                <option value="lab10">Lab 10</option>
                <option value="lab11">Lab 11</option>
                <option value="lab12">Lab 12</option>
                <option value="lab13">Lab 13</option>
                <option value="lab14">Lab 13</option>
                <option value="lab15">Lab 13</option>
	      </select>
           </td></tr>
     <table>	       
     <input type="submit" name="button" value="Save" 
            align="center" onClick="return checkData()">
  </form>
  </td></tr></table>
  </body></html>
EOM
}

# give an error if the Aj enters a student who's not in the attend database
sub showExcuseError
{
    my ($id) = @_;
print <<EOM;
  <h3 align="center"><font color="red">Error: Student $id is not in the attendance table.</font></h3>
  </body>
</html>
EOM
}


# Add a record excusing a student to the database
# Assumes that the DB has been successfully opened.
# Checks to see if the student is in the attendance database first.
# Then if this is the first time that this student appears in the
# excused table, add a new record. Otherwise update the existing record.
# usage: addExcuseRecord(studentid,lab)
sub addExcuseRecord
{
    my ($id,$lab) = @_;
    my $command;
    my $otherid;
    my $totalattend;
    my $sqlcommand = "SELECT studentid, totalattend FROM attend WHERE studentid = '$id';";
# print header
print <<EOM;
Content-type: text/html; charset="UTF-8"

<html><head><title>Record Excused Lab</title>
  </head>
<body>
EOM
##

    if ((!checkId($id)) || (!checkLab($lab)))
       {
       print("<h4>Bad data passed to script!</h4>");
       print("</body></html>");
       return;
       }
    my $stmt = $gDbh->prepare($sqlcommand);
    my $numrows = $stmt->execute;
    $gSqlError= $gDbh->err;
    $gSqlErrorStr = $gDbh->errstr;
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    if ($numrows == 0) # student id does not exist
        {
	showExcuseError($id);
	return;
        } 
    # next look in the excused DB 
    my $sqlcommand = "SELECT studentid FROM excused WHERE studentid = '$id';";
    my $stmt = $gDbh->prepare($sqlcommand);
    my $numrows = $stmt->execute;
    if ($numrows > 0) # already exists so do an update 
        {
        $command = "UPDATE excused SET $lab='true' WHERE studentid = '$id';";
        }
    else # add new record
        {
	$command = "INSERT INTO excused (studentid,$lab) VALUES ('$id','true');";
        } 
    #print("<p>About to execute command: $command</p>");
    execSqlCommand($command);
    if ($gSqlError != 0)
        {
	print("<h4>Error executing SQL command: $gSqlErrorStr</h4>");
        print("</body></html>");
	return;
        }
    
print <<EOM;
  <h2 align="center">$gTitleText<br>Student $id</h2>.
  <h2 align="center"><font color="blue">Excused student $id from $lab</font></h2>
  </body>
</html>
EOM
}


##############################################
# Main part of script begins here
##############################################

$gDbh = db_connect($wwwuser,$dbname);
($gDbh) or die "Unable to connect to database '$dbname'";

if (!defined $gButton) # first entry
{
    showRecordForm;
}
elsif (($gButton eq "Record") || ($gButton eq "record"))
{
    addAttendanceRecord($gStudentId,$gSection,$gLab);
}
elsif (($gButton eq "Count") || ($gButton eq "count"))
{
    showReviewForm;
}
elsif (($gButton eq "Review") || ($gButton eq "review"))
{
    showTotals($gLab);
}
elsif (($gButton eq "excuse") || ($gButton eq "Excuse"))
{
    showExcusedForm;
}
elsif (($gButton eq "save") || ($gButton eq "Save"))
{
    addExcuseRecord($gStudentId,$gLab);
}
else
{
    print("ERROR! 'Button' has illegal value\n");
}
 
