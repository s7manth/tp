---
layout: page
title: User Guide
---
## Table of Contents
* Table of Contents
{:toc}


## Introduction
TAilor is a **desktop app for managing contacts, optimized for use via a Command Line Interface (CLI)**
while still having the benefits of a Graphical User Interface (GUI). If you are a Computer Science Teaching
Assistant who can type fast, TAilor can get tedious contact management tasks done faster than traditional GUI apps!


--------------------------------------------------------------------------------------------------------------------

## Quick start
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

1. Ensure you have Java `11` or above installed in your Computer.

<div markdown="span" class="alert alert-warning"> :exclamation: **Caution:**
If you are a Mac User, it may be possible that some incompatibilities may surface in the form of garbled/unreadable 
text in the GUI. It is advised to switch to Azul build of OpenJDK 11 to solve this issue. 
Please refer to this <a href="https://nus-cs2103-ay2122s2.github.io/website/admin/programmingLanguages.html#programming-language">link</a>
for more information:)
 
</div>

2. Download the latest `TAilor.jar` from [here](https://github.com/AY2122S2-CS2103T-W12-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TAilor Application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note
   how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter
   will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**` n/John Doe a/A0123456P e/johnd@example.com m/CS2103T g/W12` : Adds a student

     named `John Doe` to the Contact List.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/consultation` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/dropped`, `t/makeup t/consultation` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME a/STUDENT_NUMBER`, `a/STUDENT_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `a/A0123456H a/A1111111H`, only `a/A1111111H` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Common commands
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

In addition to this, `help` command can be used to show usage instructions
for specific commands.

Format: `help [COMMAND_WORD]`

Examples :
* `help undo` will show the usage instructions for the `undo` command.
* `help mail-x` will show the usage instructions for the `mail-x` command.

#### Listing all students : `list`

Shows a list of all students in the student roster.

Format: `list`


#### Adding a student: `add`

Adds a student to the student roster.

Format:
* `add n/NAME a/STUDENT_NUMBER e/EMAIL m/MOD g/GROUP [t/TAG]…​` or
* `add n/NAME a/STUDENT_NUMBER e/EMAIL m/MOD [g/GROUP] [t/TAG]…​` if there exists a default group for the mod already.


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe a/A1234567L e/johnd@example.com m/CS2030S g/B12G`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com m/CS2100 g/T1 a/a0123456x t/stable`

#### Importing a csv file to automatically add students: `import-csv`

Can create your student roster by directly importing a csv file and forgoing the hassle of manually adding students.
This feature is tailor-made for NUS School of Computing teaching assistants who can export relevant class data from
LumiNUS.

Format: `import-csv [PATH-TO-FILE]`

To prepare your csv files, the following directions must be followed:

1. Export your student list from LumiNUS Classes and Groups.
2. Be sure to select and export the following headers only as you choose the format of your file:
   1. Name,
   2. Student Number,
   3. Email, and
   4. Group
3. Once exported, convert the file to CSV format. This can be accomplished using any modern day spreadsheet
   visualization software. Please follow this step through thoroughly. This <a href="https://support.microsoft.com/en-us/office/save-a-workbook-to-text-format-txt-or-csv-3e9a9d6c-70da-4255-aa28-fcacf1f081e6">link</a> can be referred to if guidance is required.
4. Copy the file's path and import the file into TAilor with the above command!


<div markdown="span" class="alert alert-primary">:exclamation: **Note:**
The file format, including choice of headers, will need to be adhered to for TAilor to function smoothly and provide
a good user experience. Excel sheets downloaded from LumiNUS have 2 rows preceed the row containing column headers- this would be a requirement as well if you choose to draft your own file from scratch. As such, please be mindful to not corrupt your csv file before importing to TAilor- something that can
result from writing anything or adding information that does not abide by the standard format in the file.
</div>

#### Editing a student : `edit`

Edits an existing student in the student roster.

Format: `edit INDEX [n/NAME] [a/STUDENT_NUMBER] [e/EMAIL] [m/MOD] [g/GROUP] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. 
The index **must be a positive integer** 1, 2, 3, …​ and should be any one of the indexes displayed! Negative examples include:
    * `delete 0` produces an error, as 0 is not a positive integer
    * `delete 300` for a student list with less than 300 students, will produce an error as there is no 300<sup>th</sup> student
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 a/A1122334X e/johndoe@example.com` Edits the studentNumber and email address of the 1st student to be
   `A1122334X` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.


#### Deleting a student : `delete`

Deletes the specified student from the student roster.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​ and should be any one of the indexes displayed! Negative examples include:
    * `delete 0` produces an error, as 0 is not a positive integer
    * `delete 300` for a student list with less than 300 students, will produce an error as there is no 300<sup>th</sup> student

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the student roster.
* `find n/Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

#### Setting a default group value for a specific mod: `set-default-group`

Sets a default group value for a particular mod that can be updated using the same command several times. Helps
prevent users from repeatedly entering the same data.

Format: `set-default-group m/MOD g/GROUP`

* MOD may or may not be an existing mod in TAilor's local database
* GROUP can be set any number of times for the same Mod

Examples:
* `set-default-group m/CS2101 g/G02`
  ![result for 'set-default-group m/CS2101 g/G02'](images/set_default_ex1.png)


* followed by `set-default-group m/CS2101 g/G02-MonThur4-6`

  ![result for updating default group of m/CS2101](images/set_default_ex2.png)

#### Locating students by name: `find`


Finds students whose details matches all of the search parameters.

Format: `find PREFIX/KEYWORD [MORE_KEYWORDS]…​    [PREFIX/KEYWORD [MORE_KEYWORDS]…​]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The format may seem daunting, but remember that Everything in `[]` are optional!

A simple find command can be in the form of `find n/alex`. Try it out!
</div>

* The prefixes used are the same as other commands:

| Prefix | What it stands for |
|--------|--------------------|
| n/     | Name               |
| a/     | Student Number     |
| e/     | Email              |
| m/     | Module             |
| g/     | Group              |
| t/     | Tags               |

Note:
* Multiple keywords can be given for each prefix.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* The search is case-insensitive. e.g `hans` will match `Hans`
* Keywords cannot be empty. e.g. `find n/` will return an error
* Only the specified prefixes will be searched


* For names and tags, only full words will be matched e.g. `n/Han` will not match `Hans`
* For the rest, partial words will be matched e.g. `e/exam` will match `abc@example.com`
* Students matching at least one keyword will be returned e.g. `n/Hans Bo` will return `Hans Gruber`, `Bo Yang`


* If multiple prefixes are specified, students matching ALL prefixes will be returned.

Example:

Let the initial state of the list contain these 3 students: Alex, Bernice, Charlotte.

![Initial State](images/findcommandUG/initialstate.png)

* `find n/alex bernice` returns `Alex` and `Bernice`, because `Alex` and `Bernice` fit into the search arguments for the prefix `name`.

![result for `find n/alex bernice`](images/findcommandUG/find-alex-bernice.png)
* `find n/alex charlotte m/CS g/t01` returns `Alex`, because:
  * `Alex` and `Charlotte` fit within the search arguments for the prefix `name` and `module`,
  * but only `Alex` has a group of `T01`.
  * Hence, only `Alex` meets the search requirements of ALL search arguments provided, and is shown.

![result for `find n/alex charlotte m/CS g/t01`](images/findcommandUG/find-alex-charlotte.png)

#### Undo or Redo a previous command : `undo/redo`

Undoes or redoes a previously entered command that changed a student or task.

Format: `undo` or `redo`

* `undo` can only undo the effects of an `add`, `delete`, `edit`, `clear`, `newtask` and `deltask` commands.
* Once you undo and enter a new `add`, `delete`, `edit`, `clear`, `newtask` or `deltask` command, the state that was undone will not be accessible via `redo` anymore.
* Note the `undo` command will **not** be able to undo the effects of TAilor clearing all of its data due to manual editing of data.

Example:

Let the initial state of the list contain these 3 students: Alex, Bernice, Charlotte.
![Initial State](images/undocommandUG/initial_state.png)

After `delete 1`, we will delete Alex and the list will not have Alex anymore.
![Before Undo](images/undocommandUG/before_undo.png)

After `undo`, the list will return to having Alex in it
![Initial State](images/undocommandUG/initial_state.png)

After a `redo`, the list will return to the state where Alex was deleted
![Before Undo](images/undocommandUG/before_undo.png)

#### Mailing a particular student based on index : `mail-index`

Mails a student from the student roster based on the index number shown in the application.

Format: `mail-index INDEX`

* This opens the default email application on the system with the specified mail address pre-filled at the receiver's
  address.

Example:
* `mail-index 2` would open the default mail on the system with the "to" box filled with the
  specified mail. Now, the email is ready to be sent to the student with the index 2 as shown on the application.

#### Mailing student subset based on arguments : `mail-x`

Mails a subset of students from the contact list. This is a broader version of mail functionality as it can
cover multiple students at once in order to mail them simultaneously.

Format: `mail-x [e/EMAIL] [g/GROUP] [m/MOD] [n/NAME]`

* This opens the default email application on the system with all the mail addresses specified by the arguments.
* Anyone who matches at least one of the specified arguments will be included in the mailing list.
* This can be used to specify multiple prefix based arguments to send the same mail in a single go.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
mail-x must have at least one prefix based argument passed.
</div>

Example:
* `mail-x e/johndoe@example.com n/Alex` would open the default mail on the system with the "to" box filled with the
  all the mail addresses covered by the arguments specified. Now, the email is ready to be sent to `johndoe@example.com`
  and Alex.

#### Bulk Emails to everyone on the database : `mail-all`

Mails everybody in the student roster.

Format: `mail-all`

* Opens the default email application on the system with all email addresses pre-filled in the receiver's
  field.


### Task List commands
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

#### Adding a new task: `newtask`

Adds a new Task with a given description and deadline.

Format: `newtask DESCRIPTION by/DATETIME`

* Description must be non-empty. Ie, it cannot consist of all spaces.
* DATETIME has to be in the format of : `YYYY-MM-DDThh:mm`. The format for this is shown below:

| Symbol        | What it represents    | Example                                                              |
|---------------|-----------------------|----------------------------------------------------------------------|
| YYYY          | 4-digit year          | 1999 <br> 2020                                                       |
| MM            | 2-digit month         | March - 03 <br> November - 11                                        |
| DD            | 2-digit day           | First day - 01 <br> Twelfth day - 12                                 |
| hh            | 2-digit 24hour format | 3am - 03 <br> 3pm - 15                                               |
| mm            | 2-digit minute        | On the hour - 00 <br> Last minute - 59                               |
| `-`, `:`, `T` | Separators            | Do not change these! They need to be in the corresponding positions! | 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
* The Time and Date being input must be valid. Ie, it is not possible to input a task
    with a deadline of 31st February.
* It is possible to create tasks that have a deadline before the current time, for task-tracking purposes! 
</div>

Note:
* There is no maximum length for the description, but using a long description may hinder usability and make it hard to see your tasks!
  * For your best experience, please describe your tasks with a (soft) limit of 40 characters, including spaces!
  * This will be improved in the future versions.

Examples:
* `newtask Do Homework by/2022-03-21T23:59` creates a task with description of "Do Homework" and is due on 21 March 2022, 11:59pm.
* `newtask Check Alex's lab 4 by/2022-03-31T23:59` creates a task with description "Check Alex's lab 4" and is due on 31 March 2022, 11:59pm
![result for `newtask Check Alex's lab 4 by/2022-03-31T23:59`](images/newTask-okay.png)


#### Deleting an existing task: `deltask`

Deletes the specified task from the task list.

Format: `deltask INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** (ie 1, 2, 3, …​) and should be any one of the indexes displayed! Negative examples include:
  * `delete 0` produces an error, as 0 is not a positive integer
  * `delete 300` for a task list with less than 300 tasks, will produce an error as there is no 300<sup>th</sup> task 

(Positive) Examples:
* `delete 2` with a task list of at least 2 tasks, deletes the 2nd task in the task list.



### Closing Commands
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

#### Exiting TAilor: `exit`

Simply type exit to close the application. All of your data will be stored if no unforeseen errors have occurred!


#### Clearing all entries : `clear`

Clears all entries from the student roster.

Format: `clear`

---
### Shortcuts
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

| Button    | Result                                                               |
|-----------|----------------------------------------------------------------------|
| &#8593; | Refills command textbox with previous entered command                |
| &#8595; | Refills command textbox with the command entered after the current one |

Example:
1. Commands `delete 1` `find n/Bob` `list` are entered in the app.
2. Pressing &uparrow; will fill the command box with `list`.
3. Pressing &uparrow; again will fill the command box with `find n/Bob`.
4. Pressing &downarrow; will then fill the command box with `list` again.

### Saving the data
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

TAilor's data is saved in the hard disk automatically after any command that changes the data.
There is no need to save it manually.

### Editing the data file
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

TAilor's data is saved as three JSON files. The first being ContactList's data as `[JAR file location]/data/contactlist.
json` and the second being Task List's data saved as `[JAR file location]/data/tasklist.json`. And finally,
ModuleList's data being saved as `[JAR file location]/data/modulelist.json`
Advanced users are welcome to update the data directly by editing those data files.

<div markdown="span" class="alert alert-warning"> :exclamation: **Caution:**
If your changes to the data file makes its format invalid (e.g., deleting the field of a task), TAilor will discard data in all 3 files and start
with no data at the next run. It is suggested that users manually backup copies of these files before directly editing
any of these files.

To reset the files, perform any command that changes the contact list or task list (ie add new task, add new contact).
WARNING: This will override the pre-existing data with the new data you entered.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the
       file that contains the data of your previous TAilor home folder!

--------------------------------------------------------------------------------------------------------------------

## Command summary
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

| Action                | Format, Examples                                                                                                                                                                                                                         |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**              | `help`, `help list`, `help mail-x`                                                                                                                                                                                                       |
| **List**              | `list`                                                                                                                                                                                                                                   |
| **Add**               | `add n/NAME a/STUDENT_NUMBER e/EMAIL m/MODULE g/GROUP [t/TAG]…​` or `add n/NAME a/STUDENT_NUMBER e/EMAIL m/MODULE [g/GROUP] [t/TAG]…​` <br> e.g., `add n/James Ho a/A1234567Y e/jamesho@example.com m/CS2100 g/W12 t/friend t/colleague` |
| **Import csv**        | `import-csv [PATH_TO_CSV_FILE]`                                                                                                                                                                                                          |                                                                                                                                                         |
| **Edit**              | `edit INDEX [n/NAME] [a/STUDENT_NUMBER] [e/EMAIL] [m/MODULE] [g/GROUP] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                                   |
| **Delete**            | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                      |
| **Set Default Group** | `set-default-group m/MOD g/GROUP` <br> e.g., `set-default-group m/CS2103T g/W12-1`                                                                                                                                                       |
| **Find**              | `find PREFIX/KEYWORD [MORE_KEYWORDS]…​ [PREFIX/KEYWORD [MORE_KEYWORDS]…​]…​`<br> e.g., `find n/James Jake a/A0217`                                                                                                                       |
| **Mail Index**        | `mail-index`<br> e.g., `mail 2`                                                                                                                                                                                                          |
| **Mail X**            | `mail-x`<br> e.g., `mail e/johndoe@example.com n/Alex`                                                                                                                                                                                   |
| **Mail All**          | `mail-all`                                                                                                                                                                                                                               |
| **New Task**          | `newtask DESCRIPTION by/DEADLINE` <br> e.g., `newtask Do homework by/2022-03-21T12:34`                                                                                                                                                   |
| **Delete Task**       | `deltask INDEX` <br> e.g., `deltask 3`                                                                                                                                                                                                   |
| **Undo/Redo**         | `undo`/`redo`                                                                                                                                                                                                                            |
| **Clear**             | `clear`                                                                                                                                                                                                                                  |
| **Exit**              | `exit`                                                                                                                                                                                                                                   |
