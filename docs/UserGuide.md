---
layout: page
title: User Guide
---
## Table of Contents
* Table of Contents
{:toc}


TAilor is a **desktop app for managing contacts, optimized for use via a Command Line Interface (CLI)**
while still having the benefits of a Graphical User Interface (GUI). If you are a Computer Science Teaching
Assistant who can type fast, TAilor can get tedious contact management tasks done faster than traditional GUI apps!


--------------------------------------------------------------------------------------------------------------------

## Quick start
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TAilor.jar` from [here](https://github.com/AY2122S2-CS2103T-W12-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TAilor Application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note
   how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter
   will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe a/A0123456P e/johnd@example.com m/CS2103T g/W12` : Adds a student

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

#### Listing all persons : `list`

Shows a list of all persons in the student roster.

Format: `list`


#### Adding a person: `add`

Adds a person to the student roster.

Format: `add n/NAME a/STUDENT_NUMBER e/EMAIL m/MOD g/GROUP [t/TAG]…​`


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe a/A1234567L e/johnd@example.com m/CS2030S g/B12G`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com m/CS2100 g/T1 a/a0123456x t/stable`

#### Importing a csv file to automatically add people: `import-csv`

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
   visualization software. Please follow this step through thoroughly.  
4. Copy the file's path and import the file into TAilor with the above command!


<div markdown="span" class="alert alert-primary">:bulb: **Note:**
The file format, including choice of headers, will need to be adhered to for TAilor to function smoothly and provide 
a good user experience. As such, please be mindful to not corrupt your csv file before importing- something that can 
result from writing anything or adding information that does not abide by the standard format in the file.
</div>

#### Editing a person : `edit`

Edits an existing person in the student roster.

Format: `edit INDEX [n/NAME] [a/STUDENT_NUMBER] [e/EMAIL] [m/MOD] [g/GROUP] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 a/A1122334X e/johndoe@example.com` Edits the studentNumber and email address of the 1st person to be
   `A1122334X` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.


#### Deleting a person : `delete`

Deletes the specified person from the student roster.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the student roster.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

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

#### Locating persons by name: `find`


Finds persons whose details contain any of the given keywords.

Format: `find PREFIX/KEYWORD [KEYWORD] [PREFIX/KEYWORD [KEYWORD]]…​`

* The prefixes used are the same as other commands:
  * `n/` for name
  * `a/` for studentNumber
  * `e/` for email
  * `m/` for module
  * `g/` for group
  * `t/` for tags
* Multiple keywords can be given for each tag.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the specified prefix will be searched
* For names, only full words will be matched e.g. `Han` will not match `Hans`
* For the rest, partial words will be matched e.g. `exam` will match `abc@example.com`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find n/alex david'](images/findAlexDavidResult.png)

#### Mailing a particular student based on index : `mail-index`

Mails a person from the student roster based on the index number shown in the application.

Format: `mail-index INDEX`

* This opens the default email application on the system with the specified mail address pre-filled at the receiver's
  address.

Example:
* `mail-index 2` would open the default mail on the system with the "to" box filled with the
  specified mail. Now, the email is ready to be sent to the person with the index 2 as shown on the application.

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
* DATETIME has to be in the format of : `YYYY-MM-DDThh:mm` , where
  * YYYY represents a 4-digit year
  * MM represents a 2-digit month (so March will be 03, November 11)
  * DD represents a 2-digit day (1st day of the month will be 01)
  * hh represents the hour, as in 24-hour (ie 3am is 0300, 3pm is 1500)
  * mm represents the minute.
  * The dashes `-`, colons `:` and the `T` must be in the corresponding positions.
  * The Time and Date being input must be valid. Ie, it is not possible to input a task
    with a deadline of 31st February.

Examples:
* `newtask Do Homework by/2022-03-21T23:59` creates a task with description of "Do Homework" and is due on 21 March 2022, 11:59pm.

#### Deleting an existing task: `deltask`

Deletes the specified person from the task list.

Format: `deltask INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd person in the task list.


### Closing Commands
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

#### Exiting TAilor: `exit`

Simply type exit to close the application. All of your data will be stored if no unforseen error is encountered!


#### Clearing all entries : `clear`

Clears all entries from the student roster.

Format: `clear`

---
### Shortcuts
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

| Button   | Result                                                                 |
|----------|------------------------------------------------------------------------|
| **UP**   | Refills command textbox with previous entered command                  |
| **DOWN** | Refills command textbox with the command entered after the current one |

### Saving the data
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

TAilor's data is saved in the hard disk automatically after any command that changes the data.
There is no need to save it manually.

### Editing the data file
[<sub><sup>Back to top</sup></sub>](#table-of-contents)

TAilor's data is saved as two JSON files. The first being ContactList's data as `[JAR file location]/data/contactlist.
json` and the other being Task List's data saved as `[JAR file location]/data/tasklist.json`.
Advanced users are welcome to update the data directly by editing those data files.

<div markdown="span" class="alert alert-warning"> :exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAilor will discard all data and start
with an empty data file at the next run.

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

| Action                | Format, Examples                                                                                                                                                     |
|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**              | `help`                                                                                                                                                               |
| **List**              | `list`                                                                                                                                                               |
| **Add**               | `add n/NAME a/STUDENT_NUMBER e/EMAIL m/MODULE g/GROUP [t/TAG]…​` <br> e.g., `add n/James Ho a/A1234567Y e/jamesho@example.com m/CS2100 g/W12 t/friend t/colleague`   |                                                                                                                                                         |
| **Edit**              | `edit INDEX [n/NAME] [a/STUDENT_NUMBER] [e/EMAIL] [m/MODULE] [g/GROUP] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                               |
| **Delete**            | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                  |
| **Set Default Group** | `set-default-group m/MOD g/GROUP` <br> e.g., `set-default-group m/CS2103T g/W12-1`                                                                                   |
| **Find**              | `find PREFIX/KEYWORD [MORE_KEYWORDS] [PREFIX/KEYWORD [MORE_KEYWORDS]]`<br> e.g., `find n/James Jake a/A0217`                                                         |
| **Mail Index**        | `mail-index`<br> e.g., `mail 2`                                                                                                                                      |
| **Mail X**            | `mail-x`<br> e.g., `mail e/johndoe@example.com n/Alex`                                                                                                               |
| **Mail All**          | `mail-all`                                                                                                                                                           |
| **New Task**          | `newtask DESCRIPTION by/DEADLINE` <br> e.g., `newtask Do homework by/2022-03-21T12:34`                                                                               |
| **Delete Task**       | `deltask INDEX` <br> e.g., `deltask 3`                                                                                                                               |
| **Undo/Redo**         | `undo`/`redo`                                                                                                                                                        |
| **Clear**             | `clear`                                                                                                                                                              |
| **Exit**              | `exit`                                                                                                                                                               |
